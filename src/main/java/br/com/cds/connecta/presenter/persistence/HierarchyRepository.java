package br.com.cds.connecta.presenter.persistence;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cds.connecta.presenter.entity.hierarchy.Hierarchy;

public interface HierarchyRepository extends JpaRepository<Hierarchy, Serializable> {

}
