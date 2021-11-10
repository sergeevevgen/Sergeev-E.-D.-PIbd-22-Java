import java.awt.*;
import java.lang.reflect.Array;

//Параметризованный класс-хранилище для объектов
public class Depo<T extends ITransport, U extends IInterDop>  {

    //Массив хранимых объектов
    private final T[] _places;

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
        _places = (T[]) Array.newInstance(ITransport.class,width * height);
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    //Добавить объект (как перегрузка оператора сложения в C#)
    //Логика действия: в депо добавляется локомотив/монорельс
    public int Add(T lok)
    {
        for (int i = 0; i < _places.length; ++i)
        {
            if(_places[i] == null)
            {
                _places[i] = lok;
                return i;
            }
        }
        return -1;
    }

    //Удалить объект (как перегрузка оператора вычитания в C#)
    //Логика действия: из депо забираем локомотив/монорельс
    public T Sub(int index)
    {
        if(index > -1 && index < _places.length && _places[index] != null)
        {
            T dop = _places[index];
            _places[index] = null;
            return dop;
        }
        return null;
    }

    //Больше или равно (как перегрузка оператора больше или равно)
    //Логика действия: сравнивает кол-во занятых мест с числом
    public int MoreOrEquals(double dob) {
        int a = 0;
        for (int i = 0; i < _places.length; ++i)
        {
            if(_places[i] != null)
            {
                a++;
            }
        }
        return Double.compare(a, dob);
    }

    //Меньше или равно (как перегрузка оператора меньше или равно)
    //Логика действия: сравнивает кол-во занятых мест с числом
    public int LessOrEquals(double dob) {
        int a = 0;
        for (int i = 0; i < _places.length; ++i)
        {
            if(_places[i] != null)
            {
                a++;
            }
        }
        return Double.compare(a, dob);
    }

    //Метод для отрисовки объектов в депо
    public void Draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        //Отрисовка линий разметки
        DrawMarking(g2d);

        int x = 60, y = 65;
        for(int i = 0; i < _places.length; ++i)
        {
            if(i % (pictureWidth / _placeSizeWidth) == 0 && i != 0)
            {
                y += 70;
                x = 60;
            }
            if(_places[i] != null) {
                _places[i].SetPosition(x, y, 1, 1);
                _places[i].DrawTransport(g2d);
            }
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
                g2d.drawLine(50 + i * _placeSizeWidth, 50 + j * _placeSizeHeight,
                         50 + i * _placeSizeWidth + _placeSizeWidth / 2,
                        50 + j * _placeSizeHeight);
            }
            g2d.drawLine(50 + i * _placeSizeWidth, 50, 50 + i * _placeSizeWidth,
                    50 + (pictureHeight / _placeSizeHeight) * _placeSizeHeight);
        }
    }
}
