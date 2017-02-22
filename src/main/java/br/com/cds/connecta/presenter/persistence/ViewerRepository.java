package br.com.cds.connecta.presenter.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.viewer.Viewer;

@Repository
public interface ViewerRepository extends JpaRepository<Viewer, Long>,
        JpaSpecificationExecutor<Viewer> {

	@Query("FROM Viewer WHERE  isActive = 1 and analysis = :analysis")
	List<Viewer> findByAnalysis(@Param("analysis")Analysis analysis);

}
