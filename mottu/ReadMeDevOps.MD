# Sistema de Mapeamento Inteligente de Pátio Mottu

<div align="center">
  <img src="https://img.shields.io/badge/Java-17-orange" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring_Boot-3.2.3-green" alt="Spring Boot 3.2.3">
  <img src="https://img.shields.io/badge/Docker-Ready-blue" alt="Docker Ready">
  <img src="https://img.shields.io/badge/Azure-Deployed-blue" alt="Azure Deployed">
  <img src="https://img.shields.io/badge/Maven-Project-purple" alt="Maven Project">
</div>

## 👥 Grupo
- **Daniel da Silva Barros | RM 556152**
- **Luccas de Alencar Rufino | RM 558253**

## 📝 Descrição do Projeto

A Mottu, líder em aluguel de motocicletas para entregadores de aplicativo, enfrenta desafios na localização manual de motos em seus pátios, causando perdas de frotas e retrabalhos operacionais. Este projeto implementa um Sistema de Mapeamento Inteligente de Pátio que integra:

- **Visão Computacional:** captura e reconhecimento automático de motocicletas (placa, modelo).
- **API REST:** camadas Java Spring Boot para gerenciar entidades críticas: Moto, Câmera, Usuário, Cargo, Registro e Reconhecimento.
- **Auditoria:** Log de Alterações para rastrear modificações de dados.
- **Documentação:** interface Swagger para explorar os endpoints interativamente.

## ⭐ Principais Benefícios

- Localização em segundos em vez de minutos
- Redução de perdas e erros de inventário
- Aumento de até 40% na produtividade operacional
- Escalabilidade para mais de 100 filiais
- Rastreabilidade e auditoria completa


## ⚙️ Tecnologias e Dependências

- Java 17
- Spring Boot 3.2.3 (Web, Data JPA, Validation, Cache, Actuator)
- H2 Database (memória, dev)
- Hibernate ORM 6
- Caffeine Cache
- Springdoc OpenAPI 2.2.0 (Swagger UI)
- Lombok (facilita getters/setters)
- Maven (gerenciamento de dependências)
- Docker e Docker Compose
- Azure Virtual Machine (Ubuntu 22.04, Standard_B2s)

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas com:

- **Controller:** Endpoints REST da API
- **Service:** Lógica de negócios e processamento
- **Repository:** Interação com o banco de dados
- **Model:** Entidades e objetos de transferência de dados (DTOs)
- **Config:** Configurações do Spring e cache
- **Exception:** Tratamento centralizado de exceções

## 🚀 Instalação e Execução

### ☁️ Deployment na Azure

O projeto inclui scripts para deployment automático em Azure Virtual Machine, configurando todo o ambiente necessário.

**Pré-requisitos:**
- Azure CLI instalado e configurado
- Acesso à subscription do Azure

**Passos para deployment:**

1. Clone o repositório:
```bash
git clone https://github.com/LuccasAlencar/MottuVision.git
cd MottuVision/mottu/scripts
```

2. Dê permissão de execução ao script de deploy:
```bash
chmod +x deploy.sh
```

3. Execute o script de deployment:
```bash
bash deploy.sh
```

4. Durante a execução:
   - Confirme a criação dos recursos com "yes" quando solicitado
   - Digite a senha `Fiap2TDSB2025` quando solicitado (várias vezes durante o processo)

**Importante:** O script automaticamente executará o `docker compose up -d --build` na VM, não sendo necessário executar este comando manualmente.

5. Após a conclusão bem-sucedida, a aplicação estará disponível em:
```
http://<IP-PUBLICO-DA-VM>:3000
```

**Para remover os recursos da Azure:**
```bash
chmod +x delete-vm.sh
bash delete-vm.sh
```
## 🌐 Acessando a Aplicação

### Página Principal

Após o deployment, a aplicação estará disponível na página inicial:

```
http://localhost:3000/
```

ou via Azure:

```
http://<IP-PUBLICO-DA-VM>:3000/
```

### 📖 Documentação Swagger

A documentação interativa dos endpoints estará disponível em:

```
http://localhost:3000/swagger-ui/index.html
```

ou via Azure:

```
http://<IP-PUBLICO-DA-VM>:3000/swagger-ui/index.html
```

A documentação permite explorar e testar todos os endpoints da API de forma interativa.




### 💻 Execução Local

**Pré-requisitos:**
- Java 17 JDK instalado
- Maven configurado

**Clone o repositório:**
```bash
git clone https://github.com/LuccasAlencar/MottuVision.git
cd MottuVision/mottu/scripts
```

**Build e Run:**
```bash
mvn clean spring-boot:run
```

## 📚 Infraestrutura na Azure

O projeto utiliza:

- **Resource Group:** `rg-MottuVision-Challenge-1Sem-2tdsb-brazilsouth`
- **Região:** `brazilsouth`
- **VM:** `Standard_B2s` rodando Ubuntu 22.04
- **Rede:** VNet, Subnet e NSG configurados automaticamente
- **Portas abertas:** 22 (SSH) e 3000 (API)

## 🔧 Configuração do Ambiente na VM Azure

Os scripts de deployment realizarão automaticamente:

1. Criação de Resource Group, VNet, Subnet e NSG
2. Provisionamento da VM com Ubuntu 22.04
3. Instalação do Docker e Docker Compose
4. Configuração de permissões e diretórios
5. Cópia dos arquivos de configuração Docker para a VM
6. Build e execução da aplicação via Docker Compose


## 📝 Entidades Principais

- **Moto:** Informações sobre as motocicletas (placa, modelo, status)
- **Câmera:** Dispositivos de captura instalados no pátio
- **Usuário:** Funcionários/operadores do sistema
- **Cargo:** Funções e permissões de usuários
- **Registro:** Histórico de movimentações de motos
- **Reconhecimento:** Detecções realizadas pelo sistema de visão computacional
- **LogsAlterações:** Detecções de quando algo é atualizado manualmente

---

Desenvolvido com ☕ e ♥️ por Daniel Barros e Luccas Rufino.
