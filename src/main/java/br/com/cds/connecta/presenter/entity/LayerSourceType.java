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
 * The persistent class for the TD_LAYER_SOURCE_TYPE database table.
 * 
 */
@Entity
@Table(name="TD_LAYER_SOURCE_TYPE")
@NamedQuery(name="LayerSourceType.findAll", query="SELECT t FROM LayerSourceType t")
public class LayerSourceType extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_SOURCE_TYPE")
	private Long id;

	@Column(name="DS_SOURCE_TYPE")
	private String dsSourceType;

	public LayerSourceType() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsSourceType() {
		return this.dsSourceType;
	}

	public void setDsSourceType(String dsSourceType) {
		this.dsSourceType = dsSourceType;
	}

}