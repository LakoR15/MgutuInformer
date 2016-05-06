package ru.mgutupenza.mgutuinformer.model.server;

public enum NumberWeekday {

    first("Первая неделя"),
    second("Вторая неделя");

    private String name;

    NumberWeekday(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}