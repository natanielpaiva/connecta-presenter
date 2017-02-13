package br.com.cds.connecta.presenter.controller;

import br.com.cds.connecta.framework.core.context.HibernateAwareObjectMapper;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.cds.connecta.presenter.business.applicationService.ISingleSourceAS;
import br.com.cds.connecta.presenter.entity.FileSingleSource;
import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.UrlSingleSource;
import br.com.cds.connecta.presenter.filter.SingleSourceFilter;
import java.io.ByteArrayInputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("media")
public class SingleSourceController {

	@Autowired
	private ISingleSourceAS mediaService;

	@Autowired
	private HibernateAwareObjectMapper hibernateAwareObjectMapper;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	protected ResponseEntity<SingleSource> get(@PathVariable Long id, @RequestHeader("Domain") String domain) {
		SingleSource singleSource = mediaService.get(id, domain);
		return new ResponseEntity<>(singleSource, HttpStatus.OK);
	}

	@RequestMapping("attribute/{id}")
	protected ResponseEntity<List<SingleSource>> getByAttribute(@PathVariable("id") Long id) {
		List<SingleSource> singleSource = mediaService.getByAttributeId(id);
		return new ResponseEntity<>(singleSource, HttpStatus.OK);
	}

    @RequestMapping(method = RequestMethod.GET, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    protected ResponseEntity<Iterable<SingleSource>> list(SingleSourceFilter filter,
    		@RequestHeader("Domain") String domain) {
    	filter.setDomain(domain);
    	Iterable<SingleSource> list = mediaService.list(filter);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

	@RequestMapping("auto-complete")
	protected ResponseEntity<Iterable<SingleSource>> listAutoComplete(SingleSourceFilter filter,
			@RequestHeader("Domain") String domain) {
		filter.setDomain(domain);

		Page<SingleSource> list = mediaService.listAutoComplete(filter);
		Iterable<SingleSource> content = list.getContent();

		return new ResponseEntity<>(content, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	protected ResponseEntity<SingleSource> save(@RequestBody SingleSource singleSource) {
		SingleSource newSingleSource = mediaService.save(singleSource);
		return new ResponseEntity<>(newSingleSource, HttpStatus.CREATED);
	}

	@RequestMapping(value = "file", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleSource> saveFile(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam("singlesource") String singleSource) throws Exception {

		FileSingleSource fileSingleSource = hibernateAwareObjectMapper.readValue(singleSource, FileSingleSource.class);

		mediaService.preValidate(fileSingleSource, file);
		mediaService.validate(fileSingleSource);
		SingleSource newSingleSource = mediaService.save(fileSingleSource);

		return new ResponseEntity<>(newSingleSource, HttpStatus.CREATED);
	}

	@RequestMapping(value = "url", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleSource> saveUrl(@RequestBody UrlSingleSource url) {

		mediaService.validate(url);
		SingleSource newSingleSource = mediaService.save(url);
		return new ResponseEntity<>(newSingleSource, HttpStatus.CREATED);
	}

	@RequestMapping(value = "url", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleSource> updateUrl(@RequestBody UrlSingleSource url) {
		SingleSource newSingleSource = mediaService.save(url);
		return new ResponseEntity<>(newSingleSource, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	protected ResponseEntity<?> delete(@PathVariable Long id, @RequestHeader("Domain") String domain) {
		mediaService.delete(id, domain);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping("{id}/binary")
	public void download(@PathVariable("id") Long id, HttpServletResponse response) throws Exception {
		FileSingleSource singleSource = mediaService.getFileWithBinary(id);

		ByteArrayInputStream bis = new ByteArrayInputStream(singleSource.getBinaryFile().getBinaryFile());

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				singleSource.getName() + "." + singleSource.getFileType().toString().toLowerCase());

		response.setContentLength(singleSource.getBinaryFile().getBinaryFile().length);
		response.setContentType(singleSource.getFileType().getMimeTypes());
		response.setHeader(headerKey, headerValue);

		IOUtils.copy(bis, response.getOutputStream());
		response.flushBuffer();
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> bulkDelete(@RequestBody List<Long> ids, @RequestHeader("Domain") String domain) {
		mediaService.deleteAll(ids, domain);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
