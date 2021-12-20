import java.awt.*;
import java.util.LinkedList;
import java.util.List;

//Параметризованный класс-хранилище для объектов
public class Depo<T extends ITransport, U extends IInterDop>  {

    //Список хранимых объектов
    private final List<T> _places;

    //Максимальное кол-во мест в депо
    private final int _maxCount;

    //Ширина окна отрисовки депо
    private final int pictureWidth;

    //Высота окна отрисовки депо
    private final int pictureHeight;

    //Ширина места в депо
    private final int _placeSizeWidth = 140;

    //Высота места в депо
    private final int _placeSizeHeight = 70;

    //Конструктор
    public Depo(int picWidth, int picHeight)
    {
        int width = picWidth / _placeSizeWidth;
        int height = picHeight / _placeSizeHeight;
        _maxCount = width * height;
        _places = new LinkedList<>();
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    //Добавить объект (как перегрузка оператора сложения в C#)
    //Логика действия: в депо добавляется локомотив/монорельс
    public int Add(T lok) throws DepoOverflowException {
        if(_places.size() >= _maxCount)
        {
            throw new DepoOverflowException();
        }
        _places.add(lok);
        return _places.size() - 1;
    }

    //Удалить объект (как перегрузка оператора вычитания в C#)
    //Логика действия: из депо забираем локомотив/монорельс
    public T Sub(int index) throws DepoPlaceNotFoundException {
        if(index > -1 && index < _places.size())
        {
            T dop = _places.get(index);
            _places.remove(index);
            return dop;
        }
        throw new DepoPlaceNotFoundException(index);
    }

    //Больше или равно (как перегрузка оператора больше или равно)
    //Логика действия: сравнивает кол-во свободных мест с числом
    public int MoreOrEquals(double dob) {
        int a = _maxCount - _places.size();
        return Double.compare(a, dob);
    }

    //Меньше или равно (как перегрузка оператора меньше или равно)
    //Логика действия: сравнивает кол-во свободных мест с числом
    public int LessOrEquals(double dob) {
        int a = _maxCount - _places.size();
        return Double.compare(a, dob);
    }

    //Метод для отрисовки объектов в депо
    public void Draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        //Отрисовка линий разметки
        DrawMarking(g2d);

        int x = 45, y = 35;
        for(int i = 0; i < _places.size(); ++i)
        {
            if(i % (pictureWidth / _placeSizeWidth) == 0 && i != 0)
            {
                y += 70;
                x = 45;
            }
            _places.get(i).SetPosition(x, y, 1, 1);
            _places.get(i).DrawTransport(g2d);
            x += 140;
        }
    }

    //Метод для отрисовки линий разметки
    private void DrawMarking(Graphics2D g2d)
    {
        g2d.setColor(Color.BLACK);

        for(int i = 0; i <  pictureWidth / _placeSizeWidth; ++i) {
            for (int j = 0; j < pictureHeight / _placeSizeHeight + 1; ++j)
            {
                g2d.drawLine( 30 + i * _placeSizeWidth, 20 + j * _placeSizeHeight,
                         30 + i * _placeSizeWidth + _placeSizeWidth / 2,
                         20 + j * _placeSizeHeight);
            }
            g2d.drawLine(30 + i * _placeSizeWidth, 20,30 + i * _placeSizeWidth, 20 + (pictureHeight / _placeSizeHeight) * _placeSizeHeight);
        }
    }

    //Метод получение элемента депо по индексу
    public T get_places(int index) {
        if(index > -1 && index < _places.size())
            return _places.get(index);
        return null;
    }

    //Метод очищения всех мест
    public void clear()
    {
        _places.clear();
    }
}
