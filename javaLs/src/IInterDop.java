import java.awt.Color;
import java.awt.Graphics2D;

public interface IInterDop {
    //Метод отрисовки
    void DrawPart(Graphics2D g2d, int x, int y);

    //Метод установки значения в поле-перечисление
    void setD(int dopEnum);

    //Метод установки дополнительного цвета
    void setDopc(Color dopc);
}