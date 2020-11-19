import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.util.*;

public class AdminCourseController {
    private Map<String, Course> map;

    public AdminCourseController() {
        AllCourse.deserializeFromFile();
        map = AllCourse.getMap();
    }

    //------------------------------for admin-----------------------------------
    public void updateCourseName(String courseCode, String newName) {
        Course course = map.get(courseCode);
        course.setCourseName(newName);
    }//update course name, school, add/delete index, update vacancy in a index
    //update choice by admin controller

    public void updateCourseSchool(String courseCode, String newSchool) {
        Course course = map.get(courseCode);
        course.setCourseSchool(newSchool);
    }

    public void updateCourseIndexVacancy(String courseCode, int indexNumber, int newVac) {
        Course course = map.get(courseCode);
        boolean successful = false;
        successful = course.updateIndex(indexNumber, newVac);
        if (successful)
            System.out.println(course.getCourseCode() + " " + indexNumber + " now has a new capacity of " + newVac);
        else
            System.out.println("Failed.");
    }

    public void addCourseIndex(String courseCode, int newIndex, int capacity, Timetable timeSlot) {
        Course course = map.get(courseCode);
        boolean res = course.addIndex(newIndex, capacity, timeSlot);
        if (res) System.out.println("Index: " + newIndex + " added.");
        else System.out.println("Failed.");
    }

    public boolean deleteCourseIndex(String courseCode, int indexNumber) {
        Course course = map.get(courseCode);
        boolean res = course.deleteIndex(indexNumber);
        if (res) System.out.println("Index: " + indexNumber + " deleted.");
        else System.out.println("Failed.");
        return res;
    }

    public void addCourse(String courseName, String courseCode, String school, List<CourseType> availableType, int au) {
        Course course = new Course(courseName, courseCode, school, availableType, au);
        if (map.containsKey(courseCode)) {
            System.out.println("courseCode " + courseCode + " has already exist.");
        }
        else map.put(courseCode, course);
    }

    public void checkVacByAdmin(String courseCode, int indexNumber){
        getVacByIndex(courseCode, indexNumber);
    }

    public void checkAllVacByAdmin(String courseCode){
        printAllVancancies(courseCode);
    }

    public boolean addCourseType(String courseCode, CourseType CourseTypeToAdd) {
        Course course = map.get(courseCode);
        boolean res = course.addCourseType(CourseTypeToAdd);
        if (!res) System.out.println("Course type already exist.");
        return res;
    }

    public boolean deleteCourseType(String courseCode, CourseType CourseType) {
        Course course = map.get(courseCode);
        boolean res = course.deleteCourseType(CourseType);
        if (!res) System.out.println("Course type does not exist.");
        return res;
    }

    public void printStudentsByIndexNumber(String courseCode, int indexNumber) {
        Course course = map.get(courseCode);
        Index index = course.getIndex(indexNumber);
        if (index == null) System.out.println(courseCode + " does not contain index " + indexNumber);
        else {
            List<Student> students = index.getRegisteredStudents();
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public void printStudentByCourse(String courseCode){
        Course course = map.get(courseCode);
        for (Index in : course.getAllIndex()){
            printStudentsByIndexNumber(courseCode, in.getIndexNumber());
        }
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
