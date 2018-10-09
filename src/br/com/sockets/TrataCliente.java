package br.com.sockets;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Trabalho da Unidade 2 - Sistemas Distribuídos (Sockets) - Bate Papo retornando data e hora do servidor
 * 
 * Aluno: Paulo André de Melo Costa --- Matrícula: 201522666
 * 
 */

public class TrataCliente implements Runnable {

    private InputStream cliente;
    private Servidor servidor;

    public TrataCliente(InputStream cliente, Servidor servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    public void run() {
        // Distribuidor de mensagens
        Scanner s = new Scanner(this.cliente);
        while (s.hasNextLine()) {
            servidor.distribuiMensagem(s.nextLine());
        }
        s.close();
    }
}