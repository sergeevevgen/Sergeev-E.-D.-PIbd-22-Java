import java.awt.Color;
import java.awt.Graphics2D;

//Класс для отрисовки Треугольной (двери)
public class DopClass3 implements IInterDop{

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
            case 2:
                dopenum = DopEnum.Two;
                break;
            case 3:
                dopenum = DopEnum.Three;
                break;
            default:
                dopenum = DopEnum.One;
        }
    }
    //Метод (свойство) для получения поля ДопПеречисления
    private DopEnum getD() {
        return dopenum;
    }

    //Главный метод отрисовки двери/дверей
    public void DrawPart(Graphics2D g2d, int x, int y)
    {
        //Установка цвета
        g2d.setColor(getDopc());
        //Отрисовка дефолтной прямоугольной двери
        g2d.fillRect(x + 32, y + 3, 8, 28);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x + 32, y + 3, 8, 28);

        //Массивы координат для точек
        int[] pointsX = new int[]{ x + 54, x + 62, x + 46};
        int[] pointsY = new int[]{ y + 3, y + 28, y + 28};
        int[] pointsX1 = new int[]{ x + 76, x + 84, x + 68};
        int[] pointsY1 = new int[]{ y + 3, y + 28, y + 28};

        if (getD() == DopEnum.Two || getD() == DopEnum.Three) {
            g2d.setColor(getDopc());
            g2d.fillPolygon(pointsX, pointsY, pointsX.length);
            if(getD() == DopEnum.Three)
            {
                g2d.fillPolygon(pointsX1, pointsY1, pointsX1.length);
            }

            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(pointsX, pointsY, pointsX.length);
            if(getD() == DopEnum.Three)
            {
                g2d.drawPolygon(pointsX1, pointsY1, pointsX1.length);
            }
        }
    }
}