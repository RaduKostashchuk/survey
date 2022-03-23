# survey

## О проекте

Это приложение представляет REST API для системы опросов.

В приложении хранятся как сами опросы, так и результаты их прохождения.

Администратор может создавать опросы и наполнять их вопросами.

Вопрос может быть трех типов: простой текст, выбор одного варианта, выбор нескольких вариантов.

Готовые опросы доступны без аутентификации.

Для прохождения опроса API ожидает объект с числовым идетификатором пользователя и номером опроса

на конечную точку "/start".

Для окончания опроса API ожидает объект с номером опроса, идентификатором пользователя и списком ответов

на конечную точку "/end".



## Как использовать
### Опросы
| Операция | Метод запроса | Содержание запроса | Статус ответа | Содержание ответа |
|--|--|--|--|--|
| Создание опроса | POST | **Url**: '/survey/save', **Body**: '{"name" : "Имя опроса"}' | OK | **Body**:  {"id": 5, "name": "Survey 3", } |
| Удаление опроса | DELETE | **Url**: '/survey/delete/{id}' | OK |  |
| Изменение опроса | PATCH | **Url**: '/survey/update', **Body**: '{"id" : 5, "name" : "Новое имя", "description" : "Описание}' | OK |  |
| Список всех опросов | GET | **Url**: '/survey/' | OK | **Body**:  [{"id": 1, "name": "first survey", "description": null, "questions": [{"id": 1, "text": "How old are you?", "type": "TEXT"}]}] |
| Опрос по ID | GET | **Url**: '/survey/{id}' | OK | **Body**:  [{"id": 1, "name": "first survey", "description": null, "questions": [{"id": 1, "text": "How old are you?", "type": "TEXT"}]}] |
### Вопросы
| Создание вопроса | POST | **Url**: '/question/add/{surveyId}', **Body**: '{"text" : "Как ваше имя?", "type" : 0}' | OK | **Body**:  {"id": 6, "text": "What is your speciality?", "type": "TEXT"} |
| Удаление вопроса | DELETE | **Url**: '/question/delete/{questionId}' | OK |  |
| Изменение вопроса | PATCH | **Url**: '/question/update/{surveyId}', **Body**: '{"id" : 5, "name" : "Новый вопрос", "type" : 2}' | OK |  |
### Прохождение опроса
| Старт опроса | POST | **Url**: '/survey/start', **Body**: '{"user" : {"externalId" : "123"}, "surveyId" : 4}' | OK |  |
| Окончание опроса | POST | **Url**: '/survey/end', **Body**: {"user" : {"externalId" : "112233"}, "surveyId" : "4", "answers" : [{"questionId" : "6", "text" : "Ответ"}]}| OK |  |
| Изменение вопроса | PATCH | **Url**: '/question/update/{surveyId}', **Body**: '{"id" : 5, "name" : "Новый вопрос", "type" : 2}' | OK |  |
### Просмотр результатов
| Результаты всех опросов | GET | **Url**: '/result/' | OK | [{"surveyId": 3, "user": {"externalId": "112233"}, "id": 50, "startDate": "2022-03-22T20:50:30.601008", "endDate": "2022-03-22T22:37:13.729418", "answers": [{"id": 28, "text": "Ответ 1", "questionId": 3}]}] |
| Результаты опросов для пользователя | GET | **Url**: '/result/{userId}' | OK | [{"surveyId": 3, "user": {"externalId": "112233"}, "id": 50, "startDate": "2022-03-22T20:50:30.601008", "endDate": "2022-03-22T22:37:13.729418", "answers": [{"id": 28, "text": "Ответ 1", "questionId": 3}]}] |


## Настройка и сборка

Настройки приложения содержатся в файле /src/main/resources/application.properties.

Сборка приложения осуществляется командой: mvn package.

Перед запуском приложения следует создать базу данных в соответсвии с файлом application.properties.

В базе данных необходимо запустить скрипт db/admin.sql, предварительно сгенерировав пароль с помощью

утилиты MakePass.

## Контакты

Email: kostasc@mail.ru
Telegram: @rkostashchuk