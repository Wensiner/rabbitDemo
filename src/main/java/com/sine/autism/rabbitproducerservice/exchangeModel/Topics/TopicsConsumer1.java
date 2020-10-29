package com.sine.autism.rabbitproducerservice.exchangeModel.Topics;

import com.rabbitmq.client.*;
import com.sine.autism.rabbitproducerservice.common.RabbitMQConnectUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author:wensiner
 * @Data:2020-10-28 22:13
 */
public class TopicsConsumer1 {
    //定义交换机
    private static final String EXCHANGE_NAME = "topics_exchange";
    //定义队列
    private static final String QUEUE_NAME = "topics_exchange_Q1";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitMQConnectUtil.getConnect();
        //获取通道
        Channel channel = connection.createChannel();
        //声明队列（第二个参数为队列持久化，设置为true生效）
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //绑定队列到交换机（同时指定需要订阅的routing key。可以指定多个）
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"*.kaqi.*");//指定接收发送方指定routing key为sms的消息
        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            //获取消息并且处理

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                // body 即消息体
                String msg = new String(body);
                System.out.println(" [短信服务] received : " + msg + "!");
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);



    }
}
