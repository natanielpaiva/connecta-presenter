/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.presenter.business.applicationService.IHierarchyItemAS;
import br.com.cds.connecta.presenter.entity.hierarchy.HierarchyItem;
import br.com.cds.connecta.presenter.persistence.IHierarchyItemDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class HierarchyItemAS implements IHierarchyItemAS {

    @Autowired
    private IHierarchyItemDAO daoItem;

    @PersistenceContext
    EntityManager em;

    /**
     *
     * Retorna os filhos de uma HierarchyItem
     * @param id Long
     * @return HiearachyItem
     */
    @Override
    public List<HierarchyItem> getChildItems(Long id) {
        HierarchyItem item = em.find(HierarchyItem.class, id);
        Hibernate.initialize(item.getHierarchyItem());

        return item.getHierarchyItem();
    }

    /**
     * Salva uma HierarchyItem
     * @param item
     * @param idItemParent
     * @return HierarchyItem
     */
    @Override
    public HierarchyItem saveItem(HierarchyItem item, long idItemParent) {
        if (item.getId() == null) {
            HierarchyItem itemPai = daoItem.get(idItemParent);
            List<HierarchyItem> hierarchyItem = itemPai.getHierarchyItem();
            hierarchyItem.add(item);
        } else {
            daoItem.saveOrUpdate(item);
        }
        return item;
    }

    /**
     * Realiza Update em um item da HierarchyItem
     * @param item
     * @return HierarchyItem
     */
    @Override
    public HierarchyItem updateItem(HierarchyItem item) {

        HierarchyItem newItem = em.find(HierarchyItem.class, item.getId());
        newItem.setName(item.getName());
        newItem.setFormula(item.getFormula());
        em.persist(newItem);
        return newItem;
    }

    /**
     * Delete um item de uma HierarchyItem em cascata 
     * @param id Long
     */
    @Override
    public void delete(Long id) {
        daoItem.delete(id);
    }

}
