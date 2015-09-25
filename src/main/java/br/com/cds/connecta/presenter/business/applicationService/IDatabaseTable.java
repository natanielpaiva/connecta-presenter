package br.com.cds.connecta.presenter.business.applicationService;

import java.util.List;

/**
 *
 * @author diego
 */
public interface IDatabaseTable {

    String getTableName();

    String getSchema();

    String getTableType();

    List<IDatabaseColumn> getColumns();
}
