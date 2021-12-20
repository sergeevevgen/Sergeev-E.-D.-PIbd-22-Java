import java.util.Comparator;

public class LokoComparer implements Comparator<Vehicle> {
    //Метод сравнения двух объектов типа "Vehicle"
    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        var type1 = o1.getClass().getName();
        var type2 = o2.getClass().getName();
        //Если типы "Локомотив", то используем метод сравнения Локомотивов
        if(type1.equals(type2) && type1.equals("Lokomotiv"))
        {
            return ComparerLoko((Lokomotiv) o1, (Lokomotiv) o2);
        }
        //Если типы "Монорельс", то используем метод сравнения Монорельс
        if (type1.equals(type2) && type1.equals("MonoRels"))
        {
            return ComparerMonoRels((MonoRels) o1, (MonoRels) o2);
        }
        //Если типы не равны и первый из них "Локомотив",
        //то другой - "Монорельс" (Локомотив > Монорельс)
        if(!type1.equals(type2) && type1.equals("Lokomotiv"))
        {
            return -1;
        }
        //Если типы не равны и первый из них не "Локомотив" (Монорельс),
        //то другой - "локомотив" (Локомотив > Монорельс)
        return 1;
    }
    //Метод сравнения двух объектов типа "Lokomotiv"
    private int ComparerLoko(Lokomotiv o1, Lokomotiv o2)
    {
        if(o1.getMaxSpeed() != o2.getMaxSpeed())
        {
            return Integer.compare(o1.getMaxSpeed(), o2.getMaxSpeed());
        }
        if(o1.getWeight() != o2.getWeight())
        {
            return Integer.compare(o1.getWeight(), o2.getWeight());
        }
        if(o1.getMainColor().getRGB() != o2.getMainColor().getRGB())
        {
            return Integer.compare(o1.getMainColor().getRGB(), o2.getMainColor().getRGB());
        }
        return 0;
    }

    // Метод сравнения двух объектов типа "MonoRels"
    private int ComparerMonoRels(MonoRels o1, MonoRels o2)
    {
        var res = ComparerLoko(o1, o2);
        if(res != 0)
        {
            return res;
        }
        if(o1.GetDopColor().getRGB() != o2.GetDopColor().getRGB())
        {
            return Integer.compare(o1.GetDopColor().getRGB(), o2.GetDopColor().getRGB());
        }
        if(o1.GetNumOfWins() != o2.GetNumOfWins())
        {
            return Integer.compare(o1.GetNumOfWins(), o2.GetNumOfWins());
        }
        if(o1.GetLamp() != o2.GetLamp())
        {
            return Boolean.compare(o1.GetLamp(), o2.GetLamp());
        }
        if(o1.GetAirCooler() != o2.GetAirCooler())
        {
            return Boolean.compare(o1.GetAirCooler(), o2.GetAirCooler());
        }
        if(o1.getNumOfDoors() != o2.getNumOfDoors())
        {
            return Integer.compare(o1.getNumOfDoors(), o2.getNumOfDoors());
        }
        if(o1.getTypeOfDoor() != o2.getTypeOfDoor())
        {
            return Integer.compare(o1.getTypeOfDoor(), o2.getTypeOfDoor());
        }
        return 0;
    }
}
