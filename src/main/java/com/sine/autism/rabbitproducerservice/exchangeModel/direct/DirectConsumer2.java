package com.sine.autism.rabbitproducerservice.exchangeModel.direct;

import com.rabbitmq.client.*;
import com.sine.autism.rabbitproducerservice.common.RabbitMQConnectUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description:
 * @Author:wensiner
 * @Data:2020-10-28 22:13
 */
public class DirectConsumer2 {
    //定义交换机
    private static final String EXCHANGE_NAME = "direct_exchange";
    //定义队列
    private static final String QUEUE_NAME = "direct_exchange_emil";
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = RabbitMQConnectUtil.getConnect();
        //获取通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"emil");//指定接收发送方指定routing key为emil的消息
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
