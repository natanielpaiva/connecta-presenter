package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.presenter.entity.AnalysisColumn;

public interface IAnalysisColumnDAO extends IBaseJpaDAO<AnalysisColumn> {

    AnalysisColumn get(Long id);

}
