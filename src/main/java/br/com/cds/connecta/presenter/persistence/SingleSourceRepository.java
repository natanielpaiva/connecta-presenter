/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.SingleSource;

/**
 *
 * @author nataniel
 */
@Repository
public interface SingleSourceRepository extends JpaRepository<SingleSource, Long> ,
														JpaSpecificationExecutor<SingleSource>{

    
    /**
     *
     * @param name
     * @param pageable
     * @return
     */
    @Query("FROM SingleSource t WHERE UPPER(t.name) LIKE :name")
    Page<SingleSource> findByName(@Param("name") String name, Pageable pageable);
    
}
