  # language: pt

  Funcionalidade: Restaurante

    Cenario: Criar Restaurante
      Dado um proprietário já criado
      Quando efetuar requisição para criar um novo restaurante
      Então o restaurante é criado no banco com sucesso
      E deve ser apresentado

    Cenario: Atualizar Restaurante
      Dado um restaurante já criado
      Quando atualizar o restaurante através do id
      E restaurante for encontrado pelo id solicitado
      Então dados do restaurante são atualizados com sucesso
      E deve ser apresentado

    Cenario: Listar Todos Os Restaurantes
      Dado restaurantes já criados
      Quando realizar a busca de restaurantes
      Então busca no banco todos os restaurantes com paginação
      E deve ser apresentado com paginação

    Cenario: Buscar Restaurante Por Id
      Dado um restaurante já criado
      Quando efetuar requisição de busca de restaurante por id
      Então busca no banco o restaurante pelo id solicitado com sucesso
      E deve ser apresentado

    Cenario: Buscar Restaurantes Por Filtros
      Dado restaurantes já criados
      E uma string de busca (Nome, Localizacao, Tipo de Cozinha)
      Quando realizar a busca de restaurantes
      Então busca no banco todos os restaurantes filtrados com paginação
      E deve ser apresentado com paginação

    Cenario: Deletar Restaurante Por Id
      Dado um restaurante já criado
      Quando efetuar requisição de deleção de restaurante por id
      E restaurante for encontrado pelo id solicitado
      Então o restaurante é deletado do banco de dados com sucesso
      E deve ser apresentada resposta vazia