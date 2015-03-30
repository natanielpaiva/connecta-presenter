package br.com.cds.connecta.presenter.persistence.impl;

import br.com.cds.connecta.presenter.entity.Attribute;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.SingleSource;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface SingleSourceDAO extends JpaRepository<SingleSource, Long>{

    
    @Query(name = "SingleSource.getById")
    SingleSource getWithAttributes(@Param("id") Long id);
    
    @Query(name = "SingleSource.getByAttributeId")
    List<SingleSource> getByAttributeId(@Param("id") Long id);
    
    /**
     *
     * @param name
     * @param pageable
     * @return
     */
    @Query("FROM SingleSource t WHERE UPPER(t.name) LIKE :name")
    Page<SingleSource> findByName(@Param("name") String name, Pageable pageable);

}
