import java.awt.Color;
import java.awt.Graphics2D;

//Класс для отрисовки дополнительного элемента (двери)
public class DopClass implements IInterDop{

    //Поле перечисление от ДопПеречисления
    private DopEnum dopenum;

    //Поле цвета объекта (двери/дверей)
    private Color Dopc;

    //Метод (свойство) для установки цвета
    public void setDopc(Color dopc)
    {
        Dopc = dopc;
    }

    //Метод (свойство) для получения цвета
    private Color getDopc()
    {
        return Dopc;
    }

    //Метод (свойство) для установки поля ДопПеречисления
    public void setD(int d)
    {
        switch (d) {
            case 2 -> dopenum = DopEnum.Two;
            case 3 -> dopenum = DopEnum.Three;
            default -> dopenum = DopEnum.One;
        }
    }
    //Метод (свойство) для получения поля ДопПеречисления
    private DopEnum getD() {
        return dopenum;
    }

    //Поле для выбора формы дверей
    private int realizationOfDoors;
    //Метод для установки realizationOfDoors
    public void setRealization(int realizationOfDoors) {
        if (realizationOfDoors > 0 && realizationOfDoors < 4) {
            this.realizationOfDoors = realizationOfDoors;
        }
        else {
            this.realizationOfDoors = 1;
        }
    }
    //Метод для получения realizationOfDoors
    private int getRealization() {
        return realizationOfDoors;
    }

    //Главный метод отрисовки двери/дверей
    public void DrawPart(Graphics2D g2d, int x, int y)
    {
        //Установка цвета
        g2d.setColor(getDopc());
        //Отрисовка дефолтной прямоугольной двери
        g2d.fillRect(x + 32, y + 3, 8, 28);

        //Если == 1, то рисуются прямоугольные двери, если == 2, то овальные, если другие числа, то треугольные
        if (getRealization() == 1) {
            if (getD() == DopEnum.Two) {
                g2d.setColor(getDopc());
                g2d.fillRect(x + 50, y + 3, 8, 28);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x + 50, y + 3, 8, 28);
            }

            if (getD() == DopEnum.Three) {
                g2d.setColor(getDopc());
                g2d.fillRect(x + 50, y + 3, 8, 28);
                g2d.fillRect(x + 68, y + 3, 8, 28);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x + 50, y + 3, 8, 28);
                g2d.drawRect(x + 68, y + 3, 8, 28);
            }
        }
        else if (getRealization() == 2) {
            if (getD() == DopEnum.Two) {
                g2d.setColor(this.getDopc());
                g2d.fillOval(x + 50, y + 3, 8, 28);
                g2d.setColor(Color.BLACK);
                g2d.drawOval(x + 50, y + 3, 8, 28);
            }

            if (getD() == DopEnum.Three) {
                g2d.setColor(getDopc());
                g2d.fillOval(x + 50, y + 3, 8, 28);
                g2d.fillOval(x + 68, y + 3, 8, 28);
                g2d.setColor(Color.BLACK);
                g2d.drawOval(x + 50, y + 3, 8, 28);
                g2d.drawOval(x + 68, y + 3, 8, 28);
            }
        }
        else {
            int[] pointsX = new int[]{ x + 54, x + 62, x + 46};
            int[] pointsY = new int[]{ y + 3, y + 28, y + 28};
            int[] pointsX1 = new int[]{ x + 76, x + 84, x + 68};
            int[] pointsY1 = new int[]{ y + 3, y + 28, y + 28};
            if (getD() == DopEnum.Two) {
                g2d.setColor(getDopc());
                g2d.fillPolygon(pointsX, pointsY, pointsX.length);
                g2d.setColor(Color.BLACK);
                g2d.drawPolygon(pointsX, pointsY, pointsX.length);
            }

            if (getD() == DopEnum.Three) {
                g2d.setColor(getDopc());
                g2d.fillPolygon(pointsX, pointsY, pointsX.length);
                g2d.fillPolygon(pointsX1, pointsY1, pointsX1.length);
                g2d.setColor(Color.BLACK);
                g2d.drawPolygon(pointsX, pointsY, pointsX.length);
                g2d.drawPolygon(pointsX1, pointsY1, pointsX1.length);
            }
        }
    }
}