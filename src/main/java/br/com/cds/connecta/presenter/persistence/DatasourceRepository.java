package br.com.cds.connecta.presenter.persistence;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DatasourceRepository extends JpaRepository<Datasource, Long> {
    
    
    
}
