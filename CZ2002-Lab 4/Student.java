//package sce.cz2002.yxy;

import javax.management.ObjectName;
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
    private Map<String, Index> CoursesRegistered;
    private Map<String, Index> CoursesWaitlist;
    //private Map<>
    private int SumAU = 0;
    private LocalDateTime[] AccessPeriod;
    private Timetable timeSlot;
    Timetable timetable = new Timetable();

    public Student(String name, char gender, String nationality, String matric) {
        Name = name;
        Gender = gender;
        Nationality = nationality;
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

    public void printTime() {
        CoursesRegistered.entrySet().forEach(entry->{System.out.println(entry.getKey()); });

    }

    public Map<String, Index> getCoursesWaitlist() {
        return CoursesWaitlist;

    }


    public void setAccessPeriod(LocalDateTime[] accessperiod) {
        AccessPeriod = accessperiod;

    }
    public LocalDateTime[] getAccessPeriod() {
        return AccessPeriod;
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


    public Timetable getTimetable() {
        timetable.checkClash(timeSlot);
        timetable.addTimetable(timeSlot);
        timetable.delTimetable(timeSlot);
        return timetable;
    }
}
