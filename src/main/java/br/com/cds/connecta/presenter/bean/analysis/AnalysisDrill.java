package br.com.cds.connecta.presenter.bean.analysis;

import java.util.List;

public class AnalysisDrill {
    
    private String columnToDrill;
    private List<String> columnsToSum;
    private List<DrillColumnValue> listPreviousColumns;

    public String getColumnToDrill() {
        return columnToDrill;
    }
    public void setColumnToDrill(String columnToDrill) {
        this.columnToDrill = columnToDrill;
    }
    public List<String> getColumnsToSum() {
        return columnsToSum;
    }
    public void setColumnsToSum(List<String> columnsToSum) {
        this.columnsToSum = columnsToSum;
    }
    
    public List<DrillColumnValue> getListPreviousColumns() {
        return listPreviousColumns;
    }
    public void setListPreviousColumns(List<DrillColumnValue> listPreviousColumns) {
        this.listPreviousColumns = listPreviousColumns;
    }

}
