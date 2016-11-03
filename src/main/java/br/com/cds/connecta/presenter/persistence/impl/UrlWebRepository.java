package br.com.cds.connecta.presenter.persistence.impl;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.UrlSingleSource;
@Repository
public interface UrlWebRepository extends JpaRepository<UrlSingleSource, Serializable> {

}