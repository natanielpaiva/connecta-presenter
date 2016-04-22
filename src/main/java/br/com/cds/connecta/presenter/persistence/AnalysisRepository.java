package br.com.cds.connecta.presenter.persistence;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;

/**
 *
 * @author Nataniel Paiva
 */
@Repository
@Component
public interface AnalysisRepository extends JpaRepository<Analysis, Serializable>,
														JpaSpecificationExecutor<Analysis>{
    
    @Query("FROM Analysis a WHERE UPPER(a.name) LIKE :name")
    Page<Analysis> findByName(@Param("name") String name, Pageable pageable);
    
}
