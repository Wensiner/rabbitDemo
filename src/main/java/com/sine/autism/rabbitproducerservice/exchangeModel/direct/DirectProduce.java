package com.sine.autism.rabbitproducerservice.exchangeModel.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sine.autism.rabbitproducerservice.common.RabbitMQConnectUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author:wensiner
 * @Data:2020-10-28 22:13
 */
public class DirectProduce {
    //定义exchange
    private static final String EXCHANGE_NAME = "direct_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectUtil.getConnect();
        Channel channel = connection.createChannel();
        //声明交换器(声明direct类型的交换机)
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //发送消息到交换器
        String message ="dont worry,i only have eyes for you! who can revice?";
        //“sms”为routingKey,只有短信服务能收到消息
        channel.basicPublish(EXCHANGE_NAME,"sms",null,message.getBytes());
        System.out.println(" [生产者] Sent '" + message + "'");

        channel.close();
        connection.close();
    }


}
