import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Класс-наследник для реализации Drag & Drop
public class DragMouseAdapter extends MouseAdapter {
    //Метод при зажатой мыши
    public void mousePressed(MouseEvent ev)
    {
        JComponent comp = (JComponent) ev.getSource();
        TransferHandler handler = comp.getTransferHandler();
        handler.exportAsDrag(comp,ev,TransferHandler.COPY);
    }
}
