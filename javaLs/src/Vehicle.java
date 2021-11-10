import java.awt.Color;
import java.awt.Graphics;

//Абстрактный класс-наследник от интерфейса
public abstract class Vehicle implements ITransport {

    //Позиция по оси Х
    protected float _startPosX;
    //Позиция по оси Y
    protected float _startPosY;
    //Ширина окна отрисовки
    protected int _pictureWidth;
    //Высота окна отрисовки
    protected int _pictureHeight;

    //Максимальная скорость транспорта
    private int MaxSpeed;
    //Метод для инициализации максимальной скорости транспорта
    protected void setMaxSpeed(int maxSpeed) {
        this.MaxSpeed = maxSpeed;
    }
    //Метод для получения максимальной скорости транспорта
    public int getMaxSpeed() {
        return this.MaxSpeed;
    }

    //Масса монорельса
    private int Weight;
    //Метод для инициализации массы транспорта
    protected void setWeight(int weight) {
        this.Weight = weight;
    }
    //Метод для получения массы транспорта
    public int getWeight() {
        return this.Weight;
    }

    //Основной цвет монорельса
    private Color MainColor;
    //Метод для инициализации основного цвета транспорта
    protected void setMainColor(Color mainColor) {
        this.MainColor = mainColor;
    }
    //Метод для получения основного цвета транспорта
    public Color getMainColor() {
        return this.MainColor;
    }

    //Метод установки начальной позиции транспорта
    public void SetPosition(int x, int y, int width, int height) {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    // Метод отрисовки транспорта
    public abstract void DrawTransport(Graphics g);

    //Метод для передвижения транспорта
    public abstract void MoveTransport(Direction direction);
}