package br.com.cds.connecta.presenter.persistence;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasourceParameter;

public interface WebserviceDatasourceParameterRepository
		extends JpaRepository<WebserviceDatasourceParameter, Serializable> {

	@Query("FROM WebserviceDatasourceParameter p WHERE datasource = :ds")
	List<WebserviceDatasourceParameter> findAllByWebserviceDatasource(WebserviceDatasource wds);

}
