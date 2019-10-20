import java.io.*;
import java.util.List;

class FileSupport
{
    public void getRecordsFromFile(List<Record> a, String filename)
    {
        try
        {

            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            File file = new File(classLoader.getResource(filename).getFile());

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            a.clear();

            String line;
            while ((line = br.readLine()) != null)
            {
                String[] split = line.split("\\s+");

                a.add(new Record(split[0], split[1]));
            }

            fr.close();
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void saveRecordsToFile(List<Record> a, String fileName)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for (Record x: a)
            {
                writer.write(x.getNumber() + " " + x.getSurname());
                writer.newLine();
            }

            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
