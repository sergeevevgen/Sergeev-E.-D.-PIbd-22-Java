
import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class MonoRels{
    //Объект от класса Random
    Random r = new Random();

    //Объект от класса ДопКласс
    private DopClass dopclass;

    //Позиция по оси Х
    private int _startPosX;

    //Позиция по оси Y
    private int _startPosY;

    //Ширина окна отрисовки
    private int _pictureWidth = 900;

    //Высота окна отрисовки
    private int _pictureHeight = 500;

    //Ширина монорельса
    private final int monoRelsWidth = 105;

    //Высота монорельса
    private final int monoRelsHeight = 50;

    //Максимальная скорость монорельса
    private int maxSpeed;
    //Метод для инициализации максимальной скорости монорельса
    private void SetMaxSpeed(int maxSpeed)
    {
        this.maxSpeed = maxSpeed;
    }
    //Метод для получения максимальной скорости монорельса
    public int GetMaxSpeed()
    {
        return maxSpeed;
    }

    //Масса монорельса
    private int weight;
    //Метод для инициализации массы монорельса
    private void SetWeight(int weight)
    {
        this.weight = weight;
    }
    //Метод для получения массы монорельса
    public int GetWeight()
    {
        return weight;
    }

    //Основной цвет монорельса
    private Color mainColor;
    //Метод для инициализации основного цвета монорельса
    private void SetMainColor(Color mainColor)
    {
        this.mainColor = mainColor;
    }
    //Метод для получения основного цвета монорельса
    public Color GetMainColor()
    {
        return mainColor;
    }

    //Дополнительный цвет монорельса
    private Color dopColor;
    //Метод для установки дополнительного цвета монорельса
    private void SetDopColor(Color dopColor)
    {
        this.dopColor = dopColor;
    }
    //Метод для получения дополнительного цвета монорельса
    public Color GetDopColor()
    {
        return dopColor;
    }

    //Количество окон монорельса
    private int numOfWins;
    //Метод для установки числа окон монорельса
    private void SetNumOfWins(int numOfWins)
    {
        this.numOfWins = numOfWins;
    }
    //Метод для получения числа окон монорельса
    public int GetNumOfWins()
    {
        return numOfWins;
    }

    //Признак наличия фары
    private boolean lamp;
    //Метод для установки признака наличия фары
    private void SetLamp(boolean lamp)
    {
        this.lamp = lamp;
    }
    //Метод для получения признака наличия фары
    public boolean GetLamp()
    {
        return lamp;
    }

    //Признак наличия воздухозаборников
    private boolean airCooler;
    //Метод для установки признака наличия воздухозаборников
    private void SetAirCooler(boolean airCooler)
    {
        this.airCooler = airCooler;
    }
    //Метод для получения признака наличия воздухозаборников
    public boolean GetAirCooler()
    {
        return airCooler;
    }

    //Метод для инициализации всех полей
    public void Init(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean lamp, boolean airCooler)
    {
        SetMaxSpeed(maxSpeed);
        SetWeight(weight);
        SetMainColor(mainColor);
        SetDopColor(dopColor);
        SetLamp(lamp);
        SetAirCooler(airCooler);
        SetNumOfWins(r.nextInt(4) + 1);
        //Инициализация объекта для отрисовки дверей
        dopclass = new DopClass();
        dopclass.SetDopc(GetDopColor());
        dopclass.SetD(r.nextInt(4) + 1);
    }

    //Метод установки начальной позиции монорельса
    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    //Метод для передвижения монорельса
    public void MoveTransport(Direction direction)
    {
        int step = GetMaxSpeed() * 100 / GetWeight();
        switch (direction)
        {
            // вправо
            case RIGHT:
                if (_startPosX + step < _pictureWidth - 150 - monoRelsWidth)
                {
                    _startPosX += step;
                }
                break;
            //влево
            case LEFT:
                if (_startPosX - step > 11)
                {
                    _startPosX -= step;
                }
                break;
            //вверх
            case UP:
                if (_startPosY - step > 40)
                {
                    _startPosY -= step;
                }
                break;
            //вниз
            case DOWN:
                if (_startPosY + step < _pictureHeight + 35 - monoRelsHeight)
                {
                    _startPosY += step;
                }
                break;
        }
    }

    // Метод отрисовки монорельса
    public void DrawTransport(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        //Отрисовка верхней части кузова монорельса
        int[] pointsX = new int[] {_startPosX + 7, _startPosX + 97, _startPosX + 97, _startPosX + 2};
        int[] pointsY = new int[]{_startPosY, _startPosY, _startPosY + 17, _startPosY + 17};
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(pointsX, pointsY, pointsX.length);

        //Отрисовка нижней части кузова монорельса
        pointsX = new int[] {_startPosX + 2, _startPosX + 97, _startPosX + 97, _startPosX + 2};
        pointsY = new int[] {_startPosY + 17, _startPosY + 17, _startPosY + 34, _startPosY + 34};
        g2d.setColor(GetMainColor());
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(pointsX, pointsY, pointsX.length);

        //Отрисовка трансмиссии
        pointsX = new int[] {_startPosX + 2, _startPosX + 15, _startPosX + 84, _startPosX + 97};
        pointsY = new int[] {_startPosY + 34, _startPosY + 40, _startPosY + 40, _startPosY + 34};
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        g2d.drawPolygon(pointsX, pointsY, pointsX.length);

        //Отрисовка задней части
        g2d.fillRect(_startPosX + 97, _startPosY + 3, 5, 28);

        //Отрисовка фар
        if (GetLamp())
        {
            g2d.drawRect(_startPosX + 2, _startPosY + 24, 6, 5);
            g2d.setColor(Color.yellow);
            g.fillRect(_startPosX + 3, _startPosY + 25, 5, 4);
        }

        //Отрисовка воздухозаборников
        if (GetAirCooler())
        {
            g2d.setColor(Color.BLACK);
            g2d.drawRect(_startPosX + 18, _startPosY - 5, 17, 5);
            g2d.drawRect(_startPosX + 75, _startPosY - 5, 17, 5);
            g2d.setColor(GetDopColor());
            g2d.fillRect(_startPosX + 19, _startPosY - 4, 16, 4);
            g2d.fillRect(_startPosX + 76, _startPosY - 4, 16, 4);
        }

        //Отрисовка окон
        if (GetNumOfWins() >= 1)
        {
            g2d.setColor(Color.BLUE);
            g2d.drawRect(_startPosX + 9, _startPosY + 3, 8, 11);
            g2d.setColor(Color.WHITE);
            g2d.fillRect(_startPosX + 10, _startPosY + 4, 7, 10);
        }
        if (GetNumOfWins() >= 2)
        {
            g2d.setColor(Color.BLUE);
            g2d.drawRect(_startPosX + 19, _startPosY + 3, 8, 11);
            g2d.setColor(Color.WHITE);
            g2d.fillRect(_startPosX + 20, _startPosY + 4, 7, 10);
        }
        if (GetNumOfWins() == 3)
        {
            g2d.setColor(Color.BLUE);
            g2d.drawRect(_startPosX + 86, _startPosY + 3, 8, 11);
            g2d.setColor(Color.WHITE);
            g2d.fillRect(_startPosX + 87, _startPosY + 4, 7, 10);
        }

        //Отрисовка дверей
        dopclass.DrawPart(g2d, _startPosX, _startPosY);
    }
}
