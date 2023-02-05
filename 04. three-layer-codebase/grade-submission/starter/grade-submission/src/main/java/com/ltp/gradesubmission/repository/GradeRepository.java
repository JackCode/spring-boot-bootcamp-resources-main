package com.ltp.gradesubmission.repository;

import java.util.ArrayList;
import java.util.List;

import com.ltp.gradesubmission.Grade;

public class GradeRepository {
  private List<Grade> studentGrades = new ArrayList<>();

  public Grade getGrade(int index) {
    return this.studentGrades.get(index);
  }

  public void addGrade(Grade grade) {
    this.studentGrades.add(grade);
  }

  public void updateGrade(int index, Grade grade) {
    studentGrades.set(index, grade);
  }

  public List<Grade> getGrades() {
    return this.studentGrades;
  }
}
