package com.worldmodelers.kafka.messages.serdes;

import com.worldmodelers.kafka.messages.ExampleProducerMessage;
import org.apache.kafka.common.serialization.Serdes.WrapperSerde;

public class ExampleProducerMessageSerde extends WrapperSerde<ExampleProducerMessage> {

    public ExampleProducerMessageSerde() {
        super( new ExampleProducerMessageSerializer(), new ExampleProducerMessageDeserializer() );
    }
}

