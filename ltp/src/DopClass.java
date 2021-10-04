import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class DopClass extends JComponent {
	public int x;
	
	public int y;
	
	private DopEnum dopenum;
	
	private Color Dopc;
	
	public void SetDopc(Color dopc)
	{
		Dopc = dopc;
	}
	
	private Color GetDopc()
	{
		return Dopc;
	}
	
	public void SetD(int d)
	{
		switch (d) {
			case 1 -> dopenum = DopEnum.One;
			case 2 -> dopenum = DopEnum.Two;
			case 3 -> dopenum = DopEnum.Three;
			default -> dopenum = DopEnum.One;
		}
	}
	
	public void DrawTransport(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //Отрисовка дверей
        if (dopenum == DopEnum.One)
        {
        	g2d.setColor(Color.BLACK);
            g2d.drawRect(x + 32, y + 3, 8, 28);
            
            g2d.setColor(GetDopc());
            g2d.fillRect(x + 33, y + 4, 7, 27);
        }
        if (dopenum == DopEnum.Two)
        {
        	g2d.setColor(Color.BLACK);
            g2d.drawRect(x + 32, y + 3, 8, 28);
            g2d.drawRect(x + 50, y + 3, 8, 28);
            
            g2d.setColor(GetDopc());
            g2d.fillRect(x + 33, y + 4, 7, 27);
            g2d.fillRect(x + 51, y + 4, 7, 27);
        }
        if (dopenum == DopEnum.Three)
        {
        	g2d.setColor(Color.BLACK);
            g2d.drawRect(x + 32, y + 3, 8, 28);
            g2d.drawRect(x + 50, y + 3, 8, 28);
            g2d.drawRect(x + 68, y + 3, 8, 28);
            
            g2d.setColor(GetDopc());
            g2d.fillRect(x + 33, y + 4, 7, 27);
            g2d.fillRect(x + 51, y + 4, 7, 27);
            g2d.fillRect(x + 69, y + 4, 7, 27);
        }
    }
}
