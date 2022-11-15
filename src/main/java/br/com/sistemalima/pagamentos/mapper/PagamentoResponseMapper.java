package br.com.sistemalima.pagamentos.mapper;

import br.com.sistemalima.pagamentos.dto.PagamentoResponseDTO;
import br.com.sistemalima.pagamentos.model.Observabilidade;
import br.com.sistemalima.pagamentos.model.Pagamento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PagamentoResponseMapper {

    private final static String tag = "class: PagamentoResponseMapper, ";
    private final Logger logger = LoggerFactory.getLogger(PagamentoResponseMapper.class);

    public PagamentoResponseDTO map(Pagamento pagamento, Observabilidade observabilidade) {
        logger.info(String.format(tag + observabilidade));
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getValor(),
                pagamento.getNome(),
                pagamento.getNumero(),
                pagamento.getExpiracao(),
                pagamento.getCodigo(),
                pagamento.getStatus(),
                pagamento.getFormaDePagamentoId(),
                pagamento.getPedidoId()
        );
    }
}
