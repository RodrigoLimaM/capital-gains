# Capital Gains

## Introdução
O objetivo deste projeto é implementar um programa de linha de comando (CLI) que calcula o imposto a ser pago sobre lucros ou prejuízos de operações no mercado financeiro de ações.

Exemplo de entrada:
```json lines
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},{"operation":"sell", "unit-cost":20.00, "quantity": 5000}]
[{"operation":"buy", "unit-cost":20.00, "quantity": 10000},{"operation":"sell", "unit-cost":10.00, "quantity": 5000}]

```
OBS:A última linha da entrada será uma linha vazia.

Exemplo de saída:
```json lines
[{"tax":0}, {"tax":10000}]
[{"tax":0}, {"tax":0}]
```

## Execução do projeto

Primeiro gere o arquivo jar:
```
./gradlew fatJar
```
Depois execute-o:
```
java -jar .\build\libs\capital-gains-1.0.jar
```

## Execução de testes
```
./gradlew test
```

## Desenho da solução
![capital-gains fluxo](https://github.com/RodrigoLimaM/weather-api/assets/51386403/9a503e6c-8ca8-43cd-87f7-84fb3a5f225a)
* 1 - Controller que recebe as operações e conversa com a porta de entrada;
* 2 - Parser responsável pela serialização e deserialização entre JSON's e objetos Java;
* 3 - Interface responsável pelo contrato relacionado ao calculo de impostos;
* 4 - Serviço responsável por construir o fluxo geral do calculo de impostos;
* 5 - Enum responsável por construir a estratégia pra cada tipo de operação;
* 6 - Interface em que seus implementadores conhecem e realizam os processamentos necessários pra cada tipo de operação;
* 7 - Implementação que tem como responsabilidade lidar com as operações do tipo BUY;
* 8 - Implementação que tem como responsabilidade lidar com as operações do tipo SELL;
* 9 - Serviço responsável pelas operações matemáticas necessárias para calcular os impostos;
* 10 - Objeto de domínio que contém as informações necessárias para o calculo de impostos relacionado a uma lista de operacoes;
* 11 - Objeto de domínio que representa uma operação de compra ou venda e suas informações;
* 12 - Objeto de domínio que representa o imposto relacionado a uma operação de compra ou venda;

Em resumo, o projeto utiliza objetos de domínio para encapsular informações sobre as operações, adota o padrão de projeto Strategy para lidar com o cálculo de impostos e mantém uma arquitetura modular para facilitar a expansão e manutenção do código.

## Bibliotecas utilizadas
```
java.io.BufferedReader;
```
A classe BufferedReader da biblioteca "io" foi utilizada para efetuar leitura de entrada do usuário no projeto.
```
com.fasterxml.jackson.databind.ObjectMapper;
```
A classe ObjectMapper da biblioteca "Jackson" foi utilizada para realizar de forma eficiente e flexível a deserialização e serialização de objetos JSON em objetos Java no projeto.

```
java.math.BigDecimal;
```
A classe BigDecimal da biblioteca "math" foi utilizada para lidar com cálculos financeiros devido à sua precisão decimal, evitando problemas associados com arredondamentos inapropriados em tipos de dados de ponto flutuante, como double ou float.

