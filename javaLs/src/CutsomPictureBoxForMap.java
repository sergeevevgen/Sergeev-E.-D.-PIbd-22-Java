import javax.swing.*;
import java.awt.*;

//Кастомный класс-наследник JPanel для собственной реализации PictureBox (как в с#)
public class CutsomPictureBoxForMap extends JPanel {
    //Объект от интерфейса
    private ITransport loko;

    //Переопределение метода отрисовки компонента внутри JPanel
    @Override
    protected void paintComponent(Graphics g)
    {
        if(loko != null)
            loko.DrawTransport(g);
    }

    //Метод установки объекта
    public void setLoko(ITransport loko)
    {
        this.loko = loko;
    }
}
