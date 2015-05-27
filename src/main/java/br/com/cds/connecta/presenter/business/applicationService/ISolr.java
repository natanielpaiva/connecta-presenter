/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.AnalysisColumn;
import java.util.List;

/**
 *
 * @author diego
 */
public interface ISolr {
   List<AnalysisColumn> getColumns(Long id);
}
