package br.com.cds.connecta.presenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cds.connecta.presenter.business.applicationService.IGroupAS;
import br.com.cds.connecta.presenter.business.applicationService.IQueryAS;
import br.com.cds.connecta.presenter.business.builder.IQueryBuilder;
import br.com.cds.connecta.presenter.entity.Group;
import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.querybuilder.Query;

/**
 * 
 * @author Nataniel Paiva
 */
@Controller
@RequestMapping("group")
public class GroupController {

    @Autowired
    private IGroupAS groupService;
    
    @Autowired
    private IQueryBuilder<SingleSource> builder;
    
    @Autowired
    private IQueryAS queryService;
    
    @RequestMapping(value = "/{id}", 
    		method = RequestMethod.GET, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Group> get(@PathVariable Long id,
    		@RequestHeader("Domain") String domain){
        Group group = groupService.get(id,domain);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Group>> 
    		list(@RequestHeader("Domain") String domain) {
        List<Group> list = groupService.list(domain);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Group> save(@RequestBody Group group) {
        Group newGroup;
        newGroup = groupService.save(group);
        return new ResponseEntity<Group>(newGroup, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", 
    		method = RequestMethod.PUT, 
    		produces = MediaType.APPLICATION_JSON_VALUE, 
    		consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Group> update(@PathVariable Long id,
    		@RequestBody Group group) {
        Group updateGroup = groupService.update(group);
        return new ResponseEntity<Group>(updateGroup, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", 
    		method = RequestMethod.DELETE, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Group> delete(@PathVariable Long id,
    		@RequestHeader("Domain") String domain) {
        groupService.delete(id,domain);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Group> bulkDelete(@RequestBody List<Long> ids,
    		@RequestHeader("Domain") String domain) {
        groupService.deleteAll(ids,domain);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "query", method = RequestMethod.POST, 
    		consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Query> saveQuery(@RequestBody Query query) {
        
        Query save = queryService.save(query);
        
        return new ResponseEntity<>(save, HttpStatus.OK);
    }
    
    @RequestMapping(value = "query/{id}", method = RequestMethod.GET)
    public ResponseEntity<Query> getQueryById(@PathVariable("id") Long id) {
        
        Query query = queryService.saveGetById(id);
        
        return new ResponseEntity<>(query, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "query/result", method = RequestMethod.POST, 
    		consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getResultsForQuery(@RequestBody Query query) {
        List<?> results = queryService.getSingleSourceByIds(query, null);
        
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
    
    @RequestMapping(value = "query/preview", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/sql")
    public ResponseEntity<String> getPreviewForQuery(
            @RequestBody Query query,
            @RequestParam(value="split", required = false) Boolean split) {
        return new ResponseEntity<>(builder.sqlFor(query, split, SingleSource.class), HttpStatus.OK);
    }

    @RequestMapping("single-source/{id}")
    public ResponseEntity<Group> getSingleSourceByGroupId(@PathVariable("id") Long id){
        Group group = groupService.getSingleSourceByGroupId(id);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

}
