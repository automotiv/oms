package com.example.demo;

import java.io.File;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SendOrderToOMS {

	public static void main(String[] args) {
		SendOrderToOMS orderToOMS = new SendOrderToOMS();
		orderToOMS.sendOrderToOMS(new File(""), "sometopic");
	}

	public void sendOrderToOMS(File file, String topicName) {
		Properties properties = new Properties();
		properties.put("bootstarp.servers", "localhost:9092");
		properties.put("acks", "all");
		properties.put("retires", 0);
		properties.put("linger.ms", 1);
		properties.put("buffer.memory", 33554432);
		properties.put("key.serializer", "org.apache.kafka.common.serializa-tion.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serializa-tion.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<>(properties);

		for (int i = 0; i < 10; i++)
			producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(i), Integer.toString(i)));
		System.out.println("Message sent successfully");
		producer.close();

	}

}
