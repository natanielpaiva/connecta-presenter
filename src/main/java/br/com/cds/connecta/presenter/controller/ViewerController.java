package br.com.cds.connecta.presenter.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cds.connecta.framework.amcharts.ChartTemplate;
import br.com.cds.connecta.framework.amcharts.ChartTemplateType;
import br.com.cds.connecta.presenter.business.applicationService.IViewerAS;
import br.com.cds.connecta.presenter.components.viewers.amcharts.provider.ChartProvider;
import br.com.cds.connecta.presenter.entity.viewer.Viewer;

/**
 *
 * @author Nataniel Paiva
 */
@RestController
@RequestMapping("viewer")
public class ViewerController {

    @Autowired
    private IViewerAS viewerService;
    
    @Autowired
    private ChartProvider chartProvider;

    @RequestMapping(value = "/{id}", 
    		method = RequestMethod.GET, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Viewer> get(@PathVariable Long id,
    			@RequestHeader("Domain") String domain){
    	
        Viewer viewer = viewerService.get(id,domain,true);
        return new ResponseEntity<>(viewer, HttpStatus.OK);
    }
    
    @RequestMapping(value = "public/{id}", 
    		method = RequestMethod.GET, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Viewer> getPublic(@PathVariable Long id){
        Viewer viewer = viewerService.getPublic(id,true);
        return new ResponseEntity<>(viewer, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Viewer>> list(@RequestHeader("Domain") String domain){
        List<Viewer> list = viewerService.list(domain);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Viewer> save(@RequestBody Viewer viewer)throws Exception{
        Viewer resultViewer = viewerService.saveOrUpdate(viewer);
        return new ResponseEntity<>(resultViewer, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", 
    		method = RequestMethod.PUT, 
    		produces = MediaType.APPLICATION_JSON_VALUE, 
    		consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Viewer> update(@PathVariable Long id, @RequestBody Viewer viewer){
    	viewer.setId(id);
        Viewer updateViewer= viewerService.saveOrUpdate(viewer);
        return new ResponseEntity<>(updateViewer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable Long id, @RequestHeader("Domain") String domain) throws Exception {
        viewerService.delete(id, domain);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "chart-template", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ChartTemplateType>> getChartTypes() {
        Collection<ChartTemplateType> types = chartProvider.listTemplateTypes();
        
        return new ResponseEntity<>(types, HttpStatus.OK);
    }
    
    @RequestMapping(value = "chart-template/{type}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ChartTemplate>> getTemplatesByType(
            @PathVariable("type") String type) {
        Collection<ChartTemplate> templates = chartProvider.listTemplatesFor(type);
        
        return new ResponseEntity<>(templates, HttpStatus.OK);
    }
    
    @RequestMapping(value = "chart-template/{type}/{template}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void getTemplate(
            @PathVariable("type") String type,
            @PathVariable("template") String template,
            HttpServletResponse response) throws IOException {
        String json = chartProvider.getTemplateContent(type, template);
        
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(json);
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity bulkDelete(@RequestBody List<Long> ids, @RequestHeader("Domain") String domain) {
        viewerService.deleteAll(ids, domain);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
