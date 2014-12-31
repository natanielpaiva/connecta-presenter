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
 * The persistent class for the TB_VIEWER_FILTER database table.
 * 
 */
@Entity
@Table(name="TB_VIEWER_FILTER")
@NamedQuery(name="ViewerFilter.findAll", query="SELECT t FROM ViewerFilter t")
public class ViewerFilter extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_VIEWER_FILTER_PKVIEWERFILTER_GENERATOR", sequenceName="TB_VIEWER_FILTER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_VIEWER_FILTER_PKVIEWERFILTER_GENERATOR")
	@Column(name="PK_VIEWER_FILTER")
	private Long id;

	@Column(name="NM_COLUMN")
	private String nmColumn;

	@Column(name="TXT_SELECTED_VALUE")
	private String txtSelectedValue;

	//bi-directional many-to-one association to Filter
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_FILTER")
	private Filter filter;

	//bi-directional many-to-one association to TbFilterSelector
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_FILTER_SELECTOR")
	private FilterSelector tbFilterSelector;

	//bi-directional many-to-one association to TbViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_VIEWER")
	private Viewer tbViewer;

	public ViewerFilter() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNmColumn() {
		return this.nmColumn;
	}

	public void setNmColumn(String nmColumn) {
		this.nmColumn = nmColumn;
	}

	public String getTxtSelectedValue() {
		return this.txtSelectedValue;
	}

	public void setTxtSelectedValue(String txtSelectedValue) {
		this.txtSelectedValue = txtSelectedValue;
	}

	public Filter getFilter() {
		return this.filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public FilterSelector getTbFilterSelector() {
		return this.tbFilterSelector;
	}

	public void setTbFilterSelector(FilterSelector tbFilterSelector) {
		this.tbFilterSelector = tbFilterSelector;
	}

	public Viewer getTbViewer() {
		return this.tbViewer;
	}

	public void setTbViewer(Viewer tbViewer) {
		this.tbViewer = tbViewer;
	}

}