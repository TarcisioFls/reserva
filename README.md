# Backend Reserva - API REST

## Execução de Testes

- Para executar testes unitários:

``` sh
mvn test
```

- Para executar testes de integração:

``` sh
mvn test -P integration-test
```

- Para executar testes de sistema:

``` sh
mvn test -P system-test
```

- Para executar testes de mutação:

``` sh
mvn test -DpitestSkip=false
```