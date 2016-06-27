package br.com.cds.connecta.presenter.entity.datasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.presenter.domain.DatasourceTypeEnum;
import java.io.Serializable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * The persistent class for the TB_DATASOURCE database table.
 *
 */
@Entity
@Table(name = "TB_DATASOURCE")
@Inheritance(strategy = InheritanceType.JOINED)
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

}
