package br.com.sistemalima.pagamentos.mapper;

import br.com.sistemalima.pagamentos.dto.PagamentoRequestDTO;
import br.com.sistemalima.pagamentos.model.Observabilidade;
import br.com.sistemalima.pagamentos.model.Pagamento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PagamentoRequestMapper {

    private final Logger logger = LoggerFactory.getLogger(PagamentoRequestMapper.class);
    private static final String tag = "class: PagamentoRequestMapper, ";

    public Pagamento map(PagamentoRequestDTO request, Observabilidade observabilidade) {
        logger.info(tag + observabilidade);
        return new Pagamento(
                request.getValor(),
                request.getNome(),
                request.getNumero(),
                request.getExpiracao(),
                request.getCodigo(),
                request.getPedidoId(),
                request.getFormaDePagamentoId()
        );
    }
}
