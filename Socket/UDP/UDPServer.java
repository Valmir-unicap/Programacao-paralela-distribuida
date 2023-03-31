package com.company;

import java.io.*;
import java.net.*;

class UDPServer {
    public static void main(String args[]) throws Exception {

        int porta = 9876;
        int numConn = 1;
        int contador = 0;

        DatagramSocket serverSocket = new DatagramSocket(porta);

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (contador == 0) {

            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            System.out.println("Esperando por datagrama UDP na porta " + porta);
            serverSocket.receive(receivePacket);
            System.out.print("Datagrama UDP [" + numConn + "] recebido...");

            String sentence = new String(receivePacket.getData());

            System.out.println(sentence);

            InetAddress IPAddress = receivePacket.getAddress();

            int port = receivePacket.getPort();

            String capitalizedSentence = sentence.toUpperCase();

            sendData = capitalizedSentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, port);

            System.out.println("Enviando " + capitalizedSentence + "...");

            serverSocket.send(sendPacket);

            System.out.println("Socket server fechado!");
            
            contador++;
        }
    }
}
