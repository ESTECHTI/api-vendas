# API Vendas

API REST para prática com Spring Boot, focada em cadastro e gerenciamento de usuários.

## Tecnologias

- Java 21
- Spring Boot 4.0.5
- Spring Web MVC
- Spring Data JPA
- Spring Security
- Bean Validation (Jakarta Validation)
- MapStruct
- Maven
- Banco de dados: MySQL (padrão) e H2 (perfil local)

## Estrutura Principal

- `controller`: endpoints REST
- `service`: regras de negócio
- `repository`: acesso a dados
- `dto`: objetos de entrada e saída
- `mapper`: conversão entre entidade e DTO (MapStruct)
- `exception`: tratamento global de exceções
- `security`: classes relacionadas a JWT (em evolução)

## Pré-requisitos

- JDK 21
- Maven 3.9+ (ou usar `mvnw`/`mvnw.cmd`)

## Como executar

### Opção 1: perfil padrão (MySQL)

No perfil padrão, as configurações vêm de variáveis de ambiente.

Defina:

- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`

Exemplo no PowerShell:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/api_vendas"
$env:DB_USER="root"
$env:DB_PASSWORD="root"
./mvnw spring-boot:run
```

### Opção 2: perfil local (H2 em memória)

O perfil `local` usa H2 e sobe na porta `8081`.

```powershell
./mvnw spring-boot:run "-Dspring-boot.run.profiles=local"
```

Recursos do perfil local:

- H2 Console: `http://localhost:8081/h2-console`
- JDBC URL: `jdbc:h2:mem:apivendas`
- Usuário: `sa`
- Senha: em branco

## Endpoints

Base URL (perfil local): `http://localhost:8081`

### Criar usuário

- Método: `POST`
- Rota: `/usuarios`

Body:

```json
{
	"nome": "Maria Silva",
	"email": "maria@teste.com"
}
```

### Listar usuários (com paginação/filtro)

- Método: `GET`
- Rota: `/usuarios`
- Query params opcionais:
	- `nome`
	- `email`
	- `page`
	- `size`
	- `sort` (ex.: `sort=nome,asc`)

Exemplo:

```http
GET /usuarios?nome=maria&page=0&size=10&sort=nome,asc
```

### Buscar usuário por ID

- Método: `GET`
- Rota: `/usuarios/{id}`

### Atualizar usuário

- Método: `PUT`
- Rota: `/usuarios/{id}`

Body:

```json
{
	"nome": "Maria Souza",
	"email": "maria.souza@teste.com"
}
```

### Remover usuário

- Método: `DELETE`
- Rota: `/usuarios/{id}`

## Respostas de erro

Quando um usuário não é encontrado, a API retorna HTTP `404` com o formato:

```json
{
	"mensagem": "Usuário não encontrado",
	"status": 404
}
```

## Autenticação e segurança

- No perfil `local`, as rotas exigem autenticação.
- As rotas `/auth/**` estão liberadas na configuração, mas não existe controller de autenticação implementado no momento.
- Também há classes de JWT no projeto em desenvolvimento (`JwtAuthFilter` e `JwtService`).

### Boas práticas para credenciais

- Não versione usuário/senha reais no Git.
- Use variáveis de ambiente para sobrescrever credenciais locais.
- Use valores de exemplo no README (placeholders), nunca credenciais reais.

Exemplo no PowerShell para ambiente local:

```powershell
$env:SPRING_SECURITY_USER_NAME="<seu_usuario_local>"
$env:SPRING_SECURITY_USER_PASSWORD="<sua_senha_local>"
./mvnw spring-boot:run "-Dspring-boot.run.profiles=local"
```

Exemplo de uso em requisição (placeholder):

```bash
curl -u <usuario>:<senha> http://localhost:8081/usuarios
```

## Comandos úteis

Executar testes:

```powershell
./mvnw test
```

Gerar pacote:

```powershell
./mvnw clean package
```

## Próximos passos sugeridos

- Implementar endpoint de login em `/auth` para emissão de token JWT.
- Conectar e registrar o filtro JWT na cadeia de segurança.
- Adicionar testes de integração para os endpoints de usuários.
