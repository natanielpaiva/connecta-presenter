package br.com.cds.connecta.presenter.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.cds.connecta.presenter.domain.FileExtensionEnum;

/**
 * The persistent class for the TB_FILE database table.
 *
 */
@Entity
@Table(name = "TB_FILE_SINGLE_SOURCE")
@DynamicUpdate
@SQLDelete(sql = "update TB_FILE_SINGLE_SOURCE set IS_ACTIVE = 0 where PK_SINGLE_SOURCE = ?")
@Where(clause = "ACTIVE = 1")
public class FileSingleSource extends SingleSource {

	private static final long serialVersionUID = 1L;

	@Column(name = "NM_FILE")
	private String filename;

	@Column(name = "TP_FILE")
	@Enumerated(EnumType.STRING)
	private FileExtensionEnum fileType;

	@Column(name = "TXT_USER")
	private String user;

	@Column(name = "TXT_PASSWORD")
	private String password;

	@Column(name = "URL_FILE")
	private String url;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_BINARY_FILE")
	private BinaryFile binaryFile;

	@Column(columnDefinition = "tinyint(1) default 1", name = "IS_ACTIVE")
	private boolean isActive = true;

	public BinaryFile getBinaryFile() {
		return binaryFile;
	}

	public void setBinaryFile(BinaryFile binaryFile) {
		this.binaryFile = binaryFile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public FileExtensionEnum getFileType() {
		return fileType;
	}

	public void setFileType(FileExtensionEnum fileType) {
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
