import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

//Класс-наследник от JFrame
public class FormDepo extends JFrame {

    //Объект от класса-депо
    private DepoCollection depoCollection;

    //Очередь для перемещенных объектов
    Queue<ITransport> queueTransports;

    public void Initialize()
    {
        //Задание характеристик фрэйму
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //Размеры фрэйма и его расположение на экране
        setBounds(100, 100, 900, 500);

        //Инициализация основной панели, на которой находится все содержимое
        JPanel contentPane = new JPanel();
        //Контент формы = контент с панели
        setContentPane(contentPane);
        //Установка метода расположения в панели
        contentPane.setLayout(null);

        //Панель для парковки
        panelPark = new CustomPictureBoxForDepoCollection();
        panelPark.setBounds(5,5,690,450);
        panelPark.setLayout(null);
        contentPane.add(panelPark);
        depoCollection = new DepoCollection(panelPark.getWidth(), panelPark.getHeight());
        panelPark.setDepoCollection(depoCollection);

        //Панель для элементов управления с правой стороны
        JPanel panelManage = new JPanel();
        panelManage.setBackground(Color.LIGHT_GRAY);
        panelManage.setBounds(690,5,190,450);
        panelManage.setLayout(null);
        contentPane.add(panelManage);

        //Создание кнопки "Создать Локомотив" и инициализация её свойств
        JButton btnParkLokomotiv = new JButton("Доб. локомотив");
        btnParkLokomotiv.setBounds(25, 280, 150, 25);
        btnParkLokomotiv.setMargin(new Insets(10, 10, 10, 10));
        panelManage.add(btnParkLokomotiv);

        //Создание кнопки "Создать Монорельс" и инициализация её свойств
        JButton btnParkMonoRels = new JButton("Доб. монорельс");
        btnParkMonoRels.setBounds(25, 310, 150, 25);
        btnParkMonoRels.setMargin(new Insets(10, 10, 10, 10));
        panelManage.add(btnParkMonoRels);

        //Панель как группбокс для ввода места, с которого будем забирать
        JPanel panelTake = new JPanel();
        panelTake.setBorder(BorderFactory.createTitledBorder("Забрать т/с"));
        panelTake.setBounds(25,340,150,100);
        panelTake.setLayout(null);
        panelManage.add(panelTake);

        //Label "Место"
        JLabel labelTake = new JLabel("Место:");
        labelTake.setBounds(25,40,50,20);
        panelTake.add(labelTake);

        //TextField для указания места, с которого забрать
        textFieldTake = new JTextField();
        textFieldTake.setBounds(80,40,45,20);
        textFieldTake.setColumns(5);
        panelTake.add(textFieldTake);

        //Кнопка "Забрать"
        JButton buttonTake = new JButton("Забрать");
        buttonTake.setMargin(new Insets(10, 10, 10, 10));
        buttonTake.setBounds(15,65,120,25);
        panelTake.add(buttonTake);

        //Выбор депо
        //ListBox
        //Надпись "Депо:"
        JLabel labelDepos = new JLabel("Депо:");
        labelDepos.setBounds(80,5,150,20);
        panelManage.add(labelDepos);

        //Текстовое поле для ввода названия депо
        textFieldDepos = new JTextField();
        textFieldDepos.setBounds(25,30,150,20);
        panelManage.add(textFieldDepos);

        //Кнопка "Добавить депо"
        JButton buttonAddDepo = new JButton("Добавить депо");
        buttonAddDepo.setBounds(25,55,150,25);
        panelManage.add(buttonAddDepo);

        //Кнопка "Удалить депо"
        JButton buttonDelDepo = new JButton("Удалить депо");
        buttonDelDepo.setBounds(25,200,150,25);
        panelManage.add(buttonDelDepo);

        //Модель данных для листБокса "JList"
        depoList = new DefaultListModel<>();
        //ListBox "JList"
        listBoxDepos = new JList<>(depoList);
        listBoxDepos.setBounds(25, 85,150,110);
        panelManage.add(listBoxDepos);

        //Очередь
        queueTransports = new LinkedList<>();
        //Кнопка "Забрать из очереди"
        JButton btnFromQ = new JButton("Взять из очереди");
        btnFromQ.setBounds(25,250,150,25);
        panelManage.add(btnFromQ);
        JLabel lblQ = new JLabel("0");
        lblQ.setBounds(95,230,75,20);
        panelManage.add(lblQ);
        /*
        * Методы работы кнопок и тд
        * */

        //Обработка нажатия на кнопку "Припарковать Локомотив"
        btnParkLokomotiv.addActionListener(e -> {
            if(listBoxDepos.getSelectedIndex() > -1)
            {
                Color newColor = JColorChooser.showDialog(null, "Выберите цвет",
                        Color.RED);
                if (newColor != null) {
                    var lokomotiv = new Lokomotiv(100, 1000, newColor);
                    if(depoCollection.get(listBoxDepos.getSelectedValue()).Add(lokomotiv) != -1)
                    {
                        repaint();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Парковка переполнена", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Стоянка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            repaint();
        });

        //Обработка нажатия на кнопку "Припарковать Монорельс"
        btnParkMonoRels.addActionListener((e) -> {
            if(listBoxDepos.getSelectedIndex() > -1)
            {
                Random r = new Random();
                Color newColor1 = JColorChooser.showDialog(null, "Выберите цвет",
                        Color.WHITE);
                if (newColor1 != null) {
                    Color newColor2 = JColorChooser.showDialog(null,"Выберите цвет", Color.WHITE);
                    if(newColor2 != null)
                    {
                        var monoRels = new MonoRels(100, 1000, newColor1, newColor2, r.nextBoolean(),
                                r.nextBoolean(),r.nextInt(3) + 1);
                        if (depoCollection.get(listBoxDepos.getSelectedValue()).Add(monoRels) != -1) {
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Парковка переполнена");
                        }
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Стоянка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            repaint();
        });

        //Обработка нажатия кнопки "Забрать"
        buttonTake.addActionListener((e) -> {
            if(listBoxDepos.getSelectedIndex() > -1 && tryParseInt(textFieldTake.getText()) != null)
            {
                int a = tryParseInt(textFieldTake.getText());
                var lokomotiv = depoCollection.get(listBoxDepos.getSelectedValue()).Sub(a);
                if(lokomotiv != null)
                {
                    queueTransports.add(lokomotiv);
                    lblQ.setText(Integer.toString(queueTransports.size()));
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Это место пусто!");
                }
            }
            else
            {
                if(listBoxDepos.getSelectedIndex() <= -1 && tryParseInt(textFieldTake.getText()) == null)
                {
                    JOptionPane.showMessageDialog(null, "Введите число и выберите парковку!");
                }
                else if (listBoxDepos.getSelectedIndex() > -1)
                    JOptionPane.showMessageDialog(null, "Введите число!");
                else if(tryParseInt(textFieldTake.getText()) != null)
                    JOptionPane.showMessageDialog(null, "Выберите парковку");
            }
            textFieldTake.setText("");
            repaint();
        });

        //Обработка нажатия на кнопку "Добавить депо"
        buttonAddDepo.addActionListener((e) ->
        {
            if(textFieldDepos.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Введите название депо!",
                        "Ошибка",JOptionPane.ERROR_MESSAGE);
                return;
            }
            depoCollection.AddDepo(textFieldDepos.getText());
            ReloadLevels();
        });

        //Обработка нажатия на кнопку "Удалить депо"
        buttonDelDepo.addActionListener((e) ->
        {
            if(listBoxDepos.getSelectedIndex() > -1)
            {
                int a = JOptionPane.showConfirmDialog(this, "Удалить депо <" +
                        listBoxDepos.getSelectedValue() + ">", "Удаление", JOptionPane.YES_NO_OPTION);
                if(a == 0)
                {
                    depoCollection.DelDepo(listBoxDepos.getSelectedValue());
                    ReloadLevels();
                }
            }
        });

        //Если поменялся выбранный элемент JList
        listBoxDepos.addListSelectionListener(e -> {
            panelPark.setSelectedItem(listBoxDepos.getSelectedValue());
            repaint();
        });

        //Обработка нажатия на кнопку "Забрать из очереди"
        btnFromQ.addActionListener(e -> {
            if(!queueTransports.isEmpty())
            {
                FormLokomotiv formLokomotiv = new FormLokomotiv();
                formLokomotiv.SetLokomotiv(queueTransports.remove());
                formLokomotiv.setVisible(true);
                lblQ.setText(Integer.toString(queueTransports.size()));
            }
        });
    }

    //Конструктор
    public FormDepo() {
        //Установка названия формы
        super("Депо");
        Initialize();
    }

    //проверка на int
    Integer tryParseInt(String s) {
        try {
            return new Integer(s);
        } catch (NumberFormatException e) {
            return null; // не-а, не int
        }
    }

    //Заполнение ListBox
    private void ReloadLevels()
    {
        int index = listBoxDepos.getSelectedIndex();
        depoList.removeAllElements();

        int i = 0;
       for(String name: depoCollection.Keys())
       {
           depoList.add(i, name);
           i++;
       }

        int count = depoList.size();
        if(count > 0 && (index < 0 || index >= count))
        {
            listBoxDepos.setSelectedIndex(0);
        }
        else if(index > -1 && index < count)
        {
            listBoxDepos.setSelectedIndex(index);
        }
        repaint();
    }

    /*
    Объекты управления
     */

    //ListBox
    private JList<String> listBoxDepos;

    //Для листБокса
    private DefaultListModel<String> depoList;

    //Панель для отрисовки Депо
    private CustomPictureBoxForDepoCollection panelPark;

    //TextField для указания места, с которого забрать
    private JTextField textFieldTake;

    //Текстовое поле для ввода названия депо
    private JTextField textFieldDepos;

}
