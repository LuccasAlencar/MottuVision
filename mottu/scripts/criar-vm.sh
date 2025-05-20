
#!/bin/bash
set -e

# Descobre o diretório onde o script está e, a partir dele, a raiz do projeto
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
PROJECT_ROOT="$(dirname "$SCRIPT_DIR")"

# Configurações
RG_NAME="rg-MottuVision-Challenge-1Sem-2tdsb-brazilsouth"
LOCATION="brazilsouth"
VM_NAME="vm-MottuVision-Challenge-1Sem-2tdsb-brazilsouth"
VM_SIZE="Standard_B2s"
VNET_NAME="vnet-MottuVision-Challenge-1Sem-2tdsb-brazilsouth"
SUBNET_NAME="snet-MottuVision-Challenge-1Sem-2tdsb-main"
NSG_NAME="nsg-MottuVision-Challenge-1Sem-2tdsb-brazilsouth"
ADMIN_USER="azureuser"
ADMIN_PASSWORD="Fiap2TDSB2025"
OS_DISK_SIZE=64

# Criar Resource Group, VNet, Subnet, NSG e regras básicas
az group create --name $RG_NAME --location $LOCATION --tags Tarefa=201

az network vnet create \
  --resource-group $RG_NAME \
  --name $VNET_NAME \
  --address-prefix 10.0.0.0/16 \
  --tags Tarefa=201

az network vnet subnet create \
  --resource-group $RG_NAME \
  --vnet-name $VNET_NAME \
  --name $SUBNET_NAME \
  --address-prefixes 10.0.0.0/24

az network nsg create \
  --resource-group $RG_NAME \
  --name $NSG_NAME \
  --tags Tarefa=201

# SSH
az network nsg rule create \
  --resource-group $RG_NAME \
  --nsg-name $NSG_NAME \
  --name AllowSSH \
  --priority 1000 \
  --direction Inbound \
  --access Allow \
  --protocol Tcp \
  --source-address-prefixes '*' \
  --destination-port-ranges 22

# Porta 3000
az network nsg rule create \
  --resource-group $RG_NAME \
  --nsg-name $NSG_NAME \
  --name Allow3000 \
  --priority 1200 \
  --direction Inbound \
  --access Allow \
  --protocol Tcp \
  --source-address-prefixes '*' \
  --destination-port-ranges 3000

# Criar a VM e já abrir porta 3000
az vm create \
  --resource-group $RG_NAME \
  --name $VM_NAME \
  --location $LOCATION \
  --size $VM_SIZE \
  --vnet-name $VNET_NAME \
  --subnet $SUBNET_NAME \
  --nsg $NSG_NAME \
  --admin-username $ADMIN_USER \
  --admin-password "$ADMIN_PASSWORD" \
  --os-disk-size-gb $OS_DISK_SIZE \
  --image "Ubuntu2204" \
  --public-ip-sku Standard \
  --tags Tarefa=201 \
  --custom-data "$PROJECT_ROOT/scripts/install_docker.sh"


echo "✅ VM criada e provisionada com Docker. A porta 3000 está aberta!"



