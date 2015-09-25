package br.com.cds.connecta.presenter.business.applicationService;

import java.io.Serializable;

/**
 *
 * @author diego
 */
public interface IDatabaseForeignKeyColumn extends Serializable {

    String getName();

    String getParentTableName();

}
