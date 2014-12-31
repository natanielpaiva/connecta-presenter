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
 * The persistent class for the TD_LAYER_VIEWER_IMPL database table.
 * 
 */
@Entity
@Table(name="TD_LAYER_VIEWER_IMPL")
@NamedQuery(name="LayerViewerImpl.findAll", query="SELECT t FROM LayerViewerImpl t")
public class LayerViewerImpl extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TD_LAYER_VIEWER_IMPL_IDLVIMPL_GENERATOR", sequenceName="TD_LAYER_VIEWER_IMPL_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TD_LAYER_VIEWER_IMPL_IDLVIMPL_GENERATOR")
	@Column(name="ID_LV_IMPL")
	private Long id;

	@Column(name="DS_LV_IMPL")
	private String dsLvImpl;

	public LayerViewerImpl() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsLvImpl() {
		return this.dsLvImpl;
	}

	public void setDsLvImpl(String dsLvImpl) {
		this.dsLvImpl = dsLvImpl;
	}

}