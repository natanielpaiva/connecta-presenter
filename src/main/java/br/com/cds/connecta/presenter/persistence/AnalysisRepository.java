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
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisRelation;
import java.util.List;

/**
 *
 * @author Nataniel Paiva
 */
@Repository
@Component
public interface AnalysisRepository extends JpaRepository<Analysis, Serializable>, JpaSpecificationExecutor<Analysis> {

	@Query("FROM Analysis a WHERE UPPER(a.name) LIKE :name")
	Page<Analysis> findByName(@Param("name") String name, Pageable pageable);

	@Query("SELECT c FROM Analysis a JOIN a.analysisColumns c WHERE c.id LIKE :filterId")
	AnalysisColumn findColumnById(@Param("filterId") Long filterId);

	@Query("SELECT r FROM Analysis a JOIN a.analysisRelations r" + " LEFT JOIN FETCH r.leftAnalysisColumn"
			+ " LEFT JOIN FETCH r.rightAnalysis" + " LEFT JOIN FETCH r.rightAnalysisColumn" + " WHERE a.id LIKE :id")
	List<AnalysisRelation> findRelationsById(@Param("id") Long id);

	@Query("SELECT t FROM Analysis t " 
			+ "INNER JOIN FETCH t.analysisColumns a " 
			+ "LEFT JOIN FETCH t.datasource d WHERE a.id = :id ")
	Analysis getByIdColumns(@Param("id") Long id);

}
