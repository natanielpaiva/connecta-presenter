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
 * The persistent class for the TB_DASHBOARD database table.
 * 
 */
@Entity
@Table(name="TB_DASHBOARD")
@NamedQuery(name="Dashboard.findAll", query="SELECT t FROM Dashboard t")
public class Dashboard extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DASHBOARD_PKDASHBOARD_GENERATOR", sequenceName="TB_DASHBOARD_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DASHBOARD_PKDASHBOARD_GENERATOR")
	@Column(name="PK_DASHBOARD")
	private Long id;

	@Column(name="DS_DASHBOARD")
	private String dsDashboard;

	@Column(name="FLG_SHOW")
	private String flgShow;

	@Column(name="NM_DASHBOARD")
	private String nmDashboard;

	public Dashboard() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsDashboard() {
		return this.dsDashboard;
	}

	public void setDsDashboard(String dsDashboard) {
		this.dsDashboard = dsDashboard;
	}

	public String getFlgShow() {
		return this.flgShow;
	}

	public void setFlgShow(String flgShow) {
		this.flgShow = flgShow;
	}

	public String getNmDashboard() {
		return this.nmDashboard;
	}

	public void setNmDashboard(String nmDashboard) {
		this.nmDashboard = nmDashboard;
	}

}