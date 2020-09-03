package com.kennedy.herostory;

import com.google.protobuf.GeneratedMessageV3;
import com.kennedy.herostory.cmdhandler.CmdHandlerFactory;
import com.kennedy.herostory.cmdhandler.ICmdHandler;
import com.kennedy.herostory.model.UserManager;
import com.kennedy.herostory.msg.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

/**
 * 自定义的游戏消息处理器
 */
public class GameMsgHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        //解决：先入场的玩家看不到后来入场的玩家（2可以看到1，但1看不到2）
        Broadcaster.addChannel(ctx.channel());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);

        //用户退出
        Broadcaster.removeChannel(ctx.channel());

        // 先拿到用户 Id
        Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();

        if (null == userId) {
            return;
        }

        UserManager.removeUserById(userId);

        GameMsgProtocol.UserQuitResult.Builder resultBuilder = GameMsgProtocol.UserQuitResult.newBuilder();
        resultBuilder.setQuitUserId(userId);

        GameMsgProtocol.UserQuitResult newResult = resultBuilder.build();
        Broadcaster.broadcast(newResult);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("收到客户端消息, msgClazz = " + msg.getClass().getName() + ", msg = " + msg);

//        ICmdHandler<? extends GeneratedMessageV3> cmdHandler = CmdHandlerFactory.create(msg.getClass());
//
//        if (null != cmdHandler) {
//            cmdHandler.handle(ctx, cast(msg));
//        }
        if (msg instanceof GeneratedMessageV3) {
            // 通过主线程处理器处理消息
            MainThreadProcessor.getInstance().process(ctx, (GeneratedMessageV3) msg);
        }
//        if (msg instanceof GameMsgProtocol.UserEntryCmd) {
//            new UserEntryCmdHandler().handle(ctx, (GameMsgProtocol.UserEntryCmd) msg);
//        } else if (msg instanceof GameMsgProtocol.WhoElseIsHereCmd) {
//            new WhoElseIsHereCmdHandler().handle(ctx, (GameMsgProtocol.WhoElseIsHereCmd) msg);
//        } else if (msg instanceof GameMsgProtocol.UserMoveToCmd) {
//            new UserMoveToCmdHandler().handle(ctx, (GameMsgProtocol.UserMoveToCmd) msg);
//        }

    }



}
