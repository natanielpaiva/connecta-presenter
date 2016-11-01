package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.presenter.entity.FileSingleSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.SingleSource;
import java.util.List;

/**
 * 
 * @author heloisa
 */
@Repository
public interface SingleSourceRepository extends JpaRepository<SingleSource, Long> ,
                                                JpaSpecificationExecutor<SingleSource>{

    
    @Query("FROM SingleSource t WHERE UPPER(t.name) LIKE :name")
    Page<SingleSource> findByName(@Param("name") String name, Pageable pageable);
    
    @Query("SELECT sg FROM SingleSource sg "
            + "LEFT JOIN FETCH sg.singleSourceAttributes sa "
            + "LEFT JOIN FETCH sa.attribute l WHERE sa.attribute.id = :id")
    List<SingleSource> getByAttributeId(@Param("id") Long id);
    
    @Query("SELECT sg FROM SingleSource sg "
            + "LEFT JOIN FETCH sg.singleSourceAttributes sa "
            + "LEFT JOIN FETCH sa.attribute l WHERE sg.id = :id")
    SingleSource getWithAttributes(@Param("id") Long id);
    
    @Query("SELECT s FROM SingleSource s WHERE id in(:ids)")
    List<FileSingleSource> getByIds(@Param("ids") List<Long> ids);
    
}
