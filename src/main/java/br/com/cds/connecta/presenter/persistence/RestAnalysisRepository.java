package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.presenter.entity.analysis.RestAnalysis;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public interface RestAnalysisRepository extends JpaRepository<RestAnalysis, Serializable>, JpaSpecificationExecutor<RestAnalysis>{

}

