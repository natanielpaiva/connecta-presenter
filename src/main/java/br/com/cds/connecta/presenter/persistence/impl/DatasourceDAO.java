package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DatasourceDAO extends JpaRepository<Datasource, Long> {
    
}
