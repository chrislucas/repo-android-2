## Instalar

O pre-commit é uma estrutura para gerenciar hooks do Git,
automatizando verificações de código (formatação, linting, testes) antes de cada commit.

Instale com pip install pre-commit, crie um arquivo .pre-commit-config.yaml na raiz
e execute pre-commit install para integrar ao git, garantindo código padronizado e limpo


## Instalar o Framework

```
pip install pre-commit
```

## Criar o Arquivo de Configuração

Crie o arquivo .pre-commit-config.yaml na raiz do seu repositório:

```
# .pre-commit-config.yaml
repos:
- repo: https://github.com/pre-commit/pre-commit-hooks
  rev: v4.4.0  # Use a versão mais recente
  hooks:
  - id: trailing-whitespace    # Remove espaços em branco no final
  - id: end-of-file-fixer     # Garante uma nova linha no final do arquivo
  - id: check-yaml            # Verifica a sintaxe de arquivos YAML
  - id: check-added-large-files # Impede o commit de arquivos muito grandes

```

## Instalar os Hooks no Git

Execute o comando para ativar os hooks no seu ambiente local:


```
pre-commit install
```

## Comandos Úteis


Rodar em todos os arquivos: Para testar os hooks em todo o projeto,
e não apenas nos arquivos modificados:

```
pre-commit run --all-files
```

pre commit fix trailing whitespace.
fonte: https://share.google/aimode/WOd47ChGTUvNc0o4h
```
pre-commit run trailing-whitespace --all-files
```

Pular os hooks: Para realizar um commit ignorando os hooks (não recomendado):

```
git commit -m "sua mensagem" --no-verify
```

Atualiza as versões (rev) dos seus hooks automaticamente para as
tags mais recentes nos repositórios.

```
pre-commit autoupdate
```

## Hooks Populares

Você pode adicionar outros ganchos (hooks) comuns no seu arquivo
.pre-commit-config.yaml, como:

- Prettier


## Run detekt using a Git pre-commit hook

- https://detekt.dev/docs/gettingstarted/git-pre-commit-hook/
- https://share.google/aimode/QVja62iosW7LD4d0U


## how to put detekt on pre commit
- https://share.google/aimode/QVja62iosW7LD4d0U

## Referências

- https://pre-commit.com/
- https://github.com/pre-commit/pre-commit-hooks
- https://medium.com/@habbema/pre-commit-315db54ef2d8
- https://medium.com/data-science/custom-pre-commit-hooks-for-safer-code-changes-d8b8aa1b2ebb
