package br.com.sockets;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Trabalho da Unidade 2 - Sistemas Distribuídos (Sockets) - Bate Papo retornando data e hora do servidor
 * 
 * Aluno: Paulo André de Melo Costa --- Matrícula: 201522666
 * 
 */

public class RunCliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Digite seu nome: ");
		Scanner teclado = new Scanner(System.in);
		String clienteNome = teclado.nextLine();
		new Cliente("127.0.0.1", 12345, clienteNome).executa();

	}
}
