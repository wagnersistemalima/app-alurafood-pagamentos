package br.com.sistemalima.pagamentos.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotEmpty;

@FeignClient(name = "pedido-ms")
public interface PedidoClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/pedidos/{id}/pago")
    void atualizaPagamento(
            @RequestHeader("Accept-Version") @NotEmpty(message = "informe o cabeçalho") String version,
            @RequestHeader("x-requestId") @NotEmpty(message = "informe o cabeçalho") String requestId,
            @PathVariable Long id);
}
