package br.com.cds.connecta.presenter.persistence.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
@Repository
public class BulkActionsRepository <T> {
    
    private final String DELETE_QUERY = "DELETE FROM %s WHERE %s IN (:ids)";
    
    @PersistenceContext
    private EntityManager em;
    
    public void delete(Class<?> entityClass, String idFieldName, List<T> ids) {
        Query query = em.createQuery(
            String.format(DELETE_QUERY, entityClass.getName(), idFieldName)
        );
        
        query.setParameter("ids", ids);
        
        query.executeUpdate();
    }
}
