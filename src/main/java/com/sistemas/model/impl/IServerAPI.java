package com.sistemas.model.impl;

import com.sistemas.controller.dto.Endereco;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerAPI extends Remote {
    Endereco getViacepAPI(String cep) throws RemoteException;
}
