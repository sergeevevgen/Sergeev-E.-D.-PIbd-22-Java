import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.IOException;

//Класс-коллекция депо
public class DepoCollection {

    //Словарь (хранилище) с депо
    Map<String, Depo<Vehicle, IInterDop>> depoStages;

    //Возвращение списка названий депо
    public Set<String> Keys()
    {
        return depoStages.keySet();
    }

    //Ширина окна отрисовки
    private final int pictureWidth;

    //Высота окна отрисовки
    private final int pictureHeight;

    //Разделитель для записи информации в файл
    private final String separator = ":";

    //Конструктор
    public DepoCollection(int pictureWidth, int pictureHeight)
    {
        depoStages = new HashMap<>();
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }

    //Добавление парковки
    public void AddDepo(String name)
    {
        if(!depoStages.containsKey(name))
            depoStages.put(name, new Depo<>(pictureWidth, pictureHeight));
    }

    //Удаление парковки
    public void DelDepo(String name)
    {
        depoStages.remove(name);
    }

    //Доступ к парковке через "индексатор"
    public Depo<Vehicle, IInterDop> get(String ind)
    {
        return depoStages.get(ind);
    }

    //Индексатор с 2 параметрами. Первый параметр будет выбирать элемент из
    // словаря, второй – элемент из списка параметризованного класса
    public Vehicle getV(String name, int index)
    {
        var depo = get(name);
        if(depo != null)
            return depo.get_places(index);
        return null;
    }

    //Сохранение информации по локомотивам в депо в файл
    //filename - путь и имя файла
    public boolean SaveData(String filename)
    {
        //Не добавлять, а создавать новый
        try (FileWriter fw = new FileWriter(filename, false))
        {
            fw.write("DepoCollection\n");
            for (var level : depoStages.entrySet()) {
                fw.write("Depo" + separator + level.getKey() + "\n");
                ITransport lokomotiv;
                for(int i = 0; (lokomotiv = level.getValue().get_places(i)) != null; ++i)
                {
                    if(lokomotiv.getClass() == Lokomotiv.class)
                    {
                        fw.write("Lokomotiv" + separator);
                    }
                    if(lokomotiv.getClass() == MonoRels.class)
                    {
                        fw.write("MonoRels" + separator);
                    }
                    fw.write(lokomotiv + "\n");
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    //Загрузка информации по локомотивам в депо из файла
    public boolean LoadData(String filename){
        if(!new File(filename).exists())
            return false;

        try(FileReader fr = new FileReader(filename)){
            Scanner sc = new Scanner(fr);
            if(sc.nextLine().contains("DepoCollection"))
            {
                depoStages.clear();
            }
            else
                return false;

            String key = null;
            Vehicle lokomotiv = null;
            String line;
            while(sc.hasNextLine())
            {
                line = sc.nextLine();
                if(line.contains("Depo"))
                {
                    key = line.split(separator)[1];
                    depoStages.put(key, new Depo<>(pictureWidth, pictureHeight));
                    continue;
                }
                else if(line.contains("Lokomotiv"))
                {
                    lokomotiv = new Lokomotiv(line.split(separator)[1]);
                }
                else if (line.contains("MonoRels"))
                {
                    lokomotiv = new MonoRels(line.split(separator)[1]);
                }
                if(depoStages.get(key).Add(lokomotiv) == -1)
                    return false;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    //Сохранение отдельного депо
    public boolean SaveDepo(String filename, String depoName)
    {
        //Не добавлять, а создавать новый
        try (FileWriter fw = new FileWriter(filename, false))
        {
            fw.write("Depo" + separator + depoName + "\n");
            var depo = get(depoName);
            Vehicle lokomotiv;
            for(int i = 0; (lokomotiv = depo.get_places(i)) != null; ++i)
            {
                if(lokomotiv.getClass() == Lokomotiv.class)
                {
                    fw.write("Lokomotiv" + separator);
                }
                if(lokomotiv.getClass() == MonoRels.class)
                {
                    fw.write("MonoRels" + separator);
                }
                fw.write(lokomotiv + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    //Загрузка отдельного депо
    public boolean LoadDepo(String filename)
    {
        if(!new File(filename).exists())
            return false;

        try(FileReader fr = new FileReader(filename)){
            Scanner sc = new Scanner(fr);
            String line = sc.nextLine();
            String name;
            if(line.contains("Depo"))
            {
                name = line.split(separator)[1];
                depoStages.remove(line);
                depoStages.put(name, new Depo<>(pictureWidth, pictureHeight));
            }
            else
                return false;

            Vehicle lokomotiv = null;
            while(sc.hasNextLine())
            {
                line = sc.nextLine();
                if(line.contains("Lokomotiv"))
                {
                    lokomotiv = new Lokomotiv(line.split(separator)[1]);
                }
                else if (line.contains("MonoRels"))
                {
                    lokomotiv = new MonoRels(line.split(separator)[1]);
                }
                if(depoStages.get(name).Add(lokomotiv) == -1)
                    return false;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return true;
    }
}
