# Desafio Serasa Experian – Nível 3

### Objetivo: Criar um serviço do tipo API REST, para cadastro de pessoas com score e suas regiões de afinidades

**1. Para a construção desse serviço, algumas premissas e sugestões**

   - Usar linguagem Java (preferência Java 11);
   - Usar maven no build do projeto;
   - Pode criar archetype usando o start.spring.io (adicione as dependências que achar relevantes);
   - Usar framework Spring (incluindo Spring Boot, para iniciar o serviço);
   - Montar um banco de dados em memória (pode usar o H2, HSQLDB ou MongoDB), usando Hibernate na persistência de dados;
   - Necessário pelo menos um teste unitário para cada método da camada Service, usando JUnit e Mockito;
   - Documentar contratos REST usando Swagger;
   - Colocar autenticação com token JWT

**2. Endpoints do serviço**

- POST /pessoa ◦ Informar a seguinte estrutura de dados na inclusão:
```json
{
   "nome":"Fulano de Tal",
   "telefone":“99 99999-9999”,
   "idade":99,
   "cidade":"Cidade de Fulano",
   "estado":"XX",
   "score":1000, // Entre 0 e 1000
   "regiao":"sudeste"
}
```
- Adicionar um atributo id automático e data de inclusão, além dos dados do POST, durante inclusão dos dados no banco; 
- Retornar 201 no sucesso da inclusão;

- POST /afinidade ◦ Informar a seguinte estrutura de dados na inclusão:
```json
{
   "scoreDescricao":"Insuficiente",
   "inicial":0,
   "final":200
}
```
- Retornar 201 no sucesso da inclusão;

- POST /score ◦ Informar a seguinte estrutura de dados na inclusão:
```json
{
  "scoreDescricao":"Insuficiente",
  "inicial":0,
  "final":200
}
```
- Retornar 201 no sucesso da inclusão;

- GET /pessoa/{id} ◦ Se id encontrado no banco, retornar a seguinte estrutura de dados:
```json
{
  "nome":"Fulano de Tal",
  "telefone":“99 99999-9999”,
  "idade":99,
  "scoreDescricao":"Recomendável",
  "estados":[
  "SP",
  "RJ",
  "MG",
  "ES"
  ]
}
```

- Se id encontrado no banco, retornar 200, com a estrutura de dados;
- Se id não encontrado no banco, retornar 204 (no content);

- GET /pessoa ◦ Retornar uma lista de todo o cadastro, sendo cada item da lista com a seguinte estrutura de dados:
 ```json
 [
  {
    "nome":"Fulano de Tal",
    "cidade":"Cidade de Fulano",
    "estado":"XX",
    "scoreDescricao":"Recomendável",
    "estados":[
      "SP",
      "RJ",
      "MG",
      "ES"
    ]
  },
  {
    "nome":"Sicrano de Tal",
    "cidade":"Cidade de Sicrano",
    "estado":"XX",
    "scoreDescricao":"Insuficiente",
    "estados":[
      "RS",
      "PR",
      "SC"
    ]
  }
]
```
- Se algum cadastro encontrado no banco, retornar 200, com a estrutura JSON;
- Se nenhum item encontrado no banco, retornar 204 (no content);

**3. Lógica do serviço**

- Montar lógica na camada Service, para associar a regiao da afinidade, com a regiao da pessoa, e retornar a lista de estados
  correspondentes à regiao.
- Montar lógica na camada Service, para retornar o atributo scoreDescricao, correspondente ao score encontrado entre inicial e final;
- Cadastrar via POST os seguintes dados na estrutura score:

| scoreDescricao | inicial | final |
| ------ |---------| --------- |
| Insuficiente | 0       | 200 |
| Inaceitável | 201     | 500 |
| Aceitável | 501     | 700 |
| Recomendável | 701     | 1000 |

**4. Estrutura do Banco de dados**

- pessoa
  <ul>
    <li>id – numérico</li> 
    <li>dataInclusao – data</li> 
    <li>nome – texto</li> 
    <li>telefone – texto</li> 
    <li>idade – numérico</li> 
    <li>cidade – texto</li> 
   <li>estado – texto</li>
   <li>regiao  – texto</li>
  </ul>
- afinidade
    <ul>
    <li>regiao  – texto</li> 
    <li>estados – lista</li>
    </ul>
- score
    <ul>
    <li>descricao  – texto</li>
    <li>inicial – numérico</li>
    <li>final – numérico</li>
    </ul>

## Para Testar a Aplicação:
1. Importe no postman o Arquivo serasa_collection.json localizado em: src/main/java/resources.
2. Execute a requisição authenticate localizada na pasta Login.
3. Para testar as demais requisições, na aba Authorization do postman selecione Bearer token e adicione o token gerado no passo anterior.
4. Para executar os testes e gerar o relatório de cobertura no terminal na pasta raiz do projeto execute **./mvnw test**