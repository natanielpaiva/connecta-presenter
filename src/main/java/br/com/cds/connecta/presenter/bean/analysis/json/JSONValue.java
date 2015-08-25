package br.com.cds.connecta.presenter.bean.analysis.json;

/**
 *
 * @author diego
 */
public abstract class JSONValue {
    private String name;
    private JSONValueType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONValueType getType() {
        return type;
    }

    public void setType(JSONValueType type) {
        this.type = type;
    }
}
