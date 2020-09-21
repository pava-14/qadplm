# Дипломный проект профессии «Тестировщик»

## Подготовка SUT

- Для MySQL:

```docker-compose -f docker-compose-mysql.yml up -d```

- Для PostgreSQL:

```docker-compose -f docker-compose-postgres.yml up -d```

## Просмотр данных в таблицах 

- Для MySQL:

```select-mysql-table.cmd```

- Для PostgreSQL:

```select-postgres-table.cmd```

## Удаление всех данных из таблиц 

- Для MySQL:

```clear-mysql-table.cmd```

- Для PostgreSQL:

```clear-postgres-table.cmd```

