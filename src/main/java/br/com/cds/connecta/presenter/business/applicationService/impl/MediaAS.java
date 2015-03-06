package br.com.cds.connecta.presenter.business.applicationService.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.presenter.business.applicationService.IMediaAS;
import br.com.cds.connecta.presenter.entity.BinaryFile;
import br.com.cds.connecta.presenter.entity.FileSingleSource;
import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.SingleSourceAttribute;
import br.com.cds.connecta.presenter.entity.UrlSingleSource;
import br.com.cds.connecta.presenter.persistence.IFileSingleSourceDAO;
import br.com.cds.connecta.presenter.persistence.ISingleSourceDAO;
import java.io.IOException;
import java.util.Arrays;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaAS extends AbstractBaseAS<SingleSource> implements IMediaAS {

    @Autowired
    private ISingleSourceDAO singleSourceDAO;

    @Autowired
    private IFileSingleSourceDAO fileSingleSourceDAO;

    @Override
    public List<SingleSource> list() throws Exception {
        return singleSourceDAO.list();
    }

    @Override
    public SingleSource saveOrUpdate(SingleSource singleSource) throws Exception {
        return singleSourceDAO.saveOrUpdate(singleSource);
    }

    @Override
    public void delete(Long id) throws Exception {
        singleSourceDAO.delete(id);
    }

    @Override
    public void delete(SingleSource entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SingleSource get(Long id) {
        final SingleSource singleSource = singleSourceDAO.get(id);
        final List<SingleSourceAttribute> singleSourceAttributes = singleSource.getSingleSourceAttributes();
        Hibernate.initialize(singleSourceAttributes);
        for (SingleSourceAttribute singleSourceAttribute : singleSourceAttributes) {
            Hibernate.initialize(singleSourceAttribute.getAttribute());
        }
        return singleSource;
    }

    @Override
    public void validate(FileSingleSource fileSingleSource) {
    }

    @Override
    public void validate(UrlSingleSource url) {
    }

    @Override
    public void preValidate(FileSingleSource fileSingleSource, MultipartFile file) throws IOException {

        //Verifica se é edição, caso sim ele faz outras verificações
        if (fileSingleSource.getId() != null) {
            FileSingleSource fileSingleSourceBD = fileSingleSourceDAO
                    .getWithBinary(fileSingleSource.getId());
            //Verifica se o usuário colocou algum arquivo na edição
            if (file != null) {
                //Verifica se o arqruivo que o usuário tem no banco é o mesmo que ele colocou para upar
                if (Arrays.equals(fileSingleSourceBD.getBinaryFile().getBinaryFile(), file.getBytes())) {
                    fileSingleSource.setBinaryFile(fileSingleSourceBD.getBinaryFile());
                } else {
                    fileSingleSource.setBinaryFile(fileSingleSourceBD.getBinaryFile());
                    fileSingleSource.getBinaryFile().setBinaryFile(file.getBytes());
                }
            } else {
                fileSingleSource.setBinaryFile(fileSingleSourceBD.getBinaryFile());
                fileSingleSource.setFileType(fileSingleSourceBD.getFileType());
            }

        } else {
            fileSingleSource.setBinaryFile(new BinaryFile());
            fileSingleSource.getBinaryFile().setBinaryFile(file.getBytes());
        }
    }

    @Override
    public FileSingleSource getFileWithBinary(Long id) {
        return fileSingleSourceDAO.getWithBinary(id);
    }

}
