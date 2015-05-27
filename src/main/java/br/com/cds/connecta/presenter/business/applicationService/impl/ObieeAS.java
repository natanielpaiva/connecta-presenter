package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.connector.obiee.service.Obiee;
import static br.com.cds.connecta.framework.core.util.Util.isNull;

import br.com.cds.connecta.presenter.business.applicationService.IObieeAS;
import br.com.cds.connecta.presenter.entity.AnalysisColumn;
import br.com.cds.connecta.presenter.entity.datasource.BIDatasource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class ObieeAS implements IObieeAS {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List getCatalog(Long id, String path) {
        BIDatasource BI = em.find(BIDatasource.class, id);
        if (isNull(path)) {
            path = BI.getPath();
        }
        Obiee obiee = new Obiee(BI.getAddress(), BI.getUser(), BI.getPassword(), path);
        return obiee.getCatalog();
    }

    @Override
    public List<AnalysisColumn> getColumns(Long id, String path) {
        ArrayList<AnalysisColumn> columns = new ArrayList<>();

        BIDatasource BI = em.find(BIDatasource.class, id);
        Obiee obiee = new Obiee(BI.getAddress(), BI.getUser(), BI.getPassword(), path);

        HashMap<String, List<String>> metadata = obiee.getMetadata();

        List<String> colunas = metadata.get("columns");
        List<String> formula = metadata.get("formula");

        for (int i = 0; i < formula.size(); i++) {
            AnalysisColumn ac = new AnalysisColumn();
            ac.setName(colunas.get(i));
            ac.setFormula(formula.get(i));
            columns.add(ac);
        }

        return columns;
    }
}
