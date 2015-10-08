package br.com.cds.connecta.presenter.domain;

import br.com.cds.connecta.presenter.business.strategy.connector.ConnectorStrategy;
import br.com.cds.connecta.presenter.business.strategy.connector.DataBaseConnectorStrategy;
import br.com.cds.connecta.presenter.business.strategy.connector.RestConnectorStrategy;
import br.com.cds.connecta.presenter.business.strategy.connector.SolrConnectorStrategy;

/**
 *
 * @author diego
 */
public enum DatasourceTypeEnum {

    DATABASE(AnalysisTypeEnum.DATABASE, DataBaseConnectorStrategy.class),
    ENDECA(AnalysisTypeEnum.ENDECA, null),
    HDFS(AnalysisTypeEnum.HDFS, null),
    OBIEE(AnalysisTypeEnum.OBIEE, null),
    SOLR(AnalysisTypeEnum.SOLR, SolrConnectorStrategy.class),
    WEBSERVICE(AnalysisTypeEnum.WEBSERVICE, RestConnectorStrategy.class);
    
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
