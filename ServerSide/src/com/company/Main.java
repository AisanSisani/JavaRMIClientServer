package com.company;

import com.company.Server;
import com.company.TemperatureTrackingInterface;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Main {

    public static void main(String[] args)
    {
        try
        {
            // Create an object of the interface
            // implementation class
            TemperatureTrackingInterface obj = new Server();

            // rmiregistry within the server JVM with
            // port number 1900
            LocateRegistry.createRegistry(1900);

            // Binds the remote object by the name
            // aisansisani
            Naming.rebind("rmi://localhost:1900"+
                    "/aisansisani",obj);

            System.out.println("Server is Running");
        }
        catch(Exception ae)
        {
            System.out.println(ae);
        }
    }
}
