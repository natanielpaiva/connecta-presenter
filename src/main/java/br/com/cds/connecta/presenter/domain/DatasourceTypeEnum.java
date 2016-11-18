package br.com.cds.connecta.presenter.domain;

import br.com.cds.connecta.presenter.business.strategy.connector.ConnectorStrategy;
import br.com.cds.connecta.presenter.business.strategy.connector.CsvConnectorStrategy;
import br.com.cds.connecta.presenter.business.strategy.connector.DatabaseConnectorStrategy;
import br.com.cds.connecta.presenter.business.strategy.connector.RestConnectorStrategy;
import br.com.cds.connecta.presenter.business.strategy.connector.RestConnectorStrategyTemp;
import br.com.cds.connecta.presenter.business.strategy.connector.SolrConnectorStrategy;

/**
 *
 * @author diego
 */
public enum DatasourceTypeEnum {

    DATABASE(AnalysisTypeEnum.DATABASE, DatabaseConnectorStrategy.class),
    ENDECA(AnalysisTypeEnum.ENDECA, null),
    HDFS(AnalysisTypeEnum.HDFS, null),
    OBIEE(AnalysisTypeEnum.OBIEE, null),
    SOLR(AnalysisTypeEnum.SOLR, SolrConnectorStrategy.class),
    WEBSERVICE(AnalysisTypeEnum.WEBSERVICE, RestConnectorStrategyTemp.class),
    CSV(AnalysisTypeEnum.CSV, CsvConnectorStrategy.class),
    REST(AnalysisTypeEnum.REST,RestConnectorStrategy.class);
    
    private final AnalysisTypeEnum relatedAnalysisType;
    
    private final Class<? extends ConnectorStrategy> connectorStrategy;
    
    private DatasourceTypeEnum(AnalysisTypeEnum analysisTypeEnum, Class<? extends ConnectorStrategy> connectorStrategy) {
        this.relatedAnalysisType = analysisTypeEnum;
        this.connectorStrategy = connectorStrategy;
    }

    public AnalysisTypeEnum getRelatedAnalysisType() {
        return relatedAnalysisType;
    }

    public Class<? extends ConnectorStrategy> getConnectorStrategy() {
        return connectorStrategy;
    }
    
}
