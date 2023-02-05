package com.ltp.gradesubmission.repository.service;

import java.util.List;

import com.ltp.gradesubmission.Constants;
import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;

public class GradeService {
  GradeRepository gradeRepository = new GradeRepository();

  public Grade getGrade(int index) {
    return this.gradeRepository.getGrade(index);
  }

  public void addGrade(Grade grade) {
    this.gradeRepository.addGrade(grade);
  }

  public void updateGrade(int index, Grade grade) {
    this.gradeRepository.updateGrade(index, grade);
  }

  public List<Grade> getGrades() {
    return this.gradeRepository.getGrades();
  }

  public int getGradeIndex(String id) {
    List<Grade> studentGrades = this.getGrades();
    for (int i = 0; i < studentGrades.size(); i++) {
      if (studentGrades.get(i).getId().equals(id))
        return i;
    }
    return Constants.NOT_FOUND;
  }

  public Grade getGradeById(String id) {
    int index = this.getGradeIndex(id);
    return index == Constants.NOT_FOUND ? new Grade() : this.getGrade(index);
  }

  public void submitGrade(Grade grade) {
    int index = this.getGradeIndex(grade.getId());
    if (index == Constants.NOT_FOUND) {
      this.addGrade(grade);
    } else {
      this.updateGrade(index, grade);
    }
  }
}
