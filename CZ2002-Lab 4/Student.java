import java.util.*;
import java.io.Serializable;
import java.time.*;

public class Student implements Serializable{
    private String Name;
    private String Username;
    private char Gender;
    private String Nationality;
    private String MatricNo;
    private String Password;
    private String StudentEmail;
    private String CurrentProg;
    private int StudyYear;
    private Map<String, Index> CoursesRegistered;
    private Map<String, Index> CoursesWaitlist;
    //private Map<>
    private int SumAU = 0;
    private LocalDateTime[] AccessPeriod;

    public Student(String name, String program, int year, String matric) {
        Name = name;
        CurrentProg = program;
        StudyYear = year;
        MatricNo = matric;
    }

    public String getStudentName() {
        return Name;
    }

    public String getStudentUsername() {
        return Username;
    }

    public String getStudentMatric() {
        return MatricNo;
    }

    public String getStudentPassword() {
        return Password;
    }

    public Map<String, Index> getCoursesRegistered() {
        return CoursesRegistered;
    }

    public String getStudentEmail(){ return StudentEmail;}

    public void printCoursesRegistered() {
        CoursesRegistered.entrySet().forEach(entry->{System.out.println(entry.getKey()); });
    }

    public Map<String, Index> getCoursesWaitlist() {
        return CoursesWaitlist;

    }

    //public getTimeTable() {}

    //public printTimeTable() {}

    public void setAccessPeriod(LocalDateTime[] accessperiod) {
        AccessPeriod = accessperiod;
    }

    public void addtoCoursesRegistered(Index i){
        this.CoursesRegistered.put(i.getCourseCode(),i);
    }

    public void addtoCoursesWaitlisted(Index i){
        this.CoursesWaitlist.put(i.getCourseCode(),i);

    }

    public void delfromCoursesRegistered( Index i){
        this.CoursesRegistered.remove(i.getCourseCode());
    }

    public void delfromCoursesWaitlisted(Index i){
        this.CoursesWaitlist.remove(i.getCourseCode());

    }
}
