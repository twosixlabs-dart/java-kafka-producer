package com.worldmodelers.com.kafka.messages.serdes;

import com.worldmodelers.com.kafka.messages.ExampleProducerMessage;
import org.apache.kafka.common.serialization.Serdes.WrapperSerde;

public class ExampleStreamMessageSerde extends WrapperSerde<ExampleProducerMessage> {

    public ExampleStreamMessageSerde() {
        super( new ExampleProducerMessageSerializer(), new ExampleProducerMessageDeserializer() );
    }
}

