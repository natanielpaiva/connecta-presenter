package br.com.cds.connecta.presenter.entity;

import br.com.cds.connecta.presenter.domain.SingleSourceTypeEnum;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The persistent class for the TB_URL_WEB database table.
 *
 */
@Entity
@Table(name = "TB_URL_WEB")
@NamedQuery(name = "UrlSingleSource.findAll", query = "SELECT t FROM UrlSingleSource t")
public class UrlSingleSource extends SingleSource {

    private static final long serialVersionUID = 1L;

    @Column(name = "TP_URL_WEB")
    @NotNull
    private String urlType;

    @Column(name = "TXT_URL")
    private String url;

    @Column(name = "TXT_USER")
    private String user;

    @Column(name = "TXT_PASSWORD")
    private String password;

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
