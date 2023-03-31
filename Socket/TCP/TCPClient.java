package com.company;

import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String args[]) throws Exception {
        // Cria um BufferedReader para ler entradas do usuário a partir do console
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // Define o endereço e porta do servidor ao qual o cliente se conectará
        int porta = 6789;
        String servidor = "localhost";

        System.out.println("Conectando ao servidor " + servidor + ":" + porta);

        // Cria um novo objeto Socket para se conectar ao servidor
        Socket clientSocket = new Socket(servidor, porta);

        // Cria um DataOutputStream para enviar dados ao servidor através do Socket
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // Cria um BufferedReader para ler dados enviados pelo servidor através do Socket
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Lê uma mensagem do usuário a partir do console
        System.out.print("Digite a mensagem a ser enviada para o servidor:");
        String sentence = inFromUser.readLine();

        // Envia a mensagem ao servidor através do Socket
        outToServer.writeBytes(sentence + '\n');

        // Lê a resposta do servidor através do Socket
        String modifiedSentence = inFromServer.readLine();
        System.out.println("Resposta recebida do servidor: " + modifiedSentence);

        // Fecha o Socket do cliente
        clientSocket.close();
    }
}
