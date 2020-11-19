import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;
import java.util.LinkedList;

public class Index implements Comparable<Index>, Serializable{
    private List<Student> registeredStudents;
    private LinkedList<Student> waitingListStudents;
    private int indexNumber, cnt, available, capacity; //cnt counts the number of registered students.
    private Course course;
    private static Notifier notifier;

    private Timetable timeSlot;//the format of timeslot array should be:
    // ["Mon;8:30-10:30;SEM", "TUE;10:30-12:30;LEC"]
    public Index(int number, int capacity, Course course, Timetable timeSlot ) {
        this.course = course;
        indexNumber = number;
        this.capacity = capacity;
        registeredStudents = new ArrayList<>();
        waitingListStudents = new LinkedList<>();
        cnt = 0;
        available = capacity;
        this.timeSlot = timeSlot;
        if (notifier == null) notifier = new Email();
    }

    public boolean addStudent(Student student) {
        if (student == null) return false;
        if (student.getTimetable().checkClash(this.timeSlot)) return false;
        if (available == 0) {
            waitingListStudents.add(student);
            System.out.println("Added to wait list");
            student.addtoCoursesWaitlisted(this);
            student.getTimetable().addTimetable(this.timeSlot);
            return true;
        }
        registeredStudents.add(student);
        student.addtoCoursesRegistered(this);
        student.getTimetable().addTimetable(this.timeSlot);
        cnt++;
        available--;
        return true;
    }


    public boolean deleteStudent(Student student) {
        if (student == null) return false;
        boolean res = registeredStudents.remove(student);
        if (res) {
            cnt--; available++;
            adminWaitlistStudent();
            student.delfromCoursesRegistered(this);
            student.getTimetable().deleteTable(this.timeSlot);
            return true;
        }
        else {//the student is not in the registered list (remove returns false)
            if (waitingListStudents.contains(student)){//the student is on the waiting list
                waitingListStudents.remove(student);//remove from waiting list
                student.getTimetable().deleteTable(this.timeSlot);
                student.delfromCoursesWaitlisted(this);
                return true;
            }
        }
        return false;
    }

    public int getVacancies() {
        return available;
    }

    public int getNumRegistered() {
        return cnt;
    }

    public int getNumWaitList() {
        return waitingListStudents.size();
    }

    public List<Student> getRegisteredStudents() {
        List<Student> copy = new ArrayList<>(registeredStudents);
        return copy;
    }

    public int getIndexNumber() {return indexNumber;}

    public int getCapacity() {return capacity;}

    public String getCourseCode(){return this.course.getCourseCode();}

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        available = capacity - cnt;
        adminWaitlistStudent();
    }

    public void setIndexNumber(int indexNumber) {this.indexNumber = indexNumber;}

    public void setRegisteredStudents(List<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
        cnt = registeredStudents.size();
        available = capacity - cnt;
    }

    public void printRegisteredStudents() {
        System.out.println("---------Registered students under index: " + indexNumber +"--------");
        for (Student student : registeredStudents) {
            System.out.println(student);
        }
    }

    @Override
    public String toString() {
        return "Index: " + indexNumber + " capacity: " + capacity
                + " registered students: " + cnt + " available vacancies: "  + available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return indexNumber == index.indexNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexNumber);
    }

    @Override
    public int compareTo(Index T) {
        return Integer.compare(indexNumber, T.indexNumber);
    }

    private void adminWaitlistStudent() {
        String subject = "Course registration result";
        while (available > 0 && !waitingListStudents.isEmpty()) {
            Student student = waitingListStudents.remove();
            registeredStudents.add(student);
            student.addtoCoursesRegistered(this);
            student.getTimetable().addTimetable(this.timeSlot);
            String content = "Dear " + student.getStudentName() + "\n\nYou have been added to course " + course.getCourseCode() + ", index: " + indexNumber + " successfully. ";
            notifier.notify(subject, content, student.getStudentEmail());
            System.out.println("Student " + student.getStudentName() + " added to course " + course.getCourseCode() + ", index: " + indexNumber + ". ");
            cnt++; available--;
        }
    }

}
