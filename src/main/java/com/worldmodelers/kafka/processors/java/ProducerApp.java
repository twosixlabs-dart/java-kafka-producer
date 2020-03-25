package com.worldmodelers.kafka.processors.java;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerApp {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        Properties properties = new Properties();
        InputStream propsStream = ProducerApp.class.getClassLoader().getResourceAsStream( "app.properties" );
        properties.load( propsStream );

        String topicTo = properties.getProperty( "topic.to" );

        ExampleProducer producer = new ExampleProducer( topicTo, properties );

        producer.sendRandomMessage();

    }

}
