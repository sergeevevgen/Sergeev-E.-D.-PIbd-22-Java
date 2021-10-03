import javax.swing.*;

public class FormMonoRels extends JFrame{
    private JPanel PanelMain;
    private JButton buttonMain;

    public FormMonoRels()
    {
        setContentPane(PanelMain);
        setVisible(true);
        setSize(640,480);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        new FormMonoRels();
    }
}
