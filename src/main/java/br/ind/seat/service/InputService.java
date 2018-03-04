package br.ind.seat.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.ind.seat.model.Fila;
import br.ind.seat.model.Histograma;
import br.ind.seat.model.InputOrderService;
import br.ind.seat.model.PostTo;
import br.ind.seat.util.CustomComparator;

public class InputService {

	private final static String postUrl = "http://seat.ind.br/processo-seletivo/2018/01/post.php";// put in your url
	private final String getUrl = "http://seat.ind.br/processo-seletivo/2018/01/desafio.php?nome=lucas%20goncalves%20ferreira";
	private Client c;
	private WebResource resource;
	private Gson gson;
	private Fila fila;
	private List<InputOrderService> inputs;
	private InputOrderService inputOrderService;
	private Histograma histograma;
	private List<Histograma> histogramas;

	// 'http://seat.ind.br/processo-seletivo/2018/01/desafio.php?nome=lucas%20goncalves%20ferreira'
	public List<InputOrderService> getInputs() {
		c = Client.create();
		resource = c.resource(getUrl);

		gson = new Gson();

		fila = gson.fromJson(resource.get(String.class), Fila.class);

		inputs = new ArrayList<>();
		inputOrderService = new InputOrderService();

		for (int i = 0; i < fila.getInput().size(); i++) {
			inputOrderService = fila.getInput().get(i);
			inputs.add(inputOrderService);
		}

		Collections.sort(inputs, new CustomComparator());
		Long espera = 300000L;
		int naFrente = 0;

		for (int i = 0; i < inputs.size(); i++) {

			if (inputs.get(i).getPrioridade().equals("preferencial")) {

				inputs.get(i).setEspera(espera);
				espera = espera + 300000L;
				inputs.get(i).setNaFrente(naFrente);
				naFrente++;
			}
		}

		for (int i = 0; i < inputs.size(); i++) {
			if (inputs.get(i).getPrioridade().equals("geral")) {

				inputs.get(i).setEspera(espera);
				espera = espera + 300000L;
				inputs.get(i).setNaFrente(naFrente);
				naFrente++;
			}
		}

		// System.out.println(gson.toJson(fila));
		return inputs;
	}

	public List<Histograma> histograma() {
		Long minuto = 300000L;
		Integer quantidade = 0;
		histogramas = new ArrayList<>();
		histograma = new Histograma();
		inputs = getInputs();

		for (int i = 0; i < inputs.size(); i++) {

			histograma = new Histograma();
			histograma.setQuantidade(quantidade);

			histograma.setMinutos(String.format("%02d:%02d", TimeUnit.MILLISECONDS.toHours(minuto),
					TimeUnit.MILLISECONDS.toMinutes(minuto) % 60));

			histogramas.add(histograma);
			System.out
					.println("< - "
							+ String.format("%02d:%02d", TimeUnit.MILLISECONDS.toHours(minuto),
									TimeUnit.MILLISECONDS.toMinutes(minuto) % 60)
							+ " - " + histogramas.get(i).getMinutos());

			minuto = minuto + 300000L;
			quantidade = quantidade + 1;
			// System.out.println(histograma.get(i).getMin()+"");
		}

		return histogramas;
	}

	@Test
	public void postInput() throws IOException {

		// Define the server endpoint to send the HTTP request to
		URL serverUrl = new URL(postUrl);
		HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();

		// Indicate that we want to write to the HTTP request body
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");

		// Json
		Client c = Client.create();
		WebResource resource = c.resource(getUrl);
		Gson gson = new Gson();
		fila = gson.fromJson(resource.get(String.class), Fila.class);

		inputs = getInputs();
		PostTo postTo = new PostTo();
		postTo.setChave(fila.getChave());
		postTo.setNome("lucas goncalves ferreira");
		postTo.setResultado(inputs);
		fila.setPostTo(postTo);

		gson = new Gson();
		String json = gson.toJson(fila.getPostTo().getResultado());
		String resultado = json.replaceAll("\\[","").replaceAll("\\]","");

		System.out.println(resultado);

		// Writing the post data to the HTTP request body
		BufferedWriter httpRequestBodyWriter = new BufferedWriter(
				new OutputStreamWriter(urlConnection.getOutputStream()));
		httpRequestBodyWriter
				.write("nome=" + postTo.getNome() + "&chave=" + postTo.getChave() + "&resultado=" + resultado);

		httpRequestBodyWriter.close();
		// Reading from the HTTP response body
		Scanner httpResponseScanner = new Scanner(urlConnection.getInputStream());
		while (httpResponseScanner.hasNextLine()) {
			System.out.println(httpResponseScanner.nextLine());
		}
		httpResponseScanner.close();

	}

}
