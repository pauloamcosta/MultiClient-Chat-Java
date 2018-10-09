package br.com.sockets;

import java.io.IOException;

/**
 * Trabalho da Unidade 2 - Sistemas Distribuídos (Sockets) - Bate Papo retornando data e hora do servidor
 * 
 * Aluno: Paulo André de Melo Costa --- Matrícula: 201522666
 * 
 */

public class RunServidor {
	public static void main(String[] args) 
			throws IOException {
		new Servidor(12345).executa();
	}
}
