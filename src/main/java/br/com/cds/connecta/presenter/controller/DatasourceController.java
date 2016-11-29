package br.com.cds.connecta.presenter.controller;

import br.com.cds.connecta.presenter.bean.datasource.RestDatasourceResponse;
import br.com.cds.connecta.presenter.business.applicationService.IDatabaseAS;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cds.connecta.presenter.business.applicationService.IDatasourceAS;
import br.com.cds.connecta.presenter.business.applicationService.IRestAS;
import br.com.cds.connecta.presenter.entity.datasource.BIDatasource;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import br.com.cds.connecta.presenter.entity.datasource.EndecaDatasource;
import br.com.cds.connecta.presenter.entity.datasource.HDFSDatasource;
import br.com.cds.connecta.presenter.entity.datasource.RestDatasourceRequest;
import br.com.cds.connecta.presenter.entity.datasource.RestDatasource;
import br.com.cds.connecta.presenter.entity.datasource.SolrDatasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import br.com.cds.connecta.presenter.filter.DatasourceFilter;
import java.sql.SQLException;

@Controller
@RequestMapping("datasource")
public class DatasourceController {

    @Autowired
    private IDatasourceAS service;
    
    @Autowired
    private IDatabaseAS databaseService;
    
    @Autowired
    private IRestAS restService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Datasource>> list(DatasourceFilter filter,
            @RequestHeader("Domain") String domain) {
        filter.setDomain(domain);
        Iterable<Datasource> list = service.list(filter);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "database", method = RequestMethod.POST)
    public ResponseEntity<Datasource> save(@RequestBody DatabaseDatasource datasource) {
        Datasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "test-connection", method = RequestMethod.POST)
    public ResponseEntity<DatabaseDatasource> testConnection(@RequestBody DatabaseDatasource datasource) throws SQLException {
        databaseService.testConnection(datasource);
        return new ResponseEntity<>(datasource, HttpStatus.OK);
    }

    @RequestMapping(value = "endeca", method = RequestMethod.POST)
    public ResponseEntity<Datasource> save(@RequestBody EndecaDatasource datasource) {
        Datasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "obiee", method = RequestMethod.POST)
    public ResponseEntity<Datasource> save(@RequestBody BIDatasource bi) {
        Datasource newDatasource = service.save(bi);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "solr", method = RequestMethod.POST)
    public ResponseEntity<Datasource> save(@RequestBody SolrDatasource datasource) {
        Datasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "webservice", method = RequestMethod.POST)
    public ResponseEntity<Datasource> save(@RequestBody WebserviceDatasource datasource) {
        Datasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "hdfs", method = RequestMethod.POST)
    public ResponseEntity<Datasource> save(@RequestBody HDFSDatasource datasource) {
        Datasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Datasource> get(@PathVariable("id") Long id,
            @RequestHeader("Domain") String domain) {
        Datasource newDatasource = service.get(id, domain);
        return new ResponseEntity<>(newDatasource, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id,
            @RequestHeader("Domain") String domain) {
        service.delete(id, domain);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity bulkDelete(@RequestBody List<Long> ids,
            @RequestHeader("Domain") String domain) {
        service.deleteAll(ids, domain);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }
    
    
    @RequestMapping(value = "rest", method = RequestMethod.POST)
    public ResponseEntity<Datasource> save(@RequestBody RestDatasource datasource) {
        Datasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "send-request", method = RequestMethod.POST)
    public ResponseEntity<RestDatasourceResponse>  executeRestRequeste(@RequestBody RestDatasourceRequest request, @RequestHeader("Domain") String domain) {
        
        RestDatasourceResponse response = restService.executeRestRequest(request);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}/rest", method = RequestMethod.GET)
    public ResponseEntity<RestDatasource> getRestRequest(@PathVariable("id") Long id,
            @RequestHeader("Domain") String domain) {
        
        RestDatasource restDatasource = restService.getRestDatasource(id, domain);
        return new ResponseEntity<>(restDatasource, HttpStatus.OK);
    }

}
