  # language: pt

  Funcionalidade: Reserva

    Cenario: Criar Reserva
      Dado um cliente ja criado para reserva
      E um restaurante ja criado para reserva
      Quando efetuar requisicao de criacao de reserva
      Então a reserva e criada com sucesso

    Cenario: Atualizar Reserva
      Dado uma reserva ja criada
      Quando atualizar a reserva atraves do id
      Então reserva atualizada com sucesso

    Cenario: Listar Todas As Reservas
      Dado uma reserva ja criada
      Quando realizar a busca de reservas
      Então reservas sao exibidas com sucesso

    Cenario: Buscar Reserva Por Id
      Dado uma reserva ja criada
      Quando efetuar requisicao de busca de reserva por id
      Então reserva e exibida com sucesso

    Cenario: Buscar Reservas Por Restaurante
      Dado uma reserva ja criada
      Quando efetuar requisicao de busca de reservas por restaurante
      Então reservas do restaurante sao exibidas com sucesso

    Cenario: Buscar Reservas Por Cliente
      Dado uma reserva ja criada
      Quando efetuar requisicao de busca de reservas por cliente
      Então reservas do cliente sao exibidas com sucesso