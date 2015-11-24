package br.com.cds.connecta.presenter.business.builder;

import br.com.cds.connecta.presenter.entity.querybuilder.Query;

/**
 *
 * @author Diego
 * @param <T> O tipo do retorno do QueryBuilder
 */
public interface IQueryBuilderSorl<T extends Object> {
    
    String makeQuery(Query query);
    
}
