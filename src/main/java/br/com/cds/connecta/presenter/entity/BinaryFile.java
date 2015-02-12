package br.com.cds.connecta.presenter.entity;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="TB_BINARY_FILE")
@NamedQuery(name="BinaryFile.findAll", query="SELECT b FROM BinaryFile b")
public class BinaryFile extends AbstractBaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "TB_BINARY_FILE_SEQ", sequenceName = "TB_BINARY_FILE_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_BINARY_FILE_SEQ")
    @Column(name = "PK_BINARY_FILE")
    private Long id;
    
    @Lob
    @Column(name = "BINARY_FILE")
    private byte[] binaryFile;

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
    
}
