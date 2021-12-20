// Класс-ошибка Если не найден локомотив/монорельс по определенному месту
public class DepoPlaceNotFoundException extends Exception{
    public DepoPlaceNotFoundException(int i)
    {
        super("Не найден локомотив по месту #" + i);
    }
}
