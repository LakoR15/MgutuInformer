package ru.mgutupenza.mgutuinformer.model.server;

public enum Weekday {

    Monday("Понедельник"),
    Tuesday("Вторник"),
    Wednesday("Среда"),
    Thursday("Четверг"),
    Friday("Пятница"),
    Saturday("Суббота"),
    Sunday("Воскресенье");

    private String name;

    Weekday(String s){
        name = s;
    }

    public String getName() {
        return name;
    }
}
