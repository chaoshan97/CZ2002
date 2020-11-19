import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class AdminBoundary {

    private List adminList;
    private AdminCourseController acc;
    private AdminStudentController asc;
    private Scanner sc;

    public AdminBoundary(AdminCourseController acc, AdminStudentController asc){
        AllAdmins.deserializeFromFile();
        adminList = AllAdmins.getList();
        this.acc = acc;
        this.asc = asc;
        this.sc = new Scanner(System.in);
    }

    public void printMenu(){
        System.out.println("Choose from the menu");
        System.out.println("1. Edit student access period");
        System.out.println("2. Add student");
        System.out.println("3. Add course");
        System.out.println("4. Update Course");
        System.out.println("5. Check vacancy by Index Number");
        System.out.println("6. Print Student list by Index number");
        System.out.println("7. Print Student list by Course");
    }

    public void editAcessPeriod(){
        try{
            System.out.println ("---------------Editing access period---------------");
            System.out.print("Enter Matric No: ");
            String matricNo = sc.nextLine();
            System.out.print("Enter new Access period :\n Format: yyyy-MM-dd HH:mm=yyy-MM-dd HH:mm \n");
            String access = sc.nextLine();
            asc.editAccessPeriod(matricNo, access);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addStudent(){
        try{
            System.out.println ("---------------Adding a new Student---------------");
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Matric No: ");
            String matric = sc.next();
            System.out.print("Enter nationality: ");
            String nation = sc.next();

            System.out.print("Enter gender (Male/female): ");
            String gender = sc.next();
            if (gender.toUpperCase().compareTo( "MALE")==0){
                Student s = new Student(name, '1', nation, matric);
                asc.addStudent(s);
            }
            else{
                if (gender.toUpperCase().compareTo( "FEMALE")==0){
                    Student s = new Student(name, '0', nation, matric);
                    asc.addStudent(s);
                }
                else{
                    System.out.println("Invalid gender. Re-enter everything");
                    addStudent();
                }
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }


    public void addCourse(){
        try{
            System.out.println ("---------------Adding a new Course---------------");
            System.out.print("Enter Course Name: ");
            String Name = sc.next();
            System.out.print("Enter Course Code: ");
            String code = sc.next();
            System.out.print("Enter Course School: ");
            String school = sc.next();
            List <CourseType> availableType = new ArrayList<CourseType>();

            System.out.print("Enter available course types seprated by , (no spaces): ");
            String str = sc.next();
            String[] arr = str.split(",");
            for (String st: arr){
                availableType.add(CourseType.valueOf(st));
            }

            System.out.print("Enter AU: ");
            int AU = sc.nextInt();
            acc.addCourse(Name, code, school, availableType, AU);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateCourse(){
        System.out.println ("---------------Updating Courses---------------");
        System.out.println("1. Update Course Name");
        System.out.println("2. Update Course School");
        System.out.println("3. Update Course Index vacancy");
        System.out.println("4. Add new index to Course");
        System.out.println("5. Delete index from course");
        System.out.println("6. Add new course type to Course");
        System.out.println("7. Delete course type from course");
        String code;
        int index;
        boolean flag = true;
        while (flag){
            System.out.println("Enter choice");
            int ch = sc.nextInt();
            switch(ch){
                case 1: try{
                    System.out.print("Enter Course Code: ");
                    code = sc.next();
                    System.out.print("Enter new Course Name: ");
                    String name = sc.next();
                    acc.updateCourseName(code, name);
                    flag = false;
                }
                catch(Exception e){
                    System.out.println("Enter valid course code and course name");
                    flag = true;
                }
                    break;
                case 2: try{
                    System.out.print("Enter Course Code: ");
                    code = sc.next();
                    System.out.print("Enter new Course School: ");
                    String name = sc.next();
                    acc.updateCourseSchool(code, name);
                    flag = false;
                }
                catch(Exception e){
                    flag = true;
                    System.out.println("Enter valid code and school name");
                }
                    break;
                case 3:try{
                    System.out.print("Enter Course Code: ");
                    code = sc.next();
                    System.out.print("Enter Index Number: ");
                    index = sc.nextInt();
                    System.out.print("Enter new Vacancy: ");
                    int vac = sc.nextInt();
                    acc.updateCourseIndexVacancy(code, index, vac );
                    flag = false;
                }
                catch(Exception e){
                    flag = true;
                    System.out.println("Enter valid code, index number and vacancy");
                }
                    break;
                case 4: try{
                    System.out.print("Enter Course Code: ");
                    code = sc.next();
                    System.out.print("Enter new Index number: ");
                    int newIndex  = sc.nextInt();
                    System.out.print("Enter new Index number capacity: ");
                    int cap  = sc.nextInt();
                    System.out.print("Enter Timetable (Time slot Format : Mon;8:30-10:30;SEM;Wk_2_13/Wk_1_3_5/Wk_2_4_6;HWLAB3 ) \n Enter timeslots seprated by comma (no spaces)\n");
                    String str = sc.next();
                    String[] time = str.split(",");
                    List<String> timeslots = Arrays.asList(time);
                    Timetable tt = new Timetable();
                    tt.addSlots(timeslots);
                    acc.addCourseIndex(code, newIndex, cap,tt);
                    flag = false;
                }
                catch(Exception e){
                    flag = true;
                    System.out.println(e.getMessage());
                }
                    break;
                case 5:try{
                    System.out.print("Enter Course Code: ");
                    code = sc.next();
                    System.out.print("Enter Index Number to be deleted: ");
                    index = sc.nextInt();
                    acc.deleteCourseIndex(code, index);
                    flag = false;
                }
                catch(Exception e){
                    flag =true;
                    System.out.println("Enter valid code and index number");
                }
                    break;
                case 6:try{
                    System.out.print("Enter Course Code: ");
                    code = sc.next();
                    System.out.print("Enter new Course type to be added: ");
                    String name = sc.next();
                    acc.addCourseType(code, CourseType.valueOf(name));
                    flag = false;
                }
                catch(Exception e){
                    System.out.println("Enter valid course code and course type");
                    flag = true;
                }
                    break;
                case 7:try{
                    System.out.print("Enter Course Code: ");
                    code = sc.next();
                    System.out.print("Enter Course type to be deleted: ");
                    String name = sc.next();
                    acc.deleteCourseType(code, CourseType.valueOf(name));
                    flag = false;
                }
                catch(Exception e){
                    System.out.println("Enter valid course code and course type");
                    flag = true;
                }
                    break;
                default: System.out.println("Re-enter the choice..................In valid choice!");
            }
        }
    }


    public void checkVacancy(){
        System.out.println ("---------------Check available slots for an index number ---------------");
        System.out.print("Enter Course Code: ");
        String code = sc.next();
        System.out.print("Enter Index Number: ");
        int index = sc.nextInt();
        acc.checkVacByAdmin(code, index);
    }

    public void printByIndex(){
        System.out.println ("---------------Printing students by Index ---------------");
        System.out.print("Enter Course Code: ");
        String code = sc.next();
        System.out.print("Enter Index Number: ");
        int index = sc.nextInt();
        acc.printStudentsByIndexNumber(code, index);
    }

    public void printByCourse(){
        System.out.println ("---------------Printing students by Course ---------------");
        System.out.print("Enter Course Code: ");
        String code = sc.next();
        System.out.println("---------------Students in "+code+"---------------");
        acc.printStudentByCourse(code);

    }

}
