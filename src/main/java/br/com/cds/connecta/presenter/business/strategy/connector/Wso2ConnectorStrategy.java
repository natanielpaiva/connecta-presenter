package br.com.cds.connecta.presenter.business.strategy.connector;

import br.com.cds.connecta.framework.connector2.Request;
import br.com.cds.connecta.framework.connector2.context.wso2.Wso2DataContextFactory;
import br.com.cds.connecta.framework.connector2.query.QueryBuilder;
import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.business.applicationService.IWso2AS;
import br.com.cds.connecta.presenter.domain.AnalysisTypeEnum;
import br.com.cds.connecta.presenter.domain.Wso2SearchTypeEnum;
import br.com.cds.connecta.presenter.entity.analysis.Wso2Analysis;
import br.com.cds.connecta.presenter.entity.datasource.Wso2Datasource;
import br.com.cds.connecta.presenter.persistence.DatasourceRepository;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
@Scope("prototype")
public class Wso2ConnectorStrategy extends AbstractConnectorStrategy {

    @Autowired
    private DatasourceRepository repository;

//    @Autowired
//    private IWso2AS iWso2AS;
    @Override
    protected Request makeRequest(AnalysisExecuteRequest analysisExecuteRequest) throws SQLException {

        Wso2Analysis wso2Analysis = (Wso2Analysis) analysisExecuteRequest.getAnalysis();

        Wso2Datasource wso2Datasource = (Wso2Datasource) repository.findOne(wso2Analysis.getDatasource().getId());

        Wso2DataContextFactory wso2DataContextFactory = null;
        if (wso2Analysis.getSearchType().equals(Wso2SearchTypeEnum.RANGE)) {
            wso2DataContextFactory = new Wso2DataContextFactory(
                    wso2Datasource.getUser(),
                    wso2Datasource.getPassword(),
                    wso2Datasource.getUrl(),
                    wso2Analysis.getTable(),
                    wso2Analysis.getFrom(),
                    wso2Analysis.getTo());
        } else if (wso2Analysis.getSearchType().equals(Wso2SearchTypeEnum.QUERY)) {
            wso2DataContextFactory = new Wso2DataContextFactory(
                    wso2Datasource.getUser(),
                    wso2Datasource.getPassword(),
                    wso2Datasource.getUrl(),
                    wso2Analysis.getTable(),
                    wso2Analysis.getQuery());
        }
        QueryBuilder query = new QueryBuilder();
        query.setColumns(toConnectorColumns(wso2Analysis.getAnalysisColumns()));

        addPaginationIfDefined(query, analysisExecuteRequest);
        addDrillIfDefined(query, analysisExecuteRequest, wso2DataContextFactory);
        addFiltersIfDefined(query, analysisExecuteRequest, wso2DataContextFactory);
        Request request = new Request(wso2DataContextFactory, query);

        return request;
    }

}
