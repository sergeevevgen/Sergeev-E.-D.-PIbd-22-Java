import javax.swing.*;
import java.awt.*;

//Класс-наследник от JPanel для отрисовки выбранного Депо
public class CustomPictureBoxForDepoCollection extends JPanel {

    //Объект-хранилище депо
    private DepoCollection depoCollection;

    //Выбранный элемент
    private String selectedItem = null;

    //Метод установки коллекции-депо
    public void setDepoCollection(DepoCollection depoCollection)
    {
        this.depoCollection = depoCollection;
    }

    //Переопределение метода
    @Override
    protected void paintComponent(Graphics g)
    {
        if(selectedItem != null && depoCollection != null)
        {
            depoCollection.get(selectedItem).Draw(g);
        }
    }

    //Метод установки выделенного депо
    public void setSelectedItem(String selectedItem)
    {
        this.selectedItem = selectedItem;
    }
}
