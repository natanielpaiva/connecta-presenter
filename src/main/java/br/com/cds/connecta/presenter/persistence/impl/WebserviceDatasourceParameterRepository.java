package br.com.cds.connecta.presenter.persistence.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasourceParameter;

@Repository
public interface WebserviceDatasourceParameterRepository
		extends JpaRepository<WebserviceDatasourceParameter, Serializable> {

	@Query("FROM WebserviceDatasourceParameter p WHERE datasource = :ds")
	public List<WebserviceDatasourceParameter> findAllByWebserviceDatasource(WebserviceDatasource ds);

}
