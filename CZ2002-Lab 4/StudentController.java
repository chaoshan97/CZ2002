import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.util.*;


public class StudentController {
    private Map<String, Student> studentMap;

    public StudentController(){
        AllStudent.deserializeFromFile();
        studentMap= AllStudent.getStudentMap();
    }
    private String name;
    private char gender;
    private String nationality;
    private String matricNo;

    Student student = new Student(name,gender,nationality,matricNo);
    String studentMatricNo = student.getStudentMatric();

    public void getTimetable(){
       if(studentMap.containsKey(student.getCoursesRegistered())){
           Timetable timetable =  new Timetable();
           timetable.addTimetable((Timetable) student.getCoursesRegistered());
           timetable.printTimeTable();
       }else{
           System.out.println("You do not have any course registered.");

       }

    }

    public void showMenu(String stud){
        new Studentboundry(studentMap,stud);

    }


}

