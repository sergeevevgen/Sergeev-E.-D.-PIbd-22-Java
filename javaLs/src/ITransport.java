import java.awt.Graphics;

public interface ITransport {
    //Метод установки начальной позиции
    void SetPosition(int x, int y, int width, int height);

    //Метод отрисовки транспорта
    void DrawTransport(Graphics g);

    //Метод передвижения транспорта
    void MoveTransport(Direction direction);
}