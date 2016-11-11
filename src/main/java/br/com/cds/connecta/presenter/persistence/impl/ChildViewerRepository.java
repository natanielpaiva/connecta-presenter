package br.com.cds.connecta.presenter.persistence.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.viewer.ChildViewer;
@Repository
public interface ChildViewerRepository extends JpaRepository<ChildViewer, Long> {

}
