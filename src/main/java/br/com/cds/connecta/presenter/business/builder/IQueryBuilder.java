package br.com.cds.connecta.presenter.business.builder;

import br.com.cds.connecta.presenter.entity.Query;
import java.util.List;

/**
 *
 * @author nataniel
 * @param <T> O tipo do retorno do QueryBuilder
 */
public interface IQueryBuilder<T extends Object> {
    
    List<T> fetchResultsFor(Query query);

    Query save(Query query);
    
}
