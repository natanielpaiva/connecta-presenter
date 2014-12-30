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
 * The persistent class for the VIEWERGROUP_RELATION database table.
 * 
 */
@Entity
@Table(name="VIEWERGROUP_RELATION")
@NamedQuery(name="ViewergroupRelation.findAll", query="SELECT v FROM ViewergroupRelation v")
public class ViewergroupRelation extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VIEWERGROUP_RELATION_PKVIEWERGROUPRELATION_GENERATOR", sequenceName="VIEWERGROUP_RELATION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VIEWERGROUP_RELATION_PKVIEWERGROUPRELATION_GENERATOR")
	@Column(name="PK_VIEWERGROUP_RELATION")
	private Long id;

	@Column(name="ID_RELATION")
	private BigDecimal idRelation;

	@Column(name="ID_VIEWER_GROUP")
	private BigDecimal idViewerGroup;

	public ViewergroupRelation() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getIdRelation() {
		return this.idRelation;
	}

	public void setIdRelation(BigDecimal idRelation) {
		this.idRelation = idRelation;
	}

	public BigDecimal getIdViewerGroup() {
		return this.idViewerGroup;
	}

	public void setIdViewerGroup(BigDecimal idViewerGroup) {
		this.idViewerGroup = idViewerGroup;
	}

}