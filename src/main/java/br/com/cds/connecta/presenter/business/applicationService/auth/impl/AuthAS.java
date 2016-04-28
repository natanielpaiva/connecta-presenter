package br.com.cds.connecta.presenter.business.applicationService.auth.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.presenter.business.applicationService.auth.IAuthAS;

@Service
public class AuthAS implements IAuthAS {
	
	private static final String SERVER_URL = "http://localhost:8080/connecta-portal/";
	private static final String ENDPOINT_URL = "user/validarToken";
	

	public boolean validateToken(String token){
		
		try {
			HttpURLConnection urlCon = 
					(HttpURLConnection) new URL(SERVER_URL+ENDPOINT_URL).openConnection();
			urlCon.addRequestProperty("Authorization", token);
			
			int status = urlCon.getResponseCode();
			
			if(status != HttpStatus.OK.value()){
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
