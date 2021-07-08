package server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = null ;
            switch (event.state()){
                case ALL_IDLE:
                    eventType = "∂¡∂¡ø’œ–";
                    break;
                case READER_IDLE:
                    eventType = "∂¡ø’œ–";
                    break;
                case WRITER_IDLE:
                    eventType = "–¥ø’œ–";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "≥¨ ± ¿º‰" + eventType);
            ctx.close();
        }
    }
}
