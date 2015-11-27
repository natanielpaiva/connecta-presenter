package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import br.com.cds.connecta.presenter.filter.DatasourceFilter;
import java.util.List;

/**
 *
 * @author diego
 */
public interface IDatasourceAS {

    Datasource save(Datasource datasource);

    Iterable<Datasource> list(DatasourceFilter filter);

    Datasource get(Long id);

    void delete(Long id);

    void deleteAll(List<Long> ids);

}
