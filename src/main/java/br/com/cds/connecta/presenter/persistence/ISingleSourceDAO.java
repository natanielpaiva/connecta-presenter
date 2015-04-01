package br.com.cds.connecta.presenter.persistence;

import java.util.List;

import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.SingleSourceGroup;

public interface ISingleSourceDAO extends IBaseJpaDAO<SingleSource> {

    SingleSource getWithAttributes(Long id);

    List<SingleSource> getByAttributeId(Long id);
    
    List<SingleSourceGroup> findById(Long id);

}
