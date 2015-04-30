package br.com.cds.connecta.presenter.entity.querybuilder;

import java.io.Serializable;

/**
 *
 * @author nataniel
 */
public class QueryConditionValueBetween implements Serializable {
    private String start;
    private String end;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
