
import java.awt.EventQueue;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

public class FormLokomotiv extends JFrame {

    //Панель
    private final JPanel contentPane;

    //Объект от Интерфейса
    private ITransport lokomotiv;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FormLokomotiv frame = new FormLokomotiv();
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
    public FormLokomotiv() {
        //Установка названия формы
        super("Монорельс/Локомотив");
        //Установка закрытия при нажатии на красный крестик справа сверху
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Размеры формы и ее расположение на экране
        setBounds(100, 100, 900, 500);
        //Инициализация панели
        contentPane = new JPanel();
        //Установка границ панели
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        //Контент формы = контент с панели
        setContentPane(contentPane);
        //Установка метода расположения в панели
        contentPane.setLayout(null);


        //Создание кнопки "Создать Локомотив" и инициализация её свойств
        JButton btnCreateLokomotiv = new JButton("Создать локомотив");
        btnCreateLokomotiv.setBounds(719, 11, 150, 23);
        btnCreateLokomotiv.setMargin(new Insets(10, 10, 10, 10));
        contentPane.add(btnCreateLokomotiv);

        //Создание кнопки "Создать Монорельс" и инициализация её свойств
        JButton btnCreateMonoRels = new JButton("Создать монорельс");
        btnCreateMonoRels.setBounds(719, 54, 150, 23);
        btnCreateMonoRels.setMargin(new Insets(10, 10, 10, 10));
        this.contentPane.add(btnCreateMonoRels);

        //Создание кнопки "Вверх" и инициализация её свойств
        JButton btnUp = new JButton("");
        btnUp.setBounds(804, 379, 30, 30);
        btnUp.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\Up.jpg"));
        contentPane.add(btnUp);

        //Создание кнопки "Влево" и инициализация её свойств
        JButton btnLeft = new JButton("");
        btnLeft.setBounds(764, 420, 30, 30);
        btnLeft.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\Left.jpg"));
        contentPane.add(btnLeft);

        //Создание кнопки "Вниз" и инициализация её свойств
        JButton btnDown = new JButton("");
        btnDown.setBounds(804, 420, 30, 30);
        btnDown.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\Down.jpg"));
        contentPane.add(btnDown);

        //Создание кнопки "Вправо" и инициализация её свойств
        JButton btnRight = new JButton("");
        btnRight.setBounds(844, 420, 30, 30);
        btnRight.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\Right.jpg"));
        contentPane.add(btnRight);

        //Обработка нажатия на кнопку "Создать Локомотив"
        btnCreateLokomotiv.addActionListener(e -> {
            Random rnd = new Random();
            lokomotiv = new Lokomotiv(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.red);
            lokomotiv.SetPosition(rnd.nextInt(100) + 11, rnd.nextInt(100) + 40, contentPane.getWidth(), contentPane.getHeight());
            repaint();
        });

        //Обработка нажатия на кнопку "Создать Монорельс"
        btnCreateMonoRels.addActionListener((e) -> {
            Random rnd = new Random();
            lokomotiv = new MonoRels(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.PINK, Color.red, true, true, rnd.nextInt(3) + 1);
            lokomotiv.SetPosition(rnd.nextInt(100) + 10, rnd.nextInt(100) + 40, contentPane.getWidth(), contentPane.getHeight());
            repaint();
        });

        //Обработка нажатия на кнопку "Вверх"
        btnUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(lokomotiv != null) {
                    lokomotiv.MoveTransport(Direction.UP);
                    repaint();
                }
            }
        });

        //Обработка нажатия на кнопку "Влево"
        btnLeft.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(lokomotiv != null) {
                    lokomotiv.MoveTransport(Direction.LEFT);
                    repaint();
                }
            }
        });

        //Обработка нажатия на кнопку "Вниз"
        btnDown.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(lokomotiv != null) {
                    lokomotiv.MoveTransport(Direction.DOWN);
                    repaint();
                }
            }
        });

        //Обработка нажатия на кнопку "Вправо"
        btnRight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(lokomotiv != null) {
                    lokomotiv.MoveTransport(Direction.RIGHT);
                    repaint();
                }
            }
        });
    }

    //Переопределение метода paint
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if(lokomotiv != null)
            lokomotiv.DrawTransport(g);
    }

    //Переопределение метода repaint
    @Override
    public void repaint()
    {
        super.repaint();
    }
}
