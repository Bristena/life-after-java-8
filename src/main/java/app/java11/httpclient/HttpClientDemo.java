package app.java11.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class HttpClientDemo {

  private static final String ROOT_URL = "http://jsonplaceholder.typicode.com/posts";

  public static void main(String[] args)
      throws InterruptedException, IOException, URISyntaxException {
    httpGetRequest();
    httpPostRequest();
    asynchronousGetRequest();
    asynchronousMultipleRequests();
  }

  public static void httpGetRequest() throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .uri(URI.create(ROOT_URL + "/1"))
        .headers("Accept-Enconding", "gzip, deflate")
        .build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

    String responseBody = response.body();
    int responseStatusCode = response.statusCode();

    System.out.println("httpGetRequest: " + responseBody);
    System.out.println("httpGetRequest status code: " + responseStatusCode);
  }

  public static void httpPostRequest()
      throws URISyntaxException, IOException, InterruptedException {
    HttpClient client = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .build();
    HttpRequest request = HttpRequest
        .newBuilder(new URI(ROOT_URL))
        .version(HttpClient.Version.HTTP_2)
        .POST(BodyPublishers.ofString("Sample Post Request"))
        .build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    String responseBody = response.body();
    System.out.println("httpPostRequest : " + responseBody);
  }

  public static void asynchronousGetRequest() throws URISyntaxException {
    HttpClient client = HttpClient.newHttpClient();
    URI httpURI = new URI(ROOT_URL + "/1");
    HttpRequest request = HttpRequest.newBuilder(httpURI)
        .version(HttpClient.Version.HTTP_2)
        .build();
    CompletableFuture<Void> futureResponse = client
        .sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenAccept(resp -> {
          System.out.println("Got pushed response " + resp.uri());
          System.out.println("Response status code: " + resp.statusCode());
          System.out.println("Response body: " + resp.body());
        });
    System.out.println("futureResponse" + futureResponse);

  }

  public static void asynchronousMultipleRequests() throws URISyntaxException {
    HttpClient client = HttpClient.newHttpClient();
    List<URI> uris = Arrays.asList(new URI(ROOT_URL + "/1"),
        new URI(ROOT_URL + "/2"));
    List<HttpRequest> requests = uris.stream()
        .map(HttpRequest::newBuilder)
        .map(Builder::build)
        .collect(Collectors.toList());

    CompletableFuture.allOf(requests.stream()
        .map(request -> client.sendAsync(request, BodyHandlers.ofString()))
        .toArray(CompletableFuture<?>[]::new))
        .thenAccept(System.out::println)
        .join();
  }
}
