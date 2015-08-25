package br.com.cds.connecta.presenter.bean.analysis.json;

import java.util.List;

/**
 *
 * @author diego
 */
public class JSONValueArray extends JSONValue {
    
    private List<JSONValue> values;

    public List<JSONValue> getValues() {
        return values;
    }

    public void setValues(List<JSONValue> values) {
        this.values = values;
    }
}
