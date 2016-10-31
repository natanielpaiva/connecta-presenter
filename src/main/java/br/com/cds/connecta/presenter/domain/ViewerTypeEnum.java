package br.com.cds.connecta.presenter.domain;

import br.com.cds.connecta.presenter.business.strategy.viewer.AnalysisViewerEntityInitializer;
import br.com.cds.connecta.presenter.business.strategy.viewer.CombinedViewerEntityInitializer;
import br.com.cds.connecta.presenter.business.strategy.viewer.SingleSourceGroupViewerEntityInitializer;
import br.com.cds.connecta.presenter.business.strategy.viewer.SingleSourceViewerEntityInitializer;
import br.com.cds.connecta.presenter.business.strategy.viewer.ViewerEntityInitializer;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public enum ViewerTypeEnum {
	ANALYSIS_D3(AnalysisViewerEntityInitializer.class),
    ANALYSIS(AnalysisViewerEntityInitializer.class),
    SINGLESOURCE(SingleSourceViewerEntityInitializer.class),
    SINGLESOURCE_GROUP(SingleSourceGroupViewerEntityInitializer.class),
    COMBINED(CombinedViewerEntityInitializer.class);
    
    private final Class<? extends ViewerEntityInitializer> entityInitializer;

    ViewerTypeEnum(Class<? extends ViewerEntityInitializer> entityInitializer) {
        this.entityInitializer = entityInitializer;
    }

    /**
     * Retorna o inicializador de entidades do tipo de visualizador da instância
     * 
     * @return 
     */
    public Class<? extends ViewerEntityInitializer> getEntityInitializer() {
        return entityInitializer;
    }
}