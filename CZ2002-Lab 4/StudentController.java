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


    public void getStudentBasicInfo(String name){


    }
    public void getTimetable(){
        Timetable timetable =  new Timetable();
        timetable.printTimeTable();
    }

    public void showMenu(String stud){
        new Studentboundry(studentMap,stud);

    }


}

