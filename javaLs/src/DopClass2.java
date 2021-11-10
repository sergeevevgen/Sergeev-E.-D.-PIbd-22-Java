import java.awt.Color;
import java.awt.Graphics2D;

//Класс для отрисовки Круглой (двери)
public class DopClass2 implements IInterDop
{

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

        if (getD() == DopEnum.Two || getD() == DopEnum.Three) {
            g2d.setColor(getDopc());
            g2d.fillOval(x + 50, y + 3, 8, 28);
            if(getD() == DopEnum.Three)
            {
                g2d.fillOval(x + 68, y + 3, 8, 28);
            }

            g2d.setColor(Color.BLACK);
            g2d.drawOval(x + 50, y + 3, 8, 28);
            if(getD() == DopEnum.Three)
            {
                g2d.drawOval(x + 68, y + 3, 8, 28);
            }
        }
    }
}
