package br.com.cds.connecta.presenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cds.connecta.presenter.business.applicationService.IAttributeAS;
import br.com.cds.connecta.presenter.entity.Attribute;
import br.com.cds.connecta.presenter.filter.AttributeFilter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("attribute")
public class AttributeController {

    @Autowired
    private IAttributeAS attributeAS;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Attribute>> list(AttributeFilter filter) throws Exception {
        
        Page<Attribute> list = attributeAS.list(filter);
        
        Iterable<Attribute> content = list.getContent();
        
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

}
