package com.company;

// Java program for client application

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Client
{
    public void main()
    {
        try
        {
            int option;
            Scanner sc= new Scanner(System.in);
            boolean exit = Boolean.FALSE;
            while(!exit) {
                System.out.println("Client Side");
                System.out.println("1. Interactive Client");
                System.out.println("2. Simulation Client");
                System.out.println("3. Exit");
                System.out.println("Choose an option:");
                option = sc.nextInt();
                switch (option) {
                    case 1:
                        Menu();
                        break;
                    case 2:
                        Simulation();
                        break;
                    case 3:
                        exit = Boolean.TRUE;
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("Enter a valid option.");
                }
            }
        }
        catch(Exception ae)
        {
            System.out.println(ae);
        }
    }

    void Simulation() throws MalformedURLException, NotBoundException, RemoteException, InterruptedException {
        TimeStamp clientTimeStamp = new TimeStamp(0);
        Random rand = new Random(); //instance of random class

        // lookup method to find reference of remote object
        TemperatureTrackingInterface access =
                (TemperatureTrackingInterface)Naming.lookup("rmi://localhost:1900"+
                        "/aisansisani");

        System.out.println("\nSimulation");
        for (int i=0; i<24; i++){
            System.out.println("--- time stamp: " + clientTimeStamp.getTime());
            sleep(1000);
            System.out.println("An hour passed");
            clientTimeStamp.localIncrease();
            System.out.println("--- time stamp: " + clientTimeStamp.getTime());
            int temperature_random;
            ReplyMessage<Integer> replyTemp;
            int int_random = rand.nextInt(3);
            switch (int_random){
                case 0:
                    temperature_random = rand.nextInt(101);
                    System.out.println("client command to increase temp by " + temperature_random);
                    access.increaseTemp(temperature_random, clientTimeStamp);
                    break;
                case 1:
                    temperature_random = rand.nextInt(101);
                    System.out.println("client command to decrease temp by " + temperature_random);
                    access.decreaseTemp(temperature_random, clientTimeStamp);
                    break;
                case 2:
                    replyTemp = access.readTemp(clientTimeStamp);
                    System.out.println("--- server time stamp: " + replyTemp.getTimeStamp().getTime());
                    clientTimeStamp.adapt(replyTemp.getTimeStamp());
                    System.out.println("--- time stamp: " + clientTimeStamp.getTime());
                    System.out.println("client reads temp2erature as " + replyTemp.getData());
                    break;
            }

        }
        ReplyMessage<Float> replyAvg;
        replyAvg = access.avgTemp(clientTimeStamp);
        System.out.println("--- server time stamp: " + replyAvg.getTimeStamp().getTime());
        clientTimeStamp.adapt(replyAvg.getTimeStamp());
        System.out.println("--- time stamp: " + clientTimeStamp.getTime());
        System.out.println("Average Temperature is: " + replyAvg.getData());
    }

    void Menu() throws MalformedURLException, NotBoundException, RemoteException {
        TimeStamp clientTimeStamp = new TimeStamp(0);

        // lookup method to find reference of remote object
        TemperatureTrackingInterface access =
                (TemperatureTrackingInterface)Naming.lookup("rmi://localhost:1900"+
                        "/aisansisani");

        int option;
        int value;
        ReplyMessage<Integer> replyTemp;
        ReplyMessage<Float> replyAvg;
        Scanner sc= new Scanner(System.in);

        boolean exit = Boolean.FALSE;
        while(!exit){
            System.out.println("\nInteractive");
            clientTimeStamp.localIncrease();
            System.out.println("--- increasing local time stamp");
            System.out.println("--- time stamp: " + clientTimeStamp.getTime());

            System.out.println("1. Increase Temperature");
            System.out.println("2. Decrease Temperature");
            System.out.println("3. Read Temperature");
            System.out.println("4. Average Temperature");
            System.out.println("5. Exit");
            System.out.println("Choose an option:");
            option = sc.nextInt();
            switch (option){
                case 1:
                    System.out.println("Enter value:");
                    value = sc.nextInt();
                    access.increaseTemp(value, clientTimeStamp);
                    System.out.println("--- time stamp: " + clientTimeStamp.getTime());
                    break;
                case 2:
                    System.out.println("Enter value:");
                    value = sc.nextInt();
                    access.decreaseTemp(value, clientTimeStamp);
                    System.out.println("--- time stamp: " + clientTimeStamp.getTime());

                    break;
                case 3:
                    System.out.println("--- time stamp: " + clientTimeStamp.getTime());
                    replyTemp = access.readTemp(clientTimeStamp);
                    System.out.println("--- server time stamp: " + replyTemp.getTimeStamp().getTime());
                    clientTimeStamp.adapt(replyTemp.getTimeStamp());
                    System.out.println("--- time stamp: " + clientTimeStamp.getTime());
                    System.out.println("Temperature is: " + replyTemp.getData());
                    break;
                case 4:
                    System.out.println("--- time stamp: " + clientTimeStamp.getTime());
                    replyAvg = access.avgTemp(clientTimeStamp);
                    System.out.println("--- server time stamp: " + replyAvg.getTimeStamp().getTime());
                    clientTimeStamp.adapt(replyAvg.getTimeStamp());
                    System.out.println("--- time stamp: " + clientTimeStamp.getTime());
                    System.out.println("Average Temperature is: " + replyAvg.getData());
                    break;
                case 5:
                    exit = Boolean.TRUE;
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Enter a valid option.");
            }
        }
    }
}