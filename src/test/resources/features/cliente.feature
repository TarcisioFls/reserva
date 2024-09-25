  # language: pt

  Funcionalidade: Cliente

    Cenario: Criar Cliente
      Quando efetuar requisição para criar um novo cliente
      E cliente com mesmo e-mail não existir no banco
      E cliente com mesmo cpf não existir no banco
      Então o cliente é criado no banco com sucesso
      E deve ser apresentado

    Cenario: Atualizar Cliente
      Dado um cliente já criado
      Quando atualizar o cliente através do id
      E cliente for encontrado pelo id solicitado
      E cliente com mesmo e-mail a ser atualizado não existir no banco
      Então dados do cliente são atualizados com sucesso
      E deve ser apresentado

    Cenario: Listar Todos Os Cliente
      Dado clientes já criados
      Quando realizar a busca de clientes
      Então busca no banco todos os clientes com paginação
      E deve ser apresentado com paginação

    Cenario: Buscar Cliente Por Id
      Dado um cliente já criado
      Quando efetuar requisição de busca de cliente por id
      Então busca no banco o cliente pelo id solicitado com sucesso
      E deve ser apresentado

    Cenario: Deletar Cliente Por Id
      Dado um cliente já criado
      Quando efetuar requisição de deleção de cliente por id
      E cliente for encontrado pelo id solicitado
      Então o cliente é deletado do banco de dados com sucesso
      E deve ser apresentada resposta vazia