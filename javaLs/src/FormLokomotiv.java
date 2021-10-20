
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

    //������
    private final JPanel contentPane;

    //������ �� ������ MonoRels
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
    //�����������
    public FormLokomotiv() {
        //��������� �������� �����
        super("���������/���������");
        //��������� �������� ��� ������� �� ������� ������� ������ ������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //������� ����� � �� ������������ �� ������
        setBounds(100, 100, 900, 500);
        //������������� ������
        contentPane = new JPanel();
        //��������� ������ ������
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        //������� ����� = ������� � ������
        setContentPane(contentPane);
        //��������� ������ ������������ � ������
        contentPane.setLayout(null);


        //�������� ������ "������� ���������" � ������������� � �������
        JButton btnCreateLokomotiv = new JButton("������� ���������");
        btnCreateLokomotiv.setBounds(719, 11, 150, 23);
        btnCreateLokomotiv.setMargin(new Insets(10, 10, 10, 10));
        contentPane.add(btnCreateLokomotiv);

        //�������� ������ "������� ���������" � ������������� � �������
        JButton btnCreateMonoRels = new JButton("������� ���������");
        btnCreateMonoRels.setBounds(719, 54, 150, 23);
        btnCreateMonoRels.setMargin(new Insets(10, 10, 10, 10));
        this.contentPane.add(btnCreateMonoRels);

        //�������� ������ "�����" � ������������� � �������
        JButton btnUp = new JButton("");
        btnUp.setBounds(804, 379, 30, 30);
        btnUp.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1u.png"));
        contentPane.add(btnUp);

        //�������� ������ "�����" � ������������� � �������
        JButton btnLeft = new JButton("");
        btnLeft.setBounds(764, 420, 30, 30);
        btnLeft.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1l.png"));
        contentPane.add(btnLeft);

        //�������� ������ "����" � ������������� � �������
        JButton btnDown = new JButton("");
        btnDown.setBounds(804, 420, 30, 30);
        btnDown.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1d.png"));
        contentPane.add(btnDown);

        //�������� ������ "������" � ������������� � �������
        JButton btnRight = new JButton("");
        btnRight.setBounds(844, 420, 30, 30);
        btnRight.setIcon(new ImageIcon("C:\\Users\\alexe\\Desktop\\1r.png"));
        contentPane.add(btnRight);

        //��������� ������� �� ������ "������� ���������"
        btnCreateLokomotiv.addActionListener(e -> {
            Random rnd = new Random();
            lokomotiv = new Lokomotiv(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.red);
            lokomotiv.SetPosition(rnd.nextInt(100) + 11, rnd.nextInt(100) + 40, contentPane.getWidth(), contentPane.getHeight());
            repaint();
        });

        //��������� ������� �� ������ "������� ���������"
        btnCreateMonoRels.addActionListener((e) -> {
            Random rnd = new Random();
            lokomotiv = new MonoRels(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000, Color.PINK, Color.red, true, true, rnd.nextInt(3) + 1);
            lokomotiv.SetPosition(rnd.nextInt(100) + 10, rnd.nextInt(100) + 40, contentPane.getWidth(), contentPane.getHeight());
            repaint();
        });

        //��������� ������� �� ������ "�����"
        btnUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lokomotiv.MoveTransport(Direction.UP);
                repaint();
            }
        });

        //��������� ������� �� ������ "�����"
        btnLeft.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lokomotiv.MoveTransport(Direction.LEFT);
                repaint();
            }
        });

        //��������� ������� �� ������ "����"
        btnDown.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lokomotiv.MoveTransport(Direction.DOWN);
                repaint();
            }
        });

        //��������� ������� �� ������ "������"
        btnRight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lokomotiv.MoveTransport(Direction.RIGHT);
                repaint();
            }
        });
    }

    //����� ���������
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if(lokomotiv != null)
            lokomotiv.DrawTransport(g);
    }

    @Override
    public void repaint()
    {
        super.repaint();
    }
}
