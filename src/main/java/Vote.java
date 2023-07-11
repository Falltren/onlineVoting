
import java.util.*;

public class Vote {

    private Set<Car> voteResult = new HashSet<>();
    private Scanner scanner = new Scanner(System.in);
    private Message message = new Message();
    private ValidationData validationData = new ValidationData();

    public void startVote() {
        System.out.print(message.START_VOTE);
        scanner.reset();
        int count = scanner.nextInt();
        while (!validationData.validateCarCount(count)) {
            System.out.println(message.INCORRECT_CAR_NUMBER);
            System.out.print(message.REENTER);
            count = scanner.nextInt();
        }
        for (int i = 1; i <= count; i++) {
            inputCarInfo(i);
        }
        System.out.print(message.VOTE_CREATED);
        voteResult.forEach(s -> System.out.print(s + "; "));
        System.out.println("\n" + message.EXIT);
        makingChoice();
        stopVote();
    }

    private void inputCarInfo(int carNumber) {
        while (true) {
            System.out.print(message.inputCarInfo(carNumber));
            String carInfo = new Scanner(System.in).nextLine();
            if (!validationData.validateNameLength(carInfo)) {
                System.out.println(message.INCORRECT_INPUT_LENGTH);
                continue;
            }
            String carBrand = splitInputToCarBrandAndModel(carInfo)[0];
            String carModel = splitInputToCarBrandAndModel(carInfo)[1];
            if (!validationData.validateCarBrand(carBrand)) {
                System.out.println(message.INCORRECT_CAR_BRAND);
                continue;
            }
            if (!validationData.validateCarModel(carModel)) {
                System.out.println(message.INCORRECT_CAR_MODEL);
                continue;
            }
            Car car = new Car(carInfo, 0);
            voteResult.add(car);
            break;
        }
    }

    private String[] splitInputToCarBrandAndModel(String input) {
        String[] words = input.split("\\s+");
        if (words.length == 2) {
            return words;
        } else {
            return new String[]{words[0] + " " + words[1], words[2]};
        }
    }

    private void makingChoice() {
        while (true) {
            System.out.print(message.YOUR_CHOICE);
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("0")) {
                break;
            }
            if (findCarByName(choice).isPresent()) {
                Car car = findCarByName(choice).get();
                car.setRating(car.getRating() + 1);
                System.out.println(message.ACCEPTED_VOTE);
            } else {
                System.out.println(message.INCORRECT_CAR_BRAND);
                voteResult.forEach(s -> System.out.print(s + "; "));
            }
        }
    }

    private void stopVote() {
        Car car = Collections.max(voteResult, Comparator.comparing(Car::getRating));
        int voteCount = car.getRating();
        System.out.println("\n" + message.getVoteResult(car, voteCount));
    }

    private Optional<Car> findCarByName(String name) {
        return voteResult.stream().filter(c -> c.getName().equals(name)).findAny();
    }

}