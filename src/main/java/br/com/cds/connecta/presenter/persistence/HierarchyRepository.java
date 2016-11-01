package br.com.cds.connecta.presenter.persistence;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cds.connecta.presenter.entity.hierarchy.Hierarchy;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HierarchyRepository extends JpaRepository<Hierarchy, Serializable> {
    
    @Modifying
    @Query("DELETE FROM Hierarchy WHERE id IN (:ids)")
    void bulkDeleteRecords(@Param("ids") List<Long> ids);

}
