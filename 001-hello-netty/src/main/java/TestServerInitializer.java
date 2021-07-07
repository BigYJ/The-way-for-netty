import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //请求响应编解码
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        //自定义处理（构造hello world 返回客户端）
        pipeline.addLast("testHttpServerHandler",new TestHttpServerHandler());
    }
}
