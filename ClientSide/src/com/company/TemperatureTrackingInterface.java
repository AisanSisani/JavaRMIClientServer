package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TemperatureTrackingInterface extends Remote {
    void increaseTemp(Integer value, TimeStamp clientTimeStamp) throws RemoteException;
    void decreaseTemp(Integer value, TimeStamp clientTimeStamp) throws RemoteException;
    ReplyMessage<Integer> readTemp(TimeStamp clientTimeStamp) throws RemoteException;
    ReplyMessage<Float> avgTemp(TimeStamp clientTimeStamp) throws RemoteException;
}
