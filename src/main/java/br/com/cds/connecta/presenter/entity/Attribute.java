package br.com.cds.connecta.presenter.entity;

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
 * The persistent class for the TB_ATTRIBUTE database table.
 * 
 */
@Entity
@Table(name="TB_ATTRIBUTE")
@NamedQuery(name="Attribute.findAll", query="SELECT t FROM Attribute t")
public class Attribute extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_ATTRIBUTE_PKATTRIBUTE_GENERATOR", sequenceName="TB_ATTRIBUTE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ATTRIBUTE_PKATTRIBUTE_GENERATOR")
	@Column(name="PK_ATTRIBUTE")
	private Long id;

	@Column(name="DS_ATTRIBUTE")
	private String dsAttribute;

	@Column(name="NM_ATTRIBUTE")
	private String nmAttribute;
	
	public Attribute() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsAttribute() {
		return this.dsAttribute;
	}

	public void setDsAttribute(String dsAttribute) {
		this.dsAttribute = dsAttribute;
	}

	public String getNmAttribute() {
		return this.nmAttribute;
	}

	public void setNmAttribute(String nmAttribute) {
		this.nmAttribute = nmAttribute;
	}
}