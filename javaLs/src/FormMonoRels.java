
import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

public class FormMonoRels extends JFrame {

    //Панель
    private final JPanel contentPane;
    //Объект от класса MonoRels
    private MonoRels monor;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FormMonoRels frame = new FormMonoRels();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    //Конструктор
    public FormMonoRels() {
        //Установка названия формы
        super("Монорельс");
        //Установка закрытия при нажатии на красный крестик справа сверху
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //размеры формы и ее расположение на экране
        setBounds(100, 100, 900, 500);
        //запрет на изменение размеров
        setResizable(false);
        //Инициализация панели
        contentPane = new JPanel();
        //Установка границ панели
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        //Контент формы = контент с панели
        setContentPane(contentPane);
        //Установка метода расположения в панели
        contentPane.setLayout(null);


        //Создание кнопки "Создать Монорельс" и инициализация её свойств
        JButton btnCreate = new JButton("Создать Монорельс");
        btnCreate.setBounds(719, 11, 150, 23);
        btnCreate.setMargin(new Insets(10, 10, 10, 10));
        contentPane.add(btnCreate);

        //Создание кнопки "Вверх" и инициализация её свойств
        JButton btnUp = new JButton("");
        btnUp.setBounds(804, 379, 30, 30);
        btnUp.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1u.png"));
        contentPane.add(btnUp);

        //Создание кнопки "Влево" и инициализация её свойств
        JButton btnLeft = new JButton("");
        btnLeft.setBounds(764, 420, 30, 30);
        btnLeft.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1l.png"));
        contentPane.add(btnLeft);

        //Создание кнопки "Вниз" и инициализация её свойств
        JButton btnDown = new JButton("");
        btnDown.setBounds(804, 420, 30, 30);
        btnDown.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1d.png"));
        contentPane.add(btnDown);

        //Создание кнопки "Вправо" и инициализация её свойств
        JButton btnRight = new JButton("");
        btnRight.setBounds(844, 420, 30, 30);
        btnRight.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1r.png"));
        contentPane.add(btnRight);

        //Обработка нажатия на кнопку "Создать Монорельс"
        btnCreate.addActionListener(e -> {
            Random rnd = new Random();
            monor = new MonoRels();
            monor.Init(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.PINK, Color.RED, true, true);
            monor.SetPosition(rnd.nextInt(100) + 10, rnd.nextInt(100) + 40, contentPane.getWidth(), contentPane.getHeight());
            repaint();
        });

        //Обработка нажатия на кнопку "Вверх"
        btnUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                monor.MoveTransport(Direction.UP);
                repaint();
            }
        });

        //Обработка нажатия на кнопку "Влево"
        btnLeft.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                monor.MoveTransport(Direction.LEFT);
                repaint();
            }
        });

        //Обработка нажатия на кнопку "Вниз"
        btnDown.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                monor.MoveTransport(Direction.DOWN);
                repaint();
            }
        });

        //Обработка нажатия на кнопку "Вправо"
        btnRight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                monor.MoveTransport(Direction.RIGHT);
                repaint();
            }
        });
    }

    //Метод отрисовки
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if(monor != null)
            monor.DrawTransport(g);
    }

    @Override
    public void repaint()
    {
        super.repaint();
    }
}
