package ru.mgutupenza.mgutuinformer.model.server;

import java.util.Set;

public class Discipline {

    private Long disciplineId;
    private String disciplineName;
    private Set<Schedule> schedules;

    public Discipline() {
    }

    public Long getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Long disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }
}
