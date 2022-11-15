package br.com.sistemalima.pagamentos.controller;

import br.com.sistemalima.pagamentos.dto.PagamentoRequestDTO;
import br.com.sistemalima.pagamentos.dto.PagamentoResponseDTO;
import br.com.sistemalima.pagamentos.dto.PagamentoUpdateRequestDTO;
import br.com.sistemalima.pagamentos.mapper.PagamentoRequestMapper;
import br.com.sistemalima.pagamentos.model.Observabilidade;
import br.com.sistemalima.pagamentos.model.Pagamento;
import br.com.sistemalima.pagamentos.model.Status;
import br.com.sistemalima.pagamentos.service.PagamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
@Validated
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private PagamentoRequestMapper pagamentoRequestMapper;

    private final Logger logger = LoggerFactory.getLogger(PagamentoController.class);
    private final static String listarPagamento = "listar todos pagamento";
    private final static String detalharPagamento = "Detalhar pagamento por id";
    private final static String cadastrarPagamento = "Cadastrar um pagamento";
    private final static String atualizarPagamento = "Atualizar um pagamento";
    private final static String cancelarPagamento = "Cancelar um pagamento";
    private final static String tagStart = "Inicio do processo, class: PagamentoController, ";
    private final static String tagEnd = "Fim do processo, class: PagamentoController, ";

    @GetMapping
    public ResponseEntity<Page<PagamentoResponseDTO>> listar(
            @RequestHeader("Accept-Version") @NotEmpty(message = "informe o cabeçalho") String version,
            @RequestHeader("x-requestId") @NotEmpty(message = "informe o cabeçalho") String requestId,
            @PageableDefault(size = 10) Pageable paginacao) {

        String correlationId = UUID.randomUUID().toString();

        Observabilidade observabilidade = new Observabilidade(version, requestId, listarPagamento, correlationId);

        logger.info(String.format(tagStart + observabilidade));

        Page<PagamentoResponseDTO> response = pagamentoService.obterTodos(paginacao, observabilidade);

        logger.info(String.format(tagEnd + observabilidade));

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> detalhar(
            @RequestHeader("Accept-Version") @NotEmpty(message = "informe o cabeçalho") String version,
            @RequestHeader("x-requestId") @NotEmpty(message = "informe o cabeçalho") String requestId,
            @PathVariable @NotNull Long id) {

        String correlationId = UUID.randomUUID().toString();

        Observabilidade observabilidade = new Observabilidade(version, requestId, detalharPagamento, correlationId);

        logger.info(tagStart + observabilidade);

        PagamentoResponseDTO pagamentoResponseDTO = pagamentoService.obterPorId(id, observabilidade);

        logger.info(tagEnd + observabilidade);

        return ResponseEntity.ok().body(pagamentoResponseDTO);
    }

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> cadastrar(
            @RequestHeader("Accept-Version") @NotEmpty(message = "informe o cabeçalho") String version,
            @RequestHeader("x-requestId") @NotEmpty(message = "informe o cabeçalho") String requestId,
            @Valid @RequestBody PagamentoRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {

        String correlationId = UUID.randomUUID().toString();

        Observabilidade observabilidade = new Observabilidade(version, requestId, cadastrarPagamento, correlationId);

        logger.info(String.format(tagStart + observabilidade));

        Pagamento pagamento = pagamentoRequestMapper.map(requestDTO, observabilidade);

        PagamentoResponseDTO pagamentoResponseDTO = pagamentoService.criarPagamento(pagamento, observabilidade);

        URI uri = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamentoResponseDTO.getId()).toUri();

        logger.info(tagEnd + observabilidade);

        return ResponseEntity.created(uri).body(pagamentoResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> atualizar(
            @RequestHeader("Accept-Version") @NotEmpty(message = "informe o cabeçalho") String version,
            @RequestHeader("x-requestId") @NotEmpty(message = "informe o cabeçalho") String requestId,
            @PathVariable @NotNull Long id,
            @Valid @RequestBody PagamentoUpdateRequestDTO requestDTO) {

        String correlationId = UUID.randomUUID().toString();

        Observabilidade observabilidade = new Observabilidade(version, requestId, atualizarPagamento, correlationId);

        logger.info(String.format(tagStart + observabilidade));

        Status statusEnum = requestDTO.getStatus();
        PagamentoResponseDTO pagamentoResponseDTO = pagamentoService.atualizar(id, statusEnum, observabilidade);

        logger.info(tagEnd + observabilidade);

        return ResponseEntity.ok().body(pagamentoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> remover(
            @RequestHeader("Accept-Version") @NotEmpty(message = "informe o cabeçalho") String version,
            @RequestHeader("x-requestId") @NotEmpty(message = "informe o cabeçalho") String requestId,
            @PathVariable @NotNull Long id) {

        String correlationId = UUID.randomUUID().toString();

        Observabilidade observabilidade = new Observabilidade(version, requestId, cancelarPagamento, correlationId);

        logger.info(String.format(tagStart + observabilidade));

        pagamentoService.excluirPagamento(id, observabilidade);

        logger.info(tagEnd + observabilidade);

        return ResponseEntity.noContent().build();
    }

}