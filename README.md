# Como rodar o projeto

## 1º Passo

Abra o projeto no seu editor de escolha

## 2º Passo

Rode o servidor

## 3º Passo

Rode o cliente

# Como esta organizado o projeto

## Dictionary

Aqui esta o nosso modelo para o objeto dicionario usado no cliente e no servant, com os metodos usados para adicionar, remover, listar e perquisar as palavras

## DictionaryServant

Aqui a gente tem a logica dos metodos dentro do modelo, como:

### addWord

Adicionamos uma palavra apenas se ela não existir, caso não exista retornamos um erro com a mensagem "Palavra já existe."

### removeWord

Removemos uma palavra caso ela seja encontrada, caso não seja encontrada retornamos um erro "Palavra não encontrada."

### getDefinition

Pesquisamos uma palavra caso ela exista, senão retornamos "Palavra não encontrada."

### listWords

Retornamos uma lista com todas as palavras dentro do dicionario

### saveDictionary

metodo utilizado para salvar o dicionario em JSON

### loadDictionary

metodo utilizado para carregar o dicionario

## DictionaryServer

Nosso servidor RMI, rodando na porta 1099.

## DictionaryCliente

Nosso cliente, aqui temos um menu com 5 opções:

### 1ª Opção

Pesquisar uma palavra

### 2ª Opção

Adicionar uma palavra com o seu significado

### 3ª Opção

Remover uma palavra, primeiro mostramos o significado da palavra depois perguntamos se ele quer mesmo apagar

### 4ª Opção

Listar todas as palavras dentro do dicionario

### 5ª Opção

Sair do sistema
