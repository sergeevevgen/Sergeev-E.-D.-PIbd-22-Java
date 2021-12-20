import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Random;

//Наследник от абстрактного класса Vehicle
public class Lokomotiv extends Vehicle implements Comparable<Lokomotiv>, Iterator<String>, Iterable<String> {

    //Ширина локомотива
    private int lokomotivWidth = 105;

    //Высота локомотива
    private int lokomotivHeight = 50;

    //Разделитель для записи информации по объекту в файл
    protected final String separator = ";";

    //Текущее св-во
    private int current;

    //Конструктор
    public Lokomotiv(int maxSpeed, int weight, Color mainColor) {
        setMaxSpeed(maxSpeed);
        setWeight(weight);
        setMainColor(mainColor);
    }

    //Конструктор
    protected Lokomotiv(int maxSpeed, int weight, Color mainColor, int lokomotivWidth, int lokomotivHeight) {
        setMaxSpeed(maxSpeed);
        setWeight(weight);
        setMainColor(mainColor);
        this.lokomotivWidth = lokomotivWidth;
        this.lokomotivHeight = lokomotivHeight;
    }

    //Конструктор для создания объекта с помощью информации из файла
    public Lokomotiv(String info)
    {
        String[] strs = info.split(separator);
        if(strs.length == 3)
        {
            setMaxSpeed(Integer.parseInt(strs[0]));
            setWeight(Integer.parseInt(strs[1]));
            setMainColor(new Color(Integer.parseInt(strs[2])));
        }
    }

    //Метод передвижения локомотива
    @Override
    public void MoveTransport(Direction direction) {

        //Один "шаг"
        int step = getMaxSpeed() * 100 / getWeight();
        switch(direction) {
            //Вправо
            case RIGHT:
                if (_startPosX + step < _pictureWidth - lokomotivWidth) {
                    _startPosX += step;
                }
                break;
            //Влево
            case LEFT:
                if (_startPosX - step > 0) {
                    _startPosX -= step;
                }
                break;
            //Вверх
            case UP:
                if (_startPosY - step > 0) {
                    _startPosY -= step;
                }
                break;
            //Вниз
            case DOWN:
                if (_startPosY + step < (_pictureHeight - lokomotivHeight)) {
                    _startPosY += step;
                }
        }
    }

    //Метод отрисовки локомотива
    @Override
    public void DrawTransport(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        //Отрисовка верхней части кузова локомотива
        int[] pointsX = new int[]{(int) (_startPosX + 7), (int) (_startPosX + 97), (int) (_startPosX + 97), (int) (_startPosX + 2)};
        int[] pointsY = new int[]{(int) _startPosY, (int) _startPosY, (int) (_startPosY + 17), (int) (_startPosY + 17)};
        //Установка цвета
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        //Установка цвета
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(pointsX, pointsY, pointsX.length);

        //Отрисовка нижней части кузова локомотива
        pointsX = new int[]{(int) (_startPosX + 2), (int) (_startPosX + 97), (int) (_startPosX + 97), (int) (_startPosX + 2)};
        pointsY = new int[]{(int) (_startPosY + 17), (int) (_startPosY + 17), (int) (_startPosY + 34), (int) (_startPosY + 34)};
        g2d.setColor(getMainColor());
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(pointsX, pointsY, pointsX.length);

        //Отрисовка трансмиссии
        g2d.setColor(Color.BLACK);
        //Отрисовка левой части
        pointsX = new int[]{(int) (_startPosX + 3), (int) (_startPosX + 2), (int) (_startPosX + 1), (int) _startPosX, (int) (_startPosX - 1), (int) _startPosX,
                (int) (_startPosX + 1), (int) (_startPosX + 7), (int) (this._startPosX + 7), (int) (_startPosX + 8), (int) (_startPosX + 9),
                (int) (_startPosX + 10), (int) (_startPosX + 10)};
        pointsY = new int[]{(int) (_startPosY + 34), (int) (_startPosY + 35), (int) (_startPosY + 36), (int) (_startPosY + 37),
                (int) (_startPosY + 38), (int) (_startPosY + 39), (int) (_startPosY + 40), (int) (_startPosY + 40),
                (int) (_startPosY + 39), (int) (_startPosY + 38), (int) (_startPosY + 37), (int) (_startPosY + 36), (int) (_startPosY + 35)};
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        //Отрисовка средней части н1
        pointsX = new int[]{(int) (_startPosX + 10), (int) (_startPosX + 11), (int) (_startPosX + 12), (int) (_startPosX + 13), (int) (_startPosX + 14),
                (int) (_startPosX + 15), (int) (_startPosX + 29), (int) (_startPosX + 30), (int) (_startPosX + 31), (int) (_startPosX + 32),
                (int) (_startPosX + 33), (int) (_startPosX + 34)};
        pointsY = new int[]{(int) (_startPosY + 35), (int) (_startPosY + 36), (int) (_startPosY + 37), (int) (_startPosY + 38),
                (int) (_startPosY + 39), (int) (_startPosY + 40), (int) (_startPosY + 40), (int) (_startPosY + 39), (int) (_startPosY + 38),
                (int) (_startPosY + 37), (int) (_startPosY + 36), (int) (_startPosY + 35)};
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        //Отрисовка средней части н2
        pointsX = new int[]{(int) (_startPosX + 61), (int) (_startPosX + 62), (int) (_startPosX + 63), (int) (_startPosX + 64), (int) (_startPosX + 65),
                (int) (_startPosX + 66), (int) (_startPosX + 79), (int )(_startPosX + 80), (int) (_startPosX + 81), (int) (_startPosX + 82), (int) (_startPosX + 83),
                (int) (_startPosX + 84)};
        pointsY = new int[]{(int) (_startPosY + 35), (int) (_startPosY + 36), (int) (_startPosY + 37), (int) (_startPosY + 38), (int) (_startPosY + 39),
                (int) (_startPosY + 40), (int) (_startPosY + 40), (int) (_startPosY + 39), (int) (_startPosY + 38),
                (int) (_startPosY + 37), (int) (_startPosY + 36), (int) (_startPosY + 35)};
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        //Отрисовка правой части
        pointsX = new int[]{(int) (_startPosX + 96), (int) (_startPosX + 97), (int) (_startPosX + 98), (int) (_startPosX + 99),
                (int) (_startPosX + 100), (int) (_startPosX + 99), (int) (_startPosX + 98), (int) (_startPosX + 94), (int) (_startPosX + 93),
                (int) (_startPosX + 92), (int) (_startPosX + 91), (int) (_startPosX + 90), (int) (_startPosX + 89), (int) (_startPosX + 84)};
        pointsY = new int[]{(int) (_startPosY + 34), (int) (_startPosY + 35), (int) (_startPosY + 36), (int) (_startPosY + 37), (int) (_startPosY + 38),
                (int) (_startPosY + 39), (int) (_startPosY + 40), (int) (_startPosY + 40), (int) (_startPosY + 39), (int) (_startPosY + 38), (int) (_startPosY + 37), (int) (_startPosY + 36),
                (int) (_startPosY + 36), (int) (_startPosY + 35)};
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);

