package com.sine.autism.rabbitproducerservice.common;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author:wensiner
 * @Data:2020-10-26 23:02
 */
public class RabbitMQConnectUtil {

    public static Connection getConnect() throws IOException, TimeoutException {

        //定义连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置服务器地址
        connectionFactory.setHost("119.45.210.212");
        //设置端口
        connectionFactory.setPort(5672);
        //设置Vhost
        connectionFactory.setVirtualHost("my_vhost");
        //设置账号
        connectionFactory.setUsername("root");
        //设置密码
        connectionFactory.setPassword("root");

        //通过工厂获取连接
        Connection connection = connectionFactory.newConnection();

        return connection;
    }


}
