package br.ind.seat.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostTo {

	@SerializedName("nome")
	@Expose
	private String nome;
	@SerializedName("chave")
	@Expose
	private Long chave;
	@SerializedName("resultado")
	@Expose
	private List<InputOrderService> resultado = null;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getChave() {
		return chave;
	}

	public void setChave(Long chave) {
		this.chave = chave;
	}

	public List<InputOrderService> getResultado() {
		return resultado;
	}

	public void setResultado(List<InputOrderService> resultado) {
		this.resultado = resultado;
	}

}