package ru.mgutupenza.mgutuinformer.model.server;

import java.util.Set;

public class LectureHall {

    private Long lectureHallId;
    private String lectureHallName;
    private Set<Schedule> schedules;

    public LectureHall() {
    }

    public Long getLectureHallId() {
        return lectureHallId;
    }

    public void setLectureHallId(Long lectureHallId) {
        this.lectureHallId = lectureHallId;
    }

    public String getLectureHallName() {
        return lectureHallName;
    }

    public void setLectureHallName(String lectureHallName) {
        this.lectureHallName = lectureHallName;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

}
