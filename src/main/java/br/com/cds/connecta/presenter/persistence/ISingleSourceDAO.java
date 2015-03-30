package br.com.cds.connecta.presenter.persistence;

import java.util.List;

import br.com.cds.connecta.presenter.entity.SingleSource;

public interface ISingleSourceDAO extends IBaseJpaDAO<SingleSource> {

    List<SingleSource> list();

    void refreshAttribute(SingleSource singleSource);

    List<SingleSource> getByAttributeId(Long id);

    

}
