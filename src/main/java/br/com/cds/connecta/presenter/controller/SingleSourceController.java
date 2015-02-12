package br.com.cds.connecta.presenter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import br.com.cds.connecta.framework.core.controller.AbstractBaseController;
import br.com.cds.connecta.presenter.business.applicationService.IMidiaAS;
import br.com.cds.connecta.presenter.entity.FileSingleSource;
import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.UrlSingleSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("media")
public class SingleSourceController extends AbstractBaseController<SingleSource> {

    @Autowired
    private IMidiaAS midiaService;

    @Override
    protected ResponseEntity<SingleSource> get(Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ResponseEntity<List<SingleSource>> list(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        List<SingleSource> list = midiaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<SingleSource> save(SingleSource singleSource, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        SingleSource newSingleSource = midiaService.saveOrUpdate(singleSource);
        return new ResponseEntity<>(newSingleSource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "file", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleSource> saveFile(@RequestBody FileSingleSource file) throws Exception {
        SingleSource newSingleSource = midiaService.saveOrUpdate(file);
        return new ResponseEntity<>(newSingleSource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "url", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleSource> saveUrl(@RequestBody UrlSingleSource url) throws Exception {
        SingleSource newSingleSource = midiaService.saveOrUpdate(url);
        return new ResponseEntity<>(newSingleSource, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "url", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleSource> updateUrl(@RequestBody UrlSingleSource url) throws Exception {
        SingleSource newSingleSource = midiaService.saveOrUpdate(url);
        return new ResponseEntity<>(newSingleSource, HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<SingleSource> createWithUpload(MultipartHttpServletRequest multipartRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ResponseEntity<SingleSource> update(Long id, SingleSource entity, HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ResponseEntity<SingleSource> updateWithUpload(Long id, MultipartHttpServletRequest multipartRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void delete(Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}