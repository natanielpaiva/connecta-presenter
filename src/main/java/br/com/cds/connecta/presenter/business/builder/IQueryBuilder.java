package br.com.cds.connecta.presenter.business.builder;

import br.com.cds.connecta.presenter.entity.querybuilder.Query;
import java.util.List;

/**
 *
 * @author nataniel
 * @param <T> O tipo do retorno do QueryBuilder
 */
public interface IQueryBuilder<T extends Object> {
    
    List<T> listResultsFor(Query query, Class<T> target);

    String sqlFor(Query query, Boolean split, Class<T> target);
    
}
