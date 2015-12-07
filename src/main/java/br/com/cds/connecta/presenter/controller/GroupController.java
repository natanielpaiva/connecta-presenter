package br.com.cds.connecta.presenter.controller;

import br.com.cds.connecta.framework.core.controller.AbstractBaseController;
import br.com.cds.connecta.presenter.business.applicationService.IGroupAS;
import br.com.cds.connecta.presenter.business.applicationService.IQueryAS;
import br.com.cds.connecta.presenter.business.builder.IQueryBuilder;
import br.com.cds.connecta.presenter.entity.Group;
import br.com.cds.connecta.presenter.entity.querybuilder.Query;
import br.com.cds.connecta.presenter.entity.SingleSource;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 
 * @author Nataniel Paiva
 */
@Controller
@RequestMapping("group")
public class GroupController extends AbstractBaseController<Group> {

    @Autowired
    private IGroupAS groupService;
    
    @Autowired
    private IQueryBuilder builder;
    
    @Autowired
    private IQueryAS queryService;
    
    @RequestMapping(value = "query", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Query> saveQuery(@RequestBody Query query) {
        
        Query save = queryService.save(query);
        
        return new ResponseEntity<>(save, HttpStatus.OK);
    }
    
    @RequestMapping(value = "query/{id}", method = RequestMethod.GET)
    public ResponseEntity<Query> getQueryById(@PathVariable("id") Long id) {
        
        Query query = queryService.saveGetById(id);
        
        return new ResponseEntity<>(query, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "query/result", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getResultsForQuery(@RequestBody Query query) {
        List results = queryService.getSingleSourceByIds(query, null);
        
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
    
    @RequestMapping(value = "query/preview", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/sql")
    public ResponseEntity<String> getPreviewForQuery(
            @RequestBody Query query,
            @RequestParam(value="split", required = false) Boolean split) {
        return new ResponseEntity<>(builder.sqlFor(query, split, SingleSource.class), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Group> get(Long id, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        Group group = groupService.get(id);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<List<Group>> list(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        List<Group> list = groupService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @RequestMapping("single-source/{id}")
    protected ResponseEntity<Group> getSingleSourceByGroupId(@PathVariable("id") Long id){
    
        Group group = groupService.getSingleSourceByGroupId(id);
        
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Group> save(Group group, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        Group newGroup;
        newGroup = groupService.saveOrUpdate(group);
        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<Group> createWithUpload(MultipartHttpServletRequest multipartRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ResponseEntity<Group> update(Long id, Group group,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Group updateGroup = groupService.saveOrUpdate(group);
        return new ResponseEntity<>(updateGroup, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Group> updateWithUpload(Long id, MultipartHttpServletRequest multipartRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void delete(Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        groupService.delete(id);
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity bulkDelete(@RequestBody List<Long> ids) {
        groupService.deleteAll(ids);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
