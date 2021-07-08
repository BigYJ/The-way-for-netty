package server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        channels.forEach(channel -> {
            if(channel != ctx.channel()){
                channel.writeAndFlush(ctx.channel().remoteAddress()+" sent message："+msg+"\n");
            }else {
                channel.writeAndFlush("I："+msg+"\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
    //客户端连接建立
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        if(!channels.contains(channel)){
            channels.writeAndFlush("new client "+channel.remoteAddress()+" join"+"\n");
            channels.add(channel);
        }
    }
    //客户端移除
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //自动移除
        channels.remove(ctx.channel());
        channels.writeAndFlush("client "+channel.remoteAddress()+" leave"+"\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "online"+"\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "ooffline"+"\n");
    }
}
