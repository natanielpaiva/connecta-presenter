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
 * The persistent class for the TB_GROUP database table.
 * 
 */
@Entity
@Table(name="TB_GROUP")
@NamedQuery(name="Group.findAll", query="SELECT t FROM Group t")
public class Group extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_GROUP_PKGROUP_GENERATOR", sequenceName="TB_GROUP_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_GROUP_PKGROUP_GENERATOR")
	@Column(name="PK_GROUP")
	private Long id;

	@Column(name="DS_GROUP")
	private String dsGroup;

	@Column(name="NM_GROUP")
	private String nmGroup;

	@Column(name="TP_GROUP")
	private String tpGroup;

	@Column(name="TXT_QUERY")
	private String txtQuery;

	public Group() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsGroup() {
		return this.dsGroup;
	}

	public void setDsGroup(String dsGroup) {
		this.dsGroup = dsGroup;
	}

	public String getNmGroup() {
		return this.nmGroup;
	}

	public void setNmGroup(String nmGroup) {
		this.nmGroup = nmGroup;
	}

	public String getTpGroup() {
		return this.tpGroup;
	}

	public void setTpGroup(String tpGroup) {
		this.tpGroup = tpGroup;
	}

	public String getTxtQuery() {
		return this.txtQuery;
	}

	public void setTxtQuery(String txtQuery) {
		this.txtQuery = txtQuery;
	}
}