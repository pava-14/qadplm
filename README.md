# **Дипломный проект профессии «Тестировщик»**

## Техническое задание

### Описание приложения

Приложение представляет из себя веб-сервис.

![](./issues/service.png)

Приложение предлагает купить тур по определённой цене с помощью двух способов:

1. Обычная оплата по дебетовой карте

2. Уникальная технология: выдача кредита по данным банковской карты

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

* сервису платежей (далее - Payment Gate)

* кредитному сервису (далее - Credit Gate)

Приложение должно в собственной СУБД сохранять информацию о том, каким способом был совершён платёж и успешно ли он был совершён.

Заявлена поддержка двух СУБД:

* MySQL

* PostgreSQL

### Задача

Автоматизировать сценарии (как позитивные, так и негативные) покупки тура.

## Подготовка SUT

- Для MySQL:

```docker-compose -f docker-compose-mysql.yml up -d```

```gradlew test -Ddb.url=jdbc:mysql://host.docker.internal:3306/app```

- Для PostgreSQL:

```docker-compose -f docker-compose-postgres.yml up -d```

```gradlew test -Ddb.url=jdbc:postgresql://host.docker.internal:5432/app```

- По умолчанию, 

```db.url=jdbc:mysql://host.docker.internal:3306/app```

## Работа с данными

### Просмотр данных в таблицах 

- Для MySQL:

```select-mysql-table.cmd```

- Для PostgreSQL:

```select-postgres-table.cmd```

### Удаление всех данных из таблиц 

- Для MySQL:

```clear-mysql-table.cmd```

- Для PostgreSQL:

```clear-postgres-table.cmd```

## Отчетность

[Тест-план](https://github.com/pava-14/qadplm/blob/master/Plan.md)

[Отчёт по итогам тестирования](https://github.com/pava-14/qadplm/blob/master/Report.md)

[Отчёт по итогам автоматизации](https://github.com/pava-14/qadplm/blob/master/Summary.md)

[![Build status](https://ci.appveyor.com/api/projects/status/i9yb3ium1awcukpr/branch/master?svg=true)](https://ci.appveyor.com/project/pava-14/qadplm/branch/master)
