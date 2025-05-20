#!/bin/bash
set -e

echo "🔧 Atualizando pacotes..."
apt-get update -y

echo "🔧 Instalando dependências..."
apt-get install -y ca-certificates curl gnupg lsb-release

echo "🔧 Adicionando chave GPG oficial do Docker..."
mkdir -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg \
  | gpg --dearmor -o /etc/apt/keyrings/docker.gpg

echo "🔧 Adicionando repositório do Docker..."
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] \
  https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" \
  | tee /etc/apt/sources.list.d/docker.list > /dev/null

echo "🔧 Atualizando pacotes novamente..."
apt-get update -y

echo "🐳 Instalando Docker Engine e Docker Compose plugin..."
apt-get install -y docker-ce docker-ce-cli containerd.io docker-compose-plugin

echo "👤 Adicionando usuário ao grupo docker..."
usermod -aG docker azureuser

echo "🎉 Instalação do Docker concluída!"
echo "⚠️ IMPORTANTE: Para que as permissões de grupo sejam aplicadas, o usuário precisa fazer logout e login novamente."