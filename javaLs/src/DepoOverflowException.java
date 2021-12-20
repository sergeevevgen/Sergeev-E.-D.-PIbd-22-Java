// Класс-ошибка "Если в депо уже заняты все места"
public class DepoOverflowException extends Exception{
    public DepoOverflowException()
    {
        super("В депо нет свободных мест");
    }
}
