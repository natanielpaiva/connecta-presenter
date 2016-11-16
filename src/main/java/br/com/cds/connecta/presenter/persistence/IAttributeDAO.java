package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.presenter.entity.Attribute;
import java.io.Serializable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttributeDAO extends JpaRepository<Attribute, Serializable> {

//    @Query(name = "Attribute.findByName")
    @Query("FROM Attribute t WHERE UPPER(t.name) LIKE :name")
    public Page<Attribute> findByName(@Param("name") String name, Pageable pageable);

}
