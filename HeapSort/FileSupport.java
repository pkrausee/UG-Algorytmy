import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class FileSupport
{
    public void getElemFromFile(String fileName, HeapSort h)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                h.addTabEl(Integer.valueOf(line));
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void saveElemToFile(String fileName, HeapSort h)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
        {
            for (int x: h.getTab())
            {
                writer.write(String.valueOf(x));
                writer.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
