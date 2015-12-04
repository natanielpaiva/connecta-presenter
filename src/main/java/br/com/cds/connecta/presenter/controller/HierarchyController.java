package br.com.cds.connecta.presenter.controller;

import br.com.cds.connecta.presenter.business.applicationService.IHierarchyAS;
import br.com.cds.connecta.presenter.business.applicationService.IHierarchyItemAS;
import br.com.cds.connecta.presenter.entity.hierarchy.Hierarchy;
import br.com.cds.connecta.presenter.entity.hierarchy.HierarchyItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author diego
 */
@Controller
@RequestMapping("hierarchy")
public class HierarchyController {

    @Autowired
    private IHierarchyAS service;

    @Autowired
    private IHierarchyItemAS hierarchyItemService;

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    protected @ResponseBody
    ResponseEntity<Hierarchy> save(@RequestBody Hierarchy hierarchy) {
        Hierarchy newHierarchy = service.saveOrUpdate(hierarchy);
        return new ResponseEntity<>(newHierarchy, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Hierarchy>> get(@PathVariable("id") Long id) {
        Hierarchy hierarchy = service.get(id);
        return new ResponseEntity(hierarchy, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Hierarchy>> list() {
        List<Hierarchy> list = service.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @RequestMapping(
            value = "{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "save-hierarchy-item/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    protected @ResponseBody
    ResponseEntity<HierarchyItem> saveItem(
            @RequestBody HierarchyItem hierarchyItem,
            @PathVariable("id") Long idItemParent) {
        HierarchyItem newHierarchyItem = hierarchyItemService.saveItem(hierarchyItem, idItemParent);
        return new ResponseEntity<>(newHierarchyItem, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "updade-hierarchy-item/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    protected @ResponseBody
    ResponseEntity<HierarchyItem> updateItem(@RequestBody HierarchyItem hierarchyItem) {
        HierarchyItem newHierarchyItem = hierarchyItemService.updateItem(hierarchyItem);
        return new ResponseEntity<>(newHierarchyItem, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "hierarchy-item/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<List<HierarchyItem>> getItems(@PathVariable("id") Long id) {
        List<HierarchyItem> hierarchy = hierarchyItemService.getChildItems(id);
        return new ResponseEntity(hierarchy, HttpStatus.OK);
    }

    @RequestMapping(
            value = "excluir-hierarchy-item/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    protected ResponseEntity excluirHierarchyItem(@PathVariable("id") long id) {
        hierarchyItemService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity bulkDelete(@RequestBody List<Long> ids) {
        service.deleteAll(ids);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
