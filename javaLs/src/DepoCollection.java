import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
}
