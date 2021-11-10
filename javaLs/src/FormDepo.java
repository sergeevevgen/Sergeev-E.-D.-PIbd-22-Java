import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

//Класс-наследник от JFrame
public class FormDepo extends JFrame {

    //Объект от класса-депо
    private final Depo<Lokomotiv, IInterDop> depo;

    //Конструктор
    public FormDepo() {
        //Установка названия формы
        super("Депо");
        //Установка закрытия при нажатии на красный крестик справа сверху
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //Размеры формы и ее расположение на экране
        setBounds(100, 100, 900, 500);

        //Инициализация панели
        //Основная панель
        JPanel contentPane = new JPanel();
        //Установка границ панели
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        contentPane.setBounds(11,36,889,464);
        //Контент формы = контент с панели
        setContentPane(contentPane);
        //Установка метода расположения в панели
        contentPane.setLayout(null);

        //Панель для парковки
        JPanel panelPark = new JPanel();
        panelPark.setBackground(Color.CYAN);
        panelPark.setBounds(5,5,690,450);
        panelPark.setLayout(null);
        contentPane.add(panelPark);

        //Панель для элементов управления справа
        JPanel panelManage = new JPanel();
        panelManage.setBackground(Color.GRAY);
        panelManage.setBounds(690,5,190,450);
        panelManage.setLayout(null);
        contentPane.add(panelManage);

        //Создание кнопки "Создать Локомотив" и инициализация её свойств
        JButton btnParkLokomotiv = new JButton("Доб. локомотив");
        btnParkLokomotiv.setBounds(25, 5, 150, 70);
        btnParkLokomotiv.setMargin(new Insets(10, 10, 10, 10));
        panelManage.add(btnParkLokomotiv);

        //Создание кнопки "Создать Монорельс" и инициализация её свойств
        JButton btnParkMonoRels = new JButton("Доб. монорельс");
        btnParkMonoRels.setBounds(25, 80, 150, 70);
        btnParkMonoRels.setMargin(new Insets(10, 10, 10, 10));
        panelManage.add(btnParkMonoRels);

        //Панель как группбокс
        JPanel panelTake = new JPanel();
        panelTake.setBorder(BorderFactory.createTitledBorder("Забрать т/с"));
        panelTake.setBounds(25,155,150,100);
        panelTake.setLayout(null);
        panelManage.add(panelTake);

        //Label "Место"
        JLabel labelTake = new JLabel("Место:");
        labelTake.setBounds(25,40,50,20);
        panelTake.add(labelTake);

        //TextField для указания места, с которого забрать
        JTextField textFieldTake = new JTextField();
        textFieldTake.setBounds(80,40,45,20);
        textFieldTake.setColumns(5);
        panelTake.add(textFieldTake);

        //Кнопка "Забрать"
        JButton buttonTake = new JButton("Забрать");
        buttonTake.setMargin(new Insets(10, 10, 10, 10));
        buttonTake.setBounds(15,65,120,25);
        panelTake.add(buttonTake);

        //Инициализация "Депо"
        depo = new Depo<>(panelPark.getWidth(), panelPark.getHeight());

        //Обработка нажатия на кнопку "Припарковать Локомотив"
        btnParkLokomotiv.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Выберите цвет",
                    Color.WHITE);
            if(newColor != null)
            {
                var lokomotiv = new Lokomotiv(100,1000, newColor);
                if(depo.Add(lokomotiv) != -1)
                {
                    repaint();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Парковка переполнена");
                }
            }
        });

        //Обработка нажатия на кнопку "Припарковать Монорельс"
        btnParkMonoRels.addActionListener((e) -> {
            Random r = new Random();
            Color newColor1 = JColorChooser.showDialog(null, "Выберите цвет",
                    Color.WHITE);
            if(newColor1 != null)
            {
                Color newColor2 = JColorChooser.showDialog(null,"Выберите цвет", Color.WHITE);
                if(newColor2 != null)
                {
                    var lokomotiv = new MonoRels(100, 1000, newColor1, newColor2, true,
                            true,r.nextInt(3) + 1);
                    if (depo.Add(lokomotiv) != -1) {
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Парковка переполнена");
                    }
                }
            }
        });

        //Обработка нажатия кнопки "Забрать"
        buttonTake.addActionListener((e) -> {
            if(textFieldTake.getText() != null)
            {
                if (tryParseInt(textFieldTake.getText()) != null)
                {
                    int a = tryParseInt(textFieldTake.getText());
                    var lokomotiv = depo.Sub(a);
                    if(lokomotiv != null)
                    {
                        FormLokomotiv formLokomotiv = new FormLokomotiv();
                        formLokomotiv.SetLokomotiv(lokomotiv);
                        formLokomotiv.setVisible(true);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Это место пусто!");
                    }
                }
                else
                {
                    textFieldTake.setText("");
                    JOptionPane.showMessageDialog(null, "Введите число!");
                }
                repaint();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Введите число!");
            }
        });
    }

    //Переопределение метода paint
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        depo.Draw(g);
    }


    //Переопределение метода repaint
    @Override
    public void repaint()
    {
        super.repaint();
    }


    //проверка на int
    Integer tryParseInt(String s) {
        try {
            return new Integer(s);
        } catch (NumberFormatException e) {
            return null; // не-а, не int
        }
    }
}
