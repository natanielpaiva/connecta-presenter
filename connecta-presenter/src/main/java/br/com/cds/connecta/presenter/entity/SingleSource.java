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
 * The persistent class for the TB_SINGLE_SOURCE database table.
 * 
 */
@Entity
@Table(name="TB_SINGLE_SOURCE")
@NamedQuery(name="SingleSource.findAll", query="SELECT t FROM SingleSource t")
public class SingleSource extends AbstractBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_SINGLE_SOURCE_PKSINGLESOURCE_GENERATOR", sequenceName="TB_SINGLE_SOURCE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_SINGLE_SOURCE_PKSINGLESOURCE_GENERATOR")
	@Column(name="PK_SINGLE_SOURCE")
	private Long id;

	@Column(name="DS_SINGLE_SOURCE")
	private String dsSingleSource;

	@Column(name="NM_SINGLE_SOURCE")
	private String nmSingleSource;

	public SingleSource() {
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsSingleSource() {
		return this.dsSingleSource;
	}

	public void setDsSingleSource(String dsSingleSource) {
		this.dsSingleSource = dsSingleSource;
	}

	public String getNmSingleSource() {
		return this.nmSingleSource;
	}

	public void setNmSingleSource(String nmSingleSource) {
		this.nmSingleSource = nmSingleSource;
	}
}