package com.alana.upis.padroesPS.poolConexoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alana.upis.padroesPS.poolConexoes.PoolDeConexoes;
import com.alana.upis.padroesPS.poolConexoes.modelo.Cliente;

public class ClienteDAO {

	public ClienteDAO() {

	}

	public void create(Cliente cliente) {
		Connection conexao = null;
		try {
			conexao = PoolDeConexoes.instance().getConexao();
			String sql = "insert into tbclientes " + "(nome,end,bairro,estado,cep,fone,email"
					+ " values (?,?,?,?,?,?,?)";

			try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

				stmt.setString(1, cliente.getNome());
				stmt.setString(2, cliente.getEndereco());
				stmt.setString(3, cliente.getBairro());
				stmt.setString(4, cliente.getEstado());
				stmt.setString(5, cliente.getCep());
				stmt.setString(6, cliente.getFone());
				stmt.setString(7, cliente.getEmail());
				stmt.execute();
				stmt.close();

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conexao != null) {
				PoolDeConexoes.instance().devolverConexao(conexao);

			}
		}
	}

	public void remove(Cliente cliente) {
		Connection conexao = null;
		conexao = PoolDeConexoes.instance().getConexao();
		String sql = "delete from tbclientes where id=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, cliente.getId());
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conexao != null) {
				PoolDeConexoes.instance().devolverConexao(conexao);

			}
		}
	}

	public void update(Cliente cliente) {
		Connection conexao = null;
		conexao = PoolDeConexoes.instance().getConexao();
		String sql = "update tbclientes set nome=?,"
				+ "endereco=?, bairro=?,estado=?, cep=?, fone=?,email=? where id=?";
		try {

			conexao = PoolDeConexoes.instance().getConexao();
			try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

				stmt.setString(1, cliente.getNome());
				stmt.setString(2, cliente.getEndereco());
				stmt.setString(3, cliente.getBairro());
				stmt.setString(4, cliente.getEstado());
				stmt.setString(5, cliente.getCep());
				stmt.setString(6, cliente.getFone());
				stmt.setString(7, cliente.getEmail());
				stmt.execute();
				stmt.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conexao != null) {
				PoolDeConexoes.instance().devolverConexao(conexao);

			}
		}
	}

	public void findById(Long id) {
		Connection conexao = null;
		conexao = PoolDeConexoes.instance().getConexao();
		String sql = "select * from tbclientes where id=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conexao != null) {
				PoolDeConexoes.instance().devolverConexao(conexao);

			}
		}
	}

	public List<Cliente> getLista() {
		Connection conexao = null;
		String sql = "select * from tbclientes";
		conexao = PoolDeConexoes.instance().getConexao();
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			List<Cliente> clientes = new ArrayList<Cliente>();
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Cliente cliente = new Cliente();
				cliente.setId(rs.getLong("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setBairro(rs.getString("bairro"));
				cliente.setEstado(rs.getString("estado"));
				cliente.setCep(rs.getString("cep"));
				cliente.setFone(rs.getString("fone"));
				cliente.setEmail(rs.getString("email"));

				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conexao != null) {
				PoolDeConexoes.instance().devolverConexao(conexao);

			}
		}
	}
}
