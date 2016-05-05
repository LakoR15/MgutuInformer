package ru.mgutupenza.mgutuinformer.model.server;

public class LessonTime {

    private Long lessonTimeId;
    private Double lessonTimeStart;
    private Double lessonTimeEnd;

    public LessonTime() {
    }

    public LessonTime(Double lessonTimeStart, Double lessonTimeEnd) {
        this.lessonTimeStart = lessonTimeStart;
        this.lessonTimeEnd = lessonTimeEnd;
    }

    public Long getLessonTimeId() {
        return lessonTimeId;
    }

    public void setLessonTimeId(Long lessonTimeId) {
        this.lessonTimeId = lessonTimeId;
    }

    public Double getLessonTimeStart() {
        return lessonTimeStart;
    }

    public void setLessonTimeStart(Double lessonTimeStart) {
        this.lessonTimeStart = lessonTimeStart;
    }

    public Double getLessonTimeEnd() {
        return lessonTimeEnd;
    }

    public void setLessonTimeEnd(Double lessonTimeEnd) {
        this.lessonTimeEnd = lessonTimeEnd;
    }
}
