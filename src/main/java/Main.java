import com.worldmodelers.kafka.producer.java.ExampleProducer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main( String[] args ) throws IOException, InterruptedException {

        Properties properties = new Properties();
        InputStream propsStream = Main.class.getClassLoader().getResourceAsStream( String.format( "%s.properties", args[ 0 ] ) );
        properties.load( propsStream );

        String topicTo = properties.getProperty( "topic.to" );

        ExampleProducer producer = new com.worldmodelers.kafka.producer.java.ExampleProducer( topicTo, properties );

        while ( true ) {
            Thread.sleep( 2000 );
            producer.sendRandomMessage();
        }
    }

}
