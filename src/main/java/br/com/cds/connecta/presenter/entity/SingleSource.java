package br.com.cds.connecta.presenter.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.presenter.domain.SingleSourceTypeEnum;

/**
 * The persistent class for the TB_SINGLE_SOURCE database table.
 *
 */
@Entity
@Table(name = "TB_SINGLE_SOURCE")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
@SQLDelete(sql = "update TB_SINGLE_SOURCE set IS_ACTIVE = 0 where PK_SINGLE_SOURCE = ?")
@Where(clause = "IS_ACTIVE = 1")
public class SingleSource extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PK_SINGLE_SOURCE")
	private Long id;

	@Column(name = "DS_SINGLE_SOURCE")
	private String description;

	@Column(name = "NM_SINGLE_SOURCE")
	private String name;

	@Column(name = "TP_SINGLE_SOURCE")
	@Enumerated(EnumType.STRING)
	private SingleSourceTypeEnum type;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_SINGLE_SOURCE")
	private List<SingleSourceAttribute> singleSourceAttributes;

	@Column(name = "NM_DOMAIN")
	private String domain;

	@Column(columnDefinition = "tinyint(1) default 1", name = "IS_ACTIVE")
	private boolean isActive = true;

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SingleSourceTypeEnum getType() {
		return type;
	}

	public void setType(SingleSourceTypeEnum type) {
		this.type = type;
	}

	public List<SingleSourceAttribute> getSingleSourceAttributes() {
		return singleSourceAttributes;
	}

	public void setSingleSourceAttributes(List<SingleSourceAttribute> singleSourceAttributes) {
		this.singleSourceAttributes = singleSourceAttributes;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
