package br.com.cds.connecta.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;


/**
 * The persistent class for the TA_ATTR_ANALYSIS database table.
 * 
 */
@Entity
@Table(name="TA_ATTR_ANALYSIS")
@NamedQuery(name="AttrAnalysi.findAll", query="SELECT t FROM AttrAnalysi t")
public class AttrAnalysi extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TA_ATTR_ANALYSIS_PKATTRIBUTE_GENERATOR", sequenceName="TA_ATTR_ANALYSIS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_ATTR_ANALYSIS_PKATTRIBUTE_GENERATOR")
	@Column(name="PK_ATTRIBUTE")
	private Long id;

	@Column(name="TXT_VALUE")
	private String txtValue;

	//bi-directional many-to-one association to TbAnalysi
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PK_ANALYSIS")
	private Analysis tbAnalysi;

	//bi-directional one-to-one association to TbAttribute
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PK_ATTRIBUTE")
	private Attribute tbAttribute;

	public AttrAnalysi() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTxtValue() {
		return this.txtValue;
	}

	public void setTxtValue(String txtValue) {
		this.txtValue = txtValue;
	}

	public Analysis getTbAnalysi() {
		return this.tbAnalysi;
	}

	public void setTbAnalysi(Analysis tbAnalysi) {
		this.tbAnalysi = tbAnalysi;
	}

	public Attribute getTbAttribute() {
		return this.tbAttribute;
	}

	public void setTbAttribute(Attribute tbAttribute) {
		this.tbAttribute = tbAttribute;
	}

}