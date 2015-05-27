package br.com.cds.connecta.presenter.persistence;

import java.util.List;

import br.com.cds.connecta.presenter.entity.Analysis;

public interface IAnalysisDAO extends IBaseJpaDAO<Analysis> {

    public abstract List<Analysis> list();

}
