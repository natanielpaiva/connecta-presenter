package br.com.cds.connecta.presenter.persistence;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cds.connecta.presenter.entity.analysis.WebserviceAnalysis;

public interface WebserviceAnalysisRepository extends JpaRepository<WebserviceAnalysis, Serializable> {

}
