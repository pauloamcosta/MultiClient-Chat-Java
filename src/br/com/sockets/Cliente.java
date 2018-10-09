package br.com.sockets;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Trabalho da Unidade 2 - Sistemas Distribuídos (Sockets) - Bate Papo retornando data e hora do servidor
 * 
 * Aluno: Paulo André de Melo Costa --- Matrícula: 201522666
 * 
 * Este trabalho foi realizado baseado no exemplo presente no material passado como referência para a atividade
 * adicionando melhorias no código e novas funcionalidades (entre elas o pedido na atividade: Retorno de data e hora).
 * 
 * Trata-se de um servidor que aceita vários clientes ao mesmo tempo, que podem se comunicar entre eles, exibindo o nick
 * informado pelo cliente e a hora e data em que a mensagem foi mandada.
 * 
 * Para rodar a aplicação, primeiro rodar a classe RunServidor, em seguida, rodar RunClient para cada cliente
 * que desejar conectar.
 * 
 */

public class Cliente {

	private String host;
	private int porta;
	private String nome;

	public Cliente(String host, int porta, String nome) {
		this.host = host;
		this.porta = porta;
		this.nome = nome;
	}

	public void executa() throws UnknownHostException, IOException {

		try {
			Socket cliente = new Socket(this.host, this.porta);
			System.out.println("O Cliente " + this.nome + " se conectou ao servidor!");

			Recebedor r = new Recebedor(cliente.getInputStream());
			new Thread(r).start();

			Scanner teclado = new Scanner(System.in);
			PrintStream saida = new PrintStream(cliente.getOutputStream());

			OutputStream out = cliente.getOutputStream();
			byte[] b = nome.getBytes();
			out.write(b);

			while (teclado.hasNextLine()) {
				String texto = nome + ": " + teclado.nextLine();
				saida.println(texto);
			}

			saida.close();
			teclado.close();
			cliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}