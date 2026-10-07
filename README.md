# Automação Web - Blog do Agi

Projeto de automação de testes da funcionalidade de pesquisa de artigos do Blog do Agi, desenvolvido com Java 11, Selenium, JUnit 5 e Allure.

A suíte é executada também via GitHub Actions em Windows, Linux e macOS.

## Tecnologias

- Java 11
- Maven
- Selenium WebDriver
- JUnit 5
- Allure Report
- GitHub Actions

## Cenários automatizados

- Pesquisa com termo válido, validando o retorno de artigos.
- Pesquisa com termo inexistente, validando a ausência de resultados.
- Validação da abertura do campo de pesquisa pela lupa — mantida como `Known Issue`.

## Problema identificado

Durante a análise foi identificado um problema na interface de pesquisa.

Ao clicar no ícone de lupa, o overlay de pesquisa existe no DOM, porém permanece oculto:

```text
display: none
opacity: 0
```

Consequentemente, o usuário não consegue visualizar nem utilizar o campo de pesquisa.

O cenário que valida esse comportamento foi automatizado e mantido com `@Disabled` como um **Known Issue**, evitando que um defeito já identificado deixe toda a suíte permanentemente com falha.

Para continuar validando a funcionalidade de pesquisa independentemente desse problema de interface, os cenários funcionais utilizam a própria rota pública de pesquisa do blog:

```text
https://blog.agibank.com.br/?s=<termo>
```

Dessa forma, o defeito da interface permanece documentado e coberto, enquanto a lógica de pesquisa continua sendo testada.

## Estrutura do projeto

```text
src/test
├── java/com/agiblog
│   ├── config
│   │   └── TestConfig.java
│   ├── driver
│   │   └── DriverFactory.java
│   ├── extensions
│   │   └── FailureEvidenceExtension.java
│   ├── pages
│   │   ├── BasePage.java
│   │   ├── HomePage.java
│   │   └── SearchResultsPage.java
│   └── tests
│       ├── BaseTest.java
│       └── SearchTest.java
│
└── resources
    └── allure.properties
```

Principais responsabilidades:

- `DriverFactory`: criação e configuração do WebDriver.
- `BasePage`: comportamentos comuns das páginas.
- `HomePage`: interação com a lupa e o componente de pesquisa.
- `SearchResultsPage`: interação e validação da página de resultados.
- `BaseTest`: inicialização e encerramento do browser.
- `FailureEvidenceExtension`: captura URL e screenshot quando um teste falha.
- `SearchTest`: cenários automatizados.

## Pré-requisitos

- Java 11
- Maven 3.x
- Google Chrome

Também é possível utilizar o Maven Wrapper incluído no projeto.

## Executando os testes

Com Maven:

```bash
mvn clean test
```

Executando com o navegador visível:

```bash
mvn clean test -Dheadless=false
```

### Maven Wrapper

Linux/macOS:

```bash
./mvnw clean test
```

Windows:

```powershell
.\mvnw.cmd clean test
```

Por padrão, os testes são executados em modo `headless`.

## Relatório Allure

Após executar os testes:

```bash
mvn allure:report
```

O relatório será gerado em:

```text
target/allure-report/index.html
```

Também é possível abrir o relatório diretamente:

```bash
mvn allure:serve
```

Em caso de falha, o Allure recebe automaticamente:

- screenshot do navegador;
- URL da página no momento da falha;
- detalhes da execução.

## Integração Contínua

O GitHub Actions executa a suíte automaticamente em:

- Linux;
- Windows;
- macOS.

Os relatórios Surefire, resultados Allure e relatório HTML são disponibilizados como artifacts da execução, inclusive quando algum teste falha.

## Autor

Lucas Duarte