// Класс-ошибка Если выбранное место занято
public class DepoOccupiedPlaceException extends Exception{
    public DepoOccupiedPlaceException()
    {
        super("Место занято");
    }
}
