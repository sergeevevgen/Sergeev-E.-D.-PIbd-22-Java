import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

//Наследник от абстрактного класса Vehicle
public class Lokomotiv extends Vehicle {

    Random r = new Random();
    //Ширина локомотива
    private int lokomotivWidth = 105;

    //Высота локомотива
    private int lokomotivHeight = 50;

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
}