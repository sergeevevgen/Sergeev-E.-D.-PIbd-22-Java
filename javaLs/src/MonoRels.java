import java.awt.*;
import java.util.Random;

//Дочерний класс от класса Lokomotiv
public class MonoRels extends Lokomotiv{

    //Поле-объект от интерфейса InterDop
    private IInterDop doors;

    //Дополнительный цвет монорельса
    private Color dopColor;
    //Метод для установки дополнительного цвета монорельса
    public void SetDopColor(Color dopColor)
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
    public MonoRels(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean lamp, boolean airCooler, int numOfWins, int numOfDoors) {
        super(maxSpeed, weight, mainColor, 105, 50);
        SetDopColor(dopColor);
        SetLamp(lamp);
        SetAirCooler(airCooler);
        SetNumOfWins(numOfWins);
        doors = new SquareDoors();
        doors.setD(numOfDoors);
    }

    //Выбор типа двери
    public void addTypeOfDoor(int a)
    {
        if(a == 1)
            doors = new SquareDoors();
        else if (a == 2)
            doors = new CircleDoors();
        else
            doors = new TriangleDoors();
    }

    //Кол-во дверей
    public void addNumOfDoors(int n)
    {
        doors.setD(n);
    }

    // Метод отрисовки монорельса
    @Override
    public void DrawTransport(Graphics g)
    {
        super.DrawTransport(g);
        Graphics2D g2d = (Graphics2D) g;

        //Отрисовка новой трансмиссии
        g2d.setColor(Color.BLACK);
        int[] pointsX = new int[]{(int) (_startPosX - 4), (int) (_startPosX + 10), (int) (_startPosX + 91), (int) (_startPosX + 103)};
        int[] pointsY = new int[]{(int) (_startPosY + 35), (int) (_startPosY + 47), (int) (_startPosY + 47), (int) (_startPosY + 35)};
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);

        //Отрисовка фары
        if (GetLamp()) {
            g2d.setColor(Color.yellow);
            g2d.fillRect((int) (_startPosX + 2), (int) (_startPosY + 24), 6, 5);

            g2d.setColor(Color.BLACK);
            g2d.drawRect((int) (_startPosX + 2), (int) (_startPosY + 24), 6, 5);
        }

        //Отрисовка воздухозаборников
        if (GetAirCooler()) {
            g2d.setColor(GetDopColor());
            g2d.fillRect((int) (_startPosX + 18), (int) (_startPosY - 5), 17, 5);
            g2d.fillRect((int) (_startPosX + 75), (int) (_startPosY - 5), 17, 5);

            g2d.setColor(Color.BLACK);
            g2d.drawRect((int) (_startPosX + 18), (int) (_startPosY - 5), 17, 5);
            g2d.drawRect((int) (_startPosX + 75), (int) (_startPosY - 5), 17, 5);
        }

        //Отрисовка окон
        if (GetNumOfWins() >= 2) {
            g2d.setColor(Color.CYAN);
            g2d.fillRect((int) (_startPosX + 19), (int) (_startPosY + 3), 8, 11);

            g2d.setColor(Color.BLUE);
            g2d.drawRect((int) (_startPosX + 19), (int) (_startPosY + 3), 8, 11);
        }
        if (GetNumOfWins() >= 3) {
            g2d.setColor(Color.CYAN);
            g2d.fillRect((int) (_startPosX + 86), (int) (_startPosY + 3), 8, 11);

            g2d.setColor(Color.BLUE);
            g2d.drawRect((int) (_startPosX + 86), (int) (_startPosY + 3), 8, 11);
        }

        //Отрисовка дверей
        doors.setDopc(GetDopColor());
        doors.DrawPart(g2d, (int) _startPosX, (int) _startPosY);
    }
}
