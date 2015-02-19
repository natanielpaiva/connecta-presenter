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
import br.com.cds.connecta.presenter.entity.datasource.ITypedDatasource;
import br.com.cds.connecta.presenter.entity.datasource.SolrDatasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("datasource")
public class DatasourceController {

    @Autowired
    private IDatasourceAS service;

    @RequestMapping(
            value = "database",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ITypedDatasource> save(@RequestBody DatabaseDatasource datasource) {
        ITypedDatasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "endeca",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ITypedDatasource> save(@RequestBody EndecaDatasource datasource) {
        ITypedDatasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "bi",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ITypedDatasource> save(@RequestBody BIDatasource bi) {
        ITypedDatasource newDatasource = service.save(bi);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "solr",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ITypedDatasource> save(@RequestBody SolrDatasource datasource) {
        ITypedDatasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "webservice",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ITypedDatasource> save(@RequestBody WebserviceDatasource datasource) {

        ITypedDatasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "hdfs",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ITypedDatasource> save(@RequestBody HDFSDatasource datasource) {
        ITypedDatasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Datasource>> list(HttpServletRequest arg0,
            HttpServletResponse arg1) throws Exception {
        List<Datasource> list = service.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(
            value = "{id}",
            method = RequestMethod.GET
    )
    protected ResponseEntity<Datasource> get(@PathVariable("id") Long id) throws Exception {
        Datasource newDatasource = service.get(id);
        return new ResponseEntity<>(newDatasource, HttpStatus.OK);
    }

    @RequestMapping(
            value = "{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
