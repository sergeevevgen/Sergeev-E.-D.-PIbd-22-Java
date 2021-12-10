import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        setBounds(100, 100, 900, 530);

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

        //Меню для ДепоКоллекции
        JMenu menu = new JMenu("Депо-коллекция");
        //Пункт меню "Сохранить коллекцию"
        JMenuItem saveDC = new JMenuItem("Сохранить коллекцию");
        //Пункт меню "Загрузить коллекцию"
        JMenuItem loadDC = new JMenuItem("Загрузить коллекцию");
        menu.add(saveDC);
        menu.addSeparator();
        menu.add(loadDC);

        //Меню для Депо
        JMenu menuDepo = new JMenu("Депо");
        //Пункт меню "Сохранить депо"
        JMenuItem saveDep = new JMenuItem("Сохранить депо");
        //Пункт меню "Загрузить депо"
        JMenuItem loadDep = new JMenuItem("Загрузить депо");
        menuDepo.add(saveDep);
        menuDepo.addSeparator();
        menuDepo.add(loadDep);

        //Полоса меню
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(new LineBorder(Color.BLACK));
        setJMenuBar(menuBar);
        menuBar.add(menu);
        menuBar.add(menuDepo);

        /*
        * Методы работы кнопок и тд
        * */

        //Обработка нажатия на кнопку "Припарковать Локомотив"
        btnParkLokomotiv.addActionListener(e -> {
            if(listBoxDepos.getSelectedIndex() > -1) {
                FormLokoConfig formLokoConfig = new FormLokoConfig(this);
                formLokoConfig.setVisible(true);
            }
            else
                JOptionPane.showMessageDialog(this,"Выберите депо!",
                        "Ошибка",JOptionPane.ERROR_MESSAGE);
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
               if(JOptionPane.showConfirmDialog(this, "Удалить депо <" +
                    listBoxDepos.getSelectedValue() + ">", "Удаление", JOptionPane.YES_NO_OPTION) == 0)
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

        //Обработка Сохранения депо-коллекции
        saveDC.addActionListener(e ->{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
            if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                String filename = fileChooser.getSelectedFile().getPath();
                if(!filename.contains(".txt"))
                    filename += ".txt";
                if(depoCollection.SaveData(filename))
                {
                    JOptionPane.showMessageDialog(this, "Сохранение прошло успешно",
                            "Результат", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Не сохранилось",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Обработка загрузки депо-коллекции
        loadDC.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
            if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                if(depoCollection.LoadData(fileChooser.getSelectedFile().getPath()))
                {
                    JOptionPane.showMessageDialog(this, "Файл загружен",
                            "Результат", JOptionPane.INFORMATION_MESSAGE);
                    ReloadLevels();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Файл не загружен",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Обработка загрузки депо
        loadDep.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
            if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                if(depoCollection.LoadDepo(fileChooser.getSelectedFile().getPath()))
                {
                    JOptionPane.showMessageDialog(this, "Файл загружен",
                            "Результат", JOptionPane.INFORMATION_MESSAGE);
                    ReloadLevels();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Файл не загружен",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Обработка сохранения депо
        saveDep.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
            if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                String filename = fileChooser.getSelectedFile().getPath();
                if(!filename.contains(".txt"))
                    filename += ".txt";
                if(depoCollection.SaveDepo(filename, listBoxDepos.getSelectedValue()))
                {
                    JOptionPane.showMessageDialog(this, "Сохранение прошло успешно",
                            "Результат", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Не сохранилось",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    //Конструктор
    public FormDepo() {
        //Установка названия формы
        super("Депо");
        Initialize();
    }

    //Метод добавления объекта
    public void addVehicle(Vehicle loko)
    {
        if(loko != null && listBoxDepos.getSelectedIndex() > -1)
        {
            if(depoCollection.get(listBoxDepos.getSelectedValue()).Add(loko) != -1)
            {
                repaint();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Локомотив/Монорельс не удалось поставить(",
                        "Ошибка",JOptionPane.ERROR_MESSAGE);
            }
        }
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
