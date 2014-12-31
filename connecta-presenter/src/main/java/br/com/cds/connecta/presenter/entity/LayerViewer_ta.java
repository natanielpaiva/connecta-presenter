package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;

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
 * The persistent class for the TB_LAYER_VIEWER database table.
 * 
 */
@Entity
@Table(name="TB_LAYER_VIEWER")
@NamedQuery(name="LayerViewer_ta.findAll", query="SELECT t FROM LayerViewer_ta t")
public class LayerViewer_ta extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_LAYER_VIEWER_IDVIEWER_GENERATOR", sequenceName="TB_LAYER_VIEWER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_LAYER_VIEWER_IDVIEWER_GENERATOR")
	@Column(name="ID_VIEWER")
	private Long id;

	@Column(name="DS_PARAM_NAMES")
	private String dsParamNames;

	@Column(name="DS_PARAM_VALUES")
	private String dsParamValues;

	@Column(name="ID_LV_IMPL")
	private BigDecimal idLvImpl;

	@Column(name="ID_TP_VIEWER")
	private BigDecimal idTpViewer;

	@Column(name="NM_VIEWER")
	private String nmViewer;

	//bi-directional many-to-one association to TbLayer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_LAYER")
	private Layer tbLayer;

	//bi-directional many-to-one association to TbViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_VIEWER")
	private Viewer tbViewer;

	

	public LayerViewer_ta() {
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

	public BigDecimal getIdLvImpl() {
		return this.idLvImpl;
	}

	public void setIdLvImpl(BigDecimal idLvImpl) {
		this.idLvImpl = idLvImpl;
	}

	public BigDecimal getIdTpViewer() {
		return this.idTpViewer;
	}

	public void setIdTpViewer(BigDecimal idTpViewer) {
		this.idTpViewer = idTpViewer;
	}

	public String getNmViewer() {
		return this.nmViewer;
	}

	public void setNmViewer(String nmViewer) {
		this.nmViewer = nmViewer;
	}

	public Layer getTbLayer() {
		return this.tbLayer;
	}

	public void setTbLayer(Layer tbLayer) {
		this.tbLayer = tbLayer;
	}

	public Viewer getTbViewer() {
		return this.tbViewer;
	}

	public void setTbViewer(Viewer tbViewer) {
		this.tbViewer = tbViewer;
	}
}