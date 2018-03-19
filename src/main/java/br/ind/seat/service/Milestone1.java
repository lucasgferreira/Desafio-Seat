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
import br.ind.seat.model.Histograma;
import br.ind.seat.model.InputOrderService;
import br.ind.seat.model.PostTo;
import br.ind.seat.util.CustomComparator;

public class Milestone1 {
	public final static String postUrl = "http://seat.ind.br/processo-seletivo/2018/01/post.php";// put in your url
	public final String getUrl = "http://seat.ind.br/processo-seletivo/2018/01/desafio.php?nome=lucas%20goncalves%20ferreira";
	public Client c;
	public WebResource resource;
	public Gson gson;
	public Fila fila;
	public List<InputOrderService> inputs;
	public InputOrderService inputOrderService;
	public Histograma histograma;
	public List<Histograma> histogramas;

	// 'http://seat.ind.br/processo-seletivo/2018/01/desafio.php?nome=lucas%20goncalves%20ferreira'
	public List<InputOrderService> getMilistone1() {
		c = Client.create();
		resource = c.resource(getUrl);

		gson = new Gson();

		fila = gson.fromJson(resource.get(String.class), Fila.class);

		inputs = new ArrayList<>();
		inputOrderService = new InputOrderService();

		for (int i = 0; i < fila.getInput().size(); i++) {

			if (fila.getInput().get(i).getGuiche() == null) {
				inputOrderService.setSenha(fila.getInput().get(i).getSenha());
				inputOrderService.setEmissao(fila.getInput().get(i).getEmissao());
				inputOrderService.setPrioridade(fila.getInput().get(i).getPrioridade());
				inputs.add(inputOrderService);
				inputOrderService = new InputOrderService();
			}
		}

		Collections.sort(inputs, new CustomComparator());

		// System.out.println(gson.toJson(fila));
		return inputs;
	}

//	@Test
//	@Ignore
	public void postMilestone1() throws IOException {

		// Define the server endpoint to send the HTTP request to
		URL serverUrl = new URL(postUrl);
		HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();

		// Indicate that we want to write to the HTTP request body
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");


		inputs = getMilistone1();
		PostTo postTo = new PostTo();
		postTo.setChave(fila.getChave());
		postTo.setNome("lucas goncalves ferreira");
		postTo.setResultado(inputs);
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
