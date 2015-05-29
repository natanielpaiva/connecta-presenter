package br.com.cds.connecta.presenter.controller;

import br.com.cds.connecta.presenter.bean.analysis.BIAnalysisCatalogPathName;
import br.com.cds.connecta.framework.connector.soap.service.Param;
import br.com.cds.connecta.framework.connector.soap.service.Parameters;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.cds.connecta.presenter.business.applicationService.IAnalysisAS;
import br.com.cds.connecta.presenter.business.applicationService.IDatabaseAS;
import br.com.cds.connecta.presenter.business.applicationService.IEndecaAS;
import br.com.cds.connecta.presenter.business.applicationService.IObieeAS;
import br.com.cds.connecta.presenter.business.applicationService.ISoapAS;
import br.com.cds.connecta.presenter.business.applicationService.ISolr;
import br.com.cds.connecta.presenter.entity.Analysis;
import br.com.cds.connecta.presenter.entity.AnalysisColumn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("analysis")
public class AnalysisController {

    @Autowired
    private IAnalysisAS analysisService;

    @Autowired
    private IDatabaseAS DatabaseService;
    
    @Autowired
    private ISolr SolrService;
    
    @Autowired
    private IObieeAS ObieeService;
    
    @Autowired
    private IEndecaAS EndecaService;
    
    @Autowired
    private ISoapAS SoapService;
    

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    protected ResponseEntity<Analysis> save(@RequestBody Analysis analysis) {
        Analysis newAnalysis = analysisService.saveOrUpdate(analysis);
        return new ResponseEntity<>(newAnalysis, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    protected ResponseEntity<Analysis> update(@PathVariable Long id, @RequestBody Analysis analysis) {
        analysis.setId(id);
        Analysis updatedAnalysis = analysisService.saveOrUpdate(analysis);
        return new ResponseEntity<>(updatedAnalysis, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    protected void delete(@PathVariable("id") Long id) {
        analysisService.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    protected ResponseEntity<Analysis> get(Long id) {
        Analysis analysis = analysisService.get(id);
        return new ResponseEntity<>(analysis, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<List<Analysis>> list() {
        List<Analysis> list = analysisService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //lista colunas do sorl
    @RequestMapping(value = "{id}/columns-sorl", method = RequestMethod.GET)
    public ResponseEntity<List<AnalysisColumn>> getColumnsSorl(
            @PathVariable Long id) {
        List<AnalysisColumn> list = SolrService.getColumns(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //lista tabelas e colunas de banco de dados
    @RequestMapping(value = "{id}/columns-datasource", method = RequestMethod.GET)
    public ResponseEntity<List> getColumnsDatasource(
            @PathVariable Long id) {
        List list = DatabaseService.getTables(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    //retorna a análises e suas respectivas colunas
    @RequestMapping(value = "{id}/analysis-columns", method = RequestMethod.GET)
    public ResponseEntity<Analysis> getAnalysisColumns(
            @PathVariable Long id) {
        Analysis analysis = analysisService.getByIdColumns(id);
        return new ResponseEntity<>(analysis, HttpStatus.OK);
    }
    
    //lista colunas de um select
    @RequestMapping(value = "{id}/sql-view-columns", method = RequestMethod.GET)
    public ResponseEntity<List> getSqlViewColumns(
            @PathVariable Long id) {
        List list = DatabaseService.getTables(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //lista catalog do obiee
    @RequestMapping(value = "{id}/catalog-obiee", method = RequestMethod.POST)
    public ResponseEntity<List> getFolderObiee(
            @PathVariable Long id,
            @RequestBody BIAnalysisCatalogPathName path) {
        List catalog = ObieeService.getCatalog(id, path.getPath());
        return new ResponseEntity<>(catalog, HttpStatus.OK);
    }
    
   // lista tabelas e colunas do obiee
    @RequestMapping(value = "{id}/columns-obiee", method = RequestMethod.POST)
    public ResponseEntity<List<AnalysisColumn>> getColumnsObiee(
            @PathVariable Long id,
            @RequestBody BIAnalysisCatalogPathName path) {
        List<AnalysisColumn> list  = ObieeService.getColumns(id, path.getPath());
        return new ResponseEntity<>(list,  HttpStatus.OK);
    }
    
    //lista Dominios do Endeca
    @RequestMapping(value = "{id}/domains-endeca", method = RequestMethod.GET)
    public ResponseEntity<List> getDomainsEndeca(
            @PathVariable Long id) {
            List domains = EndecaService.getDomains(id);
        return new ResponseEntity<>(domains, HttpStatus.OK);
    }
    
    //lista colunas do Endeca
    @RequestMapping(value = "{id}/columns-endeca/{domain}", method = RequestMethod.GET)
    public ResponseEntity<List<AnalysisColumn>> getCalumnsEndeca(
            @PathVariable Long id,
            @PathVariable String domain) {
            List<AnalysisColumn> domains = EndecaService.getColumns(id, domain);
        return new ResponseEntity<>(domains, HttpStatus.OK);
    }
    
    //lista metodos(Operation) do soap
    @RequestMapping(value = "{id}/method-soap", method = RequestMethod.GET)
    public ResponseEntity<List> getMethodSoap(
            @PathVariable Long id) {
            List soapColumns = SoapService.getMethodsSoap(id);
        return new ResponseEntity<>(soapColumns, HttpStatus.OK);
    }
    
    //lista metodos(Operation) do soap
    @RequestMapping(value = "{id}/columns-soap/operation/{operation}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getColumnsSoap(
            @PathVariable Long id,
            @PathVariable String operation,
            @RequestBody List<Parameters> parameters) {
            //System.err.println("-----------");parameters
            
            String soapColumns = SoapService.getColumnsSoap(id, operation, parameters);
        return new ResponseEntity<>(soapColumns, HttpStatus.OK);
    }

}
