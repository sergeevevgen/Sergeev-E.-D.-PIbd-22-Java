
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

//Форма является наследником JFrame
public class FormMonoRels extends JFrame {
	//поле Панель (на ней располагается весь контент
	private JPanel contentPane;
	//Поле Объект от класса Монорельс
	private MonoRels monor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMonoRels frame = new FormMonoRels();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	//Конструктор
	public FormMonoRels() {
		super("Ìîíîðåëüñ");
		//Установка закрытия приложения на "крестик" по-умолчанию
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Установка размеров и позиции на экране
		setBounds(100, 100, 900, 500);
		//инициализация Панели
		contentPane = new JPanel();
		//Установка границ
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		//Контент - панелька
		setContentPane(contentPane);
		//Позиционирование внутри панельки будет абсолютным
		contentPane.setLayout(null);
		
		
		//Создание кнопки "Создать" и задание ей размера, расположения
		JButton btnCreate = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C");
		btnCreate.setBounds(799, 11, 75, 23);
		btnCreate.setMargin(new Insets(10, 10, 10, 10));
		//Помещаем ее на панель
		contentPane.add(btnCreate);
		
		//Создание кнопки "Наверх" и задание ей размера, расположения
		JButton btnUp = new JButton("");
		btnUp.setBounds(804, 379, 30, 30);
		btnUp.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1u.png"));
		//Помещаем ее на панель
		contentPane.add(btnUp);
		
		//Создание кнопки "Влево" и задание ей размера, расположения
		JButton btnLeft = new JButton("");
		btnLeft.setBounds(764, 420, 30, 30);
		btnLeft.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1l.png"));
		//Помещаем ее на панель
		contentPane.add(btnLeft);
		
		//Создание кнопки "Вниз" и задание ей размера, расположения
		JButton btnDown = new JButton("");
		btnDown.setBounds(804, 420, 30, 30);
		btnDown.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1d.png"));
		//Помещаем ее на панель
		contentPane.add(btnDown);
		
		//Создание кнопки "Вправо" и задание ей размера, расположения
		JButton btnRight = new JButton("");
		btnRight.setBounds(844, 420, 30, 30);
		btnRight.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1r.png"));
		//Помещаем ее на панель
		contentPane.add(btnRight);
		
		
		
		//Метод обработки нажатия на кнопку "Создать"
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rnd = new Random();
				//инициализация поле-объекта
				monor = new MonoRels();
				//Вызовем метода инициализации его полей
				monor.Init(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.red, Color.blue, true, true);
				//Установка позиции
				monor.SetPosition(rnd.nextInt(100) + 10, rnd.nextInt(100) + 40, contentPane.getWidth(), contentPane.getHeight());
				//Перерисовка
				repaint();
			}
		});
		
		//Метод обработки нажатия на кнопку "Наверх"
		btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Сдвиг вверх
				monor.MoveTransport(Direction.UP);
				//Перерисовка
				repaint();
			}
		});
		
		//Метод обработки нажатия на кнопку "Влево"
		btnLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Сдвиг влево
				monor.MoveTransport(Direction.LEFT);
				//Перерисовка
				repaint();
			}
		});
		
		//Метод обработки нажатия на кнопку "Влево"
		btnDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Сдвиг вниз
				monor.MoveTransport(Direction.DOWN);
				//Перерисовка
				repaint();
			}
		});
		
		//Метод обработки нажатия на кнопку "Вправо"
		btnRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Сдвиг вправо
				monor.MoveTransport(Direction.RIGHT);
				//Перерисовка
				repaint();
			}
		});
	}
	
	//Переопределение метода paint
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		if(monor != null)
			monor.DrawTransport(g);
	}
	
	//Переопределение метода repaint
	@Override
	public void repaint()
	{
		super.repaint();
	}
}
