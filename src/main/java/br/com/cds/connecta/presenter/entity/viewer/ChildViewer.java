package br.com.cds.connecta.presenter.entity.viewer;

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
 * The persistent class for the TA_CHILD_VIEWER database table.
 * 
 */
@Entity
@Table(name="TA_CHILD_VIEWER")
@NamedQuery(name="ChildViewer.findAll", query="SELECT t FROM ChildViewer t")
public class ChildViewer extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PK_CHILD_VIEWER")
	private Long id;

	//bi-directional many-to-one association to TbCmbViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_CMB_VIEWER")
	private CombinedViewer tbCmbViewer;

	//bi-directional many-to-one association to TbViewer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FK_VIEWER")
	private Viewer tbViewer;

	public ChildViewer() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CombinedViewer getTbCmbViewer() {
		return this.tbCmbViewer;
	}

	public void setTbCmbViewer(CombinedViewer tbCmbViewer) {
		this.tbCmbViewer = tbCmbViewer;
	}

	public Viewer getTbViewer() {
		return this.tbViewer;
	}

	public void setTbViewer(Viewer tbViewer) {
		this.tbViewer = tbViewer;
	}

}