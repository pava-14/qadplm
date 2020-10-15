# Дипломный проект профессии «Тестировщик»

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

[Отчёт по итогам тестирования](https://github.com/pava-14/qadplm/blob/master/Report.md)

[Отчёт по итогам автоматизации](https://github.com/pava-14/qadplm/blob/master/Summary.md)

[![Build status](https://ci.appveyor.com/api/projects/status/i9yb3ium1awcukpr/branch/master?svg=true)](https://ci.appveyor.com/project/pava-14/qadplm/branch/master)
