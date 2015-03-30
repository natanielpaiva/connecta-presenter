package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.FileSingleSource;
import java.util.List;

import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.UrlSingleSource;
import br.com.cds.connecta.presenter.filter.SingleSourceFilter;
import java.io.IOException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ISingleSourceAS {

    abstract SingleSource get(Long id);

    abstract List<SingleSource> list() throws Exception;

    abstract SingleSource saveOrUpdate(SingleSource entity) throws Exception;

    abstract void delete(Long id) throws Exception;

    abstract void delete(SingleSource singleSource) throws Exception;

    void validate(FileSingleSource fileSingleSource);

    void validate(UrlSingleSource url);
    
    void preValidate(FileSingleSource fileSingleSource, MultipartFile file) throws IOException;

    FileSingleSource getFileWithBinary(Long id);
    
    List<SingleSource> getByAttributeId(Long id);
    
    public Page<SingleSource> listAutoComplete(SingleSourceFilter filter);

}
