package br.com.sistemalima.pagamentos.dto;

import br.com.sistemalima.pagamentos.model.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PagamentoUpdateRequestDTO {

    @JsonProperty("status")
    private Status status;

    public PagamentoUpdateRequestDTO() {
    }

    public PagamentoUpdateRequestDTO(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
