import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassBD implements InterfBD {
    private final String inputGamesFile;
    private final String inputGroupsFile;
    private String outputGroups;
    private String outputResults;
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
        System.out.println(games);
        List<Group> groups = fileParser.parseGroupsFromFile(inputGroupsFile);
        Set<Team> teams = fileParser.parseTeamsFromFile(inputGamesFile);
        Set<Team> t2 = new HashSet<>();
        for (Game game : games) {
            Team team1 = game.getTeam1();
            Team team11 = teams.stream().filter(t->t.getName().equals(team1.getName())).findFirst().get();

            team1.setGoalsScored(team11.getGoalsScored() + game.getGoalsScored1());
            team1.setGoalsConceded(team11.getGoalsConceded() + game.getGoalsScored2());

            Team team2 = game.getTeam2();
            Team team22 = teams.stream().filter(t->t.getName().equals(team2.getName())).findFirst().get();

            team2.setGoalsScored(team22.getGoalsScored() + game.getGoalsScored2());
            team2.setGoalsConceded(team22.getGoalsConceded() + game.getGoalsScored1());

            if (game.getGoalsScored1() > game.getGoalsScored2()) {
                team1.setWinsCount(team11.getWinsCount() + 1);
                team1.setPoints(team11.getPoints() + 3);
                team2.setDefeatsCount(team22.getDefeatsCount() + 1);
            } else if (game.getGoalsScored1() < game.getGoalsScored2()) {
                team2.setWinsCount(team22.getWinsCount() + 1);
                team2.setPoints(team22.getPoints() + 3);
                team1.setDefeatsCount(team11.getDefeatsCount() + 1);
            } else {
                team1.setDrawsCount(team11.getDrawsCount() + 1);
                team2.setDrawsCount(team22.getDrawsCount() + 1);
                team1.setPoints(team11.getPoints() + 1);
                team2.setPoints(team22.getPoints() + 1);
            }
            t2.add(team1);
            t2.add(team2);
        }

        System.out.println(t2);


        fileParser.parseGroupsFromFile2(inputGroupsFile, t2);

        List<Team> teamList = t2.stream()
                .sorted(new ChampionsLeagueComparator())
                .toList();

        printGroup(teamList);
    }
    public void printGroup(List<Team> teamList) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputGroups))) {
            for (Team team : teamList) {
                writer.write("Team: " + team.getName());
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
}
