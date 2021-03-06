package com.sine.autism.rabbitproducerservice.exchangeModel.Topics;

import com.rabbitmq.client.*;
import com.sine.autism.rabbitproducerservice.common.RabbitMQConnectUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author:wensiner
 * @Data:2020-10-28 22:54
 */
public class TopicsConsumer2 {
    //定义交换机
    private static final String EXCHANGE_NAME = "topics_exchange";
    //定义队列
    private static final String QUEUE_NAME = "topics_exchange_Q2";
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitMQConnectUtil.getConnect();
        //获取通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机(同时订阅haven和xiao的消息)
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"*.*.haven");//指定接收发送方指定routing key为emil的消息
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"xiao.#");
        //创建消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body);
                System.out.println(" [邮件服务] received : " + message + "!");
            }
        };
        //监听队列，自动消费
        channel.basicConsume(QUEUE_NAME,true,"",consumer);
    }
}
