package com.worldmodelers.com.kafka.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleProducerMessageJsonFormat {
    private Logger LOG = LoggerFactory.getLogger( ExampleProducerMessageJsonFormat.class );

    ObjectMapper mapper = new ObjectMapper();

    public ExampleProducerMessage unmarshalMessage( String json ) throws JsonProcessingException {
        return mapper.readValue( json, ExampleProducerMessage.class );
    }

    public String marshalMessage( ExampleProducerMessage message ) throws JsonProcessingException {
        return mapper.writeValueAsString( message );
    }
}
