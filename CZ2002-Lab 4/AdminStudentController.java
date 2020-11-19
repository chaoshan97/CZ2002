import java.util.Map;
import java.time.*;
import java.util.Date;
import java.text.SimpleDateFormat;

class AdminStudentController{
    private Map<String, Student> studentMap;
    public AdminStudentController() {
        AllStudent.deserializeFromFile();
        studentMap = AllStudent.getStudentMap();
        System.out.println(studentMap.entrySet());
    }

    public void addStudent (Student s){
        if (studentMap.containsKey(s.getStudentMatric())){
            System.out.println("Matric No:" + s.getStudentMatric() + "already exist.");
        }
        else studentMap.put(s.getStudentMatric(), s);
    }

    public void editAccessPeriod(String matricNo, String newAccessPeriod){
        try{
            if (studentMap.containsKey(matricNo)==false){
                System.out.println("Matric No: does not exist");
            }
            else{
                Student s = studentMap.get(matricNo);
                String arr[] = newAccessPeriod.split("=");
                System.out.println(newAccessPeriod);
                SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date opening = parser.parse(arr[0]);
                Date closing = parser.parse(arr[1]);
                LocalDateTime openingTime = LocalDateTime.ofInstant(opening.toInstant(),ZoneId.systemDefault());
                LocalDateTime closingTime = LocalDateTime.ofInstant(closing.toInstant(),ZoneId.systemDefault());
                LocalDateTime[] access = {openingTime,closingTime};
                s.setAccessPeriod(access);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}