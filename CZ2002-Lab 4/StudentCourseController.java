import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.util.*;

public class StudentCourseController {
    private Map<String, Course> map;

    public StudentCourseController() {
        AllCourse.deserializeFromFile();
        map = AllCourse.getMap();
    }

    //------------------------------for student----------------------------------
    public boolean addStudent(String courseCode, int indexNumber, Student student) {
        Course course = map.get(courseCode);
        if (course.addStudentToIndex(student, indexNumber)) {
            System.out.println("Student " + student.getStudentName() + " added to " + courseCode +".");
            return true;
        }
        else {
            System.out.println("Failed.");
            return false;
        }
    }

    public boolean dropStudent(String courseCode, int indexNumber, Student student) {
        Course course = map.get(courseCode);
        if (course.deleteStudentFromIndex(student, indexNumber)) {
            System.out.println("Student " + student.getStudentName() + " deleted to " + courseCode + ".");
            return true;
        }
        else {
            System.out.println("Failed.");
            return false;
        }
    }

    public void swapIndexStudent(String courseCode, int indexNumber1, int indexNumber2, Student student) {
        Course course = map.get(courseCode);
        boolean res = course.swapIndex(student, indexNumber1, indexNumber2);
        if (res) System.out.println("Course " + courseCode + ": Student " + student.getStudentName() + ", index " + indexNumber1 + " swapped to index " + indexNumber2 + ".");
        else System.out.println("Failed.");
    }

    public void swapWithAnotherStud(String courseCode, int indexNumber1, int indexNumber2 , Student stud1, Student stud2)
    {
        Course course = map.get(courseCode);
        if (course.swapIndexWithAnotherOne(indexNumber1, indexNumber2 , stud1, stud2))
            System.out.println("Course " + courseCode + ": Student " + stud1.getStudentName() + ", index " + indexNumber1 + " swapped with student " + stud2.getStudentName() + ", index " + indexNumber2 + ".");
        else
            System.out.println("Failed.");
    }

    public int getVacancies(String courseCode, int indexNumber) {
        return getVacByIndex(courseCode, indexNumber);
    }

    public void printAllVacancies(String courseCode) {
        printAllVacancies(courseCode);
    }

    //------------------------------helper function------------------------------
    private void printAllVancancies(String courseCode) {
        Course course = map.get(courseCode);
        List<Index> indexes = course.getAllIndex();
        for (Index i : indexes) {
            System.out.println(i);
        }
    }

    private int getVacByIndex(String courseCode, int indexNumber) {
        Course course = map.get(courseCode);
        int res = course.getIndexVacancy(indexNumber);
        if (res < 0) System.out.println("Index number " + indexNumber + " does not belong to " + courseCode + ".");
        return res;
    }


}
