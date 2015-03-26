package br.com.cds.connecta.presenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cds.connecta.presenter.business.applicationService.IDatasourceAS;
import br.com.cds.connecta.presenter.entity.datasource.BIDatasource;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import br.com.cds.connecta.presenter.entity.datasource.EndecaDatasource;
import br.com.cds.connecta.presenter.entity.datasource.HDFSDatasource;
import br.com.cds.connecta.presenter.entity.datasource.SolrDatasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import br.com.cds.connecta.presenter.filter.DatasourceFilter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("datasource")
public class DatasourceController {

    @Autowired
    private IDatasourceAS service;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Datasource>> list(DatasourceFilter filter) {
        Iterable<Datasource> list = service.list(filter);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "database", method = RequestMethod.POST)
    public ResponseEntity<Datasource> save(@RequestBody DatabaseDatasource datasource) {
        Datasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "endeca", method = RequestMethod.POST)
    public ResponseEntity<Datasource> save(@RequestBody EndecaDatasource datasource) {
        Datasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "bi", method = RequestMethod.POST)
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
        Datasource newDatasource = service.saveWebservice(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "hdfs", method = RequestMethod.POST)
    public ResponseEntity<Datasource> save(@RequestBody HDFSDatasource datasource) {
        Datasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Datasource> get(@PathVariable("id") Long id) {
        Datasource newDatasource = service.get(id);
        return new ResponseEntity<>(newDatasource, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
