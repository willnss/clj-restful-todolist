# Projeto clj-restful-todolist

API com um CRUD bem simplificada de lista de tarefas, sem testes automatizados e doc, apenas para exercitar os conhecimentos básicos recém adquiridos da linguagem Clojure. Neste projeto foi utilizado as bibliotecas abaixo:

- Compojure para criação da api
- Toucan para interação com o banco de dados Postgres 
- Migratus para automatizar a criação da tabela necessária
- Schema para simplificar o model-binding e validação do paylod

Foi utilizado o Docker/docker-compose para simplificar a criação do banco de dados.

## Ferramentas usadas para implementar o projeto e rodar

- clojure CLI 1.10.2.796 
- openjdk/java 14.0.2 2020-07-14
- docker 20.10.2 (Docker Desktop com WSL2 backend)
- ubuntu 20 LTS + vscode-server e extensão WSL do Visual Code
- leiningen 2.9.5

## Como executar o projeto

**Note que o projeto foi originalmente desenvolvimento em ambiente Linux com o Ubuntu rodando sob o WSL2 no Windows 10**

### 1.  Após realizar o clone do projeto abra o terminal e execute o comando abaixo para baixar a imagem do postgres:
```bash
$ docker-compose up -d
```

### 2. Execute o migration do projeto através do comando abaixo, será necessário que o container com o postgres iniciado no passo anterior esteja em execução:
```bash
$ lein migratus migrate
```
Caso deseje visualizar o banco utilize o pg-admin através do navegador pelo link (http://localhost:16543), o container e credênciais também estão especificados no arquivo `docker-compose.yml`

### 3. Caso a migration tenha sido executada com sucesso execute o comando abaixo para subir a aplicação e acesse o link (http://localhost:3000/swagger) em seguida:

```bash
$ lein run
```

## Observações 

Caso precise alterar as informações de conexão altere os arquivos `project.clj`  na seção `:migratus` e e `src/clj_restful_todolist/core.clj` na definição de `db-spec`.

## Licença

Vide arquivo LICENSE