import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.util.*;


public class StudentController {
    private String name;
    private String program;
    private int year;
    private String matricNo;

    HashMap<String, Student> studentMap = new HashMap<String, Student>();
    //Map<String,Student> studentMap;
    Student student = new Student(name,program,year,matricNo);
    String studentMatricNo = student.getStudentMatric();

   /* public void setMap(Map<String, Student> studentMap) {
        this.studentMap = studentMap;
    }*/

//    public static studentMap<String, Student> getMap() {
//        return studentMap;
//    }
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

