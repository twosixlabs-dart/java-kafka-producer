package com.worldmodelers.kafka.producer.java;

import com.worldmodelers.kafka.messages.ExampleProducerMessage;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class ExampleProducer {

    private final Logger LOG = LoggerFactory.getLogger( ExampleProducer.class );
    private Properties kafkaProps = new Properties();
    private String topic;
    private KafkaProducer<String, ExampleProducerMessage> producer;

    // Constructor retrieves kafka-specific properties from system or application
    // properties prefixed with "kafka." See test.properties in the test resources
    // directory for an example
    public ExampleProducer( String topicIn, Properties properties ) {
        properties.forEach( ( key, val ) -> {
            if ( key.toString().startsWith( "kafka" ) ) {
                kafkaProps.put( key.toString().substring( 6 ), val );
            }
        } );

        topic = topicIn;
        producer = new KafkaProducer<>( kafkaProps );
    }

    // Serdes are objects that handle serializing and deserializing kafka messages
    // one is needed to serialize the key (simple string serde) and another to serialize
    // the message itself (in this case the custom ExampleProducerMessageSerde defined in the
    // messages package
    public void sendRandomMessage() {
        String key = UUID.randomUUID().toString();
        List<String> breadcrumbs = new ArrayList<>();
        breadcrumbs.add( "java-kafka-producer" );
        ExampleProducerMessage value = new ExampleProducerMessage( key, breadcrumbs );

        ProducerRecord<String, ExampleProducerMessage> message = new ProducerRecord<>( topic, key, value );

        try {
            producer.send( message ).get();
        } catch ( Exception e ) {
            LOG.error( e.getClass().getSimpleName() + " : " + e.getMessage() + " : " + e.getCause() );
            e.printStackTrace();
        }

        LOG.info( "Successfully published " + key + " to topic " + topic );
    }
}