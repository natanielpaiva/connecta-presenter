package br.com.cds.connecta.presenter.bean.analysis.json;

/**
 *
 * @author diego
 */
public class JSONValuePrimitive<T> extends JSONValue {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
