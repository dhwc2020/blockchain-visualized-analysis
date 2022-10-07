import com.yupi.client.CollectionClientHandler;
import com.yupi.codec.MessageCodec;
import com.yupi.codec.MessageDecoder;
import com.yupi.codec.MessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author dhwc
 * @create 2022-10-06 20:03
 */
public class TestMain {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        Bootstrap serverBootstrap = new Bootstrap();
        serverBootstrap.group(bossGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(new MessageDecoder(new MessageCodec<>()));
                        ch.pipeline().addLast(new CollectionClientHandler(new MessageEncoder(new MessageCodec<>())));
                        ch.pipeline().addLast(new MessageEncoder(new MessageCodec<>()));
                    }
                });
        serverBootstrap.connect("127.0.0.1", 3879).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else {
                System.err.println("连接失败!");
            }
        });
    }
}
