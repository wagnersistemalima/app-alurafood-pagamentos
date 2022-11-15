package br.com.sistemalima.pagamentos.model;

public class Observabilidade {

    private String version;

    private String requestId;

    private String resourceName;

    private String correlationId;

    public Observabilidade() {
    }

    public Observabilidade(String version, String requestId, String resourceName, String correlationId) {
        this.version = version;
        this.requestId = requestId;
        this.resourceName = resourceName;
        this.correlationId = correlationId;
    }

    public String getVersion() {
        return version;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    @Override
    public String toString() {
        return "Observabilidade{" +
                "version='" + version + '\'' +
                ", requestId='" + requestId + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", correlationId='" + correlationId + '\'' +
                '}';
    }
}
