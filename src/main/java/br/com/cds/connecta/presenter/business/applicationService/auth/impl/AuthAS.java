package br.com.cds.connecta.presenter.business.applicationService.auth.impl;

import br.com.cds.connecta.framework.core.context.ConnectaConfigurationService;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.presenter.business.applicationService.auth.IAuthAS;
import java.util.Observable;
import java.util.Observer;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AuthAS implements IAuthAS, Observer {
    
    private ConnectaConfigurationService connectaConfigurationService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthAS.class);
    private static final String ENDPOINT_URL = "/user/validarToken";
    
    private String connectaPortalUrl = "";

    @Override
    public boolean validateToken(String token) {
        connectaPortalUrl = connectaConfigurationService.getConfiguration().getConnectaPortalBackend();
        
        try {
            HttpURLConnection urlCon
                    = (HttpURLConnection) new URL(connectaPortalUrl + ENDPOINT_URL).openConnection();
            urlCon.addRequestProperty("Authorization", token);

            int status = urlCon.getResponseCode();

            if (status != HttpStatus.OK.value()) {
                return false;
            }
        } catch (IOException e) {
            LOGGER.error("Não foi possível se conectar ao Portal");
            return false;
        }

        return true;
    }

    @Autowired
    public void setConnectaConfigurationService(ConnectaConfigurationService connectaConfigurationService) {
        this.connectaConfigurationService = connectaConfigurationService;
        
        this.connectaConfigurationService.addObserver(this);
    }

    @Override
    public void update(Observable o, Object o1) {
        connectaPortalUrl = connectaConfigurationService.getConfiguration().getConnectaPortalBackend();
    }

}
