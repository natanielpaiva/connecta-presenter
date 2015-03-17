package br.com.cds.connecta.presenter.controller;

import br.com.cds.connecta.framework.core.context.HibernateAwareObjectMapper;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import br.com.cds.connecta.framework.core.controller.AbstractBaseController;
import br.com.cds.connecta.presenter.business.applicationService.IMediaAS;
import br.com.cds.connecta.presenter.entity.FileSingleSource;
import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.UrlSingleSource;
import java.io.ByteArrayInputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("media")
public class SingleSourceController extends AbstractBaseController<SingleSource> {

    @Autowired
    private IMediaAS mediaService;

    @Autowired
    private HibernateAwareObjectMapper hibernateAwareObjectMapper;

    @Override
    protected ResponseEntity<SingleSource> get(Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SingleSource singleSource = mediaService.get(id);
        return new ResponseEntity<>(singleSource, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<List<SingleSource>> list(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        List<SingleSource> list = mediaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<SingleSource> save(SingleSource singleSource, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        SingleSource newSingleSource = mediaService.saveOrUpdate(singleSource);
        return new ResponseEntity<>(newSingleSource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "file", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleSource> saveFile(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("singlesource") String singleSource
    ) throws Exception {

        FileSingleSource fileSingleSource = hibernateAwareObjectMapper.readValue(singleSource, FileSingleSource.class);

        mediaService.preValidate(fileSingleSource, file);
        mediaService.validate(fileSingleSource);
        SingleSource newSingleSource = mediaService.saveOrUpdate(fileSingleSource);

        return new ResponseEntity<>(newSingleSource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "url", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleSource> saveUrl(@RequestBody UrlSingleSource url) throws Exception {

        mediaService.validate(url);
        SingleSource newSingleSource = mediaService.saveOrUpdate(url);
        return new ResponseEntity<>(newSingleSource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "url", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleSource> updateUrl(@RequestBody UrlSingleSource url) throws Exception {
        SingleSource newSingleSource = mediaService.saveOrUpdate(url);
        return new ResponseEntity<>(newSingleSource, HttpStatus.OK);
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
        mediaService.delete(id);
    }

    @RequestMapping("{id}/binary")
    public void download(@PathVariable("id") Long id, HttpServletResponse response) throws Exception {
        FileSingleSource singleSource = mediaService.getFileWithBinary(id);

        ByteArrayInputStream bis = new ByteArrayInputStream(singleSource.getBinaryFile().getBinaryFile());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                singleSource.getName() + "." + singleSource.getFileType().toString().toLowerCase() );

        response.setContentLength(singleSource.getBinaryFile().getBinaryFile().length);
        response.setContentType(singleSource.getFileType().getMimeTypes());
        response.setHeader(headerKey, headerValue);

        IOUtils.copy(bis, response.getOutputStream());
        response.flushBuffer();
    }

}
