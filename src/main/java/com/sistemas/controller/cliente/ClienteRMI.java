package com.sistemas.controller.cliente;

import com.sistemas.controller.dto.Endereco;
import com.sistemas.model.impl.IServerAPI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteRMI {
    public static void main(String[] args) throws NotBoundException, RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IServerAPI serverAPI = (IServerAPI) registry.lookup("ViaCepService");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Informe seu CEP: ");
            String cep = scanner.nextLine();
            System.out.println("consultando...");
            Endereco endereco = serverAPI.getViacepAPI(cep);

            System.out.println("=================  R E S P O N S E  =================");
            System.out.println(endereco);
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
