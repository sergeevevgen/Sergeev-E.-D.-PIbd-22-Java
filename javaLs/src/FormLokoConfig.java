import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Objects;
import java.beans.PropertyChangeListener;

//Класс-наследник от JFrame (Окно выбора объекта, его характеристик)
public class FormLokoConfig extends JFrame {

    //Поле-выбранный локомотив
    Vehicle lokomotiv = null;

    public void Initialize()
    {
        //Задание характеристик фрэйму
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        //Размеры фрэйма и его расположение на экране
        setBounds(100, 100, 700, 300);

        //Инициализация основной панели, на которой находится все содержимое
        JPanel contentPane = new JPanel();
        contentPane.setBounds(0,0,760,300);
        //Контент формы = контент с панели
        setContentPane(contentPane);
        //Установка метода расположения в панели
        contentPane.setLayout(null);

        //Инициализация панели с параметрами
        //Групп-бокс для выбора параметров
        JPanel groupBoxParams = new JPanel();
        groupBoxParams.setBounds(12,140,380,110);
        groupBoxParams.setBorder(new TitledBorder("Параметры"));
        groupBoxParams.setLayout(null);
        contentPane.add(groupBoxParams);

        //Надпись "Макс.скорость"
        JLabel labelMaxSpeed = new JLabel("Макс. скорость:");
        labelMaxSpeed.setBounds(20,22,100,15);
        groupBoxParams.add(labelMaxSpeed);

        //Надпись "Масса"
        JLabel labelWeight = new JLabel("Масса:");
        labelWeight.setBounds(20,62,50,15);
        groupBoxParams.add(labelWeight);

        //JSpinner скорости
        spinnerMaxSpeed = new JSpinner(new SpinnerNumberModel(500,100,1000, 10));
        spinnerMaxSpeed.setBounds(65,37,45,25);
        groupBoxParams.add(spinnerMaxSpeed);

        //JSpinner массы
        spinnerWeight = new JSpinner(new SpinnerNumberModel(500,100,1000, 10));
        spinnerWeight.setBounds(65,77,45,25);
        groupBoxParams.add(spinnerWeight);

        //Надпись Кол-во окон
        JLabel labelWins = new JLabel("Кол-во окон:");
        labelWins.setBounds(130,22,100,15);
        groupBoxParams.add(labelWins);

        //Надпись Кол-во дверей
        JLabel labelDoors = new JLabel("Кол-во дверей:");
        labelDoors.setBounds(130,62,100,15);
        groupBoxParams.add(labelDoors);

        //JSpinner окон
        spinnerWins = new JSpinner(new SpinnerNumberModel(2,1,3,1));
        spinnerWins.setBounds(175,37,45,25);
        groupBoxParams.add(spinnerWins);

        //JSpinner дверей
        spinnerDoors = new JSpinner(new SpinnerNumberModel(2,1,3,1));
        spinnerDoors.setBounds(175,77,45,25);
        groupBoxParams.add(spinnerDoors);

        //Чекбокс "Фары"
        checkBoxLamp = new JCheckBox("Фара");
        checkBoxLamp.setBounds(240,37,100,25);
        groupBoxParams.add(checkBoxLamp);

        //Чекбокс "Воздухозаборники"
        checkBoxAirCooler = new JCheckBox("Воздухозаборники");
        checkBoxAirCooler.setBounds(240,77,135,25);
        groupBoxParams.add(checkBoxAirCooler);

        //Инициализация панели с типами объекта
        //Панель выбора типа объекта
        JPanel groupBoxVariantOfObj = new JPanel();
        groupBoxVariantOfObj.setLayout(null);
        groupBoxVariantOfObj.setBorder(new TitledBorder("Тип объекта"));
        groupBoxVariantOfObj.setBounds(12,12,180,100);
        contentPane.add(groupBoxVariantOfObj);

        //Надпись "Локомотив"
        JLabel labelLoko = new JLabel("Локомотив", SwingConstants.CENTER);
        labelLoko.setBounds(20,20,130,25);
        labelLoko.setBorder(new LineBorder(Color.BLACK));
        labelLoko.addMouseListener(new DragMouseAdapter());
        labelLoko.setTransferHandler(new TransferHandler("text"));
        groupBoxVariantOfObj.add(labelLoko);

        //Надпись "Монорельс"
        JLabel labelMono = new JLabel("Монорельс", SwingConstants.CENTER);
        labelMono.setBounds(20,60,130,25);
        labelMono.setBorder(new LineBorder(Color.BLACK));
        labelMono.addMouseListener(new DragMouseAdapter());
        labelMono.setTransferHandler(new TransferHandler("text"));
        groupBoxVariantOfObj.add(labelMono);

        //Надпись для передачи типа объекта
        JLabel labelType = new JLabel();
        labelType.setBounds(202,12,175,120);
        labelType.setTransferHandler(new TransferHandler("text"));
        contentPane.add(labelType);

        //Панель отрисовки объекта
        panelShow = new CutsomPictureBoxForMap();
        panelShow.setBounds(202,12,175,120);
        panelShow.setBorder(new LineBorder(Color.BLACK));
        panelShow.setTransferHandler(new TransferHandler("text"));
        contentPane.add(panelShow);

        //Группбокс "Цвета"
        JPanel groupBoxColors = new JPanel();
        groupBoxColors.setLayout(null);
        groupBoxColors.setBorder(new TitledBorder("Цвета"));
        groupBoxColors.setBounds(390,0,280,160);
        contentPane.add(groupBoxColors);

        //JLabel "Основной цвет"
        labelMainColor = new JLabel("Основной цвет", SwingConstants.CENTER);
        labelMainColor.setBounds(30,20,100,25);
        labelMainColor.setBorder(new LineBorder(Color.BLACK));
        labelMainColor.setTransferHandler(new TransferHandler("background"));
        groupBoxColors.add(labelMainColor);

        //JLabel "Доп. цвет"
        labelDopColor= new JLabel("Доп. цвет", SwingConstants.CENTER);
        labelDopColor.setBounds(150,20,100,25);
        labelDopColor.setBorder(new LineBorder(Color.BLACK));
        labelDopColor.setTransferHandler(new TransferHandler("background"));
        groupBoxColors.add(labelDopColor);

        //Панели с цветами
        //Красная
        JPanel panelRed = new JPanel();
        panelRed.setBorder(new LineBorder(Color.BLACK));
        panelRed.setBounds(30,60,40,40);
        panelRed.setBackground(Color.RED);
        panelRed.addMouseListener(new DragMouseAdapter());
        panelRed.setTransferHandler(new TransferHandler("background"));
        groupBoxColors.add(panelRed);

        //Желтая
        JPanel panelYellow = new JPanel();
        panelYellow.setBorder(new LineBorder(Color.BLACK));
        panelYellow.setBounds(90,60,40,40);
        panelYellow.setBackground(Color.YELLOW);
        panelYellow.addMouseListener(new DragMouseAdapter());
        panelYellow.setTransferHandler(new TransferHandler("background"));
        groupBoxColors.add(panelYellow);

        //Черная
        JPanel panelBlack = new JPanel();
        panelBlack.setBorder(new LineBorder(Color.BLACK));
        panelBlack.setBounds(150,60,40,40);
        panelBlack.setBackground(Color.BLACK);
        panelBlack.addMouseListener(new DragMouseAdapter());
        panelBlack.setTransferHandler(new TransferHandler("background"));
        groupBoxColors.add(panelBlack);

        //Белая
        JPanel panelWhite = new JPanel();
        panelWhite.setBorder(new LineBorder(Color.BLACK));
        panelWhite.setBounds(210,60,40,40);
        panelWhite.setBackground(Color.WHITE);
        panelWhite.addMouseListener(new DragMouseAdapter());
        panelWhite.setTransferHandler(new TransferHandler("background"));
        groupBoxColors.add(panelWhite);

        //Серая
        JPanel panelGray = new JPanel();
        panelGray.setBorder(new LineBorder(Color.BLACK));
        panelGray.setBounds(30,110,40,40);
        panelGray.setBackground(Color.GRAY);
        panelGray.addMouseListener(new DragMouseAdapter());
        panelGray.setTransferHandler(new TransferHandler("background"));
        groupBoxColors.add(panelGray);

        //Оранжевая
        JPanel panelOrange = new JPanel();
        panelOrange.setBorder(new LineBorder(Color.BLACK));
        panelOrange.setBounds(90,110,40,40);
        panelOrange.setBackground(Color.ORANGE);
        panelOrange.addMouseListener(new DragMouseAdapter());
        panelOrange.setTransferHandler(new TransferHandler("background"));
        groupBoxColors.add(panelOrange);

        //Зелёная
        JPanel panelGreen = new JPanel();
        panelGreen.setBorder(new LineBorder(Color.BLACK));
        panelGreen.setBounds(150,110,40,40);
        panelGreen.setBackground(Color.GREEN);
        panelGreen.addMouseListener(new DragMouseAdapter());
        panelGreen.setTransferHandler(new TransferHandler("background"));
        groupBoxColors.add(panelGreen);

        //Синяя
        JPanel panelBlue = new JPanel();
        panelBlue.setBorder(new LineBorder(Color.BLACK));
        panelBlue.setBounds(210,110,40,40);
        panelBlue.setBackground(Color.BLUE);
        panelBlue.addMouseListener(new DragMouseAdapter());
        panelBlue.setTransferHandler(new TransferHandler("background"));
        groupBoxColors.add(panelBlue);

        //Кнопка "Добавить"
        buttonAdd = new JButton("Добавить");
        buttonAdd.setBounds(579,190,90,25);
        contentPane.add(buttonAdd);

        //Кнопка "Отмена"
        JButton buttonCancel = new JButton("Отмена");
        buttonCancel.setBounds(579,220,90,25);
        contentPane.add(buttonCancel);

        //Панель выбора типа двери
        JPanel groupBoxType = new JPanel();
        groupBoxType.setLayout(null);
        groupBoxType.setBorder(new TitledBorder("Тип двери"));
        groupBoxType.setBounds(390,157,150,100);
        contentPane.add(groupBoxType);

        //Надпись "Тип"
        JLabel labelTypeDoor = new JLabel("Тип", SwingConstants.CENTER);
        labelTypeDoor.setBorder(new LineBorder(Color.BLACK));
        labelTypeDoor.setBounds(20,75,60,20);
        labelTypeDoor.setTransferHandler(new TransferHandler("text"));
        groupBoxType.add(labelTypeDoor);

        //Надпись прямоугольной двери
        JLabel labelSq = new JLabel("Прямоугольная");
        labelSq.setBounds(5,17,100,15);
        labelSq.setBorder(new LineBorder(Color.BLACK));
        labelSq.addMouseListener(new DragMouseAdapter());
        labelSq.setTransferHandler(new TransferHandler("text"));
        groupBoxType.add(labelSq);

        //Надпись круглой двери
        JLabel labelCl = new JLabel("Круглая");
        labelCl.setBounds(5,37,100,15);
        labelCl.setBorder(new LineBorder(Color.BLACK));
        labelCl.addMouseListener(new DragMouseAdapter());
        labelCl.setTransferHandler(new TransferHandler("text"));
        groupBoxType.add(labelCl);

        //Надпись треугольной двери
        JLabel labelTr = new JLabel("Треугольная");
        labelTr.setBounds(5,57,100,15);
        labelTr.setBorder(new LineBorder(Color.BLACK));
        labelTr.addMouseListener(new DragMouseAdapter());
        labelTr.setTransferHandler(new TransferHandler("text"));
        groupBoxType.add(labelTr);

        /*
        * Реализация Drag & Drop
        */

        //Слушатель типа двери
        PropertyChangeListener doorTypeListener = propertyChangeEvent -> {
            if(lokomotiv != null && lokomotiv instanceof MonoRels m)
            {
                if(Objects.equals(labelTypeDoor.getText(), "Прямоугольная"))
                {
                    m.addTypeOfDoor(1);
                    m.addNumOfDoors((int) spinnerDoors.getValue());
                    lokomotiv = m;
                    panelShow.setLoko(lokomotiv);
                    repaint();
                }
                else if (Objects.equals(labelTypeDoor.getText(), "Круглая"))
                {
                    m.addTypeOfDoor(2);
                    m.addNumOfDoors((int) spinnerDoors.getValue());
                    lokomotiv = m;
                    panelShow.setLoko(lokomotiv);
                    repaint();
                }
                else if (Objects.equals(labelTypeDoor.getText(), "Треугольная"))
                {
                    m.addTypeOfDoor(3);
                    m.addNumOfDoors((int) spinnerDoors.getValue());
                    lokomotiv = m;
                    panelShow.setLoko(lokomotiv);
                     repaint();
                }
            }
            labelTypeDoor.setText("Тип");
        };


        //Слушатель типа Локомотива
        PropertyChangeListener lokoTypeListener = propertyChangeEvent ->
        {
            if(Objects.equals(labelType.getText(), "Локомотив"))
            {
                DrawLoko();
            }
            else if (Objects.equals(labelType.getText(), "Монорельс"))
            {
                DrawMono();
            }
            labelType.setText("");
        };

        //Слушатель основного цвета
        PropertyChangeListener mainColorListener = propertyChangeEvent -> {
            if(lokomotiv != null)
            {
                lokomotiv.setMainColor(labelMainColor.getBackground());
                panelShow.setLoko(lokomotiv);
                repaint();
            }
        };

        //Слушатель доп. цвета
        PropertyChangeListener dopColorListener = propertyChangeEvent -> {
            if(lokomotiv != null && lokomotiv instanceof MonoRels m)
            {
                m.SetDopColor(labelDopColor.getBackground());
                lokomotiv = m;
                panelShow.setLoko(lokomotiv);
                repaint();
            }
        };

        labelType.addPropertyChangeListener("text", lokoTypeListener);
        labelMainColor.addPropertyChangeListener("background", mainColorListener);
        labelDopColor.addPropertyChangeListener("background", dopColorListener);
        labelTypeDoor.addPropertyChangeListener("text", doorTypeListener);

        //Обработка нажатия на кнопку "Отмена"
        buttonCancel.addActionListener((e) -> dispose());
    }

