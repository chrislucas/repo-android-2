#!/bin/bash

# Este script deve ser executado da raiz do repositório
# Ele entra no diretório JustComposeLabs e executa o detekt via Gradle

echo "Running detekt check via Gradle in JustComposeLabs..."

# Armazena o diretório atual para voltar depois, se necessário
START_DIR=$(pwd)
SCRIPT_DIR=$(dirname "$0")

# Caminho para o diretório do projeto JustComposeLabs
PROJECT_DIR="$SCRIPT_DIR/JustComposeLabs"

if [ ! -d "$PROJECT_DIR" ]; then
    echo "Erro: Diretorio $PROJECT_DIR nao encontrado."
    exit 1
fi

cd "$PROJECT_DIR" || exit 1

# Executa o detekt usando o Gradle Wrapper do projeto
# Se argumentos forem passados (ex: arquivos alterados no git), eles sao ignorados
# pois o detekt via Gradle ja esta configurado para varrer o projeto.
# Para suportar arquivos especificos, seria necessario configurar uma task customizada.
./gradlew detekt
EXIT_CODE=$?

# Volta para o diretório original
cd "$START_DIR"

if [ $EXIT_CODE -ne 0 ]; then
  echo "***********************************************"
  echo "                 detekt failed                 "
  echo " Please fix the above issues before committing "
  echo "***********************************************"
  exit $EXIT_CODE
else
  echo "detekt passed!"
fi
