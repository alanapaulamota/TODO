package com.alana.upis.padroesPS.poolConexoes.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import com.alana.upis.padroesPS.poolConexoes.PoolDeConexoes;

public class Principal {
	public static void main(String[] args) throws SQLException {
//
//		try (Connection connection = new ConnectionFactory().getConnection()) {
//
//			String sql = "insert into Empregados" + "(nome,dataNasc, salario)" + " values (?,?,?,?)";
//
//			PreparedStatement stmt = connection.prepareStatement(sql);
//
//			stmt.setLong(1, 1);
//			stmt.setString(2, "NomeTeste");
//			stmt.setString(3, "aaaaa@aaaa.com");
//			stmt.setDate(4, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
//
//			stmt.execute();
//			stmt.close();
//
//			System.out.println("Inserido!");
//
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
	}
}
