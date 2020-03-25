package com.worldmodelers.kafka.processors.java;

import scala.collection.immutable.Stream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerApp {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        String configName;

        if (args.length > 0) configName = args[0];
        else configName = "default";

        String configResource = configName.trim() + ".properties";

        Properties properties = new Properties();
        InputStream propsStream = Stream.Cons.class.getClassLoader().getResourceAsStream( configResource );
        properties.load( propsStream );

        String topicTo = properties.getProperty( "topic.to" );

        ExampleProducer producer = new ExampleProducer( topicTo, properties );

        producer.sendRandomMessage();

    }

}
