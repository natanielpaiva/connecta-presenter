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
 * The persistent class for the TB_CONFIG_VIEWER database table.
 * 
 */
@Entity
@Table(name="TB_CONFIG_VIEWER")
@NamedQuery(name="ConfigViewer.findAll", query="SELECT t FROM ConfigViewer t")
public class ConfigViewer extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PK_CONFIG_VIEWER")
	private Long id;

	@Column(name="FML_ATTRIBUTE")
	private String fmlAttribute;

	@Column(name="NM_ATTRIBUTE")
	private String nmAttribute;
	
	public ConfigViewer() {
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFmlAttribute() {
		return this.fmlAttribute;
	}

	public void setFmlAttribute(String fmlAttribute) {
		this.fmlAttribute = fmlAttribute;
	}

	public String getNmAttribute() {
		return this.nmAttribute;
	}

	public void setNmAttribute(String nmAttribute) {
		this.nmAttribute = nmAttribute;
	}
}