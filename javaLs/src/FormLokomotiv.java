import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Класс-наследник от JFrame
public class FormLokomotiv extends JFrame {

    //Объект от класса Random
    Random rnd = new Random();

    //Панель
    private final JPanel contentPane;

    //Панель-карты от Кастомного PictureBox
    private final CutsomPictureBoxForMap panelMap;

    //Объект от Интерфейса
    private ITransport lokomotiv;

    //Конструктор
    public FormLokomotiv() {
        //Установка названия формы
        super("Монорельс/Локомотив");
        //Установка закрытия при нажатии на красный крестик справа сверху
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        //Размеры формы и ее расположение на экране
        setBounds(100, 100, 900, 500);
        //Инициализация панели
        contentPane = new JPanel();
        //Контент формы = контент с панели
        setContentPane(contentPane);
        //Установка метода расположения в панели
        contentPane.setLayout(null);

        //Инициализация панели-карты
        panelMap = new CutsomPictureBoxForMap();
        panelMap.setBackground(Color.GRAY);
        panelMap.setBounds(0,0,700,461);
        contentPane.add(panelMap);

        //Создание кнопки "Создать Локомотив" и инициализация её свойств
        JButton btnCreateLokomotiv = new JButton("Создать локомотив");
        btnCreateLokomotiv.setBounds(719, 11, 150, 23);
        btnCreateLokomotiv.setMargin(new Insets(10, 10, 10, 10));
        contentPane.add(btnCreateLokomotiv);

        //Создание кнопки "Создать Монорельс" и инициализация её свойств
        JButton btnCreateMonoRels = new JButton("Создать монорельс");
        btnCreateMonoRels.setBounds(719, 54, 150, 23);
        btnCreateMonoRels.setMargin(new Insets(10, 10, 10, 10));
        contentPane.add(btnCreateMonoRels);

        //Создание кнопки "Вверх" и инициализация её свойств
        JButton btnUp = new JButton("");
        btnUp.setBounds(804, 379, 30, 30);
        btnUp.setIcon(new ImageIcon("images\\1u.png"));
        contentPane.add(btnUp);

        //Создание кнопки "Влево" и инициализация её свойств
        JButton btnLeft = new JButton("");
        btnLeft.setBounds(764, 420, 30, 30);
        btnLeft.setIcon(new ImageIcon("images\\1l.png"));
        contentPane.add(btnLeft);

        //Создание кнопки "Вниз" и инициализация её свойств
        JButton btnDown = new JButton("");
        btnDown.setBounds(804, 420, 30, 30);
        btnDown.setIcon(new ImageIcon("images\\1d.png"));
        contentPane.add(btnDown);

        //Создание кнопки "Вправо" и инициализация её свойств
        JButton btnRight = new JButton("");
        btnRight.setBounds(844, 420, 30, 30);
        btnRight.setIcon(new ImageIcon("images\\1r.png"));
        contentPane.add(btnRight);

        //Обработка нажатия на кнопку "Создать Локомотив"
        btnCreateLokomotiv.addActionListener(e -> {
            lokomotiv = new Lokomotiv(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.red);
            lokomotiv.SetPosition(rnd.nextInt(100) + 11, rnd.nextInt(100) + 40, contentPane.getWidth(), contentPane.getHeight());
            panelMap.setLoko(lokomotiv);
            repaint();
        });

        //Обработка нажатия на кнопку "Создать Монорельс"
        btnCreateMonoRels.addActionListener((e) -> {
            lokomotiv = new MonoRels(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.PINK, Color.red, true, true, rnd.nextInt(3) + 1);
            lokomotiv.SetPosition(rnd.nextInt(100) + 10, rnd.nextInt(100) + 40, contentPane.getWidth(), contentPane.getHeight());
            panelMap.setLoko(lokomotiv);
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

    //Метод передачи локомотива/монорельса на фрэйм
    public void SetLokomotiv(ITransport lokomotiv)
    {
        this.lokomotiv = lokomotiv;
        this.lokomotiv.SetPosition(rnd.nextInt(100) + 10, rnd.nextInt(100) + 40, panelMap.getWidth(), panelMap.getHeight());
        panelMap.setLoko(lokomotiv);
        repaint();
    }

    //Переопределение метода repaint
    @Override
    public void repaint()
    {
        super.repaint();
    }
}
