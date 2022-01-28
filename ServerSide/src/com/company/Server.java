package com.company;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject
        implements TemperatureTrackingInterface{

    Integer Temp;
    ArrayList<Integer> Temps;
    TimeStamp serverTimeStamp;
    // Default constructor to throw RemoteException
    // from its parent constructor
    Server() throws RemoteException
    {
        super();
        Temp = 0;
        Temps = new ArrayList<>();
        Temps.add(Temp);
        serverTimeStamp = new TimeStamp(0);
    }

    public void increaseTemp(Integer value, TimeStamp clientTimeStamp) throws RemoteException{
        System.out.println("increase command came by " + value);
        Temp+=value;
        Temps.add(Temp);
        System.out.println("--- time stamp: " + serverTimeStamp.getTime());
        System.out.println("--- client time stamp: " + clientTimeStamp.getTime());
        serverTimeStamp.adapt(clientTimeStamp);
        System.out.println("--- time stamp: " + serverTimeStamp.getTime());
    }
    public void decreaseTemp(Integer value, TimeStamp clientTimeStamp) throws RemoteException{
        System.out.println("increase command came by " + value);
        Temp-=value;
        Temps.add(Temp);
        System.out.println("--- time stamp: " + serverTimeStamp.getTime());
        System.out.println("--- client time stamp: " + clientTimeStamp.getTime());
        serverTimeStamp.adapt(clientTimeStamp);
        System.out.println("--- time stamp: " + serverTimeStamp.getTime());
    }
    public ReplyMessage<Integer> readTemp(TimeStamp clientTimeStamp) throws RemoteException{
        System.out.println("read command came, temperature: " + Temp);
        System.out.println("--- time stamp: " + serverTimeStamp.getTime());
        System.out.println("--- client time stamp: " + clientTimeStamp.getTime());
        serverTimeStamp.adapt(clientTimeStamp);
        System.out.println("--- time stamp: " + serverTimeStamp.getTime());
        return new ReplyMessage<>(Temp, serverTimeStamp);

    }
    public ReplyMessage<Float> avgTemp(TimeStamp clientTimeStamp) throws RemoteException{
        Float sum = 0.0F;
        for(Integer e: Temps){
            sum+=e;
        }
        Float result = sum/Temps.size();

        System.out.println("avg command came, avg: " + result);
        System.out.println("--- time stamp: " + serverTimeStamp.getTime());
        System.out.println("--- client time stamp: " + clientTimeStamp.getTime());
        serverTimeStamp.adapt(clientTimeStamp);
        System.out.println("--- time stamp: " + serverTimeStamp.getTime());

        return new ReplyMessage<>(result, serverTimeStamp);
    }
}

