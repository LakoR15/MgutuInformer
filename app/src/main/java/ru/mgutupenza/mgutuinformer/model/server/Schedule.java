package ru.mgutupenza.mgutuinformer.model.server;

public class Schedule {


    private Long scheduleId;
    private Groups groups;
    private Teacher teacher;
    private FormOfTraining formOfTraining;
    private Discipline discipline;
    private EmploymentType employmentType;
    private LectureHall lectureHall;
    private LessonTime lessonTime;
    private NumberWeekday numberWeekday;
    private Weekday weekday;

    public Schedule(Groups groups, Teacher teacher) {
        this.groups = groups;
        this.teacher = teacher;
    }

    public Groups getGroups() {
        return groups;
    }

    public Teacher getTeacher() {
        return teacher;
    }



    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public FormOfTraining getFormOfTraining() {
        return formOfTraining;
    }

    public void setFormOfTraining(FormOfTraining formOfTraining) {
        this.formOfTraining = formOfTraining;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public LectureHall getLectureHall() {
        return lectureHall;
    }

    public void setLectureHall(LectureHall lectureHall) {
        this.lectureHall = lectureHall;
    }

    public LessonTime getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(LessonTime lessonTime) {
        this.lessonTime = lessonTime;
    }

    public NumberWeekday getNumberWeekday() {
        return numberWeekday;
    }

    public void setNumberWeekday(NumberWeekday numberWeekday) {
        this.numberWeekday = numberWeekday;
    }

    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
    }
}
