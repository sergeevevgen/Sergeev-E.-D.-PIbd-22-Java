//Класс-ошибка "Если на парковке уже есть автомобиль с такими же
        //характеристиками"
public class DepoAlreadyHaveException extends Exception{
    public DepoAlreadyHaveException()
    {
        super("В депо уже есть такой локомотив");
    }
}
