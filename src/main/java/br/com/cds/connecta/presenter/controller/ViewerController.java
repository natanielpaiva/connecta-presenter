package br.com.cds.connecta.presenter.controller;

import br.com.cds.connecta.framework.core.controller.AbstractBaseController;
import br.com.cds.connecta.presenter.business.applicationService.IViewerAS;
import br.com.cds.connecta.presenter.entity.Viewer;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nataniel
 */
@Controller
@RequestMapping("viewer")
public class ViewerController extends AbstractBaseController<Viewer> {

    @Autowired
    private IViewerAS viewerService;

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

}