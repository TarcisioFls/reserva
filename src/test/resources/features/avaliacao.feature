  # language: pt

  Funcionalidade: Avaliacao

    Cenario: Criar Avaliacao
      Quando criar uma nova avaliacao para uma reserva
      Então cria avaliacao no banco com sucesso
      E deve ser apresentado o resultado

    Cenario: Listar Todas As Avaliacoes
      Dado uma avaliacao ja criada
      Quando realizar a busca de avaliacoes
      Então avaliacoes sao exibidas com sucesso