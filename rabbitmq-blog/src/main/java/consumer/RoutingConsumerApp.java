package consumer;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class RoutingConsumerApp {
    private static final String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws Exception {
        System.out.println("==============================================");
        System.out.println("Please write title topic for select. Command: set_topic *");
        System.out.println("==============================================");

        Set<String> allTopic = new HashSet<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String selectTop;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        String queueName = channel.queueDeclare().getQueue();

        while (true) {
            selectTop = reader.readLine();
            if (selectTop.startsWith("set_topic ")) {
                selectTop = selectTop.replace("set_topic ", "");
                break;
            }
            else System.out.println("!!! Write title topic for select. Command: set_topic *");
        }

        helper();

        String routingKey = "com.it.program." + selectTop + ".#";
        channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
        // add topic in array
        allTopic.add(selectTop);
        System.out.println(" [*] Waiting for messages with routing key (" + routingKey + "):");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "'\n'" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });

        //cycle for listener command.
        while (true) {
            selectTop = reader.readLine();
            if (selectTop.startsWith("add_topic ")) {
                selectTop = selectTop.replace("add_topic ", "");
                routingKey = "com.it.program." + selectTop + ".#";
                channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
                allTopic.add(selectTop);
                System.out.println(" [!] Add topic (" + selectTop + "):");
            } if (selectTop.startsWith("delete_topic ")) {
                selectTop = selectTop.replace("delete_topic ", "");
                routingKey = "com.it.program." + selectTop + ".#";
                channel.queueUnbind(queueName, EXCHANGE_NAME, routingKey);
                allTopic.remove(selectTop);
                System.out.println(" [!] Delete topic (" + selectTop + "):");
            } if (selectTop.equals("show_topics")) {
                System.out.println("All topics:");
                for (String x : allTopic) {
                    System.out.print(x + " ");
                }
                System.out.println();
            } if (selectTop.equals("help")) {
                helper();
            }
        }
    }

    static public void helper() {
        System.out.println("====================HELP======================");
        System.out.println("For add topic write command: add_topic *");
        System.out.println("For delete topic write command: delete_topic *");
        System.out.println("For show all topics write command: show_topics");
        System.out.println("For see all command, write command: help");
        System.out.println("==============================================");
    }
}
