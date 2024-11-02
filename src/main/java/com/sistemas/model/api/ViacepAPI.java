package com.sistemas.model.api;

import com.google.gson.Gson;
import com.sistemas.controller.dto.Endereco;
import com.sistemas.model.impl.IServerAPI;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ViacepAPI extends UnicastRemoteObject implements IServerAPI {

    public ViacepAPI() throws RemoteException {
        super();
    }

    @Override
    public Endereco getViacepAPI(String cep) throws RemoteException {
        Endereco endereco = null;
        HttpGet request = new HttpGet("https://viacep.com.br/ws/"+cep+"/json/");
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
             CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null ) {
                String result = EntityUtils.toString(entity);
                Gson gson = new Gson();
                endereco = gson.fromJson(result, Endereco.class);
            }
            return endereco;
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
