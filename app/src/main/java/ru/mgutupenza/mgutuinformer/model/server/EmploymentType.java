package ru.mgutupenza.mgutuinformer.model.server;

import java.util.Set;

public class EmploymentType {

    private Long employmentTypeId;
    private String employmentTypeName;
    private Set<Schedule> schedules;

    public EmploymentType() {
    }

    public Long getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(Long employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public String getEmploymentTypeName() {
        return employmentTypeName;
    }

    public void setEmploymentTypeName(String employmentTypeName) {
        this.employmentTypeName = employmentTypeName;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

}
