/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.FileSingleSource;
import br.com.cds.connecta.presenter.entity.SingleSource;

/**
 *
 * @author nataniel
 */
@Repository
public interface SingleSourceRepository
		extends JpaRepository<SingleSource, Long>, JpaSpecificationExecutor<SingleSource> {

	/**
	 *
	 * @param name
	 * @param pageable
	 * @return
	 */
	@Query("FROM SingleSource t WHERE UPPER(t.name) LIKE :name")
	Page<SingleSource> findByName(@Param("name") String name, Pageable pageable);

	@Query("SELECT sg FROM SingleSource sg LEFT JOIN FETCH sg.singleSourceAttributes sa LEFT JOIN FETCH sa.attribute l WHERE sa.attribute.id = :id")
	List<SingleSource> getByAttributeId(@Param("id") long id);

	@Query("SELECT sg FROM SingleSource sg LEFT JOIN FETCH sg.singleSourceAttributes sa LEFT JOIN FETCH sa.attribute l WHERE sg.id = :id")
	SingleSource getWithAttributes(@Param("id") long id);
	
	@Query("SELECT s FROM SingleSource s WHERE id in(:ids)")
	List<FileSingleSource> getByIds(@Param("ids") List<Long> ids);

}
