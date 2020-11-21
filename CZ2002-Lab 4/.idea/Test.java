import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Arrays;

class Test{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        /*
        String arr []= {"Mon;8:30-10:30;SEM;All;LT2", "TUE;10:30-12:30;LAB;Odd;SWLAB1"};
        String arr2 []= {"Mon;6:30-8:30;SEM;All;LT1A", "TUE;12:30-14:30;LAB;All;HWLAB3"};
        List l1 = Arrays.asList(arr);
        List l2 = Arrays.asList(arr2);
        Timetable t = new Timetable();
        t.addSlots(l1);
        Timetable t2 = new Timetable();
        t2.addSlots(l2);
        System.out.println(t.checkClash(t2));
        //t.addTimetable(t2);
        t.printTimeTable();
        t.delTimetable(t2);
        System.out.println();
        t.printTimeTable();

        Password p1 = new Password("PRATYUSH002","abc2043fg",1);
        Password p2 = new Password("pratyush002","abc2043fg",0);
        //System.out.println(p1.getPassword() + " "+ p2.getPassword());
        System.out.println(p1.equals(p2));
        */
        Login p1 = new Login("abc123","abc1234",1);
        Login p2 = new Login("abc123","abc1234",0);
        Login[] pass = {p1,p2};
        FileHandle pc = new AllLogin();
        List<login> p= Arrays.asList(pass);
        ((Allpasswords)pc).setList(p);
        pc.serializeToFile();
        /*
        FileHandle ac = new AllCourse();
        FileHandle as = new AllStudent();
        as.deserializeFromFile();
        ac.deserializeFromFile();
        AdminCourseController acc = new AdminCourseController(((AllCourse)ac).getMap());
        AdminStudentController asc = new AdminStudentController(((AllStudent)as).getStudentMap());
        AdminBoundary ab = new AdminBoundary(acc,asc);
        ab.printMenu();
        while (true){
            System.out.println("----------------------------------------");
            System.out.println("Enter choice (Admin): (Enter 0 to stop)");
            int ch = sc.nextInt();
            if (ch == 0){
                break;
            }
            switch(ch){
                case 1: ab.editAcessPeriod();
                        break;
                case 2: ab.addStudent();
                        break;
                case 3: ab.addCourse();
                        break;
                case 4: ab.updateCourse();
                        break;
                case 5: ab.checkVacancy();
                        break;
                case 6: ab.printByCourse();
                        break;
                case 7: ab.printByIndex();
                        break;
                default: System.out.println("Invalid choice");
            }

        }
        ac.serializeToFile();
        as.serializeToFile();
        */
    }
}