import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileSupport {
    static String readFile(String path) throws IOException {

        byte[] file = Files.readAllBytes(Paths.get(path));

        String text = new String(file);
        text = text.replace("\n", "").replace("\r", "");

        return text;

    }
}
