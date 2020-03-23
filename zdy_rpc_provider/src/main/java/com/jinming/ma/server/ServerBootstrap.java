package com.jinming.ma.server;


import com.jinming.ma.handler.UserServerHandler;
import com.jinming.ma.pojo.RpcDecoder;
import com.jinming.ma.pojo.RpcEncoder;
import com.jinming.ma.pojo.RpcRequest;
import com.jinming.ma.service.JSONSerializer;
import com.jinming.ma.service.UserServiceImpl;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerBootstrap implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>netty 服务启动执行，提供者启动<<<<<<<<<<<<<");
        //启动监听
        startServer("127.0.0.1",8990);
    }

    //hostName:ip地址  port:端口号
    public static void startServer(String hostName,int port) throws InterruptedException {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        io.netty.bootstrap.ServerBootstrap serverBootstrap = new io.netty.bootstrap.ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new RpcEncoder(String.class, new JSONSerializer()));
                        pipeline.addLast(new RpcDecoder(RpcRequest.class, new JSONSerializer()));
                        pipeline.addLast(new UserServerHandler());
                    }
                });
        serverBootstrap.bind(hostName,port).sync();


    }
}
