package view;

import static java.lang.System.lineSeparator;

import domain.Ladder;
import domain.Leg;
import domain.Line;
import domain.Player;
import domain.Players;
import domain.Reward;
import domain.Rewards;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String LADDER_RESULT = lineSeparator() + "사다리 결과" + lineSeparator();
    private static final String EXECUTION_RESULT = lineSeparator() + "실행 결과";
    private static final String STICK = "|";
    private static final String LEG_UNIT = "-";
    private static final String FORMAT_NAME = "%6s";
    private static final String FORMAT_REWARD = "%6s";
    private static final String FORMAT_ALL_PLAYER_RESULT = "%s : %s" + lineSeparator();

    public static void printLadder(Players players, Ladder ladder, Rewards rewards) {
        System.out.println(LADDER_RESULT);
        printPlayers(players.getPlayers());
        printLines(players, ladder);
        printRewards(players, rewards.getRewards());
    }

    private static void printRewards(Players players, List<Reward> rewards) {
        System.out.print(rewards.get(0).getReward() + " ");
        rewards.stream().skip(1).forEach((reward) -> {
            System.out.printf(FORMAT_REWARD, reward.getReward());
        });
        System.out.println();
    }

    private static void printLines(Players players, Ladder ladder) {
        List<Line> lines = ladder.getLines();
        for (Line line : lines) {
            printPrefixSpace(players.getPlayers());
            printLine(line.getLegs());
        }
    }

    private static void printPlayers(List<Player> players) {
        System.out.print(players.get(0).getName() + " ");
        players.stream().skip(1).forEach((player) -> {
            System.out.printf(FORMAT_NAME, player.getName());
        });
        System.out.println();
    }

    private static void printPrefixSpace(List<Player> players) {
        System.out.print(" ".repeat(getFirstPlayerNameSize(players)));
    }

    private static int getFirstPlayerNameSize(List<Player> players) {
        return players.get(0).getName().length();
    }

    private static void printLine(List<Leg> legs) {
        for (Leg leg : legs) {
            System.out.print(STICK);
            System.out.print(extractLeg(leg.isExistLeg()));
        }
        System.out.print(STICK + lineSeparator());
    }

    private static String extractLeg(boolean isExistLeg) {
        if (isExistLeg) {
            return LEG_UNIT.repeat(5);
        }
        return " ".repeat(5);
    }

    public static void printPlayerResult(String playerReward) {
        System.out.println(EXECUTION_RESULT);
        System.out.println(playerReward);
    }

    public static void printAllPlayerResult(Map<Player, String> allPlayerReward) {
        System.out.println(EXECUTION_RESULT);
        for (Player player : allPlayerReward.keySet()) {
            System.out.printf(FORMAT_ALL_PLAYER_RESULT, player.getName(), allPlayerReward.get(player));
        }
    }
}
