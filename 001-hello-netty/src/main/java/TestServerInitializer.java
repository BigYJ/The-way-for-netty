import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //������Ӧ�����
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        //�Զ��崦������hello world ���ؿͻ��ˣ�
        pipeline.addLast("testHttpServerHandler",new TestHttpServerHandler());
    }
}
