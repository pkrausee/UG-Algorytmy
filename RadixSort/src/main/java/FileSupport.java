import java.io.*;
import java.util.List;

class FileSupport
{
    public void getRecordsFromFile(List<Record> a)
    {
        try
        {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            File nazwiska = new File(classLoader.getResource("nazwiska.txt").getFile());

            FileReader fr = new FileReader(nazwiska);
            BufferedReader br = new BufferedReader(fr);

            a.clear();

            String line;
            while ((line = br.readLine()) != null)
            {
                String[] temp = line.split("\\s+");

                a.add(new Record(temp[0], temp[1]));
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
