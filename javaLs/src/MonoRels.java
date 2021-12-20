import java.awt.*;
import java.util.Iterator;

//Дочерний класс от класса Lokomotiv
public class MonoRels extends Lokomotiv {

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

    //Поле-хранилище кол-ва дверей
    private int doorsCount;

    //Поле-хранилище реализации дверей
    private int doorsRealization;

    //Текущее св-во
    private int current;

    //Метод для инициализации всех полей
    public MonoRels(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean lamp, boolean airCooler, int numOfWins, int numOfDoors) {
        super(maxSpeed, weight, mainColor, 105, 50);
        SetDopColor(dopColor);
        SetLamp(lamp);
        SetAirCooler(airCooler);
        SetNumOfWins(numOfWins);
        addTypeOfDoor(1);
        addNumOfDoors(numOfDoors);
    }

    //Конструктор для создания объекта с помощью информации из файла
    public MonoRels(String info){
        super(info);
        String[] strs = info.split(separator);
        if(strs.length == 9)
        {
            setMaxSpeed(Integer.parseInt(strs[0]));
            setWeight(Integer.parseInt(strs[1]));
            setMainColor(new Color(Integer.parseInt(strs[2])));
            SetDopColor(new Color(Integer.parseInt(strs[3])));
            SetLamp(Boolean.parseBoolean(strs[4]));
            SetAirCooler(Boolean.parseBoolean(strs[5]));
            SetNumOfWins(Integer.parseInt(strs[6]));
            addTypeOfDoor(Integer.parseInt(strs[7]));
            addNumOfDoors(Integer.parseInt(strs[8]));
        }
    }

    //Выбор типа двери
    public void addTypeOfDoor(int a)
    {
        doorsRealization = a;
        if(doorsRealization == 1) {
            doors = new SquareDoors();
        }
        else if (doorsRealization == 2) {
            doors = new CircleDoors();
        }
        else {
            doors = new TriangleDoors();
        }
    }

    //Выбор типа двери
    public int getTypeOfDoor()
    {
        return doorsRealization;
    }

    //Кол-во дверей
    public void addNumOfDoors(int n)
    {
        doorsCount = n;
        doors.setD(doorsCount);
    }

    //Кол-во дверей
    public int getNumOfDoors()
    {
        return doorsCount;
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

    //Переопределение метода toString()
    @Override
    public String toString() {
        return super.toString() + separator + GetDopColor().getRGB()
                + separator + GetLamp() + separator + GetAirCooler() +
                separator + GetNumOfWins() + separator + getTypeOfDoor() +
                separator + getNumOfDoors();
    }

    //Методы сравнения объектов
    //Перегрузка метода от object
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MonoRels monoObj)) {
            return false;
        }
        return equals(monoObj);
    }

    //Метод сравнения для класса MonoRels
    public boolean equals(MonoRels other) {
        if (other == null) {
            return false;
        }
        if (!getClass().getName().equals(other.getClass().getName())) {
            return false;
        }
        if (getMaxSpeed() != other.getMaxSpeed()) {
            return false;
        }
        if (getWeight() != other.getWeight()) {
            return false;
        }
        if(getMainColor().getRGB() != other.getMainColor().getRGB())
        {
            return false;
        }
        if(GetDopColor().getRGB() != other.GetDopColor().getRGB())
        {
            return false;
        }
        if(GetNumOfWins() != other.GetNumOfWins())
        {
            return false;
        }
        if(GetLamp() != other.GetLamp())
        {
            return false;
        }
        if(GetAirCooler() != other.GetAirCooler())
        {
            return false;
        }
        if(getNumOfDoors() != other.getNumOfDoors())
        {
            return false;
        }
        return getTypeOfDoor() == other.getTypeOfDoor();
    }

    //Переопределение метода интерфейса Comparable
    @Override
    public int compareTo(Lokomotiv o) {
        var res = super.compareTo(o);
        if(res != 0)
        {
            return res;
        }
        MonoRels m = (MonoRels) o;
        if(GetDopColor().getRGB() != m.GetDopColor().getRGB())
        {
            return Integer.compare(GetDopColor().getRGB(), m.GetDopColor().getRGB());
        }
        if(GetNumOfWins() != m.GetNumOfWins())
        {
            return Integer.compare(GetNumOfWins(), m.GetNumOfWins());
        }
        if(GetLamp() != m.GetLamp())
        {
            return Boolean.compare(GetLamp(), m.GetLamp());
        }
        if(GetAirCooler() != m.GetAirCooler())
        {
            return Boolean.compare(GetAirCooler(), m.GetAirCooler());
        }
        if(getNumOfDoors() != m.getNumOfDoors())
        {
            return Integer.compare(getNumOfDoors(), m.getNumOfDoors());
        }
        if(getTypeOfDoor() != m.getTypeOfDoor())
        {
            return Integer.compare(getTypeOfDoor(), m.getTypeOfDoor());
        }
        return 0;
    }

    //Переопределение метода для Iterator, Iterable
    @Override
    public Iterator<String> iterator() {
        return this;
    }

    //Переопределение метода для Iterator, Iterable
    @Override
    public boolean hasNext() {
        if(current > 8){
            current = -1;
            return false;
        }
        return true;
    }

    //Переопределение метода для Iterator, Iterable
    @Override
    public String next() {
        current++;
        if(current == 0)
            return String.valueOf(getMaxSpeed());
        if(current == 1)
            return String.valueOf(getWeight());
        if(current == 2)
            return String.valueOf(getMainColor().getRGB());
        if (current == 3)
            return String.valueOf(GetDopColor().getRGB());
        if(current == 4)
            return String.valueOf(GetNumOfWins());
        if(current == 5)
            return String.valueOf(GetLamp());
        if(current == 6)
            return String.valueOf(GetAirCooler());
        if(current == 7)
            return String.valueOf(getNumOfDoors());
        if(current == 8)
            return String.valueOf(getTypeOfDoor());
        return null;
    }
}
