package br.com.cds.connecta.presenter.controller;

import br.com.cds.connecta.presenter.bean.analysis.obiee.BIAnalysisCatalogPathName;
import br.com.cds.connecta.framework.connector.soap.service.Parameters;
import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.bean.analysis.json.JSONValue;
import br.com.cds.connecta.presenter.bean.analysis.json.JSONValueParser;
import br.com.cds.connecta.presenter.bean.analysis.xml.XMLNodeParser;
import br.com.cds.connecta.presenter.bean.analysis.xml.XMLNode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.cds.connecta.presenter.business.applicationService.IAnalysisAS;
import br.com.cds.connecta.presenter.business.applicationService.ICsvAS;
import br.com.cds.connecta.presenter.business.applicationService.IDatabaseAS;
import br.com.cds.connecta.presenter.business.applicationService.IEndecaAS;
import br.com.cds.connecta.presenter.business.applicationService.IObieeAS;
import br.com.cds.connecta.presenter.business.applicationService.IRestAS;
import br.com.cds.connecta.presenter.business.applicationService.ISoapAS;
import br.com.cds.connecta.presenter.business.applicationService.ISolr;
import br.com.cds.connecta.presenter.business.applicationService.dataExtractor.IDataExtractorAS;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.analysis.CsvAnalysis;
import br.com.cds.connecta.presenter.entity.analysis.WebserviceAnalysis;
import br.com.cds.connecta.presenter.entity.querybuilder.Query;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import br.com.cds.connecta.presenter.filter.AnalysisFilter;
import javax.xml.transform.dom.DOMSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("analysis")
public class AnalysisController {

    @Autowired
    private IAnalysisAS analysisService;

    @Autowired
    private IDatabaseAS databaseService;

    @Autowired
    private ISolr solrService;

    @Autowired
    private IObieeAS obieeService;

    @Autowired
    private IEndecaAS endecaService;

    @Autowired
    private ISoapAS soapService;

    @Autowired
    private IRestAS restService;
    
    @Autowired
    private ICsvAS csvService;
    
    @Autowired
    private IDataExtractorAS extractor;

    private final JSONValueParser parser = new JSONValueParser();

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
    protected ResponseEntity<Analysis> 
    				update(@PathVariable Long id, @RequestBody Analysis analysis) {
        analysis.setId(id);
        Analysis updatedAnalysis = analysisService.saveOrUpdate(analysis);
        return new ResponseEntity<>(updatedAnalysis, HttpStatus.OK);
    }

