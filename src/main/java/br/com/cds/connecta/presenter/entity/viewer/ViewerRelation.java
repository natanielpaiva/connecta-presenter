package br.com.cds.connecta.presenter.entity.viewer;

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

@Entity
@Table(name="VIEWER_RELATION")
@NamedQuery(name="ViewerRelation.findAll", query="SELECT v FROM ViewerRelation v")
public class ViewerRelation extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VIEWER_RELATION_PKVIEWERRELATION_GENERATOR", sequenceName="VIEWER_RELATION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VIEWER_RELATION_PKVIEWERRELATION_GENERATOR")
	@Column(name="PK_VIEWER_RELATION")
	private Long id;

	@Column(name="ID_RELATION")
	private BigDecimal idRelation;

	@Column(name="ID_VIEWER")
	private BigDecimal idViewer;

	public ViewerRelation() {
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

	public BigDecimal getIdViewer() {
		return this.idViewer;
	}

	public void setIdViewer(BigDecimal idViewer) {
		this.idViewer = idViewer;
	}

}