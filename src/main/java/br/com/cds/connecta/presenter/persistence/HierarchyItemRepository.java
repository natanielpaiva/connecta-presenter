package br.com.cds.connecta.presenter.persistence;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cds.connecta.presenter.entity.hierarchy.HierarchyItem;

public interface HierarchyItemRepository  extends JpaRepository<HierarchyItem, Serializable>{

}
