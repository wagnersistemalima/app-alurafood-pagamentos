package br.com.sistemalima.pagamentos.amqp;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PagamentoAMQPConfiguration {

//    // criar uma queue/fila
//
//    @Bean
//    public Queue criarFila() {
//        //return new Queue("pagamento.concluido", false);
//        return QueueBuilder.nonDurable("pagamento.concluido").build();
//    }

    // criar Exange

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("pagamento.ex");
    }

    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory connect) {
        return new RabbitAdmin(connect);
    }

    // inicializar RabbitAdmin / disparar evento interface

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    // Conversor Jackson2JsonMessageConverter

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // configuração para envio objeto json RabbitTemplate

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;

    }

}