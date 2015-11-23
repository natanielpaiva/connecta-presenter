package br.com.cds.connecta.presenter.entity.querybuilder;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author nataniel
 */
public class QueryConditionValue implements Serializable {
    private String value;
    private QueryConditionValueBetween between;
    private List<String> in;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public QueryConditionValueBetween getBetween() {
        return between;
    }

    public void setBetween(QueryConditionValueBetween between) {
        this.between = between;
    }

    public List<String> getIn() {
        return in;
    }

    public void setIn(List<String> in) {
        this.in = in;
    }
}
