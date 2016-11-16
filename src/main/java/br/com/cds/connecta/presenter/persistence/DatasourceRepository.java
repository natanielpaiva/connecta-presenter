package br.com.cds.connecta.presenter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.datasource.Datasource;

@Repository
public interface DatasourceRepository extends JpaRepository<Datasource, Long>,
        JpaSpecificationExecutor<Datasource> {

}
