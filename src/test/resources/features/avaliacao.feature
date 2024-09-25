  # language: pt

  Funcionalidade: Avaliacao

    Cenario: Criar avaliacao
      Dado que tenho uma reserva
      Quando criar uma nova avaliacao para uma reserva
      Então cria avaliacao no banco com sucesso
      E deve ser apresentado o resultado