package com.worldmodelers.kafka.messages.serdes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.worldmodelers.kafka.messages.ExampleProducerMessage;
import com.worldmodelers.kafka.messages.ExampleProducerMessageJsonFormat;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class ExampleProducerMessageSerializer extends ExampleProducerMessageJsonFormat implements Serializer<ExampleProducerMessage> {
    public void configure( Map<String, ?> configs, Boolean isKey ) { }

    public byte[] serialize( String topic, ExampleProducerMessage data ) {
        byte[] dataOut = null;

        try {
            dataOut = marshalMessage( data ).getBytes();
        } catch ( JsonProcessingException e ) {
            e.printStackTrace();
        }

        return dataOut;
    }

    public void close() { }
}