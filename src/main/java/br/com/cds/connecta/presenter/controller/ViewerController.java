package br.com.cds.connecta.presenter.controller;

import br.com.cds.connecta.framework.amcharts.ChartTemplate;
import br.com.cds.connecta.framework.amcharts.ChartTemplateType;
import br.com.cds.connecta.framework.amcharts.provider.ChartProvider;
import br.com.cds.connecta.framework.core.controller.AbstractBaseController;
import br.com.cds.connecta.presenter.business.applicationService.IViewerAS;
import br.com.cds.connecta.presenter.entity.viewer.Viewer;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Nataniel Paiva
 */
@Controller
@RequestMapping("viewer")
public class ViewerController extends AbstractBaseController<Viewer> {

    @Autowired
    private IViewerAS viewerService;
    
    private final ChartProvider chartProvider = new ChartProvider();

    @Override
    protected ResponseEntity<Viewer> get(Long id,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Viewer viewer = viewerService.get(id);
        return new ResponseEntity<>(viewer, HttpStatus.OK);
    }
    
    @Override
    protected ResponseEntity<List<Viewer>> list(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        List<Viewer> list = viewerService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Viewer> save(Viewer viewer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Viewer resultViewer = viewerService.saveOrUpdate(viewer);
        return new ResponseEntity<>(resultViewer, HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<Viewer> update(Long id, Viewer group,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Viewer updateGroup = viewerService.saveOrUpdate(group);
        return new ResponseEntity<>(updateGroup, HttpStatus.OK);
    }

    @Override
    protected void delete(Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        viewerService.delete(id);
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

}
