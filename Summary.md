# Отчёт по итогам автоматизации

В ходе выполнения работ по автоматизации сценариев тестирования сайта покупки тура были запланированы мероприятия:

**1. Разработка тестовых сценариев**

  - Разработано 29 тестовых сценариев: [план автоматизации.](https://github.com/pava-14/qadplm/blob/master/Plan.md)
  
**2. Автоматизация разработанных сценариев**

  - Автоматизировано (Java+JUnit5+Selenide+Allure) 29 тестовых сценариев

**3. Прогон тестовых сценариев**

  - 29 автоматизированных тестовых сценария были выполнены в окружении
    - ОS Windows 10 version 2020 64-bit
    - Browser Google Chrome Версия 85.0.4183.102 64bit
    - openjdk version "11.0.7" 2020-04-14
    - OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.7+10)
    - OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.7+10, mixed mode)

**4. Анализ результатов**

  - Найдено 12 дефектов, с различной серьезностью:
    - 6 средней
    - 5 низкой
    - 1 тривиальной

**5. Составление отчетности**

Составлена отчетность с описанием найденных дефектов, статистикой по количеству и серьезности дефектов. Представлены результаты прогона тестовых сценариев.

## Выполнение плана тестирования

Не удалось реализовать автоматическое тестирование двух СУБД одновременно, т.к. доступные параметры настройки приложения aqa-shop не позволяет обеспечить запуск нескольких экземпляров приложения одновременно, для использования разных СУБД.


## Сработавшие риски

Недостаток времени, в связи с необходимостью первоочередного выполнения других задач.

## Общий итог по времени

Учитывая, сработавшие риски, общий итог времени 320 часов.