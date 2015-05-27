/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
