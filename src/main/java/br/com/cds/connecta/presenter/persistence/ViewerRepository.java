package br.com.cds.connecta.presenter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.viewer.Viewer;

@Repository
public interface ViewerRepository extends JpaRepository<Viewer, Long>,
        JpaSpecificationExecutor<Viewer> {

}