    //Конструктор
    public FormLokoConfig(FormDepo formDepo) {
        //Установка названия формы
        super("Кастомизация объекта");
        //Инициализация объектов формы
        Initialize();

        //Обработка нажатия на кнопку "Добавить"
        buttonAdd.addActionListener((e) -> {
            if(lokomotiv != null)
            {
                formDepo.addVehicle(lokomotiv);
                dispose();
            }
        });
    }

    private void DrawLoko()
    {
        lokomotiv = new Lokomotiv((int) spinnerMaxSpeed.getValue(),
                (int) spinnerWeight.getValue(), Color.WHITE);
        lokomotiv.SetPosition(10,10, panelShow.getWidth(), panelShow.getHeight());
        panelShow.setLoko(lokomotiv);
        repaint();
    }

    private void DrawMono()
    {
        lokomotiv = new MonoRels((int) spinnerMaxSpeed.getValue(),
                (int) spinnerWeight.getValue(), Color.WHITE, Color.BLACK,
                checkBoxLamp.isSelected(), checkBoxAirCooler.isSelected()
                , (int) spinnerWins.getValue(), (int) spinnerDoors.getValue());
        lokomotiv.SetPosition(10,10, panelShow.getWidth(), panelShow.getHeight());
        panelShow.setLoko(lokomotiv);
        repaint();
    }

    /*
    Объекты управления
     */

    //JSpinner скорости
    private JSpinner spinnerMaxSpeed;

    //JSpinner массы
    private JSpinner spinnerWeight;

    //JSpinner окон
    private JSpinner spinnerWins;

    //JSpinner дверей
    private JSpinner spinnerDoors;

    //Чекбокс "Фары"
    private JCheckBox checkBoxLamp;

    //Чекбокс "Воздухозаборники"
    private JCheckBox checkBoxAirCooler;

    //Панель отрисовки объекта
    private CutsomPictureBoxForMap panelShow;

    //JLabel "Основной цвет"
    private JLabel labelMainColor;

    //JLabel "Доп. цвет"
    private JLabel labelDopColor;

    //Кнопка "Добавить"
    private JButton buttonAdd;
}

