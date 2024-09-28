  # language: pt

  Funcionalidade: Cliente

    Cenario: Criar Cliente
      Quando efetuar requisicao para criar um novo cliente
      Então cliente criado com sucesso

    Cenario: Atualizar Cliente
      Dado um cliente ja criado
      Quando atualizar o cliente atraves do id
      Então cliente atualizado com sucesso

    Cenario: Listar Todos Os Cliente
      Dado um cliente ja criado
      Quando realizar a busca de clientes
      Então clientes sao exibidos com sucesso

    Cenario: Buscar Cliente Por Id
      Dado um cliente ja criado
      Quando efetuar requisicao de busca de cliente por id
      Então cliente e exibido com sucesso

    Cenario: Deletar Cliente Por Id
      Dado um cliente ja criado
      Quando efetuar requisicao de delecao de cliente por id
      Então cliente e deletado com sucesso
