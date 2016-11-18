package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.presenter.entity.datasource.RestDatasource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public interface RestDatasourceRepository extends JpaRepository<RestDatasource, Long>,
        JpaSpecificationExecutor<RestDatasource> {
}