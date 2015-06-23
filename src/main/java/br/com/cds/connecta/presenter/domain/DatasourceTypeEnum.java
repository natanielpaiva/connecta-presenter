package br.com.cds.connecta.presenter.domain;

/**
 *
 * @author diego
 */
public enum DatasourceTypeEnum {

    DATABASE(AnalysisTypeEnum.DATABASE),
    ENDECA(AnalysisTypeEnum.ENDECA),
    HDFS(AnalysisTypeEnum.HDFS),
    BI(AnalysisTypeEnum.BI),
    SOLR(AnalysisTypeEnum.SOLR),
    WEBSERVICE(AnalysisTypeEnum.WEBSERVICE);
    
    private final AnalysisTypeEnum relatedAnalysisType;
    
    private DatasourceTypeEnum(AnalysisTypeEnum analysisTypeEnum) {
        this.relatedAnalysisType = analysisTypeEnum;
    }

    public AnalysisTypeEnum getRelatedAnalysisType() {
        return relatedAnalysisType;
    }
    
}
