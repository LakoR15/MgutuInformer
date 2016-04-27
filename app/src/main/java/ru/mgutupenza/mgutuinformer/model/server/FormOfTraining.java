package ru.mgutupenza.mgutuinformer.model.server;

import java.util.Set;

public class FormOfTraining {

    private Long formOfTrainingId;
    private String formOfTrainingName;
    private Set<Course> course;

    public FormOfTraining() {
    }

    public FormOfTraining(String formOfTrainingName) {
        this.formOfTrainingName = formOfTrainingName;
    }

    public Long getFormOfTrainingId() {
        return formOfTrainingId;
    }

    public void setFormOfTrainingId(Long formOfTrainingId) {
        this.formOfTrainingId = formOfTrainingId;
    }

    public String getFormOfTrainingName() {
        return formOfTrainingName;
    }

    public void setFormOfTrainingName(String formOfTrainingName) {
        this.formOfTrainingName = formOfTrainingName;
    }

    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }

}
