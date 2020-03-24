package com.worldmodelers.kafka.messages.serdes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.worldmodelers.kafka.messages.ExampleProducerMessage;
import com.worldmodelers.kafka.messages.ExampleProducerMessageJsonFormat;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ExampleProducerMessageDeserializer extends ExampleProducerMessageJsonFormat implements Deserializer<ExampleProducerMessage> {
    public void configure( Map<String, ?> configs, Boolean isKey ) {}

    public ExampleProducerMessage deserialize( String topic, byte[] data ) {
        ExampleProducerMessage dataOut = null;
        try {
            dataOut = unmarshalMessage( new String( data, StandardCharsets.UTF_8 ) );
        } catch ( JsonProcessingException e ) {
            e.printStackTrace();
        }

        return dataOut;
    }

    public void close( ) {}
}
