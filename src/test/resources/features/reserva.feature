  # language: pt

  Funcionalidade: Reserva

    Cenario: Criar Reserva
      Dado um cliente já criado
      E um restaurante já criado
      Quando efetuar requisição de reserva para determinado restaurante e cliente
      E data e hora da reserva forem após a data e hora atual
      E cliente for encontrado por id
      E restaurante for encontrado por id
      E capacidade do restaurante não tiver sido atingida
      E cliente não possuir outra reserva para este restaurante
      E cliente não possuir outra reserva para o mesmo horário
      E reserva estiver dentro do horário permitido pelo restaurante
      Então a reserva é criada no banco com sucesso
      E deve ser apresentado