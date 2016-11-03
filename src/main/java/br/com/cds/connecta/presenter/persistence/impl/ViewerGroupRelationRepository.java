package br.com.cds.connecta.presenter.persistence.impl;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.viewer.ViewerGroupRelation;

@Repository
public interface ViewerGroupRelationRepository extends JpaRepository<ViewerGroupRelation, Serializable> {

}
