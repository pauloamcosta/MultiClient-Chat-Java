package br.com.sockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Trabalho da Unidade 2 - Sistemas Distribuídos (Sockets) - Bate Papo retornando data e hora do servidor
 * 
 * Aluno: Paulo André de Melo Costa --- Matrícula: 201522666
 * 
 */

public class Servidor {

	private int porta;
	private List<PrintStream> clientes;

	public Servidor(int porta) {
		this.porta = porta;
		this.clientes = new ArrayList<PrintStream>();
	}

	public void executa() throws IOException {
		try {
			ServerSocket servidor = new ServerSocket(this.porta);
			System.out.println("Porta " + this.porta + " aberta!");

			while (true) {
				// aceita um cliente
				Socket cliente = servidor.accept();
				System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());

				// adiciona saida do cliente à lista
				PrintStream ps = new PrintStream(cliente.getOutputStream());
				this.clientes.add(ps);

				// cria tratador de cliente numa nova thread
				TrataCliente tc = new TrataCliente(cliente.getInputStream(), this);
				new Thread(tc).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void distribuiMensagem(String msg) {
		for (PrintStream cliente : this.clientes) {
			try {
				// Envia para todos mensagem com a data e hora do servidor
				Locale localeBR = new Locale("pt", "BR");
				SimpleDateFormat fmt = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'as' HH:mm:ss" , localeBR);
				cliente.println(msg);
				cliente.println("Data: " + fmt.format(new Date()) + "\n");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}