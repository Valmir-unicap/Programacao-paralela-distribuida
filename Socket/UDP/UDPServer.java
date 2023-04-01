package com.company;

import java.io.IOException;
import java.net.*;

class UDPServer {
    private static final int PORTA = 9876;
    private static final int NUM_CONN = 1;

    public static void main(String[] args) {
        
        try (DatagramSocket serverSocket = new DatagramSocket(PORTA)) {
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            while (true) {

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                System.out.println("Socket server iniciado!");

                serverSocket.receive(receivePacket);
                System.out.print("Datagrama UDP [" + NUM_CONN + "] recebido...");

                String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println(sentence);

                InetAddress ipAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                String capitalizedSentence = sentence.toUpperCase();
                sendData = capitalizedSentence.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                serverSocket.send(sendPacket);

                System.out.println("Enviando " + capitalizedSentence + "...");
                System.out.println("Socket server fechado!");
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar ou fechar o DatagramSocket: " + e.getMessage());
        }
    }
}
