package br.com.cds.connecta.presenter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import br.com.cds.connecta.framework.core.controller.AbstractBaseController;
import br.com.cds.connecta.presenter.business.applicationService.IManterAnaliseAS;
import br.com.cds.connecta.presenter.entity.Analysis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("analysis")
public class ManterAnaliseController extends AbstractBaseController<Analysis> {

    @Autowired
    private IManterAnaliseAS manterAnaliseAS;

    @Override
    protected ResponseEntity<Analysis> save(Analysis analysis, HttpServletRequest arg1,
            HttpServletResponse arg2) throws Exception {
        Analysis newAnalysis = manterAnaliseAS.saveOrUpdate(analysis);
        return new ResponseEntity<Analysis>(newAnalysis, HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<Analysis> update(Long arg0, Analysis analysis, HttpServletRequest arg2,
            HttpServletResponse arg3) throws Exception {
        Analysis updatedAnalysis = manterAnaliseAS.saveOrUpdate(analysis);
        return new ResponseEntity<Analysis>(updatedAnalysis, HttpStatus.OK);
    }

    @Override
    protected void delete(Long id, HttpServletRequest arg1,
            HttpServletResponse arg2) throws Exception {
        manterAnaliseAS.delete(id);
    }

    @Override
    protected ResponseEntity<Analysis> get(Long id, HttpServletRequest arg1,
            HttpServletResponse arg2) throws Exception {
        Analysis analisys = manterAnaliseAS.get(id);
        
        return new ResponseEntity<Analysis>(analisys, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<List<Analysis>> list(HttpServletRequest arg0,
            HttpServletResponse arg1) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected ResponseEntity<Analysis> updateWithUpload(Long arg0,
            MultipartHttpServletRequest arg1, HttpServletRequest arg2,
            HttpServletResponse arg3) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected ResponseEntity<Analysis> createWithUpload(MultipartHttpServletRequest arg0,
            HttpServletRequest arg1, HttpServletResponse arg2) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}
