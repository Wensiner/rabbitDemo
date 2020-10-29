package com.sine.autism.rabbitproducerservice.exchangeModel.Fanout;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sine.autism.rabbitproducerservice.common.RabbitMQConnectUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author:wensiner
 * @Data:2020-10-27 23:13
 */
public class FanoutProduce {
    //定义exchange
    private static final String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectUtil.getConnect();
        Channel channel = connection.createChannel();
        //声明交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //发送消息到交换器
        String message ="dont worry,i only have eyes for you!";
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println(" [生产者] Sent '" + message + "'");

        channel.close();
        connection.close();
    }


}
