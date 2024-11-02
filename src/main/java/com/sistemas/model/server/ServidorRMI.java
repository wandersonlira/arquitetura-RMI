package com.sistemas.model.server;

import com.sistemas.model.api.ViacepAPI;
import com.sistemas.model.impl.IServerAPI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {
    public static void main(String[] args) {
        try {
            IServerAPI viacepAPI = new ViacepAPI();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ViaCepService", viacepAPI);
            System.out.println("Servidor RMI está pronto.");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
