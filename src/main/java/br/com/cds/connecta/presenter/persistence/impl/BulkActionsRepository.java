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
    
    /**
     * Remove todas as entidades da classe informada que contém os
     * identificadores informados.
     * 
     * @param entityClass A Classe da entidade a realizar a exclusão em massa
     * @param ids A lista de identificadores a serem removidos
     * @param idFieldName O nome do campo identificador
     */
    public void delete(Class<?> entityClass, List<T> ids, String idFieldName) {
        Query query = em.createQuery(
            String.format(DELETE_QUERY, entityClass.getName(), idFieldName)
        );
        
        query.setParameter("ids", ids);
        
        query.executeUpdate();
    }
    
    /**
     * Atalho para o método delete utilizando o padrão de nome de campo de
     * identificador como "id"
     * 
     * @param entityClass A Classe da entidade a realizar a exclusão em massa
     * @param ids A lista de identificadores a serem removidos
     */
    public void delete(Class<?> entityClass, List<T> ids) {
        delete(entityClass, ids, "id");
    }
}
