import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class  AllCourse implements FileHandle{
    private static Map<String, Course> map;
    public AllCourse() {}

    public static List readSerializedObject(String filename){
        return FileHandle.readSerializedObject(filename);
    }

    public static void writeSerializedObject(String filename,List list){
        FileHandle.writeSerializedObject(filename, list);
    }

    public static void serializeToFile() {
        try {
            if (map != null) {
                FileOutputStream fileOut =
                        new FileOutputStream("courseInfo.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(map);
                out.close();
                fileOut.close();
                System.out.printf("Serialized data is saved in courseInfo.ser");
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void deserializeFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream("courseInfo.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            map = (Map<String, Course>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            map = new HashMap<String, Course>();
        } catch (ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    public static Map<String, Course> getMap() {
        return map;
    }

    public static void setMap(Map<String, Course> thisMap) {
        map = thisMap;
    }

}
