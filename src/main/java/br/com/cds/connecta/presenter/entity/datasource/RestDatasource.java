package br.com.cds.connecta.presenter.entity.datasource;

import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "TB_REST_DS")
public class RestDatasource extends Datasource{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_REQUEST_DS")
    private Set<RestDatasourceRequest> requests;

    public Set<RestDatasourceRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<RestDatasourceRequest> requests) {
        this.requests = requests;
    }
    
}
