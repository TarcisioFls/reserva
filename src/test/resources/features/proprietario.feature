  # language: pt

  Funcionalidade: Proprietário

    Cenario: Criar Proprietário
      Quando efetuar requisicao para criar um novo proprietario
      Então proprietario criado com sucesso

    Cenario: Atualizar Proprietário
      Dado um proprietario ja criado
      Quando atualizar o proprietario atraves do id
      Então proprietario atualizado com sucesso

    Cenario: Listar Todos Os Proprietarios
      Dado um proprietario ja criado
      Quando realizar a busca de proprietarios
      Então proprietarios sao exibidos com sucesso

    Cenario: Buscar Proprietario Por Id
      Dado um proprietario ja criado
      Quando efetuar requisicao de busca de proprietario por id
      Então proprietario e exibido com sucesso

    Cenario: Deletar Proprietario Por Id
      Dado um proprietario ja criado
      Quando efetuar requisicao de delecao de proprietario por id
      Então proprietario e deletado com sucesso