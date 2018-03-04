package br.ind.seat.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fila {

	@SerializedName("mensagem")
	@Expose
	private String mensagem;
	@SerializedName("nome")
	@Expose
	private String nome;
	@SerializedName("chave")
	@Expose
	private Long chave;
	@SerializedName("input")
	@Expose
	private List<InputOrderService> input = null;
	@SerializedName("postTo")
	@Expose
	private PostTo postTo;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

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

	public List<InputOrderService> getInput() {
		return input;
	}

	public void setInput(List<InputOrderService> input) {
		this.input = input;
	}

	public PostTo getPostTo() {
		return postTo;
	}

	public void setPostTo(PostTo postTo) {
		this.postTo = postTo;
	}

}
