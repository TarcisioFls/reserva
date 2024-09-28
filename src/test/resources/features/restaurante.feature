  # language: pt

  Funcionalidade: Restaurante

    Cenario: Criar Restaurante
      Quando efetuar requisicao para criar um novo restaurante
      Então restaurante criado com sucesso

    Cenario: Atualizar Restaurante
      Dado um restaurante ja criado
      Quando atualizar o restaurante atraves do id
      Então restaurante atualizado com sucesso

    Cenario: Listar Todos Os Restaurantes
      Dado um restaurante ja criado
      Quando realizar a busca de restaurantes
      Então restaurantes sao exibidos com sucesso

    Cenario: Buscar Restaurante Por Id
      Dado um restaurante ja criado
      Quando efetuar requisicao de busca de restaurante por id
      Então restaurante e exibido com sucesso

    Cenario: Buscar Restaurantes Por Filtros
      Dado um restaurante ja criado
      Quando realizar a busca de restaurantes com filtro
      Então restaurantes filtrados sao exibidos com sucesso

    Cenario: Deletar Restaurante Por Id
      Dado um restaurante ja criado
      Quando efetuar requisicao de delecao de restaurante por id
      Então restaurante e deletado com sucesso
