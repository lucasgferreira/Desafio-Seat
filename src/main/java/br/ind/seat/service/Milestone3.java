package br.ind.seat.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.ind.seat.model.Fila;
import br.ind.seat.model.Histograma;
import br.ind.seat.model.InputOrderService;
import br.ind.seat.model.PostTo;

public class Milestone3 extends Milestone2 {

	// 'http://seat.ind.br/processo-seletivo/2018/01/desafio.php?nome=lucas%20goncalves%20ferreira'
	public List<InputOrderService> getMilestone3() {
		Milestone2 milestone2 = new Milestone2();

		inputs = milestone2.getMilestone2();

		Long espera = 0L;
		int index = 0;
		for (int i = 0; i < inputs.size(); i++) {

			if (i == 0) {
				inputs.get(i).setEspera(espera);
			} else {

				espera = inputs.get(index).getEmissao();
				espera = inputs.get(i).getEmissao() - espera;
				inputs.get(i).setEspera(espera);
			}
			index = i - index;
		}

		//System.out.println(gson.toJson(inputs));
		return inputs;
	}

	// para execultar o JUNIT no postMilestone3() use o @Ignore no postMilestone2 na
//	// classe Milestone2
//	@Test
//	@Ignore
	public void postMilestone3() throws IOException {

		// Define the server endpoint to send the HTTP request to
		URL serverUrl = new URL(postUrl);
		HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();

		// Indicate that we want to write to the HTTP request body
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");

		// Json
		Client c = Client.create();
		WebResource resource = c.resource(getUrl);
		gson = new Gson();
		fila = gson.fromJson(resource.get(String.class), Fila.class);

		PostTo postTo = new PostTo();
		postTo.setChave(fila.getChave());
		postTo.setNome("lucas goncalves ferreira");
		postTo.setResultado(getMilestone3());
		fila.setPostTo(postTo);

		gson = new Gson();
		String json = gson.toJson(fila.getPostTo().getResultado());
		// String resultado = json.replaceAll("\\[","").replaceAll("\\]","");

		System.out.println(json);

		// Writing the post data to the HTTP request body
		BufferedWriter httpRequestBodyWriter = new BufferedWriter(
				new OutputStreamWriter(urlConnection.getOutputStream()));
		httpRequestBodyWriter.write("nome=" + postTo.getNome() + "&chave=" + postTo.getChave() + "&resultado=" + json);

		httpRequestBodyWriter.close();
		// Reading from the HTTP response body
		Scanner httpResponseScanner = new Scanner(urlConnection.getInputStream());
		while (httpResponseScanner.hasNextLine()) {
			System.out.println(httpResponseScanner.nextLine());
		}
		httpResponseScanner.close();

	}

	public List<Histograma> histograma() {
		Long minuto = 300000L;
		Integer quantidade = 0;
		histogramas = new ArrayList<>();
		inputs = getMilestone3();
		histograma = new Histograma();

		histograma.setQuantidade(quantidade);
		histogramas.add(histograma);
		histogramas.add(histograma);
		histogramas.add(histograma);
		histogramas.add(histograma);

		for (int index = 0; index < inputs.size(); index++) {

			histograma = new Histograma();
			if (inputs.get(index).getEspera() <= minuto) {

				quantidade = histogramas.get(0).getQuantidade() + 1;

				histograma.setMinutos("Senhas esperaram menos de 00:05 minutos");
				histograma.setQuantidade(quantidade);
				histogramas.set(0, histograma);
			}
			if (inputs.get(index).getEspera() > minuto && inputs.get(index).getEspera() <= minuto * 2) {
				quantidade = histogramas.get(1).getQuantidade() + 1;

				histograma.setMinutos("Senhas esperaram mais de 00:05 minutos menos 00:10 minutos");
				histograma.setQuantidade(quantidade);
				histogramas.set(1, histograma);
			}
			if (inputs.get(index).getEspera() > minuto * 2 && inputs.get(index).getEspera() < minuto * 3) {
				quantidade = histogramas.get(2).getQuantidade() + 1;

				histograma.setMinutos("Senhas esperaram mais de 00:10 minutos menos 00:15 minutos");
				histograma.setQuantidade(quantidade);
				histogramas.set(2, histograma);
			}
			if (inputs.get(index).getEspera() > minuto * 3) {
				quantidade = histogramas.get(3).getQuantidade() + 1;

				histograma.setMinutos("Senhas esperaram mais de 00:15 minutos");
				histograma.setQuantidade(quantidade);
				histogramas.set(3, histograma);
			}

		}
		for (int index = 0; index <= 3; index++) {
			System.out.println(
					"< - " + histogramas.get(index).getQuantidade() + " - " + histogramas.get(index).getMinutos());
		}
		return histogramas;
	}
}
