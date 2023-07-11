public class Message {

    public final String START_VOTE = "Голосование за автомобиль года" + "\n\n"
            + "Сколько моделей авто участвует в голосовании?: ";

    public final String VOTE_CREATED = "Голосование создано!" + "\n" + "Выберите модель из списка: ";

    public final String YOUR_CHOICE = "Ваш выбор?: ";

    public final String ACCEPTED_VOTE = "Ваш голос принят!";

    public final String EXIT = "Для подсчета голосов введите 0";

    public final String INCORRECT_CAR_BRAND = "Ведена некорректная марка автомобиля!";

    public final String INCORRECT_CAR_MODEL = "Введена некорректная модель автомобиля!";

    public final String INCORRECT_INPUT_LENGTH = "Количество слов в названии авто должно быть 2 или 3 ";

    public final String INCORRECT_CAR_NUMBER = "Число авто, участвующих в голосовании должно быть не более 99";

    public final String REENTER = "Повторите ввод: ";

    public String getVoteResult(Car car, int voteCount) {
        return "Голосование завершено!" + "\n"
                + "Лучший автомобиль года " + car + "\n"
                + "Количество голосов: " + voteCount;
    }

    public String inputCarInfo(int carNumber) {
        return "Введите модель " + carNumber + "-го автомобиля: ";
    }


}
