package br.com.cds.connecta.presenter.entity.datasource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.presenter.domain.DatasourceTypeEnum;

/**
 * The persistent class for the TB_DATASOURCE database table.
 *
 */
@Entity
@Table(name = "TB_DATASOURCE")
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete( sql= "update TB_DATASOURCE set IS_ACTIVE = 0 where PK_DATASOURCE = ?")
@Where(clause = "IS_ACTIVE = 1")
public class Datasource extends AbstractBaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_DATASOURCE")
    private Long id;

    @Column(name = "DS_DATASOURCE")
    private String description;

    @Column(name = "NM_DATASOURCE")
    private String name;

    @Column(name = "TP_DATASOURCE")
    @Enumerated(EnumType.STRING)
    private DatasourceTypeEnum type;


	@Column(name = "TXT_SENHA")
    private String password;

    @Column(name = "TXT_USUARIO")
    private String user;

    @Column(name = "NM_DOMAIN")
    private String domain;
    
    @Column(columnDefinition= "tinyint(1) default 1", name = "IS_ACTIVE" )
    private Boolean isActive = true;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DatasourceTypeEnum getType() {
        return type;
    }

    public void setType(DatasourceTypeEnum type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
    
    public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
    
}
