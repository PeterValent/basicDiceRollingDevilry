import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class RollingDice {
    public int numberOfRolls;
    public int diceNumber;
    public List<List<Integer>> calculation = new ArrayList<>();
    public int result;
    public String modifier;
    public List<String> output = new ArrayList<>();
    public int targetListIndex = 0;
    public int criticalValue = 0;

    public RollingDice(String input, int rollType) {
        numberOfRolls = calculateNumberOfRolls(Arrays.asList(input.split(" ")));
        diceNumber = calculateDiceNumber(Arrays.asList(input.split(" ")));
        modifier = getModifier(input);
        rollDice();
        result = calculation.get(targetListIndex).get(calculation.get(targetListIndex).size() - 1);
        if (rollType != 0) {
            advantageDisadvantageRolls(rollType);
        }
        createOutput();
    }

    public int calculateNumberOfRolls(List<String> input) {
        return Integer.parseInt(input.stream().filter(i -> i.contains("d")).findFirst().get().charAt(0) == 'd' ?
                "1" : input.stream().filter(i -> !i.contains("a") && i.contains("d")).findFirst().get().split("d")[0]);
    }

    public int calculateDiceNumber(List<String> input) {
        String diceFace = input.stream().filter(i -> i.contains("d")).findFirst().get().split("d")[1];
        if (diceFace.split("\\+").length > 1 || diceFace.split("-").length > 1) {
            return Integer.parseInt(diceFace.split("\\+")[0].split("-")[0]);
        } else {
            return Integer.parseInt(diceFace);
        }
    }

    public String getModifier(String input) {
        if (input.split(" ").length > 1) {
            List<String> splitInput = Arrays.asList(input.split(" "));
            return splitInput.contains("+") || splitInput.contains("-") ? splitInput.stream().filter(i -> i.contains("+") || i.contains("-")).findFirst().get() + splitInput.get(splitInput.size() - 1) : "";
        } else {
            if (input.contains("+")) {
                return input.substring(input.indexOf("+"));
            } else if (input.contains("-")) {
                return input.substring(input.indexOf("-"));
            } else {
                return "";
            }
        }
    }

    public void rollDice() {
        Random random = new Random();
        List<Integer> rollRecord = new ArrayList<>();
        int rollSum = 0;
        for (int i = 0; i < numberOfRolls; i++) {
            int roll = random.nextInt(diceNumber - 1) + 1;
            rollRecord.add(roll);
            rollSum += roll;
        }
        rollRecord.add(rollSum);
        calculation.add(rollRecord);
    }

    public void advantageDisadvantageRolls(int rollType) {
        rollDice();
        if (rollType == 1) {
            output.add("Rolled with advantage: ");
            targetListIndex = calculation.indexOf(calculation.stream().max(Comparator.comparingInt(i -> i.get(i.size() - 1))).get());
        } else if (rollType == 2) {
            output.add("Rolled with disadvantage: ");
            targetListIndex = calculation.indexOf(calculation.stream().min(Comparator.comparingInt(i -> i.get(i.size() - 1))).get());
        }
        result = calculation.get(targetListIndex).get(calculation.get(targetListIndex).size() - 1);
    }

    public void createOutput() {
        if (modifier.length() > 0) {
            createOutputWithModifier();
        } else {
            for (List<Integer> rolls : calculation
            ) {
                if (calculation.indexOf(rolls) == targetListIndex) {
                    output.add(String.format("(%s)", rolls.subList(0, rolls.size() - 1).stream().map(String::valueOf)
                            .collect(Collectors.joining(" + "))) + " = " + result);
                } else {
                    output.add(String.format("(%s)", rolls.subList(0, rolls.size() - 1).stream().map(String::valueOf)
                            .collect(Collectors.joining(" + "))));
                }
            }
        }
    }

    public void createOutputWithModifier() {
        if (modifier.charAt(0) == '+') {
            result += Integer.parseInt(modifier.substring(1));
        } else if (modifier.charAt(0) == '-') {
            result -= Integer.parseInt(modifier.substring(1));
        }
        for (List<Integer> rolls : calculation
        ) {
            if (calculation.indexOf(rolls) == targetListIndex) {
                output.add(String.format("(%s)", rolls.subList(0, rolls.size() - 1).stream().map(String::valueOf)
                        .collect(Collectors.joining(" + "))) + " " + modifier.charAt(0) + " " + modifier.substring(1) + " = " + result);
            } else {
                output.add(String.format("(%s)", rolls.subList(0, rolls.size() - 1).stream().map(String::valueOf)
                        .collect(Collectors.joining(" + "))) + " " + modifier.charAt(0) + " " + modifier.substring(1));
            }
        }
    }

}
