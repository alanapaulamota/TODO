package com.alana.upis.padroesPS.poolConexoes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PoolDeConexoesTeste {

	public void executar() {
		System.out.println("Iniciando execucao ...");
		System.out.println("INFO: " + PoolDeConexoes.instance().info());
		int qtde = 20;

		for (int i = 0; i < qtde; i++) {
			String nome = String.format("cliente-%02d", i);

			new Thread(new Executavel(nome)).start();
		}
	}

	public static void main(String[] args) {
		new PoolDeConexoesTeste().executar();
	}

	class Executavel implements Runnable {
		private String nome;

		public Executavel(String nome) {
			this.nome = nome;
		}

		public void run() {
			long random = (long) (Math.random() + 1) * 1500;
			try {
				Thread.sleep(random);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			System.out.printf("[%s] Iniciando ...%n", nome);
			Connection conexao = null;
			try {

				System.out.printf("[%s] Recuperando conexao ...%n", nome);
				conexao = PoolDeConexoes.instance().getConexao();

				String sql = "select nome from tbclientes";
				System.out.printf("[%s] Executando query%n", nome);
				try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

					if (rs.next()) {
						String nome = rs.getString("nome");
						System.out.printf("[%s] nome: '%s'%n", nome, nome);
					}
				}

			} catch (Exception e) {
				System.out.printf("[%s] Excecao: %s :: %s%n", nome, e.getMessage(), PoolDeConexoes.instance().info());
			} finally {
				if (conexao != null) {
					System.out.printf("[%s] Devolvendo conexao%n", nome);
					PoolDeConexoes.instance().devolverConexao(conexao);
					System.out.println("INFO: " + PoolDeConexoes.instance().info());
				}
			}

		}
	}

}
