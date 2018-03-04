package br.ind.seat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InputOrderService extends Input {
	@SerializedName("naFrente")
	@Expose
	private Integer naFrente;
	@SerializedName("espera")
	@Expose
	private Long espera;

	public Integer getNaFrente() {
		return naFrente;
	}

	public void setNaFrente(Integer naFrente) {
		this.naFrente = naFrente;
	}

	public Long getEspera() {
		return espera;
	}

	public void setEspera(Long espera) {
		this.espera = espera;
	}

}
