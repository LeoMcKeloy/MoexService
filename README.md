#MoexService

Сервис для получения информации по бондам с [Московской биржи](https://www.moex.com/ru/bondization/issuer).

## Запуск на локальной машине

Для запуска потребуется установленный Docker for Desktop.
Dockerfile для создания образа и запуска контейнера сервиса находится в корне.

### build image
>docker build -t moex .

### run container
>docker run -p 8003:8003 --name moex-service -t moex

## Доступ к OpenAPI

[Open api](http://localhost:8003/swagger-ui.html)
