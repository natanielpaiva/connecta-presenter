package br.com.cds.connecta.presenter.persistence;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewer;

public interface AnalysisViewerRepository extends JpaRepository<AnalysisViewer, Serializable>{

}
