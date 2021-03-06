package br.com.cds.connecta.presenter.components.viewers.amcharts.serializer;

import br.com.cds.connecta.presenter.components.viewers.amcharts.addition.Color;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/**
 *
 * @author nataniel
 */
public class ColorSerializer extends JsonSerializer<Color> {

    @Override
    public void serialize(Color color, JsonGenerator generator, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        
        generator.writeString(color.toString());
        
    }

}
