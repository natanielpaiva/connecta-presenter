package br.com.cds.connecta.presenter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cds.connecta.presenter.business.applicationService.IDatasourceAS;
import br.com.cds.connecta.presenter.entity.datasource.BIDatasource;
import br.com.cds.connecta.presenter.entity.datasource.DatabaseDatasource;
import br.com.cds.connecta.presenter.entity.datasource.EndecaDatasource;
import br.com.cds.connecta.presenter.entity.datasource.HDFSDatasource;
import br.com.cds.connecta.presenter.entity.datasource.ITypedDatasource;
import br.com.cds.connecta.presenter.entity.datasource.SolrDatasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("datasource")
public class DatasourceController {

    @Autowired
    private IDatasourceAS service;
    
    @RequestMapping(
            value="database",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ITypedDatasource> save(@RequestBody DatabaseDatasource datasource) {
        ITypedDatasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }
    
    @RequestMapping(
        value="endeca",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ITypedDatasource> save(@RequestBody EndecaDatasource datasource) {
        ITypedDatasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }
     
    @RequestMapping(
        value="bi",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ITypedDatasource> save(@RequestBody BIDatasource bi) {
        ITypedDatasource newDatasource = service.save(bi);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }
     
    @RequestMapping(
            value="solr",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ITypedDatasource> save(@RequestBody SolrDatasource datasource) {
        ITypedDatasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }
    
     
    
    
     
    @RequestMapping(
            value="webservice",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ITypedDatasource> save(@RequestBody WebserviceDatasource datasource) {
        ITypedDatasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }
    
     
    @RequestMapping(
            value="hdfs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ITypedDatasource> save(HDFSDatasource datasource) {
        ITypedDatasource newDatasource = service.save(datasource);
        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
    }
    
    
//    @RequestMapping(
//        method = RequestMethod.POST,
//        produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ITypedDatasource> save(
//            @RequestBody BIDatasource datasource) throws Exception {
//        ITypedDatasource newDatasource = service.save(datasource);
//        return new ResponseEntity<>(newDatasource, HttpStatus.CREATED);
//    }

//    public ResponseEntity<Datasource> update(Long arg0, Datasource analysis, HttpServletRequest arg2,
//            HttpServletResponse arg3) throws Exception {
//        Datasource updatedDatasource = datasourceService.update(analysis);
//        return new ResponseEntity<>(updatedDatasource, HttpStatus.OK);
//    }
//
//    public void delete(Long id, HttpServletRequest arg1,
//            HttpServletResponse arg2) throws Exception {
//        datasourceService.delete(id);
//    }
//
//    public ResponseEntity<Datasource> get(Long id, HttpServletRequest arg1,
//            HttpServletResponse arg2) throws Exception {
//        Datasource analysis = datasourceService.get(id);
//        
//        return new ResponseEntity<>(analysis, HttpStatus.OK);
//    }
//
//    public ResponseEntity<List<Datasource>> list(HttpServletRequest arg0,
//            HttpServletResponse arg1) throws Exception {
//        List<Datasource> list = datasourceService.list();
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
}
