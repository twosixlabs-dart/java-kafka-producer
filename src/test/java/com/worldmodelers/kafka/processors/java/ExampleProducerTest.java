package com.worldmodelers.kafka.processors.java;

import com.worldmodelers.kafka.messages.ExampleProducerMessage;
import com.worldmodelers.kafka.messages.ExampleProducerMessageJsonFormat;
import kafka.server.KafkaConfig$;
import net.mguenther.kafka.junit.*;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class ExampleProducerTest extends ExampleProducerMessageJsonFormat {

    private final Logger LOG = LoggerFactory.getLogger( ExampleProducerTest.class );

    private EmbeddedKafkaConfig kafkaConfig = EmbeddedKafkaConfig
            .create()
            .with( KafkaConfig$.MODULE$.PortProp(), 6308 )
            .build();

    private EmbeddedKafkaClusterConfig kafkaClusterConfig = EmbeddedKafkaClusterConfig
            .create()
            .provisionWith( kafkaConfig )
            .build();

    private Properties properties = new Properties();

    public ExampleProducerTest() throws IOException {
        InputStream propStream = getClass().getClassLoader().getResourceAsStream( "test.properties" );
        properties.load( propStream );
    }

    @Rule
    public EmbeddedKafkaCluster cluster = EmbeddedKafkaCluster.provisionWith( kafkaClusterConfig );

    @Test
    public void ExampleConsumerShouldReadAMessageFromATopic() throws IOException, InterruptedException, ExecutionException {
        String topic = properties.getProperty( "topic.to" );
        ExampleProducer producer = new ExampleProducer( topic, properties );

        producer.sendRandomMessage();

        ObserveKeyValues<String, String> observeRequest = ObserveKeyValues.on(topic, 1).useDefaults();

        List<KeyValue<String, String>> observedValues = cluster.observe(observeRequest);

        ExampleProducerMessage message = unmarshalMessage( observedValues.get( 0 ).getValue() );

        assertEquals( "java-kafka-producer", message.getBreadcrumbs().get(0) );
        assertEquals( 1, message.getBreadcrumbs().size() );
        assertEquals( 36, observedValues.get( 0 ).getKey().length() );
    }
}