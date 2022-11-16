package br.com.sistemalima.pagamentos.service;

import br.com.sistemalima.pagamentos.dto.PagamentoResponseDTO;
import br.com.sistemalima.pagamentos.exceptions.BadRequestExceptions;
import br.com.sistemalima.pagamentos.http.PedidoClient;
import br.com.sistemalima.pagamentos.mapper.PagamentoResponseMapper;
import br.com.sistemalima.pagamentos.model.Observabilidade;
import br.com.sistemalima.pagamentos.model.Pagamento;
import br.com.sistemalima.pagamentos.model.Status;
import br.com.sistemalima.pagamentos.repository.PagamentoRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PagamentoResponseMapper pagamentoResponseMapper;

    @Autowired
    private PedidoClient pedidoClient;

    private final Logger logger = LoggerFactory.getLogger(PagamentoService.class);
    private static final String tag = "class: PagamentoService, ";
    private static final String errorMessageNotFound = "Recurso não encontrado, ";

    @Transactional(readOnly = true)
    public Page<PagamentoResponseDTO> obterTodos(Pageable paginacao, Observabilidade observabilidade) {
        logger.info(String.format(tag + observabilidade));
        return pagamentoRepository.findAll(paginacao).map(pagamento -> pagamentoResponseMapper.map(pagamento, observabilidade));
    }

    @Transactional(readOnly = true)
    public PagamentoResponseDTO obterPorId(Long id, Observabilidade observabilidade) {

        logger.info(String.format(tag + observabilidade));

        Pagamento pagamento = validaIdPagamento(id, observabilidade);

        return pagamentoResponseMapper.map(pagamento, observabilidade);
    }

    @Transactional
    public PagamentoResponseDTO criarPagamento(Pagamento pagamento, Observabilidade observabilidade) {

        logger.info(tag + observabilidade);

        pagamento.setStatus(Status.CRIADO);
        pagamentoRepository.save(pagamento);

        return pagamentoResponseMapper.map(pagamento, observabilidade);
    }

    @Transactional
    public PagamentoResponseDTO atualizar(Long id, Status statusEnuum, Observabilidade observabilidade) {

        logger.info(tag + observabilidade);

        Pagamento pagamentoDB = validaIdPagamento(id, observabilidade);

        pagamentoDB.setStatus(statusEnuum);

        pagamentoRepository.save(pagamentoDB);

        return pagamentoResponseMapper.map(pagamentoDB, observabilidade);
    }

    @Transactional
    public void excluirPagamento(Long id, Observabilidade observabilidade) {

        logger.info(tag + observabilidade);

        validaIdPagamento(id, observabilidade);

        pagamentoRepository.deleteById(id);
    }
    @Transactional
    public void confirmaPagamento(Long id, Observabilidade observabilidade, String version, String requestId) throws IOException {

        logger.info(String.format(tag + observabilidade));

        Pagamento pagamento = validaIdPagamento(id, observabilidade);


        try {
            pedidoClient.atualizaPagamento(version, requestId, pagamento.getPedidoId());
            pagamento.setStatus(Status.CONFIRMADO);
            pagamentoRepository.save(pagamento);
        } catch (FeignException ex) {
            if (ex.status() == 404) {
                throw new EntityNotFoundException(ex.getMessage());
            } else if (ex.status() == 400) {
                throw new BadRequestExceptions((ex.getMessage()));
            } else {
                throw new IOException(ex.getMessage());
            }
        }
    }

    private Pagamento validaIdPagamento(Long id, Observabilidade observabilidade) {
        logger.info(String.format(tag + " valida pagamento, " + observabilidade));
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);

        if(pagamento.isEmpty()) {
            logger.error(String.format("Error valida id do pagamento, " + errorMessageNotFound + tag + observabilidade));
            throw new EntityNotFoundException(errorMessageNotFound);
        }
        return pagamento.get();
    }

}
