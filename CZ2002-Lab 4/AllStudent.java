
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AllStudent implements FileHandle {
    private static Map<String, Student> studentMap;
    public AllStudent(){}

    public static void serializeToFile() {
        try {
            if (studentMap != null) {
                FileOutputStream fileOut =
                        new FileOutputStream("Student.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(studentMap);
                out.close();
                fileOut.close();
                System.out.printf("Serialized data is saved in Student.ser");
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static void deserializeFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream("Student.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            studentMap =(Map<String, Student>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            studentMap = new HashMap<String, Student>();
        } catch (ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    public static Map<String, Student> getStudentMap() {
        return studentMap;
    }

    public static void setMap(Map<String, Student> studentMap) {
        this.studentMap = studentMap;
    }
}