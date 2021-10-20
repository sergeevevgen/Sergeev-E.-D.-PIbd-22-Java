import java.awt.Color;
import java.awt.Graphics2D;

//Класс для отрисовки дополнительного элемента (двери)
public class DopClass{

    //Поле перечисление от ДопПеречисления
    private DopEnum dopenum;

    //Поле цвета объекта (двери/дверей)
    private Color Dopc;

    //Метод (свойство) для установки цвета
    public void SetDopc(Color dopc)
    {
        Dopc = dopc;
    }

    //Метод (свойство) для получения цвета
    private Color GetDopc()
    {
        return Dopc;
    }

    //Метод (свойство) для установки поля ДопПеречисления
    public void SetD(int d)
    {
        switch (d) {
            case 2 -> dopenum = DopEnum.Two;
            case 3 -> dopenum = DopEnum.Three;
            default -> dopenum = DopEnum.One;
        }
    }

    //Метод (свойство) для получения поля ДопПеречисления
    private DopEnum getD() {
        return this.dopenum;
    }


    //Главный метод отрисовки двери/дверей
    public void DrawPart(Graphics2D g2d, int x, int y)
    {
        //В зависимости от значения рисуется одна, две, три двери
        g2d.setColor(GetDopc());
        g2d.fillRect(x + 33, y + 4, 7, 27);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(x + 32, y + 3, 8, 28);

        if (dopenum == DopEnum.Two)
        {
            g2d.setColor(GetDopc());
            g2d.fillRect(x + 51, y + 4, 7, 27);

            g2d.setColor(Color.BLACK);
            g2d.drawRect(x + 50, y + 3, 8, 28);
        }
        if (dopenum == DopEnum.Three)
        {
            g2d.setColor(GetDopc());
            g2d.fillRect(x + 51, y + 4, 7, 27);
            g2d.fillRect(x + 69, y + 4, 7, 27);

            g2d.setColor(Color.BLACK);
            g2d.drawRect(x + 50, y + 3, 8, 28);
            g2d.drawRect(x + 68, y + 3, 8, 28);
        }
    }
}