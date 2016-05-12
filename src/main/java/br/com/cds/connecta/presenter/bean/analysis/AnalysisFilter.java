package br.com.cds.connecta.presenter.bean.analysis;

import br.com.cds.connecta.framework.connector2.query.QueryFilterOperator;
import br.com.cds.connecta.framework.connector2.query.QueryFilterValue;

/**
 *
 * @author diego
 */
public class AnalysisFilter {
    
    private String columnName;
    private QueryFilterOperator operator;
    private QueryFilterValue value;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
    public QueryFilterOperator getOperator() {
        return operator;
    }

    public void setOperator(QueryFilterOperator operator) {
        this.operator = operator;
    }

    public QueryFilterValue getValue() {
        return value;
    }

    public void setValue(QueryFilterValue value) {
        this.value = value;
    }
    
}
