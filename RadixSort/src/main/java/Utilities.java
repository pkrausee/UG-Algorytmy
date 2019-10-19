import java.util.List;


class Utilities
{
    public int maxLength(List<Record> a)
    {
        int maxLength = 0;

        for (Record aa : a)
        {
            if(maxLength < aa.getSurname().length())
            {
                maxLength = aa.getSurname().length();
            }
        }

        return maxLength;
    }

    public void showRecords(List<Record> a)
    {
        for (Record aa : a)
        {
            System.out.println(aa.getNumber() + " " + aa.getSurname());
        }
        System.out.print("\n");
    }
}
