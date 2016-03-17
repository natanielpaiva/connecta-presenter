package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;


/**
 * The persistent class for the TB_LAYER database table.
 * 
 */
@Entity
@Table(name="TB_LAYER")
@NamedQuery(name="Layer.findAll", query="SELECT t FROM Layer t")
public class Layer extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_LAYER")
	private Long id;

	@Column(name="DS_LAYER")
	private String dsLayer;

	@Column(name="ID_SOURCE")
	private BigDecimal idSource;

	@Column(name="NM_LAYER")
	private String nmLayer;

	public Layer() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsLayer() {
		return this.dsLayer;
	}

	public void setDsLayer(String dsLayer) {
		this.dsLayer = dsLayer;
	}

	public BigDecimal getIdSource() {
		return this.idSource;
	}

	public void setIdSource(BigDecimal idSource) {
		this.idSource = idSource;
	}

	public String getNmLayer() {
		return this.nmLayer;
	}

	public void setNmLayer(String nmLayer) {
		this.nmLayer = nmLayer;
	}
}