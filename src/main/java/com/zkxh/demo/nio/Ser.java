package com.zkxh.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName Ser
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/17 14:44
 * @Vserion v0.0.1
 */

public class Ser {


    private int port;

    private ServerSocketChannel server;

    private Selector selector;

    public Ser(int port) throws IOException {

        this.port = port;

        server = ServerSocketChannel.open();

        server.socket().bind(new InetSocketAddress(this.port));

        server.configureBlocking(false);

        selector = Selector.open();

        server.register(selector, SelectionKey.OP_ACCEPT);


    }

    public void listener() throws IOException {

        while (true) {
            int i = selector.select();

            if (i == 0) {
                continue;
            }
            Set<SelectionKey> keys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                //TODO 处理
                process(selectionKey);

                //处理结束 ，移除一个
                iterator.remove();
            }


        }
    }

    ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
    ByteBuffer sendBuffer = ByteBuffer.allocate(1024);


    private void process(SelectionKey selectionKey) throws IOException {

        //判断是否注册
        if (selectionKey.isAcceptable()) {
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
        }
        //判断是否可读
        else if (selectionKey.isReadable()) {
            receiveBuffer.clear();
            SocketChannel client = (SocketChannel) selectionKey.channel();
            int len = client.read(receiveBuffer);
            if (len > 0) {
                String msg = new String(receiveBuffer.array(), 0, len);
                System.out.println(msg);
            }
            client.register(selector, SelectionKey.OP_WRITE);
        }
        //判断是否可写
        else if (selectionKey.isWritable()) {
            sendBuffer.clear();
            SocketChannel client = (SocketChannel) selectionKey.channel();
            client.write(sendBuffer);

            client.register(selector, SelectionKey.OP_READ);
        }
    }

}
