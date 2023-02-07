package com.ltp.gradesubmission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ltp.gradesubmission.pojo.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.GradeService;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

  @Mock
  private GradeRepository gradeRepository;

  @InjectMocks
  private GradeService gradeService;

  @Test
  public void getGradesFromRepoTest() {
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
      new Grade("Jackson", "Math", "A+"),
      new Grade("Patrick", "Medicine", "C")
    ));

    List<Grade> result = gradeService.getGrades();

    assertEquals("Jackson", result.get(0).getName());
    assertEquals("Medicine", result.get(1).getSubject());
  }

  @Test
  public void gradeIndexTest() {
    Grade grade = new Grade("Jackson", "Math", "A+");
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
    when(gradeRepository.getGrade(0)).thenReturn(grade);

    int valid = gradeService.getGradeIndex(grade.getId());
    int notFound = gradeService.getGradeIndex("123");

    assertEquals(0, valid);
    assertEquals(Constants.NOT_FOUND, notFound);
  }

  @Test
  public void getGradeByIdTest() {
    Grade grade = new Grade("Jackson", "Math", "A+");
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
    when(gradeRepository.getGrade(0)).thenReturn(grade);

    String id = grade.getId();
    Grade result = gradeService.getGradeById(id);

    assertEquals(grade, result);
  }

  @Test
  public void addGradeTest() {
    Grade grade = new Grade("Jackson", "Math", "A+");
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
    when(gradeRepository.getGrade(0)).thenReturn(grade);

    Grade newGrade = new Grade("Patrick", "Medicine", "F");
    gradeService.submitGrade(newGrade);

    verify(gradeRepository, times(1)).addGrade(newGrade);
  }

  @Test
  public void updateGradeTest() {
    Grade grade = new Grade("Jackson", "Math", "A+");
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
    when(gradeRepository.getGrade(0)).thenReturn(grade);

    grade.setScore("A-");
    gradeService.submitGrade(grade);
    verify(gradeRepository, times(1)).updateGrade(grade, 0);
  }

}
