import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ClassBD implements InterfBD {
    private final String inputGamesFile;
    private final String inputGroupsFile;
    private final String outputGroups;
    private final String outputResults;
    private final FileParser fileParser = new FileParser();

    public ClassBD(String inputGamesFile, String inputGroupsFile, String outputGroups, String outputResults) {
        this.inputGamesFile = inputGamesFile;
        this.inputGroupsFile = inputGroupsFile;
        this.outputGroups = outputGroups;
        this.outputResults = outputResults;
    }

    @Override
    public void generateReport() {
        Set<Game> games = fileParser.parseGamesFromFile(inputGamesFile);
        Map<String, List<Team>> teamsMap = new HashMap<>();

        for (Game game : games) {
            Team team1 = game.getTeam1();
            addTeamToMap(teamsMap, team1);

            Team team2 = game.getTeam2();
            addTeamToMap(teamsMap, team2);
        }

        Set<Team> teams = new HashSet<>();
        teamsMap.forEach((name, teamList) -> {
            Team team = new Team();
            teamList.forEach(t -> {
                team.setName(t.getName());
                team.setPoints(team.getPoints() + t.getPoints());
                team.setDefeatsCount(team.getDefeatsCount() + t.getDefeatsCount());
                team.setDrawsCount(team.getDrawsCount() + t.getDrawsCount());
                team.setWinsCount(team.getWinsCount() + t.getWinsCount());
                team.setGoalsConceded(team.getGoalsConceded() + t.getGoalsConceded());
                team.setGoalsScored(team.getGoalsScored() + t.getGoalsScored());
            });
            teams.add(team);
        });

        fileParser.parseGroupsFromFile2(inputGroupsFile, teams);

        List<Team> teamList = teams.stream()
                .sorted(new ChampionsLeagueComparator())
                .toList();
        printGroups(teamList);

        teamList = teams.stream()
                .sorted(new TeamComparator())
                .toList();
        printResults(teamList);
    }

    private static void addTeamToMap(Map<String, List<Team>> teamsMap, Team team1) {
        if (teamsMap.containsKey(team1.getName())) {
            List<Team> teamList = teamsMap.get(team1.getName());
            teamList.add(team1);
            teamsMap.put(team1.getName(), teamList);
        } else {
            List<Team> teamList = new ArrayList<>();
            teamList.add(team1);
            teamsMap.put(team1.getName(), teamList);
        }
    }

    public void printGroups(List<Team> teamList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputGroups))) {
            for (Team team : teamList) {
                writer.write("Team: " + team.getName());
                writer.newLine();
                writer.write("Group: " + team.getGroupName());
                writer.newLine();
                writer.write("Points: " + team.getPoints());
                writer.newLine();
                writer.write("Wins: " + team.getWinsCount());
                writer.newLine();
                writer.write("Draws: " + team.getDrawsCount());
                writer.newLine();
                writer.write("Defeats: " + team.getDefeatsCount());
                writer.newLine();
                writer.write("Goals Scored: " + team.getGoalsScored());
                writer.newLine();
                writer.write("Goals Conceded: " + team.getGoalsConceded());
                writer.newLine();
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printResults(List<Team> teamList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputResults))) {
            for (Team team : teamList) {
                writer.write("Team: " + team.getName());
                writer.newLine();
                writer.write("Points: " + team.getPoints());
                writer.newLine();
                int numberOfGames = team.getWinsCount() + team.getDrawsCount() + team.getDefeatsCount();
                writer.write("Number of games: " + numberOfGames);
                writer.newLine();
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