    @RequestMapping(
            value = "{id}",
            method = RequestMethod.DELETE)
    protected ResponseEntity delete(@PathVariable("id") Long id,
    		@RequestHeader("Domain") String domain) {
        analysisService.delete(id,domain);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    protected ResponseEntity<Analysis> get(@PathVariable("id") Long id,
    		@RequestHeader("Domain") String domain) {
        Analysis analysis = analysisService.get(id, domain);
        return new ResponseEntity<>(analysis, HttpStatus.OK);
    }
    

    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<Iterable<Analysis>> list(AnalysisFilter filter,
    		@RequestHeader("Domain") String domain) {
    	filter.setDomain(domain);
        Iterable<Analysis> list = analysisService.list(filter);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //lista colunas do sorl
    @RequestMapping(value = "{id}/columns-sorl", method = RequestMethod.GET)
    public ResponseEntity<List<AnalysisColumn>> getColumnsSorl(
            @PathVariable Long id) {
        List<AnalysisColumn> list = solrService.getColumns(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //lista tabelas e colunas de banco de dados
    @RequestMapping(value = "{id}/columns-datasource", method = RequestMethod.GET)
    public ResponseEntity<List> getColumnsDatasource(
            @PathVariable Long id) {
        List list = databaseService.getTables(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * Executa Análises de acordo com o AnalysisExecuteRequest
     * 
     * @param analysisExecuteRequest
     * @return 
     */
    @RequestMapping(value = "result", method = RequestMethod.POST)
    public ResponseEntity<List<Map<String, Object>>> getResult(@RequestBody AnalysisExecuteRequest analysisExecuteRequest) {
        List<Map<String, Object>> result = extractor.executeAnalysis(analysisExecuteRequest);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
//    /**
//     * Executa uma análise de banco de dados
//     * 
//     * FIXME APAGAR - Nao precisa de ID do datasource na URL pra funcionar, apenas no json
//     * @param id
//     * @param databaseAnalysis
//     * @return 
//     */
//    @RequestMapping(value = "{id}/execute-sql", method = RequestMethod.POST)
//    public ResponseEntity getResultSql(
//            @PathVariable Long id,
//            @RequestBody DatabaseAnalysis databaseAnalysis) {
//        List<Map<String, Object>> dataSql = databaseService.getDataSql(databaseAnalysis);
//        return new ResponseEntity<>(dataSql, HttpStatus.OK);
//    }
    
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
        List list = databaseService.getTables(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //lista catalog do obiee
    @RequestMapping(value = "{id}/catalog-obiee", method = RequestMethod.POST)
    public ResponseEntity<List> getFolderObiee(
            @PathVariable Long id,
            @RequestBody BIAnalysisCatalogPathName path) {
        List catalog = obieeService.getCatalog(id, path.getPath());
        return new ResponseEntity<>(catalog, HttpStatus.OK);
    }

    // lista tabelas e colunas do obiee
    @RequestMapping(value = "{id}/columns-obiee", method = RequestMethod.POST)
    public ResponseEntity<List<AnalysisColumn>> getColumnsObiee(
            @PathVariable Long id,
            @RequestBody BIAnalysisCatalogPathName path) {
        List<AnalysisColumn> list = obieeService.getColumns(id, path.getPath());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //lista Dominios do Endeca
    @RequestMapping(value = "{id}/domains-endeca", method = RequestMethod.GET)
    public ResponseEntity<List> getDomainsEndeca(
            @PathVariable Long id) {
        List domains = endecaService.getDomains(id);
        return new ResponseEntity<>(domains, HttpStatus.OK);
    }

    //lista colunas do Endeca
    @RequestMapping(value = "{id}/columns-endeca/{domain}", method = RequestMethod.GET)
    public ResponseEntity<List<AnalysisColumn>> getColumnsEndeca(
            @PathVariable Long id,
            @PathVariable String domain) {
        List<AnalysisColumn> domains = endecaService.getColumns(id, domain);
        return new ResponseEntity<>(domains, HttpStatus.OK);
    }

    //lista metodos(Operation) do soap
    @RequestMapping(value = "{id}/method-soap", method = RequestMethod.GET)
    public ResponseEntity<List> getMethodSoap(
            @PathVariable Long id) {
        List soapColumns = soapService.getMethodsSoap(id);
        return new ResponseEntity<>(soapColumns, HttpStatus.OK);
    }

    //Retona a resposta do Soap no formato json
    @RequestMapping(value = "{id}/soap/operation/{operation}", 
    		method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<XMLNode> getSoap(
            @PathVariable Long id,
            @PathVariable String operation,
            @RequestBody List<Parameters> parameters) {

        DOMSource xmlSoap = soapService.getDOMSourceSoap(id, operation, parameters);

        //Converte o DOMSource para um padrao que o Jackson possa entender
        XMLNodeParser parseBeanNode = new XMLNodeParser();
        XMLNode parseNode = parseBeanNode.parseNode(xmlSoap.getNode());

        return new ResponseEntity<>(parseNode, HttpStatus.OK);
    }

    //Retona o soap aplicando os xml
    @RequestMapping(value = "{id}/soap-applying-xpath/operation/{operation}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSoapApplyingXpath(
            @PathVariable Long id,
            @PathVariable String operation,
            @RequestBody WebserviceAnalysis ws) {

        List<Map<String, Object>> resultXmlXpath = soapService.getResultXmlXpath(id, ws);

        return new ResponseEntity<>(resultXmlXpath, HttpStatus.OK);
    }

    @RequestMapping(value = "autocomplete", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    protected ResponseEntity<Iterable<Analysis>> listAutoComplete(AnalysisFilter filter,
    		@RequestHeader("Domain") String domain) {
    	filter.setDomain(domain);
    	
        Page<Analysis> list = analysisService.listAutoComplete(filter);
        Iterable<Analysis> content = list.getContent();

        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    //Retorna json de Rest via get
    @RequestMapping(value = "{id}/get-rest", method = RequestMethod.GET)
    public ResponseEntity getJsonRest(
            @PathVariable Long id) {
        Object rest = restService.getJsonRest(id);
        return new ResponseEntity<>(rest, HttpStatus.OK);
    }

    //Retorn Json de Rest com expecificaçoes 
    @RequestMapping(value = "{id}/get-rest-specifications",
            method = RequestMethod.GET)
    public ResponseEntity getRestSpecifications(@PathVariable Long id) {
        Object rest = restService.getJsonRest(id);
        InputStream is = new ByteArrayInputStream(rest.toString().getBytes());
        JSONValue parse = parser.parse(is);
        return new ResponseEntity<>(parse, HttpStatus.OK);
    }

    //Retona o json aplicando jsonPath
    @RequestMapping(value = "{id}/rest-get-applying-jsonPath",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRestGetApplyingJsonPath(
            @PathVariable Long id,
            @RequestBody WebserviceAnalysis ws) {

        Object result = restService.getResultApplyingJsonPath(id, ws);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Extri parte de um json
    @RequestMapping(value = "{id}/extract-json",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getExtractJson(
            @PathVariable Long id,
            @RequestBody WebserviceAnalysis ws) {
        //mudar o nome dessa funcao
        Object result = restService.getJsonPartJsonPath(id, ws);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/solr-result-applying-query/facet/{facet}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSolrResultApplyingQuery(
            @PathVariable Long id,
            @PathVariable int facet,
            @RequestBody Query query) {
        List<Map<String, Object>> solrResultApplyingQuery = 
        		solrService.getSolrResultApplyingQuery(id, query, facet);
        return new ResponseEntity<>(solrResultApplyingQuery, HttpStatus.OK);
    }

    
    //excuta CSV
    @RequestMapping(value = "/result-csv", method = RequestMethod.POST, 
    		consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getResultCSV(
            @RequestBody CsvAnalysis csvAnalysis) {
        List<Map<String, Object>> dataCsv = csvService.getDataCsv(csvAnalysis);
        
        return new ResponseEntity<>(dataCsv, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity bulkDelete(@RequestBody List<Long> ids,
    		@RequestHeader("Domain") String domain) {
        analysisService.deleteAll(ids, domain);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
