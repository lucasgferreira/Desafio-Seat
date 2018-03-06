package br.ind.seat.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ind.seat.model.Histograma;
import br.ind.seat.model.InputOrderService;
import br.ind.seat.service.Milestone3;

@ManagedBean
@ViewScoped
public class InputBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7204720458748034541L;

	private List<InputOrderService> inputOrderServices;
	private List<Histograma> histogramas;
	private Histograma histograma;

	public List<InputOrderService> getInputOrderServices() {
		return inputOrderServices;
	}

	public void setInputOrderServices(List<InputOrderService> inputOrderServices) {
		this.inputOrderServices = inputOrderServices;
	}

	public List<Histograma> getHistogramas() {
		return histogramas;
	}

	public void setHistogramas(List<Histograma> histogramas) {
		this.histogramas = histogramas;
	}

	public Histograma getHistograma() {
		return histograma;
	}

	public void setHistograma(Histograma histograma) {
		this.histograma = histograma;
	}

	@PostConstruct
	public void listar() {
		Milestone3 inputService = new Milestone3();

		inputOrderServices = inputService.getMilestone3();
		histogramas = inputService.histograma();
	}

}
