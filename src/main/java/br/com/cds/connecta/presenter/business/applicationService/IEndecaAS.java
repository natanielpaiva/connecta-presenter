package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.analysis.AnalysisColumn;
import java.util.List;

/**
 *
 * @author diego
 */
public interface IEndecaAS {

    public List getDomains(Long id);

    public List<AnalysisColumn> getColumns(Long id, String domain);

}
