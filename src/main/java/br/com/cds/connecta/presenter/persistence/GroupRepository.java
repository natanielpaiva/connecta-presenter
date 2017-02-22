package br.com.cds.connecta.presenter.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>, JpaSpecificationExecutor<Group>  {

	List<Group> findAll();

	@Query("SELECT g FROM Group g " + "LEFT JOIN FETCH g.singleSourceGroup sgg " + "LEFT JOIN FETCH sgg.singleSource k "
			+ "WHERE g.id = :id")
	Group getSingleSourceByGroupId(Long id);

	@Query("SELECT g FROM Group g " + "INNER JOIN FETCH g.singleSourceGroup ssg "
			+ "INNER JOIN FETCH ssg.singleSource sg " + "WHERE g.id = :id")
	Group getSingleSourceByGroupId(long id);

}
