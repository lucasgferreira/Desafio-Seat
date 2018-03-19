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

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.ind.seat.model.Fila;
import br.ind.seat.model.InputOrderService;
import br.ind.seat.model.PostTo;
import br.ind.seat.util.CustomComparator;

public class Milestone2 extends Milestone1 {

	// 'http://seat.ind.br/processo-seletivo/2018/01/desafio.php?nome=lucas%20goncalves%20ferreira'
	public List<InputOrderService> getMilestone2() {
		Milestone1 milestone1 = new Milestone1();

		inputs = new ArrayList<>();
		inputOrderService = new InputOrderService();
		fila = new Fila();
		fila.setInput(milestone1.getMilistone1());

		int index = 0;

		for (int i = 0; i < fila.getInput().size(); i++) {
			inputOrderService = new InputOrderService();

			if (fila.getInput().get(i).getGuiche() == null) {
				if (fila.getInput().get(i).getPrioridade().equals("preferencial")) {
					inputOrderService.setSenha(fila.getInput().get(i).getSenha());
					inputOrderService.setEmissao(fila.getInput().get(i).getEmissao());
					inputOrderService.setPrioridade(fila.getInput().get(i).getPrioridade());
					inputOrderService.setNaFrente(index);
					inputs.add(inputOrderService);
					index = index + 1;
				}

			}
		}

		for (int i = 0; i < fila.getInput().size(); i++) {
			inputOrderService = new InputOrderService();

			if (fila.getInput().get(i).getGuiche() == null) {
				if (fila.getInput().get(i).getPrioridade().equals("geral")) {
					inputOrderService.setSenha(fila.getInput().get(i).getSenha());
					inputOrderService.setEmissao(fila.getInput().get(i).getEmissao());
					inputOrderService.setPrioridade(fila.getInput().get(i).getPrioridade());
					inputOrderService.setNaFrente(index);
					inputs.add(inputOrderService);
					index = index + 1;
				}

			}
		}

		Collections.sort(inputs, new CustomComparator());
		// System.out.println(gson.toJson(fila));
		return inputs;
	}

	// para execultar o JUNIT no postMilestone2() use o @Ignore no postMilestone1 na
	// classe Milestone1
//	@Test
//	@Ignore
	public void postMilestone2() throws IOException {

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
		fila = new Fila();
		fila = gson.fromJson(resource.get(String.class), Fila.class);

		PostTo postTo = new PostTo();
		postTo.setChave(fila.getChave());
		postTo.setNome("lucas goncalves ferreira");
		postTo.setResultado(getMilestone2());
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
}
