  # language: pt

  Funcionalidade: Proprietário

    Cenario: Criar Proprietário
      Quando efetuar requisição para criar um novo proprietário
      E proprietário com mesmo e-mail não existir no banco
      Então o proprietário é criado no banco com sucesso
      E deve ser apresentado

    Cenario: Atualizar Proprietário
      Dado um proprietário já criado
      Quando atualizar o proprietário através do id
      E proprietário for encontrado pelo id solicitado
      E proprietário com mesmo e-mail a ser atualizado não existir no banco
      Então dados do proprietário são atualizados com sucesso
      E deve ser apresentado

    Cenario: Listar Todos Os Proprietarios
      Dado proprietários já criados
      Quando realizar a busca de proprietários
      Então busca no banco todos os proprietários com paginação
      E deve ser apresentado com paginação

    Cenario: Buscar Proprietario Por Id
      Dado um proprietário já criado
      Quando efetuar requisição de busca de proprietário por id
      Então busca no banco o proprietário pelo id solicitado com sucesso
      E deve ser apresentado

    Cenario: Deletar Proprietario Por Id
      Dado um proprietário já criado
      Quando efetuar requisição de deleção de proprietário por id
      E proprietário for encontrado pelo id solicitado
      Então o proprietário é deletado do banco de dados com sucesso
      E deve ser apresentada resposta vazia