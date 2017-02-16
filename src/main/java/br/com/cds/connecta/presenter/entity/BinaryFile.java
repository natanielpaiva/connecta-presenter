package br.com.cds.connecta.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_BINARY_FILE")
@DynamicUpdate
@SQLDelete(sql = "update TB_BINARY_FILE set IS_ACTIVE = 0 where PK_BINARY_FILE = ?")
@Where(clause = "IS_ACTIVE = 1")
public class BinaryFile extends AbstractBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PK_BINARY_FILE")
	private Long id;

	@Lob
	@Column(name = "BINARY_FILE")
	private byte[] binaryFile;

	@Column(columnDefinition = "tinyint(1) default 1", name = "IS_ACTIVE")
	private boolean isActive = true;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getBinaryFile() {
		return binaryFile;
	}

	public void setBinaryFile(byte[] binaryFile) {
		this.binaryFile = binaryFile;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
