package br.com.cds.connecta.presenter.components;

import br.com.cds.connecta.presenter.bean.analysis.json.JSONValue;
import br.com.cds.connecta.presenter.bean.analysis.json.JSONValueArray;
import br.com.cds.connecta.presenter.bean.analysis.json.JSONValueObject;
import br.com.cds.connecta.presenter.bean.analysis.json.JSONValueParser;
import br.com.cds.connecta.presenter.bean.analysis.json.JSONValuePrimitive;
import br.com.cds.connecta.presenter.bean.analysis.json.JSONValueType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.json.JSONException;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author diego
 */
public class JSONValueParserTest {
    
    private final JSONValueParser parser = new JSONValueParser();
    
    /**
     * TODO Continuar
     * 
     * @throws JSONException
     * @throws FileNotFoundException 
     */
    @Test
    public void testJsonSchema() throws JSONException, FileNotFoundException {
        FileInputStream fis = new FileInputStream("src/test/resources/json/analysis/json-reflection-person-list.json");
        JSONValue json = parser.parse(fis);
        
        assertThat(json.getType(), is(JSONValueType.ARRAY));
        assertThat(json.getName(), equalTo(null));
        
        JSONValueArray array = (JSONValueArray) json;
        
        assertThat(array.getValues().size(), is(2));
        
        for (JSONValue value : array.getValues()) {
            assertThat(json.getName(), equalTo(null));
            assertThat(value.getType(), is(JSONValueType.OBJECT));
            
            JSONValueObject object = (JSONValueObject) value;
            
            assertThat(object.getAttributes().size(), is(5));
            
            assertThat(object.getAttributes().get(0).getName(), is("name"));
            assertThat(object.getAttributes().get(0).getType(), is(JSONValueType.STRING));
            assertThat(
                ((JSONValuePrimitive<String>)object.getAttributes().get(0)).getValue(),
                anyOf(is("John"), is("Mary"))
            );
            
            assertThat(object.getAttributes().get(1).getName(), is("age"));
            assertThat(object.getAttributes().get(1).getType(), is(JSONValueType.NUMBER));
            
//            assertThat(
//                ((JSONValuePrimitive<Number>)object.getAttributes().get(1)).getValue(),
//                anyOf(equalTo(25), equalTo(29))
//            );
        }
    }
    
    
    
}
