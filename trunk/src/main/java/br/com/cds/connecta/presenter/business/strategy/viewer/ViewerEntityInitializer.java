package br.com.cds.connecta.presenter.business.strategy.viewer;

import br.com.cds.connecta.presenter.entity.viewer.Viewer;

/**
 * Interface de inicializadores de entidades que extendem de Viewer
 * 
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public interface ViewerEntityInitializer {
    /**
     * Inicializa as entidades de maneira específica a cada tipo de viewer
     * @param viewer 
     * @return  
     */
    Viewer initializeViewerEntity(Viewer viewer);
}
