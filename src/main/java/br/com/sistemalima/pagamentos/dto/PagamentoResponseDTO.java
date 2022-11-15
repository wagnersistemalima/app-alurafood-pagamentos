package br.com.sistemalima.pagamentos.dto;

import br.com.sistemalima.pagamentos.model.Status;

import java.math.BigDecimal;

public class PagamentoResponseDTO {

    // atributos

    private Long id;
    private BigDecimal valor;
    private String nome;
    private String numero;
    private String expiracao;
    private String codigo;
    private Status status;
    private Long formaDePagamentoId;
    private Long pedidoId;

    // contrutor padrão

    public PagamentoResponseDTO() {
    }

    // construtor com argumentos
    public PagamentoResponseDTO(Long id, BigDecimal valor, String nome, String numero, String expiracao, String codigo, Status status, Long formaDePagamentoId, Long pedidoId) {
        this.id = id;
        this.valor = valor;
        this.nome = nome;
        this.numero = numero;
        this.expiracao = expiracao;
        this.codigo = codigo;
        this.status = status;
        this.formaDePagamentoId = formaDePagamentoId;
        this.pedidoId = pedidoId;
    }

    // getters


    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }

    public String getNumero() {
        return numero;
    }

    public String getExpiracao() {
        return expiracao;
    }

    public String getCodigo() {
        return codigo;
    }

    public Status getStatus() {
        return status;
    }

    public Long getFormaDePagamentoId() {
        return formaDePagamentoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    // setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setExpiracao(String expiracao) {
        this.expiracao = expiracao;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setFormaDePagamentoId(Long formaDePagamentoId) {
        this.formaDePagamentoId = formaDePagamentoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }
}
