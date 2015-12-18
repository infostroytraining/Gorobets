package com.entity;

import java.awt.*;

/*
* Реализовать регистрацию пользователей сайта с защитой от роботов(капчей):
- в качестве регистрационной информации использовать как минимум

    электронный адресс
    пароль
    имя пользователя
    фамилия пользователя
    аватар пользователя (опционально) - загрузка картинки на сервер

- реализовать валидацию данных пользователя только на стороне сервера. (клиентскую валидацию делать не нужно)

- список существующих пользователей задать константама (но структура DAO - Service - Servlet для сущности User
 должна присутствовать в коде)

- в качестве капчи использовать картинку с напечатанными на ней цифрами(буквами). В качестве источника цифр можно
использовать генератор псевдослучайных чисел с заданным начальным значением. Можно добавить точек для усложнения
автоматического распознавания, но это не обязательно. Картинка должна генерироваться Сервлетом, для рисования капчи
можно использовать сторонние библиотеки.

- продумать механизм хранения придуманного числа между этапами генерации картинки и проверки введенного значения.
Рекомендую нарисовать sequence diagram, отражающую взаимодействие клиента и сервера на пути передачи данных.
 Учитывать, что само придуманное число не должно посылаться клиенту в явном виде нигде, кроме как на картинке.

- Реализовать  custom-tag для размещения картинки капчи. Тег должен размещать HTML-код для отображения картинки.

Домашнее задание должно присутствовать в репозитории если не в полном объеме, то хотя-бы в каком-то промежуточном виде,
 чтобы я смогла оценить прогресс и выделить типичные для всех ошибки. Коммиты в репозиторий желательно делать
   гранулярными, т.е. закончили какую-то небольшую часть функционала и залили это в репозиторий. */

// до 15.12.15
/*Задание

        1. Реализовать уровень доступа к данным, который взаимодействует с базой PostgreSQL. Реализовать методы
         добавления, удаления, получения и обновления пользователя в базе. Реализовать логику регистрации и логина
          пользователя с взаимодействием с базой данных.

        2. Подключить к проекту log4j2 и добавить качественное логгирование кода.

        - все логи должны выводиться в консоль.
        - для уровня доступа с базой данных логи должны также записываться в файл dao-log.txt.*/

/*В дополнение к домашнему заданию:

Для веб-приложения регистрации пользователей реализовать log4j2 custom appender для логгирования.
Данный аppender должен посылать HTTP POST-запрос по адресу:
http://host:port/server-log-app/logs

В параметре POST-запроса передать логгируемое событие:
Например:
post parameter name: logEvent
post parameter value: 22:18:54.818 DEBUG com.example.web.listener.ContextListener:contextInitialized:26 - service initialized.

Реализовать сервер логгирования, который принимает и сохраняет лог-сообщения в памяти или в базе данных, а также отдает имеющуюся лог-информацию по требованию.

По GET-запросу сервер обязан вернуть все сохраненные лог-сообщения, при этом желательно (но не обязательно):
1) реализовать возможность получения лог-сообщений в формате JSON, если в GET-запросе будет указан параметр format со значением pretty;
2) реализовать выделение получаемых лог-сообщений цветом в зависимости от уровня логгирования.



*/
public class User {

    private int id;

    private String email;
    private String password;
    private String name;
    private String surname;

    private Image avatar;

    public User() {
    }

    public User(String email, String password, String name, String surname/*, Image avatar*/) {

        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", avatar=" + avatar +
                '}';
    }
}
