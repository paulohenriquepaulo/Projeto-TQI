# Projeto-TQI
Projeto processo seletivo TQI

Inicie o sistema a partir da classe
```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

O Sistema utiliza o banco de dados h2, para visualizar os dados acesse
[H2]: http://localhost:8080/h2-console


# Utilização

O Sistema pode ser testado utilizando o swagger-ui a partir do endereço
[Swagger] http://localhost:8080/swagger-ui/index.html#/


1 - Cadastrar Cliente ```POST \clientes```
```json
{
  "nome": "string",
  "email": "string",
  "cpf": "string",
  "rg": "string",
  "endereco": {
    "logradouro": "string",
    "estado": "AMAZONAS",
    "cidade": "string",
    "numero": 0,
    "complemento": "string"
  },
  "renda": 0,
  "senha": "string"
}
```

2 - Cadastrar Empréstimo ```POST /emprestimos```
```json
{
  "situacao": "SOLICITADO",
  "valor": 0,
  "dataPrimeiraParcela": "2022-01-10",
  "quantidadeParcelas": 60
}
```

3 - Listar Empréstimos ```GET /emprestimos```
```json
[
  {
    "codigo": 0,
    "valor": 0,
    "quantidadeParcelas": 0
  }
]
```

4 - Detalhar Empréstimo ```GET /emprestimos/{id}```
```json
{
  "codigo": 0,
  "valorEmprestimo": 0,
  "quantidadeParcelas": 0,
  "emailCliente": "string",
  "rendaCliente": 0,
  "dataPrimeiraParcela": "2022-01-10"
}
```