package com.company;

import java.io.*;
import java.net.*;

class TCPServer {

    public static void main(String args[]) throws Exception {
        String clientSentence;

        String capitalizedSentence;

        ServerSocket welcomeSocket = new ServerSocket(6789);

        System.out.println("Conecção inicializada!");

        System.out.println("ponto 1 ");

        Socket connectionSocket = welcomeSocket.accept();

        while (true) {
            System.out.println("ponto 2");
            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(
                            connectionSocket.getInputStream()
                    )
            );
            DataOutputStream outToClient = new DataOutputStream(
                    connectionSocket.getOutputStream()
            );

            System.out.println("ponto 3");
            clientSentence = inFromClient.readLine();

            System.out.println("ponto 4");
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
            connectionSocket.close();

            System.out.println("Conecção finalizada!");
        }
    }
}
