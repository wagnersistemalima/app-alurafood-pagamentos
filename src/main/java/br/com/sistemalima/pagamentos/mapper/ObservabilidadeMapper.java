package br.com.sistemalima.pagamentos.mapper;

import br.com.sistemalima.pagamentos.constant.ApiConstantRequestId;
import br.com.sistemalima.pagamentos.constant.ApiConstantVersion;
import br.com.sistemalima.pagamentos.exceptions.BadRequestExceptions;
import br.com.sistemalima.pagamentos.model.Observabilidade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ObservabilidadeMapper {


    public ObservabilidadeMapper() {
    }

    private final Logger logger = LoggerFactory.getLogger(ObservabilidadeMapper.class);
    private final static String tag = "class: ObservabilidadeMapper, ";
    private final static String messageErrorVersion = "Cabeçalho da requisição version não validado!";
    private final static String messageErrorRequestId = "Cabeçalho da requisição requestId não validado!";

    public Observabilidade map(String version, String requestId, String resourceName, String correlationId) {
        logger.info(String.format(tag + "validando headers"));

        return validaHeaders(version, requestId, resourceName, correlationId);
    }

    private Observabilidade validaHeaders(String version, String requestId, String resourceName, String correlationId) {

        ApiConstantVersion.initialization();
        ApiConstantRequestId.initialization();

        for (String v: ApiConstantVersion.listVersion) {
            if (v.equals(version)) {
               break;

            } else {
                logger.error(String.format("Error " + tag + messageErrorVersion));
                throw new BadRequestExceptions(messageErrorVersion);
            }
        }

        for (String r: ApiConstantRequestId.listRequestId) {
            if (r.equals(requestId)) {
                break;
            } else {
                logger.error(String.format("Error " + tag + messageErrorRequestId));
                throw new BadRequestExceptions(messageErrorRequestId);
            }
        }
        return new Observabilidade(
                version,
                requestId,
                resourceName,
                correlationId

        );
    }
}
