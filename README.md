# api-gestao-biblioteca
API REST para gestão de biblioteca, desenvolvida para gerenciar o cadastro de livros, autores, editoras, usuários e o controle de empréstimos. O sistema permite que funcionários registrem empréstimos e devoluções, enquanto clientes podem consultar o catálogo e agendar retiradas de livros.

## Quickstart
Seguindo as intruções abaixo, a aplicação subirá localmente em menos de 2 minutos.

### Pré-requisitos
* [Docker Desktop](https://docker.com) (inclui Docker Compose)
* Git
* Conexão com a internet

### Instalação passo a passo

1. **Clone o repositório:**
   ```bash
   git clone <https://github.com/Yan-Balbi/api-gestao-biblioteca.git>
   cd <pasta-do-repositorio>
   ```

2. **Configure as variaveis de ambiente:**
   Copie o arquivo de exemplo de .env e atualize os valores.
   ```bash
   cp .env.example .env
   ```

3. **Faça Build e inicie os containeres:**
   ```bash
   docker compose up --build
   ```

O backend agora deverá estar rodando em `http://localhost:8080`.
<!-- 
---

## 🛠️ Docker Configuration Details

### Available Services
* **backend**: The main application server.
* **database**: PostgreSQL/MongoDB instance for data storage.
* **cache**: Redis instance for session management and caching.

### Useful Commands

* **Stop services:**
  ```bash
  docker compose down
  ```
* **Stop services and remove volumes (wipes data):**
  ```bash
  docker compose down -v
  ```
* **View real-time logs:**
  ```bash
  docker compose logs -f
  ```
* **View logs for a specific service:**
  ```bash
  docker compose logs -f backend
  ```
* **Execute a command inside the backend container:**
  ```bash
  docker compose exec backend <command>
  ```

---

## 🧪 Running Tests

Run your test suite inside the isolated Docker environment using the following command:

```bash
docker compose exec backend npm test
```
*(Note: Replace `npm test` with your language's test command, e.g., `pytest` or `go test`)*

---

## 📦 Production Deployment

To build a production-ready, minified Docker image, run:

```bash
docker build --target production -t backend-service:latest .
```

### Production Best Practices Implemented:
* **Multi-stage builds:** Keeps the final image size minimal.
* **Non-root user:** Runs the application securely without root privileges.
* **Layer caching:** Optimized `Dockerfile` instructions for faster subsequent builds.
-->
