package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.Attribute;
import br.com.cds.connecta.presenter.filter.AttributeFilter;
import org.springframework.data.domain.Page;

public interface IAttributeAS {

    public Page<Attribute> list(AttributeFilter filter);

}
