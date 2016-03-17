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
 * The persistent class for the TB_LAYER_VIEWER_GROUP database table.
 * 
 */
@Entity
@Table(name="TB_LAYER_VIEWER_GROUP")
@NamedQuery(name="LayerViewerGroup.findAll", query="SELECT t FROM LayerViewerGroup t")
public class LayerViewerGroup extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_VIEWER_GROUP")
	private Long id;

	@Column(name="DS_PARAM_NAMES")
	private String dsParamNames;

	@Column(name="DS_PARAM_VALUES")
	private String dsParamValues;

	public LayerViewerGroup() {
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsParamNames() {
		return this.dsParamNames;
	}

	public void setDsParamNames(String dsParamNames) {
		this.dsParamNames = dsParamNames;
	}

	public String getDsParamValues() {
		return this.dsParamValues;
	}

	public void setDsParamValues(String dsParamValues) {
		this.dsParamValues = dsParamValues;
	}

}