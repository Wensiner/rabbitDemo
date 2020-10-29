package com.sine.autism.rabbitproducerservice.exchangeModel.Topics;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.sine.autism.rabbitproducerservice.common.RabbitMQConnectUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author:wensiner
 * @Data:2020-10-28 22:55
 */
public class TopicsProduce {
    //定义exchange
    private static final String EXCHANGE_NAME = "topics_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectUtil.getConnect();
        Channel channel = connection.createChannel();
        //声明交换器(声明direct类型的交换机),第三个参数为交换机持久化设置为true,默认为false
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC,true);
        //发送消息到交换器
        String message ="匹配模式测试";
        //“sms”为routingKey,只有短信服务能收到消息(第三个参数为消息持久化)
        channel.basicPublish(EXCHANGE_NAME,"wensiner.kaqi.haven", MessageProperties.TEXT_PLAIN,message.getBytes());
        System.out.println(" [生产者] Sent '" + message + "'");

        channel.close();
        connection.close();
    }


}
