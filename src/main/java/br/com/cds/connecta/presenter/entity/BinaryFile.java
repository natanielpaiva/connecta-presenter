package br.com.cds.connecta.presenter.entity;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import java.sql.Clob;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TB_BINARY_FILE")
@DynamicUpdate
public class BinaryFile extends AbstractBaseEntity {

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
