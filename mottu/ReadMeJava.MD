# Sistema de Mapeamento Inteligente de Pátio Mottu

<div align="center">
  <img src="https://img.shields.io/badge/Java-17-orange" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring_Boot-3.2.3-green" alt="Spring Boot 3.2.3">
  <img src="https://img.shields.io/badge/Docker-Ready-blue" alt="Docker Ready">
  <img src="https://img.shields.io/badge/Azure-Deployed-blue" alt="Azure Deployed">
  <img src="https://img.shields.io/badge/Maven-Project-purple" alt="Maven Project">
</div>


## Grupo:
#### Daniel da Silva Barros | RM 556152
#### Luccas de Alencar Rufino | RM 558253  
<br>

## 📝 Descrição do Projeto

A Mottu, líder em aluguel de motocicletas para entregadores de aplicativo, enfrenta desafios na localização manual de motos em seus pátios, causando perdas de frotas e retrabalhos operacionais. Este projeto implementa um Sistema de Mapeamento Inteligente de Pátio que integra:

__Visão Computacional:__ captura e reconhecimento automático de motocicletas (placa, modelo).

__API REST:__ camadas Java Spring Boot para gerenciar entidades críticas: Moto, Câmera, Usuário, Cargo, Registro e Reconhecimento.

__Auditoria:__ Log de Alterações para rastrear modificações de dados.

__Documentação:__ interface Swagger para explorar os endpoints interativamente.

## ⭐ Principais Benefícios

Localização em segundos em vez de minutos

Redução de perdas e erros de inventário

Aumento de até 40% na produtividade operacional

Escalabilidade para mais de 100 filiais

Rastreabilidade e auditoria completa

## ⚙️ Tecnologias e Dependências

Java 17

Spring Boot 3.2.3 (Web, Data JPA, Validation, Cache, Actuator)

H2 Database (memória, dev)

Hibernate ORM 6

Caffeine Cache

Springdoc OpenAPI 2.2.0 (Swagger UI)

Lombok (facilita getters/setters)

Maven (gerenciamento de dependências)

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas com:

- **Controller:** Endpoints REST da API
- **Service:** Lógica de negócios e processamento
- **Repository:** Interação com o banco de dados
- **Model:** Entidades e objetos de transferência de dados (DTOs)
- **Config:** Configurações do Spring e cache
- **Exception:** Tratamento centralizado de exceções

## 📝 Entidades Principais

- **Moto:** Informações sobre as motocicletas (placa, modelo, status)
- **Câmera:** Dispositivos de captura instalados no pátio
- **Usuário:** Funcionários/operadores do sistema
- **Cargo:** Funções e permissões de usuários
- **Registro:** Histórico de movimentações de motos
- **Reconhecimento:** Detecções realizadas pelo sistema de visão computacional
- **LogsAlterações:** Detecções de quando algo é atualizado manualmente

---

## 🚀 Como Executar

__Clone o repositório:__

git clone https://github.com/LuccasAlencar/MottuVision.git

cd ---------------------------------------------------------------

__Build e Run:__

mvn clean spring-boot:run

__Acessar a API:__

Base URL: http://localhost:3000/

## 📖 Documentação Swagger

Após iniciar a aplicação, abra no navegador:

http://localhost:3000/swagger-ui/index.html#/

__Interativo:__ explore e teste todos os endpoints.



## 📜 Lista Completa de Endpoints REST

Abaixo, todos os endpoints disponíveis na API (usando o context-path /api):
| Entidades | Método | URL | Descrição |
| ------------- | ------------- | ------------- | ------------- |
| Moto | GET  | /api/motos | Listar motos (filtros: ?marca=...&modelo=..., paginação/ordenação).|
|  | GET | /api/motos/{id} | Buscar moto por ID.|
|  | POST | /api/motos | Criar nova moto.|
|  | PUT | /api/motos/{id} | Atualizar moto existente.|
|  | DELETE | /api/motos/{id} | Deletar moto por ID. |
| Câmera  | GET | /api/cameras | Listar câmeras (filtros: ?status=...&localizacao=..., paginação). |
|  | GET  | /api/cameras/{id} | Buscar câmera por ID. |
|  | POST | /api/cameras | Criar nova câmera. |
|  | PUT  | /api/cameras/{id} | Atualizar câmera existente |
|  | DELETE | /api/cameras/{id} | Deletar câmera por ID. |
| Usuário | GET | /api/usuarios | Listar usuários (paginação/ordenação). |
|  | GET | /api/usuarios/{id} | Buscar usuário por ID. |
|  | POST  | /api/usuarios | Criar novo usuário. |
|  | PUT | /api/usuarios/{id} | Atualizar usuário existente. |
|  | DELETE  | /api/usuarios/{id} | Deletar usuário por ID. |
| Cargo | GET | /api/cargos | Listar cargos (paginação/ordenação). |
|  | GET  | /api/cargos/{id} | Buscar cargo por ID. |
|  | POST | /api/cargos | Criar novo cargo. |
|  | PUT | /api/cargos/{id} | Atualizar cargo existente. |
|  | DELETE | /api/cargos/{id} | Deletar cargo por ID. |
| Registro | GET  | /api/registros | Listar registros de entrada/saída (filtros/data, paginação). |
|  | GET | /api/registros/{id} | Buscar registro por ID. |
|  | POST | /api/registros | Criar novo registro manual ou automático. |
|  | PUT | /api/registros/{id} | Atualizar registro existente. |
|  | DELETE | /api/registros/{id} | Deletar registro por ID. |
| Reconhecimento | GET | /api/reconhecimentos | Listar reconhecimentos (filtros/data, paginação). |
|  | GET | /api/reconhecimentos/{id} | Buscar reconhecimento por ID. |
|  | POST | /api/reconhecimentos | Criar novo reconhecimento automático. |
|  | PUT | /api/reconhecimentos/{id} | Atualizar reconhecimento existente. |
|  | DELETE | /api/reconhecimentos/{id} | Deletar reconhecimento por ID. |
| LogAlteracoes | GET | /api/log-alteracoes | Listar logs de alterações (filtros/data, paginação). |
|  | GET | /api/log-alteracoes/{id} | Buscar log de alteração por ID. |
|  | POST | /api/log-alteracoes | Criar novo log de alteração (após update de entidade). |
|  | DELETE | /api/log-alteracoes/{id} | Deletar log de alteração por ID. |

Use ?page=0&size=10&sort=id,desc para paginação/ordenação.


Desenvolvido com ☕ e ♥️ por Daniel Barros e Luccas Rufino.