        // Отрисовка двери
        g2d.setColor(Color.WHITE);
        g2d.fillRect((int) (_startPosX + 32), (int) (_startPosY + 3), 8, 28);
        g2d.setColor(Color.BLACK);
        g2d.drawRect((int) (_startPosX + 32), (int) (_startPosY + 3), 8, 28);


        //Отрисовка колёс
        g2d.setColor(Color.WHITE);
        g2d.fillOval((int) (_startPosX + 8), (int) (_startPosY + 35), 11, 11);
        g2d.fillOval((int) (_startPosX + 30), (int) (_startPosY + 35), 11, 11);
        g2d.fillOval((int) (_startPosX + 58), (int) (_startPosY + 35), 11, 11);
        g2d.fillOval((int) (_startPosX + 80), (int) (_startPosY + 35), 11, 11);
        g2d.setColor(Color.BLACK);
        g2d.drawOval((int) (_startPosX + 8), (int) (_startPosY + 35), 11, 11);
        g2d.drawOval((int) (_startPosX + 30), (int) (_startPosY + 35), 11, 11);
        g2d.drawOval((int) (_startPosX + 58), (int) (_startPosY + 35), 11, 11);
        g2d.drawOval((int) (_startPosX + 80), (int) (_startPosY + 35), 11, 11);

        //Отрисовка задней части кузова
        g2d.setColor(Color.BLACK);
        g2d.fillRect((int) (_startPosX + 97), (int) (_startPosY + 3), 5, 28);

        //Отрисовка окна
        g2d.setColor(Color.CYAN);
        g2d.fillRect((int) (_startPosX + 9), (int) (_startPosY + 3), 8, 11);

        g2d.setColor(Color.BLUE);
        g2d.drawRect((int) (_startPosX + 9), (int) (_startPosY + 3), 8, 11);
    }

    //Переопределение метода toString()
    @Override
    public String toString() {
        return getMaxSpeed() + separator + getWeight() + separator + getMainColor().getRGB();
    }

    //Методы сравнения объектов
    //Перегрузка метода от object
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Lokomotiv lokoObj)) {
            return false;
        }
        return equals(lokoObj);
    }

    //Метод сравнения для класса Lokomotiv
    public boolean equals(Lokomotiv other) {
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
        return getMainColor().getRGB() == other.getMainColor().getRGB();
    }

    //Переопределение метода интерфейса Comparable
    @Override
    public int compareTo(Lokomotiv o) {
        if(getMaxSpeed() != o.getMaxSpeed())
        {
            return Integer.compare(getMaxSpeed(), o.getMaxSpeed());
        }
        if(getWeight() != o.getWeight())
        {
            return Integer.compare(getWeight(), o.getWeight());
        }
        if(getMainColor().getRGB() != o.getMainColor().getRGB())
        {
            return Integer.compare(getMainColor().getRGB(), o.getMainColor().getRGB());
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
        if(current > 2){
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
        return null;
    }
}