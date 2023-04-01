package com.company;

import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String args[]) throws Exception {

        int contador = 0;

        // Cria um objeto ServerSocket na porta 6789
        final int PORTA = 6789;
        ServerSocket servidorSocket = new ServerSocket(PORTA);
        System.out.println("Socket servidor iniciada!");
        System.out.println("Servidor iniciado na porta = " + PORTA);

        // Aguarda a conexão de clientes indefinidamente
        while (contador == 0) {

            // Aguarda a conexão de um cliente e cria um novo objeto Socket para lidar com ele
            Socket socketCliente = servidorSocket.accept();
            System.out.println("Cliente conectado: " + socketCliente);

            // Cria os streams de entrada e saída para o Socket
            BufferedReader entradaDoCliente = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            DataOutputStream saidaParaCliente = new DataOutputStream(socketCliente.getOutputStream());

            // Lê a mensagem enviada pelo cliente
            String mensagemDoCliente = entradaDoCliente.readLine();
            System.out.println("Mensagem recebida do cliente: " + mensagemDoCliente);

            // Converte a mensagem para maiúsculas
            String mensagemEmMaiusculas = mensagemDoCliente.toUpperCase() + '\n';

            // Envia a mensagem em maiúsculas de volta para o cliente
            saidaParaCliente.writeBytes(mensagemEmMaiusculas);
            System.out.println("Mensagem enviada para o cliente: " + mensagemEmMaiusculas);

            // Fecha o Socket para este cliente
            socketCliente.close();
            System.out.println("Cliente desconectado: " + socketCliente);
            servidorSocket.close();
            System.out.println("Servidor desconectado: " + servidorSocket);
            System.out.println("");
            System.out.println("Socket servidor finalizada!");
            contador++;
        }
    }
}
