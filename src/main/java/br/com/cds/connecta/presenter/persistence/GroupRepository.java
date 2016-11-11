package br.com.cds.connecta.presenter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 
 * @author heloisa
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long>, 
					JpaSpecificationExecutor<Group> {
    
    @Query("SELECT g FROM Group g "
            + "LEFT JOIN FETCH g.singleSourceGroup sgg "
            + "LEFT JOIN FETCH sgg.singleSource k "
            + "WHERE g.id = :id")
    Group getByWithSingleSourceId(@Param("id") Long id);
    
    @Query("SELECT g FROM Group g "
            + "LEFT JOIN FETCH g.groupAttribute sgg "
            + "LEFT JOIN FETCH sgg.attribute k "
            + "WHERE g.id = :id")
    Group getByWithAttributeId(@Param("id") Long id);
    
    @Query("SELECT g FROM Group g "
            + "LEFT JOIN FETCH g.query q "
            + "WHERE g.id = :id")
    Group getByWithQueryId(@Param("id") Long id);
    
    @Query("SELECT g FROM Group g "
            + "INNER JOIN FETCH g.singleSourceGroup ssg "
            + "INNER JOIN FETCH ssg.singleSource sg "
            + "WHERE g.id = :id")
    Group getSingleSourceByGroupId(@Param("id") Long id);
    
}
