import java.util.ArrayList;
import java.util.List;


class RadixSort
{
    public void radixSort(List<Record> a, int wordLength)
    {
        fillGaps(a, maxLength(a));

        int maxValue = (char)27;
        ArrayList<Record> b = new ArrayList<Record>();
        ArrayList<Integer> c = new ArrayList<Integer>();

        if(Character.isLetter(maxValue))
        {
            maxValue = maxValue - 96;
        }

        for(int i = wordLength - 1; i >= 0; i--)
        {
            fillWithZerosI(c, maxValue);
            fillWithZerosR(b, a.size());

            for (Record record : a) {
                int currChar = record.getSurname().charAt(i);
                if (currChar > 96)
                    currChar -= 95;
                else
                    currChar -= 26;

                c.set(currChar - 1, c.get(currChar - 1) + 1);
            }

            for(int j = 1; j < maxValue; j++)
            {
                c.set(j, c.get(j) + c.get(j - 1));
            }

            for(int j = a.size() - 1; j >= 0; j--)
            {
                int currChar = a.get(j).getSurname().charAt(i);
                if(currChar > 96)
                    currChar -= 95;
                else
                    currChar -= 26;

                b.set(c.get(currChar - 1) - 1, a.get(j));
                c.set(currChar - 1, c.get(currChar - 1) - 1);
            }

            for(int j = 0; j < a.size(); j++)
            {
                a.set(j, b.get(j));
            }
        }

        delGaps(a);
    }

    private void fillWithZerosI(List<Integer> a, int amount)
    {
        a.clear();

        for(int i = 0; i < amount; i++)
        {
            a.add(0);
        }
    }

    private void fillWithZerosR(List<Record> a, int amount)
    {
        a.clear();

        for(int i = 0; i < amount; i++)
        {
            a.add(new Record("", ""));
        }
    }

    private void fillGaps(List<Record> a, int wordLength)
    {
        for (Record aa : a)
        {
            StringBuilder sb = new StringBuilder(aa.getSurname());
            for(int i = 0; i < wordLength - aa.getSurname().length(); i++)
            {
                sb.append((char)27);
            }
            aa.setSurname(sb.toString());
        }
    }

    private void delGaps(List<Record> a)
    {
        for (Record aa : a)
        {
            StringBuilder sb = new StringBuilder(aa.getSurname());

            for(int i = sb.length() - 1; i >= 0; i--)
            {
                if(aa.getSurname().charAt(i) == (char)27)
                    sb.deleteCharAt(i);
            }

            aa.setSurname(sb.toString());
        }
    }

    private int maxLength(List<Record> a)
    {
        int maxLength = 0;

        for (Record aa : a)
        {
            if(maxLength < aa.getSurname().length())
                maxLength = aa.getSurname().length();
        }

        return maxLength;
    }
}
