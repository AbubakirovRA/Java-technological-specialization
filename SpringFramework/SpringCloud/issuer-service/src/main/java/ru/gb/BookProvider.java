package ru.gb;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.Data;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.*;

import java.util.UUID;


@Service
public class BookProvider {

  // HttpClient - java.net
  // RestTemplate - spring.web
  // WebClient - spring.reactive

  private final WebClient webClient;
  private final EurekaClient eurekaClient;

  public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction, EurekaClient eurekaClient) {
    webClient = WebClient.builder()
      .filter(loadBalancerExchangeFilterFunction)
      .build();
      this.eurekaClient = eurekaClient;
  }

public UUID getRandomBookId() {
  Book randomBook = webClient.get()
          .uri(getBookServiceUrl() + "/api/book/random")
          .retrieve()
          .bodyToFlux(Book.class)
          .blockFirst();

  if (randomBook != null) {
    return randomBook.getId();
  } else {
    throw new IllegalStateException("Received null book from the book service.");
  }
}


  private String getBookServiceUrl() {
    Application application = eurekaClient.getApplication("book-service");
    InstanceInfo instanceInfo = application.getInstances().getFirst(); // Получаем первый экземпляр сервиса
      return "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort();
  }

}

@Data
class Book {

  private UUID id;
  private String name;
  private Author author;

}

@Data
class Author {

  private UUID id;
  private String firstName;
  private String lastName;

}

