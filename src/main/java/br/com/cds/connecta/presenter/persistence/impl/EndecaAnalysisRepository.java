package br.com.cds.connecta.presenter.persistence.impl;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.analysis.EndecaAnalysis;
@Repository
public interface EndecaAnalysisRepository extends JpaRepository<EndecaAnalysis, Serializable> {

}
