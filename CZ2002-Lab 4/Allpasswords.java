
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
class Allpasswords implements FileHandle {
    private static List passwordList;

    public Allpasswords() {

    }

    public static List getList() {
        return passwordList;
    }

    public static List readSerializedObject(String filename) {
        return FileHandle.readSerializedObject(filename);
    }

    public static void writeSerializedObject(String filename, List list) {
        FileHandle.writeSerializedObject(filename, list);
    }

    public static void deserializeFromFile() {
        Allpasswords.passwordList = (ArrayList) (readSerializedObject(""));
    }

    public static void serializeToFile() {
        writeSerializedObject("password.dat", passwordList);
    }
}

t


