import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;


class Record
{
    private String number;
    private String surname;

    public Record(String number, String surname)
    {
        this.number = number;
        this.surname = surname;
    }

    public String toString() {
        return number + " " + surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}


class FileSupport
{
    public void getRecordsFromFile(ArrayList<Record> a)
    {
        try (BufferedReader br = new BufferedReader(new FileReader("nazwiska.txt")))
        {
            a.clear();

            String line;
            while ((line = br.readLine()) != null)
            {
                String[] temp = line.split("\\s+");

                a.add(new Record(temp[0], temp[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveRecordsToFile(ArrayList<Record> a, String fileName)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
        {
            for (Record x: a)
            {
                //Same nazwiska:
                //writer.write(x.getSurname());

                writer.write(x.getNumber() + " " + x.getSurname());
                writer.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Utilities
{
    public int maxLength(ArrayList<Record> a)
    {
        int maxLength = 0;

        for (Record aa : a) {
            if(maxLength < aa.getSurname().length())
                maxLength = aa.getSurname().length();
        }

        return maxLength;
    }

    public void showRecords(ArrayList<Record> a)
    {
        for (Record aa : a) {
            System.out.println(aa.getNumber() + " " + aa.getSurname());
        }
        System.out.print("\n");
    }
}
