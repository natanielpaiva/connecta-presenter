package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.presenter.business.applicationService.IAttributeAS;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.presenter.entity.Attribute;
import br.com.cds.connecta.presenter.filter.AttributeFilter;
import br.com.cds.connecta.presenter.persistence.AttributeRepository;
import static br.com.cds.connecta.presenter.util.AssertUtil.isNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Service
public class AttributeAS implements IAttributeAS {

//    @Autowired
//    private IAttributeDAO dao;
    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public Page<Attribute> list(AttributeFilter filter) {

        String name = filter.getName();

        if (isNull(name)) {
            name = "";
        }

        return attributeRepository.findByName("%" + name.toUpperCase() + "%", filter.makePageable());
    }

}
