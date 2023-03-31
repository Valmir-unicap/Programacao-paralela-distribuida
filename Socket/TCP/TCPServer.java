package com.company;

import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String args[]) throws Exception {

        int contador = 0;

        // Cria um objeto ServerSocket na porta 6789
        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("Servidor iniciado");

        // Aguarda a conexão de clientes indefinidamente
        while (contador == 0) {
            // Aguarda a conexão de um cliente e cria um novo objeto Socket para lidar com ele
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Cliente conectado: " + connectionSocket);

            // Cria os streams de entrada e saída para o Socket
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            // Lê a mensagem enviada pelo cliente
            String clientSentence = inFromClient.readLine();
            System.out.println("Mensagem recebida do cliente: " + clientSentence);

            // Converte a mensagem para maiúsculas
            String capitalizedSentence = clientSentence.toUpperCase() + '\n';

            // Envia a mensagem em maiúsculas de volta para o cliente
            outToClient.writeBytes(capitalizedSentence);
            System.out.println("Mensagem enviada para o cliente: " + capitalizedSentence);

            // Fecha o Socket para este cliente
            connectionSocket.close();
            System.out.println("Cliente desconectado: " + connectionSocket);
            welcomeSocket.close();
            System.out.println("Servidor desconectado: " + welcomeSocket);

            contador++;
        }
    }
}
