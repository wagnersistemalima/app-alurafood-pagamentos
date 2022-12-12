# Microsserviço de pagamento

* Foi desenvolvido do zero o microsserviço de pagamentos, onde foi criado controlador, modelos, dto (data transfer object), repository.
* A Aplicação se conecta com um banco de dados MySQL.
* Foi utilizada uma arquitetura de microsserviços, fazendo service discovery, centralizando requisições através de um API Gateway, fazendo balanceamento de carga.
* Foi implementado uma comunicação síncrona usando ferramentas do Spring Cloud, além de projetar conceitos de circuit breaker e fallback para prevenir falhas ou inoperabilidade em algum dos microsserviços.

| :placard: Vitrine.Dev |     |
| -------------  | --- |
| :sparkles: Nome        | **Microsserviço de pagamento**
| :label: Tecnologias | SpringBoot, Java, MySQL, Maven
| :rocket: URL         | https://url-deploy.com.br
| :fire: Desafio     | https://www.alura.com.br/curso-online-microsservicos-implementando-java-spring

<!-- Inserir imagem com a #vitrinedev ao final do link -->
![alter-text](./images/ms-vitrine.png)
[url-vitrine-dev](https://cursos.alura.com.br/vitrinedev/wagner-sistemalima)


## Setup do Projeto
* Linguagem de programação: Java
* Tecnologia: Spring Boot 2.6.13
* Gerenciador de dependência: Maven
* Java 17
* IDE IntelJ

## Implementação utilizando as ferramentas do ecossistema Spring com Java 17
* Spring Web: Crie aplicativos da web, incluindo RESTful, usando Spring MVC. Usa Apache Tomcat como o contêiner integrado padrão.
* Bean Validation: é uma especificação que permite validar objetos com facilidade em diferentes camadas da aplicação. A vantagem de usar Bean Validation é que as restrições ficam inseridas nas classes de modelo.
* Spring Cloud OpenFeign: Uma maneira elegante de criar clientes HTTP em Java
* JPA : biblioteca padrão de persistência de dados no java, baseado no mapeamento objeto relacional
* Banco de dados MySQL
* Protocolo AMQP: é a sigla para Advanced Messaging Queue Protocol, e é o protocolo base no qual foi construído o RabbitMQ.
* Spring AMQP: O projeto Spring AMQP aplica os principais conceitos do Spring ao desenvolvimento de soluções de mensagens baseadas em AMQP. Fornecemos um “modelo” como uma abstração de alto nível para enviar e receber mensagens. Também fornecemos suporte para POJOs orientados a mensagens. Essas bibliotecas facilitam o gerenciamento de recursos AMQP enquanto promovem o uso de injeção de dependência e configuração declarativa. Em todos esses casos, você pode ver semelhanças com o suporte JMS no Spring Framework

## Mensagerias

* Mensageria é uma técnica de comunicação assíncrona entre as aplicações.
* Evitamos o acoplamento entre as partes colocando uma camada intermediária, que é o nosso message broker.
* Esse message broker pode fazer armazenamento, enfileiramento, filtragem e roteamento de mensagens.

## Spring AMQP

* Possui bibliotecas que facilitam a implementação da comunicação baseada em protocolo AMQP.
* Ele é dividido em duas partes: spring-amqp, que é a abstração base para a comunicação, e spring-rabbit, com implementações mais específicas para o RabbitMQ.
* Ao utilizarmos a dependência spring-boot-starter-amqp no nosso arquivo pom.xml, já temos essas duas dependências encapsuladas, característica padrão quando utilizamos os starters do Spring Boot.
* https://spring.io/projects/spring-amqp

## RabbitMQ
* https://www.rabbitmq.com/
* Server do RabbitMQ, responsável pela administração, como controlar as filas, as exchanges, entre outras funcionalidades.
* É o agente de mensagens de código aberto mais amplamente implantado.

![alter-text](./images/rabbitMQ-produces.png)