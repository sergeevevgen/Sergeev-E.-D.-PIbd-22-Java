
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

	private JPanel contentPane;
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
	public FormMonoRels() {
		super("Монорельс");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton btnCreate = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C");
		btnCreate.setBounds(799, 11, 75, 23);
		btnCreate.setMargin(new Insets(10, 10, 10, 10));
		contentPane.add(btnCreate);
		
		JButton btnUp = new JButton("");
		btnUp.setBounds(804, 379, 30, 30);
		btnUp.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1u.png"));
		contentPane.add(btnUp);
		
		JButton btnLeft = new JButton("");
		btnLeft.setBounds(764, 420, 30, 30);
		btnLeft.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1l.png"));
		contentPane.add(btnLeft);
		
		JButton btnDown = new JButton("");
		btnDown.setBounds(804, 420, 30, 30);
		btnDown.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1d.png"));
		contentPane.add(btnDown);
		
		JButton btnRight = new JButton("");
		btnRight.setBounds(844, 420, 30, 30);
		btnRight.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1r.png"));
		contentPane.add(btnRight);
		
		
		
		//Обработка нажатий
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rnd = new Random();
				monor = new MonoRels();
				monor.Init(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.red, Color.blue, true, true);
				monor.SetPosition(rnd.nextInt(100) + 10, rnd.nextInt(100) + 40, contentPane.getWidth(), contentPane.getHeight());
				repaint();
			}
		});
		
		btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				monor.MoveTransport(Direction.UP);
				repaint();
			}
		});
		
		btnLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				monor.MoveTransport(Direction.LEFT);
				repaint();
			}
		});
		
		btnDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				monor.MoveTransport(Direction.DOWN);
				repaint();
			}
		});
		
		btnRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				monor.MoveTransport(Direction.RIGHT);
				repaint();
			}
		});
	}

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
