package br.com.cds.connecta.presenter.persistence.list;

import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import java.io.Serializable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nataniel Paiva
 */
@Repository
public interface AnalysisListRepository extends JpaRepository<Analysis, Serializable> {
    
    @Query("FROM Analysis a WHERE UPPER(a.name) LIKE :name")
    public Page<Analysis> findByName(@Param("name") String name, Pageable pageable);
    
}
