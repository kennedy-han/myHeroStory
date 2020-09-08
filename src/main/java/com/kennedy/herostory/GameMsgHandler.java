package com.kennedy.herostory;

import com.google.protobuf.GeneratedMessageV3;
import com.kennedy.herostory.cmdhandler.CmdHandlerFactory;
import com.kennedy.herostory.cmdhandler.ICmdHandler;
import com.kennedy.herostory.model.UserManager;
import com.kennedy.herostory.msg.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义的游戏消息处理器
 */
public class GameMsgHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 日志对象
     */
    static private final Logger LOGGER = LoggerFactory.getLogger(GameMsgHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        // 添加客户端信道
        //解决：先入场的玩家看不到后来入场的玩家（2可以看到1，但1看不到2）
        Broadcaster.addChannel(ctx.channel());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);

        //用户退出
        // 移除客户端信道
        Broadcaster.removeChannel(ctx.channel());

        // 先拿到用户 Id
        Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();

        if (null == userId) {
            return;
        }

        LOGGER.info("用户离线, userId = {}", userId);

        // 移除用户
        UserManager.removeUserById(userId);

        // 广播用户离场的消息
        GameMsgProtocol.UserQuitResult.Builder resultBuilder = GameMsgProtocol.UserQuitResult.newBuilder();
        resultBuilder.setQuitUserId(userId);

        GameMsgProtocol.UserQuitResult newResult = resultBuilder.build();
        Broadcaster.broadcast(newResult);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof GeneratedMessageV3) {
            // 通过主线程处理器处理消息
            MainThreadProcessor.getInstance().process(ctx, (GeneratedMessageV3) msg);
        }

    }

}
