import java.awt.*;

//Класс-программа (запуск приложения)
public class Programm {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FormDepo frame = new FormDepo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
