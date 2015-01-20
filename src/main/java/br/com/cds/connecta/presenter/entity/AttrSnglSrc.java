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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;


/**
 * The persistent class for the TA_ATTR_SNGL_SRC database table.
 * 
 */
@Entity
@Table(name="TA_ATTR_SNGL_SRC")
@NamedQuery(name="AttrSnglSrc.findAll", query="SELECT t FROM AttrSnglSrc t")
public class AttrSnglSrc extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TA_ATTR_SNGL_SRC_PKATTRSNGLSRC_GENERATOR", sequenceName="TA_ATTR_SNGL_SRC_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_ATTR_SNGL_SRC_PKATTRSNGLSRC_GENERATOR")
	@Column(name="PK_ATTR_SNGL_SRC")
	private Long id;

	@Column(name="TXT_VALUE")
	private String txtValue;

	//bi-directional many-to-one association to TbAttribute
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_ATTRIBUTE")
	private Attribute tbAttribute;

	//bi-directional many-to-one association to TbSingleSource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_SINGLE_SOURCE")
	private SingleSource tbSingleSource;

	public AttrSnglSrc() {
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

	public Attribute getTbAttribute() {
		return this.tbAttribute;
	}

	public void setTbAttribute(Attribute tbAttribute) {
		this.tbAttribute = tbAttribute;
	}

	public SingleSource getTbSingleSource() {
		return this.tbSingleSource;
	}

	public void setTbSingleSource(SingleSource tbSingleSource) {
		this.tbSingleSource = tbSingleSource;
	}

}