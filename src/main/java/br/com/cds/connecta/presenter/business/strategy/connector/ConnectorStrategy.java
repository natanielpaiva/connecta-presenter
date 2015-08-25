/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.business.strategy.connector;

import br.com.cds.connecta.framework.connector.util.ConnectorColumn;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author diego
 */
public interface ConnectorStrategy {
    
    List<Map<String, Object>>getDataProvider(Analysis analysis, List<ConnectorColumn> columns);
    
}
