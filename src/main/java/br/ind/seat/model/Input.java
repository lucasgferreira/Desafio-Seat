package br.ind.seat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Input {

	@SerializedName("senha")
	@Expose
	private String senha;
	@SerializedName("emissao")
	@Expose
	private Long emissao;
	@SerializedName("prioridade")
	@Expose
	private String prioridade;
	@SerializedName("chamada")
	@Expose
	private Long chamada;
	@SerializedName("guiche")
	@Expose
	private String guiche;
	@SerializedName("atendente")
	@Expose
	private String atendente;
	@SerializedName("fim")
	@Expose
	private Long fim;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getEmissao() {
		return emissao;
	}

	public void setEmissao(Long emissao) {
		this.emissao = emissao;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public Long getChamada() {
		return chamada;
	}

	public void setChamada(Long chamada) {
		this.chamada = chamada;
	}

	public String getGuiche() {
		return guiche;
	}

	public void setGuiche(String guiche) {
		this.guiche = guiche;
	}

	public String getAtendente() {
		return atendente;
	}

	public void setAtendente(String atendente) {
		this.atendente = atendente;
	}

	public Long getFim() {
		return fim;
	}

	public void setFim(Long fim) {
		this.fim = fim;
	}

}
