package de.dhbw.webapis.client.repository

import de.dhbw.webapis.common.domain.Product
import io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.netty.http.client.HttpClient
import reactor.netty.tcp.TcpClient
import java.util.concurrent.TimeUnit.MILLISECONDS

@Repository
class ProductClientRepository(webClientBuilder: WebClient.Builder) {

  private final val tcpClient = TcpClient.create()
    .option(CONNECT_TIMEOUT_MILLIS, 100)
    .doOnConnected {
      it.addHandlerLast(ReadTimeoutHandler(1000, MILLISECONDS))
        .addHandlerLast(WriteTimeoutHandler(1000, MILLISECONDS))
    }

  private val webClient = webClientBuilder.baseUrl("http://localhost:8080")
    .clientConnector(ReactorClientHttpConnector(HttpClient.from(tcpClient)))
    .build()

  fun findAll() = webClient.get()
    .uri("/products")
    .retrieve()
    .bodyToFlux<Product>()
}
