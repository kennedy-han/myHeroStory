package com.kennedy.herostory.cmdhandler;

import com.kennedy.herostory.Broadcaster;
import com.kennedy.herostory.model.User;
import com.kennedy.herostory.model.UserManager;
import com.kennedy.herostory.mq.MQProducer;
import com.kennedy.herostory.mq.VictorMsg;
import com.kennedy.herostory.msg.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * 用户攻击指令处理器
 */
public class UserAttkCmdHandler implements ICmdHandler<GameMsgProtocol.UserAttkCmd> {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAttkCmdHandler.class);

    @Override
    public void handle(ChannelHandlerContext ctx, GameMsgProtocol.UserAttkCmd cmd) {

        if (null == ctx ||
                null == cmd) {
            return;
        }

        // 获取攻击者 Id
        Integer attkUserId = (Integer)ctx.channel().attr(AttributeKey.valueOf("userId")).get();
        if (null == attkUserId) {
            return;
        }

        // 获取被攻击者 Id
        Integer targetUserId = cmd.getTargetUserId();
        if (null == targetUserId) {
            return;
        }

        GameMsgProtocol.UserAttkResult.Builder newBuilder = GameMsgProtocol.UserAttkResult.newBuilder();
        newBuilder.setTargetUserId(targetUserId);
        newBuilder.setAttkUserId(attkUserId);

        GameMsgProtocol.UserAttkResult newResult = newBuilder.build();
        Broadcaster.broadcast(newResult);

        //获取被攻击者
        User targetUser = UserManager.getUserById(targetUserId);
        if (null == targetUser) {
            return;
        }

        // 在此打印线程名称
        LOGGER.info("当前线程 = {}", Thread.currentThread().getName());
        // 我们可以看到不相同的线程名称...
        // 用户 A 在攻击用户 C 的时候, 是在线程 1 里,
        // 用户 B 在攻击用户 C 的时候, 是在线程 2 里,
        // 线程 1 和线程 2 同时修改用户 C 的血量...
        // 这是要出事的节奏啊!

        // 可以根据自己的喜好写,
        // 例如加上装备加成、躲避、格挡、暴击等等...
        // 这些都属于游戏的业务逻辑了!
        int subtractHp = 10;
        targetUser.currHp = targetUser.currHp - subtractHp;

        // 广播减血消息
        broadcastSubtractHp(targetUserId, subtractHp);

        if (targetUser.currHp <= 0) {
            // 广播死亡消息
            broadcastDie(targetUserId);

            if (!targetUser.died) {
                // 设置死亡标志
                targetUser.died = true;

                // 发送消息到 MQ
                VictorMsg mqMsg = new VictorMsg();
                mqMsg.winnerId = attkUserId;
                mqMsg.loserId = targetUserId;
                MQProducer.sendMsg("Victor", mqMsg);
            }
        }
    }

    /**
     * 广播减血消息
     *
     * @param targetUserId 被攻击者 Id
     * @param subtractHp   减血量
     */
    private void broadcastSubtractHp(Integer targetUserId, int subtractHp) {
        if (targetUserId <= 0 ||
                subtractHp <= 0) {
            return;
        }

        GameMsgProtocol.UserSubtractHpResult.Builder newBuilder = GameMsgProtocol.UserSubtractHpResult.newBuilder();
        newBuilder.setTargetUserId(targetUserId);
        newBuilder.setSubtractHp(subtractHp);

        GameMsgProtocol.UserSubtractHpResult newResult = newBuilder.build();
        Broadcaster.broadcast(newResult);
    }

    /**
     * 广播死亡消息
     *
     * @param targetUserId 被攻击者 Id
     */
    private void broadcastDie(Integer targetUserId) {
        if (targetUserId <= 0) {
            return;
        }

        GameMsgProtocol.UserDieResult.Builder newBuilder = GameMsgProtocol.UserDieResult.newBuilder();
        newBuilder.setTargetUserId(targetUserId);

        GameMsgProtocol.UserDieResult newResult = newBuilder.build();
        Broadcaster.broadcast(newResult);
    }
}
