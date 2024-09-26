  # language: pt

  Funcionalidade: Reserva

    Cenario: Criar Reserva
      Dado um cliente ja criado para reserva
      E um restaurante ja criado para reserva
      Quando efetuar requisicao de criacao de reserva
      Então a reserva e criada com sucesso