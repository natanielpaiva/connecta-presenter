package br.com.cds.connecta.framework.core.controller.common;

import br.com.cds.connecta.framework.core.bean.message.MessageModel;
import br.com.cds.connecta.framework.core.domain.MessageTypeEnum;
import br.com.cds.connecta.framework.core.exception.ResourceNotFoundException;
import br.com.cds.connecta.presenter.BaseTest;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public class InitControllerTest extends BaseTest {
    
    @Autowired InitControllerChild controller;
    
    @Test
    public void handleNotFound() {
        ResourceNotFoundException exception = new ResourceNotFoundException("br.com.cds.connecta.presenter.entity.Viewer");
        
        ResponseEntity<MessageModel> responseEntity = controller.handleException(exception);
        
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(responseEntity.hasBody(), is(true));
        assertThat(responseEntity.getBody().getType(), is(MessageTypeEnum.ERROR));
        assertThat(responseEntity.getBody().getMessage(), is("Registro de Visualizador não encontrado."));
    }
    
}
