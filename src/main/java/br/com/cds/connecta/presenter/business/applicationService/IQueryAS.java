package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.FileSingleSource;
import br.com.cds.connecta.presenter.entity.querybuilder.Query;
import java.util.List;

/**
 *
 * @author nataniel
 */
public interface IQueryAS {
    
    Query save(Query query);
    
    Query saveGetById(Long id);
    
    List<FileSingleSource> getSingleSourceByIds(Query query, Class<Object> target);
}
