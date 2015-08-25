package br.com.cds.connecta.presenter.bean.analysis.json;

import java.util.List;

/**
 *
 * @author diego
 */
public class JSONValueObject extends JSONValue {
    private List<JSONValue> attributes;

    public List<JSONValue> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<JSONValue> attributes) {
        this.attributes = attributes;
    }
}
