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
public class User {

    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;

    private Image avatar;


    public User(String email, String password, String name, String surname/*, Image avatar*/) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
//        this.avatar = avatar; ????
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
}
