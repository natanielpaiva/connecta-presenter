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
 * The persistent class for the TB_LAYER_SOURCE database table.
 * 
 */
@Entity
@Table(name="TB_LAYER_SOURCE")
@NamedQuery(name="LayerSource.findAll", query="SELECT t FROM LayerSource t")
public class LayerSource extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_LAYER_SOURCE_IDSOURCE_GENERATOR", sequenceName="TB_LAYER_SOURCE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_LAYER_SOURCE_IDSOURCE_GENERATOR")
	@Column(name="ID_SOURCE")
	private Long id;

	@Column(name="DS_LINK_EXTERNO")
	private String dsLinkExterno;

	@Column(name="DS_LINK_INTERNO")
	private String dsLinkInterno;

	@Column(name="ID_SOURCE_TYPE")
	private BigDecimal idSourceType;

	@Column(name="NM_PASS_REST")
	private String nmPassRest;

	@Column(name="NM_SOURCE")
	private String nmSource;

	@Column(name="NM_USER_REST")
	private String nmUserRest;

	public LayerSource() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsLinkExterno() {
		return this.dsLinkExterno;
	}

	public void setDsLinkExterno(String dsLinkExterno) {
		this.dsLinkExterno = dsLinkExterno;
	}

	public String getDsLinkInterno() {
		return this.dsLinkInterno;
	}

	public void setDsLinkInterno(String dsLinkInterno) {
		this.dsLinkInterno = dsLinkInterno;
	}

	public BigDecimal getIdSourceType() {
		return this.idSourceType;
	}

	public void setIdSourceType(BigDecimal idSourceType) {
		this.idSourceType = idSourceType;
	}

	public String getNmPassRest() {
		return this.nmPassRest;
	}

	public void setNmPassRest(String nmPassRest) {
		this.nmPassRest = nmPassRest;
	}

	public String getNmSource() {
		return this.nmSource;
	}

	public void setNmSource(String nmSource) {
		this.nmSource = nmSource;
	}

	public String getNmUserRest() {
		return this.nmUserRest;
	}

	public void setNmUserRest(String nmUserRest) {
		this.nmUserRest = nmUserRest;
	}

}