package br.com.cds.connecta.presenter.persistence.impl;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.GroupAttribute;
@Repository
public interface GroupAttributeRepository extends JpaRepository<GroupAttribute, Serializable> {

}
