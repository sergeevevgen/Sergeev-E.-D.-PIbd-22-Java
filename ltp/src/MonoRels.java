
import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class MonoRels extends JComponent{
    Random r = new Random();

    private DopClass dopclass;
    
    private int _startPosX;

    private int _startPosY;

    private int _pictureWidth = 900;

    private int _pictureHeight = 500;

    private final int monoRelsWidth = 105;

    private final int monoRelsHeight = 50;

    private int maxSpeed;
    private void SetMaxSpeed(int maxSpeed)
    {
        this.maxSpeed = maxSpeed;
    }
    public int GetMaxSpeed()
    {
        return maxSpeed;
    }

    private int weight;
    private void SetWeight(int weight)
    {
        this.weight = weight;
    }
    public int GetWeight()
    {
        return weight;
    }

    private Color mainColor;
    private void SetMainColor(Color mainColor)
    {
        this.mainColor = mainColor;
    }
    public Color GetMainColor()
    {
        return mainColor;
    }

    private Color dopColor;

    private void SetDopColor(Color dopColor)
    {
        this.dopColor = dopColor;
    }
    public Color GetDopColor()
    {
        return dopColor;
    }

    private int numOfWins;
    private void SetNumOfWins(int numOfWins)
    {
        this.numOfWins = numOfWins;
    }
    public int GetNumOfWins()
    {
        return numOfWins;
    }

    private boolean lamp;
    private void SetLamp(boolean lamp)
    {
        this.lamp = lamp;
    }
    public boolean GetLamp()
    {
        return lamp;
    }

    private boolean airCooler;
    private void SetAirCooler(boolean airCooler)
    {
        this.airCooler = airCooler;
    }
    public boolean GetAirCooler()
    {
        return airCooler;
    }

    public void Init(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean lamp, boolean airCooler)
    {
        SetMaxSpeed(maxSpeed);
        SetWeight(weight);
        SetMainColor(mainColor);
        SetDopColor(dopColor);
        SetLamp(lamp);
        SetAirCooler(airCooler);
        SetNumOfWins(r.nextInt(4) + 1);
        dopclass = new DopClass();
        dopclass.SetDopc(dopColor);
        dopclass.SetD(r.nextInt(2) + 1);
    }

    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        dopclass.x = _startPosX;
        dopclass.y = _startPosY;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    public void MoveTransport(Direction direction)
    {
        int step = GetMaxSpeed() * 100 / GetWeight();
        switch (direction)
        {
            // вправо
            case RIGHT:
                if (_startPosX + step < _pictureWidth - 110 - monoRelsWidth)
                {
                    _startPosX += step;
                    dopclass.x = _startPosX;
                }
                break;
            //влево
            case LEFT:
                if (_startPosX - step > 5)
                {
                    _startPosX -= step;
                    dopclass.x = _startPosX;
                }
                break;
            //вверх
            case UP:
                if (_startPosY - step > 50)
                {
                    _startPosY -= step;
                    dopclass.y = _startPosY;
                }
                break;
            //вниз
            case DOWN:
                if (_startPosY + step < _pictureHeight + 40 - monoRelsHeight)
                {
                    _startPosY += step;
                    dopclass.y = _startPosY;
                }
                break;
        }
    }

    // <summary>
    /// Отрисовка монорельса
    /// </summary>
    public void DrawTransport(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Отрисовка верхней части кузова монорельса
        int[] pointsX = new int[] {_startPosX + 7, _startPosX + 97, _startPosX + 97, _startPosX + 2};
        int[] pointsY = new int[]{_startPosY, _startPosY, _startPosY + 17, _startPosY + 17};
        g2d.setColor(GetMainColor());
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(pointsX, pointsY, pointsX.length);

        //Отрисовка нижней части кузова монорельса
        pointsX = new int[] {_startPosX + 2, _startPosX + 97, _startPosX + 97, _startPosX + 2};
        pointsY = new int[] {_startPosY + 17, _startPosY + 17, _startPosY + 34, _startPosY + 34};
        g2d.setColor(GetDopColor());
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(pointsX, pointsY, pointsX.length);

        //Отрисовка трансмиссии
        pointsX = new int[] {_startPosX + 2, _startPosX + 15, _startPosX + 84, _startPosX + 97};
        pointsY = new int[] {_startPosY + 34, _startPosY + 40, _startPosY + 40, _startPosY + 34};
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        g2d.drawPolygon(pointsX, pointsY, pointsX.length);

        //Отрисовка задней части
        g2d.fillRect(_startPosX + 97, _startPosY + 3, 5, 28);

        //Отрисовка фар
        if (GetLamp())
        {
            g2d.drawRect(_startPosX + 2, _startPosY + 24, 6, 5);
            g2d.setColor(Color.yellow);
            g.fillRect(_startPosX + 3, _startPosY + 25, 5, 4);
        }

        //Отрисовка воздухозаборников
        if (GetAirCooler())
        {
            g2d.setColor(Color.BLACK);
            g2d.drawRect(_startPosX + 18, _startPosY - 5, 17, 5);
            g2d.drawRect(_startPosX + 75, _startPosY - 5, 17, 5);
            g2d.setColor(Color.WHITE);
            g2d.fillRect(_startPosX + 19, _startPosY - 4, 16, 4);
            g2d.fillRect(_startPosX + 76, _startPosY - 4, 16, 4);
        }

        //Отрисовка окон

        if (GetNumOfWins() >= 1)
        {
            g2d.setColor(Color.BLUE);
            g2d.drawRect(_startPosX + 9, _startPosY + 3, 8, 11);
            g2d.setColor(Color.WHITE);
            g2d.fillRect(_startPosX + 10, _startPosY + 4, 7, 10);
        }
        if (GetNumOfWins() >= 2)
        {
            g2d.setColor(Color.BLUE);
            g2d.drawRect(_startPosX + 19, _startPosY + 3, 8, 11);
            g2d.setColor(Color.WHITE);
            g2d.fillRect(_startPosX + 20, _startPosY + 4, 7, 10);
        }
        if (GetNumOfWins() == 3)
        {
            g2d.setColor(Color.BLUE);
            g2d.drawRect(_startPosX + 86, _startPosY + 3, 8, 11);
            g2d.setColor(Color.WHITE);
            g2d.fillRect(_startPosX + 87, _startPosY + 4, 7, 10);
        }

        dopclass.DrawTransport(g2d);
    }
}
