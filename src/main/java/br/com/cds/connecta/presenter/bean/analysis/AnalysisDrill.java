package br.com.cds.connecta.presenter.bean.analysis;

import java.util.List;

public class AnalysisDrill {
    
    private String columnToDrill;
    private List<String> columnsToSum;
    private String previousDrillColumn;
    private String filterDrillValue;
    
    public String getFilterDrillValue() {
        return filterDrillValue;
    }
    public void setFilterDrillValue(String filterDrillValue) {
        this.filterDrillValue = filterDrillValue;
    }
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
    public String getPreviousDrillColumn() {
        return previousDrillColumn;
    }
    public void setPreviousDrillColumn(String previousDrillColumn) {
        this.previousDrillColumn = previousDrillColumn;
    }

}
