package ru.netology.mode;

public class DataGenerator {
    private DataGenerator() {

    }

    public static User generateUser() {
        User user = new User("vasya", "qwerty123");
        return user;
    }
}
