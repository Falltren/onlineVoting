
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Vote {

    private Map<String, Integer> voteResult = new HashMap<>();
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
        voteResult.keySet().forEach(s -> System.out.print(s + "; "));
        System.out.println("\n" + message.EXIT);
        makingChoice();
        stopVote();
    }

    private void inputCarInfo(int i) {
        while (true) {
            System.out.print(message.inputCarInfo(i));
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
            voteResult.put(carInfo, 0);
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
            if (voteResult.containsKey(choice)) {
                int voteCount = voteResult.get(choice);
                voteResult.put(choice, voteCount + 1);
                System.out.println(message.ACCEPTED_VOTE);
            } else {
                System.out.println(message.INCORRECT_CAR_BRAND);
                voteResult.keySet().forEach(s -> System.out.print(s + "; "));
            }
        }
    }

    private void stopVote() {
        String car = Collections.max(voteResult.entrySet(), Map.Entry.comparingByValue()).getKey();
        int voteCount = voteResult.get(car);
        System.out.println("\n" + message.getVoteResult(car, voteCount));
    }

}