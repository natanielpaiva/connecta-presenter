package br.com.cds.connecta.presenter.business.applicationService.impl;

import br.com.cds.connecta.framework.core.exception.ResourceNotFoundException;
import java.util.List;

import org.springframework.stereotype.Service;

import static br.com.cds.connecta.framework.core.util.Util.isNotNull;
import static br.com.cds.connecta.framework.core.util.Util.isNull;
import br.com.cds.connecta.presenter.business.applicationService.ISingleSourceAS;
import br.com.cds.connecta.presenter.domain.FileExtensionEnum;
import br.com.cds.connecta.presenter.entity.Attribute;
import br.com.cds.connecta.presenter.entity.BinaryFile;
import br.com.cds.connecta.presenter.entity.FileSingleSource;
import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.SingleSourceAttribute;
import br.com.cds.connecta.presenter.entity.UrlSingleSource;
import br.com.cds.connecta.presenter.filter.SingleSourceFilter;
import br.com.cds.connecta.presenter.persistence.IFileSingleSourceDAO;
import br.com.cds.connecta.presenter.persistence.ISingleSourceDAO;
import br.com.cds.connecta.presenter.persistence.SingleSourceRepository;
import br.com.cds.connecta.presenter.persistence.impl.BulkActionsRepository;
import java.io.IOException;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.io.FilenameUtils;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SingleSourceAS implements ISingleSourceAS {

    @Autowired
    private BulkActionsRepository bulk;

    @Autowired
    private SingleSourceRepository singleSourceListRepository;

    @Autowired
    private ISingleSourceDAO singleSourceDAO;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private IFileSingleSourceDAO fileSingleSourceDAO;

    @Override
    public List<SingleSource> list() throws Exception {
        return singleSourceListRepository.findAll();
    }

    @Override
    public Page<SingleSource> listAutoComplete(SingleSourceFilter filter) {
        String name = filter.getName();
        if (isNull(name)) {
            name = "";
        }

        return singleSourceListRepository.findByName("%" + name.toUpperCase() + "%", filter.makePageable());

    }

    @Override
    public SingleSource saveOrUpdate(SingleSource singleSource) throws Exception {
        refreshAttribute(singleSource);
        return singleSourceListRepository.save(singleSource);
    }

    private void refreshAttribute(SingleSource singleSource) {
        if (isNotEmpty(singleSource.getSingleSourceAttributes())) {
            for (SingleSourceAttribute singleSourceAttribute : singleSource.getSingleSourceAttributes()) {
                if (isNotNull(singleSourceAttribute.getAttribute()) && isNotNull(singleSourceAttribute.getAttribute().getId())) {
                    Attribute merge = em.merge(singleSourceAttribute.getAttribute());
                    singleSourceAttribute.setAttribute(merge);
                }
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        singleSourceListRepository.delete(id);
    }

    @Override
    public void delete(SingleSource entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SingleSource get(Long id) {
        SingleSource singlesource;
        
        try {
            singlesource = singleSourceDAO.getWithAttributes(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException(SingleSource.class.getCanonicalName());
        }

        return singlesource;
    }

    @Override
    public void validate(FileSingleSource fileSingleSource) {
    }

    @Override
    public void validate(UrlSingleSource url) {
    }

    @Override
    public void preValidate(FileSingleSource fileSingleSource, MultipartFile file) throws IOException {

        //Verifica se é edição, caso si\m ele faz outras verificações
        if (fileSingleSource.getId() != null) {
            FileSingleSource fileSingleSourceBD = fileSingleSourceDAO
                    .getWithBinary(fileSingleSource.getId());
            //Verifica se o usuário colocou algum arquivo na edição
            if (file != null) {

                fileSingleSource.setFileType(FileExtensionEnum.valueOf(
                        FilenameUtils.getExtension(fileSingleSource.getFilename()).toUpperCase()
                ));

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
            fileSingleSource.setFileType(FileExtensionEnum.valueOf(
                    FilenameUtils.getExtension(fileSingleSource.getFilename()).toUpperCase()
            ));

            fileSingleSource.setBinaryFile(new BinaryFile());
            fileSingleSource.getBinaryFile().setBinaryFile(file.getBytes());
        }
    }

    @Override
    public FileSingleSource getFileWithBinary(Long id) {
        return fileSingleSourceDAO.getWithBinary(id);
    }

    @Override
    public List<SingleSource> getByAttributeId(Long id) {
        return singleSourceDAO.getByAttributeId(id);
    }

    @Override
    public void deleteAll(List<Long> ids) {
        bulk.delete(SingleSource.class, ids);
    }

}
