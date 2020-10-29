package com.sine.autism.rabbitproducerservice.basicWorkRabbitDemo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sine.autism.rabbitproducerservice.common.RabbitMQConnectUtil;

/**
 * @Description:
 * @Author:wensiner
 * @Data:2020-10-26 23:17
 */
public class MessageProduce  {
//1.定义一个quer名称
private static final String QUEUE_NAME = "simple_queue";
public static void main(String[]args) throws Exception{

    Connection connection = RabbitMQConnectUtil.getConnect();
    //2.从连接中获取通道
    Channel channel = connection.createChannel();
    // 3、声明（创建）队列
    //参数：String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
    /**
     * 参数明细
     * 1、queue 队列名称
     * 2、durable 是否持久化，如果持久化，mq重启后队列还在
     * 3、exclusive 是否独占连接，队列只允许在该连接中访问，如果connection连接关闭队列则自动删除,如果将此参数设置true可用于临时队列的创建
     * 4、autoDelete 自动删除，队列不再使用时是否自动删除此队列，如果将此参数和exclusive参数设置为true就可以实现临时队列（队列不用了就自动删除）
     * 5、arguments 参数，可以设置一个队列的扩展参数，比如：可设置存活时间
     */
    channel.queueDeclare(QUEUE_NAME,false,false,false,null);
    //消息内容
    String message = "hello-dear-rabbit 123 ";
    // 4向指定的队列中发送消息
    //参数：String exchange, String routingKey, BasicProperties props, byte[] body
    /**
     * 参数明细：
     * 1、exchange，交换机，如果不指定将使用mq的默认交换机（设置为""）
     * 2、routingKey，路由key，交换机根据路由key来将消息转发到指定的队列，如果使用默认交换机，routingKey设置为队列的名称
     * 3、props，消息的属性
     * 4、body，消息内容
     */
    // 循环发布任务
    for (int i = 0; i < 50; i++) {
        // 消息内容
        message = "wensiner.. " + i;
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        Thread.sleep(i * 2);
    }
    //关闭资源
    channel.close();
    connection.close();
    }








}
