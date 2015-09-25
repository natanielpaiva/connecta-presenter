package br.com.cds.connecta.presenter.business.applicationService;

import java.util.List;

/**
 *
 * @author diego
 */
public interface IObieeAS {

    public List getCatalog(Long id, String path);

    public List getColumns(Long id, String path);

}
