package br.com.cds.connecta.presenter.entity;

import br.com.cds.connecta.presenter.domain.SingleSourceTypeEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the TB_FILE database table.
 *
 */
@Entity
@Table(name = "TB_FILE")
@NamedQuery(name = "FileSingleSource.findAll", query = "SELECT t FROM FileSingleSource t")
public class FileSingleSource extends SingleSource {

    private static final long serialVersionUID = 1L;

    @Column(name = "NM_FILE")
    private String filename;

    @Column(name = "TP_FILE")
    private String fileType;

    @Column(name = "TXT_USER")
    private String user;
    
    @Column(name = "TXT_PASSWORD")
    private String password;

    @Column(name = "URL_FILE")
    private String url;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
