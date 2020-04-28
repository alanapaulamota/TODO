package com.alana.upis.padroesPS.poolConexoes.modelo;

import java.util.Calendar;

public class Clientes {

	private Long id;
	private String nome;
	private String email;
	private Calendar dataNascimento;

	public Long getId() {
		return this.id;
	}

	public void setId(Long novo) {
		this.id = novo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String novo) {
		this.nome = novo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String novo) {
		this.email = novo;
	}

	public Calendar getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNasc(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
