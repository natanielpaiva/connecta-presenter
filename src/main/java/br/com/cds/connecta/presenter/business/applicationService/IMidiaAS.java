package br.com.cds.connecta.presenter.business.applicationService;

import java.util.List;

import br.com.cds.connecta.presenter.entity.SingleSource;

public interface IMidiaAS {

    public abstract SingleSource get(Long id) throws Exception;

    public abstract List<SingleSource> list() throws Exception;

    public abstract SingleSource saveOrUpdate(SingleSource entity) throws Exception;

    public abstract void delete(Long id) throws Exception;

    public abstract void delete(SingleSource singleSource) throws Exception;

}
