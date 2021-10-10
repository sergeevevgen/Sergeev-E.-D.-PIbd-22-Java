
import java.util.Random;
import java.awt.*;
import javax.swing.*;

//Наследуется от класса JComponent для возможности отрисовки
public class MonoRels extends JComponent{
    //Поле-объект от класса рандом
    Random r = new Random();
    
    //Поле-объект от класса ДопКласс
    private DopClass dopclass;
    
    //Позиция по оси Х
    private int _startPosX;

    //Позиция по оси Y
    private int _startPosY;

    //Ширина окна отрисовки
    private int _pictureWidth = 900;

    //Высота окна отрисовки
    private int _pictureHeight = 500;

    //Ширина Монорельса
    private final int monoRelsWidth = 105;

    //Высота Монорельса
    private final int monoRelsHeight = 50;

    //Поле (Свойство) Максимальная скорость
    private int maxSpeed;
    //Метод (как свойство) для установки максимальной скорости
    private void SetMaxSpeed(int maxSpeed)
    {
        this.maxSpeed = maxSpeed;
    }
    //Метод (как свойство) для получения максимальной скорости
    public int GetMaxSpeed()
    {
        return maxSpeed;
    }
    
    //Поле (Свойство) Масса
    private int weight;
    //Метод (как свойство) для установки массы
    private void SetWeight(int weight)
    {
        this.weight = weight;
    }
    //Метод (как свойство) для получения массы
    public int GetWeight()
    {
        return weight;
    }

    //Поле (Свойство) Основной цвет
    private Color mainColor;
    //Метод (как свойство) для установки основного цвета
    private void SetMainColor(Color mainColor)
    {
        this.mainColor = mainColor;
    }
    //Метод (как свойство) для получения основного цвета
    public Color GetMainColor()
    {
        return mainColor;
    }

    //Поле (Свойство) Дополнительный цвет
    private Color dopColor;
    //Метод (как свойство) для установки дополнительного цвета
    private void SetDopColor(Color dopColor)
    {
        this.dopColor = dopColor;
    }
    //Метод (как свойство) для получения дополнительного цвета
    public Color GetDopColor()
    {
        return dopColor;
    }

    //Поле (Свойство) кол-во окон
    private int numOfWins;
    //Метод (как свойство) для установки кол-ва окон
    private void SetNumOfWins(int numOfWins)
    {
        this.numOfWins = numOfWins;
    }
    //Метод (как свойство) для получения кол-ва окон
    public int GetNumOfWins()
    {
        return numOfWins;
    }

    //Поле (Свойство) Признак наличия фар
    private boolean lamp;
    //Метод (как свойство) для установки признака наличия/не наличия фар
    private void SetLamp(boolean lamp)
    {
        this.lamp = lamp;
    }
     //Метод (как свойство) для получения признака наличия/не наличия фар
    public boolean GetLamp()
    {
        return lamp;
    }

    //Поле (Свойство) признак наличия воздухозаборников
    private boolean airCooler;
    //Метод (как свойство) для установки признака наличия/не наличия воздухозаборников
    private void SetAirCooler(boolean airCooler)
    {
        this.airCooler = airCooler;
    }
     //Метод (как свойство) для получения признака наличия/не наличия воздухозаборников
    public boolean GetAirCooler()
    {
        return airCooler;
    }

    //Метод инициализации полей объекта
    public void Init(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean lamp, boolean airCooler)
    {
        SetMaxSpeed(maxSpeed);
        SetWeight(weight);
        SetMainColor(mainColor);
        SetDopColor(dopColor);
        SetLamp(lamp);
        SetAirCooler(airCooler);
        SetNumOfWins(r.nextInt(4) + 1);
        //инициализация объекта ДопКласса
        dopclass = new DopClass();
        dopclass.SetDopc(dopColor);
        dopclass.SetD(r.nextInt(2) + 1);
    }

    //Метод установки позиции для объекта
    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        dopclass.x = _startPosX;
        dopclass.y = _startPosY;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    //Метод передвижения объекта
    public void MoveTransport(Direction direction)
    {
        int step = GetMaxSpeed() * 100 / GetWeight();
        switch (direction)
        {
            // âïðàâî
            case RIGHT:
                if (_startPosX + step < _pictureWidth - 110 - monoRelsWidth)
                {
                    _startPosX += step;
                    dopclass.x = _startPosX;
                }
                break;
            //âëåâî
            case LEFT:
                if (_startPosX - step > 5)
                {
                    _startPosX -= step;
                    dopclass.x = _startPosX;
                }
                break;
            //ââåðõ
            case UP:
                if (_startPosY - step > 50)
                {
                    _startPosY -= step;
                    dopclass.y = _startPosY;
                }
                break;
            //âíèç
            case DOWN:
                if (_startPosY + step < _pictureHeight + 40 - monoRelsHeight)
                {
                    _startPosY += step;
                    dopclass.y = _startPosY;
                }
                break;
        }
    }

    //Метод отрисовки транспорта
    public void DrawTransport(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Îòðèñîâêà âåðõíåé ÷àñòè êóçîâà ìîíîðåëüñà
        int[] pointsX = new int[] {_startPosX + 7, _startPosX + 97, _startPosX + 97, _startPosX + 2};
        int[] pointsY = new int[]{_startPosY, _startPosY, _startPosY + 17, _startPosY + 17};
        g2d.setColor(GetMainColor());
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(pointsX, pointsY, pointsX.length);

        //Îòðèñîâêà íèæíåé ÷àñòè êóçîâà ìîíîðåëüñà
        pointsX = new int[] {_startPosX + 2, _startPosX + 97, _startPosX + 97, _startPosX + 2};
        pointsY = new int[] {_startPosY + 17, _startPosY + 17, _startPosY + 34, _startPosY + 34};
        g2d.setColor(GetDopColor());
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(pointsX, pointsY, pointsX.length);

        //Îòðèñîâêà òðàíñìèññèè
        pointsX = new int[] {_startPosX + 2, _startPosX + 15, _startPosX + 84, _startPosX + 97};
        pointsY = new int[] {_startPosY + 34, _startPosY + 40, _startPosY + 40, _startPosY + 34};
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        g2d.drawPolygon(pointsX, pointsY, pointsX.length);

        //Îòðèñîâêà çàäíåé ÷àñòè
        g2d.fillRect(_startPosX + 97, _startPosY + 3, 5, 28);

        //Îòðèñîâêà ôàð
        if (GetLamp())
        {
            g2d.drawRect(_startPosX + 2, _startPosY + 24, 6, 5);
            g2d.setColor(Color.yellow);
            g.fillRect(_startPosX + 3, _startPosY + 25, 5, 4);
        }

        //Îòðèñîâêà âîçäóõîçàáîðíèêîâ
        if (GetAirCooler())
        {
            g2d.setColor(Color.BLACK);
            g2d.drawRect(_startPosX + 18, _startPosY - 5, 17, 5);
            g2d.drawRect(_startPosX + 75, _startPosY - 5, 17, 5);
            g2d.setColor(Color.WHITE);
            g2d.fillRect(_startPosX + 19, _startPosY - 4, 16, 4);
            g2d.fillRect(_startPosX + 76, _startPosY - 4, 16, 4);
        }

        //Îòðèñîâêà îêîí

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

        dopclass.DrawTransport(g2d);
    }
}
