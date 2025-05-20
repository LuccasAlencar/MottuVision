#!/usr/bin/env bash
set -e

# --- identifica caminhos ---
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
PROJECT_ROOT="$(dirname "$SCRIPT_DIR")"
COMPOSE_FILE="$PROJECT_ROOT/docker-compose.yaml"
DOCKERFILE="$PROJECT_ROOT/Dockerfile"
INSTALL_SCRIPT="$PROJECT_ROOT/scripts/install-docker.sh"

# --- variáveis de infra ---
RG="rg-MottuVision-Challenge-1Sem-2tdsb-brazilsouth"
VM="vm-MottuVision-Challenge-1Sem-2tdsb-brazilsouth"

# 1) Cria a VM
bash "$PROJECT_ROOT/scripts/criar-vm.sh"

# 2) Pega o IP público
IP=$(az vm show \
  --resource-group "$RG" \
  --name "$VM" \
  --show-details \
  --query publicIps -o tsv)
echo "✅ VM pronta em $IP"

# 3) Copia os arquivos necessários (agora incluindo o Dockerfile)
echo "📁 Copiando arquivos para a VM..."
scp "$COMPOSE_FILE" "$DOCKERFILE" "$INSTALL_SCRIPT" azureuser@"$IP":/home/azureuser/

# 4) Executa a instalação como sudo (primeira conexão)
echo "🔧 Instalando Docker na VM..."
ssh -t azureuser@"$IP" "chmod +x install-docker.sh && sudo ./install-docker.sh"

# 5) Inicia o serviço Docker e garante que esteja rodando
echo "🚀 Iniciando o serviço Docker..."
ssh -t azureuser@"$IP" "sudo systemctl enable docker && sudo systemctl start docker"

# 6) Para aplicar as mudanças de grupo sem fazer logout e login, use newgrp
echo "🔄 Aplicando permissões do grupo docker para o usuário azureuser..."
ssh -t azureuser@"$IP" "sudo chmod 666 /var/run/docker.sock"

# 7) Criando pasta de dados para persistência
echo "📂 Criando pasta de dados para volumes Docker..."
ssh -t azureuser@"$IP" "mkdir -p /home/azureuser/data"

# 8) Roda o docker compose com as permissões corretas
echo "🐳 Executando Docker Compose..."
ssh -t azureuser@"$IP" "
  docker --version &&
  cd /home/azureuser &&
  docker compose -f docker-compose.yaml up -d --build
"

echo "🎉 Aplicação disponível em http://$IP:3000"