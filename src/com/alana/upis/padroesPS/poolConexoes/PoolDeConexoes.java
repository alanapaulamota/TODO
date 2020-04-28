package com.alana.upis.padroesPS.poolConexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PoolDeConexoes {
	private static volatile PoolDeConexoes instance;

	private String url = "jdbc:mysql://localhost:3306/LojaManutencao";
	private String user = "root";
	private String senha = "123";

	private int qtdeConexoes = 5;
	private Set<Connection> conexoes;
	private Set<Connection> conexoesEmUso;

	private PoolDeConexoes() throws SQLException, ClassNotFoundException {
		conexoes = Collections.synchronizedSet(new HashSet<>());
		conexoesEmUso = Collections.synchronizedSet(new HashSet<>());
		for (int i = 0; i < qtdeConexoes; i++) {
			abrirConexao();
		}
	}

	public static PoolDeConexoes instance() {
		if (instance == null) {
			synchronized (PoolDeConexoes.class) {
				if (instance == null) {
					try {
						instance = new PoolDeConexoes();
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
						throw new RuntimeException("Erro ao iniciar pool", e);
					}
				}
			}
		}
		return instance;
	}

	private void abrirConexao() throws SQLException, ClassNotFoundException {
		System.err.println("Abrindo conexao ...");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, user, senha);
		System.err.println("conexao="+connection);
		conexoes.add(connection);
	}

	public Connection getConexao() {
		synchronized (conexoes) {
			if (conexoes.isEmpty()) {
				throw new RuntimeException("Nao ha conexoes disponiveis");
			}
			Connection conexao = conexoes.iterator().next();
			conexoes.remove(conexao);
			synchronized (conexoesEmUso) {
				conexoesEmUso.add(conexao);
			}
			return conexao;
		}
	}

	public void devolverConexao(Connection conexao) {
		synchronized (conexoesEmUso) {
			conexoesEmUso.remove(conexao);
			synchronized (conexoes) {
				conexoes.add(conexao);
			}
		}
	}

	public int qtdeConexoesDiponiveis() {
		return conexoes.size();
	}
	
	public int qtdeConexoesEmUso() {
		return conexoesEmUso.size();
	}
	
	public String info() {
		return String.format("Conexoes: disponiveis=%d, uso=%d", qtdeConexoesDiponiveis(), qtdeConexoesEmUso());
	}
}
