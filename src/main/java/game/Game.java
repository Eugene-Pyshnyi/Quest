package game;

import java.util.*;

public class Game {
    private Map<String, String> questions;
    private String nextQuestion;
    private List<String> currentOptions;
    private boolean defeated;
    private boolean victory;

    public boolean isVictory() {
        return victory;
    }

    public boolean isDefeated() {
        return defeated;
    }

    public Game() {
        initializeQuestions();
        startGame();
    }

    public void initializeQuestions() {
        questions = new HashMap<>();
        questions.put("gameStart", "You have lost memory. Accept the UFO call?");
        questions.put("acceptUFOCall", "You accepted the UFO call. Are you going to climb up to the captain?");
        questions.put("climbToCaptain", "You have climbed up to the captain. Who are you?");
        questions.put("declineUFOCall", "You declined the UFO call...");
        questions.put("refuseCaptain", "You refused to climb up to the captain...");
        questions.put("tellLie", "Your lie has been revealed...");
        questions.put("tellTruth", "You have been returned home!");

    }
    public String startGame() {
        nextQuestion = "gameStart";
        return questions.get(nextQuestion);
    }

    public List<String> getCurrentOptions() {
        currentOptions = new ArrayList<>();

        switch (nextQuestion) {
            case "gameStart" :
                currentOptions.add("Accept the UFO call");
                currentOptions.add("Decline the UFO call");
                break;
            case "acceptUFOCall" :
                currentOptions.add("Climb up to the captain");
                currentOptions.add("Refuse to climb up to the captain");
                break;
            case "climbToCaptain" :
                currentOptions.add("Tell truth about yourself");
                currentOptions.add("Lie about yourself");
                break;
        }
        return currentOptions;
    }


    public String processAnswer(String answer) {
        if (answer == null) {
            return "You have lost memory. Accept the UFO call?";
        }
        switch (nextQuestion) {
            case "gameStart":
                if (answer.equalsIgnoreCase("Accept the UFO call")) {
                    nextQuestion = "acceptUFOCall";
                } else {
                    nextQuestion = "declineUFOCall";
                    defeated = true;
                }
                break;
            case "acceptUFOCall":
                if (answer.equalsIgnoreCase("Climb up to the captain")) {
                    nextQuestion = "climbToCaptain";
                } else {
                    nextQuestion = "refuseCaptain";
                    defeated = true;
                }
                break;
            case "climbToCaptain":
                if (answer.equalsIgnoreCase("Tell truth about yourself")) {
                    nextQuestion = "tellTruth";
                    victory = true;
                } else {
                    nextQuestion = "tellLie";
                    defeated = true;
                }
                break;
        }
        return questions.get(nextQuestion);
    }
    public void resetGame() {
        nextQuestion = "gameStart";
        defeated = false;
        victory = false;
    }
    public boolean isGameOver() {
        return nextQuestion.equals("tellTruth") || nextQuestion.equals("declineUFOCall")
                || nextQuestion.equals("refuseCaptain") || nextQuestion.equals("tellLie");
    }
}
