# Анализатор страниц

[![Actions Status](https://github.com/liadiann/java-project-72/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/liadiann/java-project-72/actions)
[![my-workflow](https://github.com/liadiann/java-project-72/actions/workflows/my-workflow.yml/badge.svg)](https://github.com/liadiann/java-project-72/actions/workflows/my-workflow.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=liadiann_java-project-72&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=liadiann_java-project-72)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=liadiann_java-project-72&metric=bugs)](https://sonarcloud.io/summary/new_code?id=liadiann_java-project-72)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=liadiann_java-project-72&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=liadiann_java-project-72)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=liadiann_java-project-72&metric=coverage)](https://sonarcloud.io/summary/new_code?id=liadiann_java-project-72)

[Анализатор страниц](https://java-project-72-h5ru.onrender.com) - это сайт, который анализирует указанные страницы
на SEO пригодность.

Это приложение является полноценным веб-сайтом на базе фреймворка Javalin. В качестве шаблонизатора используется
JTE. Взаимодействие с базой данных происходит через JDBC.

### Установка

1. Клонирование репозитория
```bash
git clone git@github.com:liadiann/java-project-72.git
```
2. Переход в директорию app
```bash
cd app
```
3. Установка проекта
```bash
./gradlew installDist
```
4. Запуск приложения. Пример:
```bash
./gradlew run
```
5. Запуск в браузере http://localhost:7070/

### Использование

На главной странице необходимо url, который будет проверяться. Он добавится в список всех
добавленных сайтов. Из этого списка можно перейти на нужный url. Там будет информация о нем
и кнопка для его проверки. После нажатия, вся информация появится на этой же странице.
