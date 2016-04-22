package br.com.cds.connecta.presenter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.Group;
import br.com.cds.connecta.presenter.entity.datasource.Datasource;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>, 
													JpaSpecificationExecutor<Group> {
    
}
