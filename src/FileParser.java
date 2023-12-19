import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileParser {
    public Set<Game> parseGamesFromFile(String inputGamesFile) {
        Set<Game> games = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputGamesFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    Game game = getGame(parts);

                    games.add(game);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while parsing games");
        }

        return games;
    }

    public List<Group> parseGroupsFromFile(String inputGroupsFile) {
        List<Group> groups = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputGroupsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length > 1) {
                    String groupName = parts[0];
                    List<Team> teams = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        String teamName = parts[i];
                        Team team = new Team(teamName);
                        teams.add(team);
                    }

                    Group group = new Group();
                    group.setName(groupName);
                    group.setTeams(teams);

                    groups.add(group);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while parsing groups");
        }

        return groups;
    }

    public void parseGroupsFromFile2(String inputGroupsFile, Set<Team> teams1) {
        List<Group> groups = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputGroupsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length > 1) {
                    String groupName = parts[0];
                    List<Team> teams = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        String teamName = parts[i];
                        Team team = new Team(teamName);

                        Team t = teams1.stream()
                                .filter(team1 -> team1.getName().equals(team.getName()))
                                .findFirst().get();
                        t.setGroupName(groupName);
                        teams1.add(t);
                        teams.add(team);
                    }

                    Group group = new Group();
                    group.setName(groupName);
                    group.setTeams(teams);

                    groups.add(group);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while parsing groups");
        }

    }

    private static Game getGame(String[] parts) {
        String team1Name = parts[0];
        String[] scoreParts = parts[1].split(":");
        int goalsScored1 = Integer.parseInt(scoreParts[0]);
        int goalsScored2 = Integer.parseInt(scoreParts[1]);
        String team2Name = parts[2];

        Team team1 = new Team(team1Name);
        Team team2 = new Team(team2Name);


        Game game = new Game();

        game.setGoalsScored1(goalsScored1);
        game.setGoalsScored2(goalsScored2);

        team1.setGoalsScored(game.getGoalsScored1());
        team1.setGoalsConceded(game.getGoalsScored2());

        team2.setGoalsScored(game.getGoalsScored2());
        team2.setGoalsConceded(game.getGoalsScored1());

        if (game.getGoalsScored1() > game.getGoalsScored2()) {
            team1.setWinsCount(1);
            team1.setPoints(3);
            team2.setDefeatsCount(1);
        } else if (game.getGoalsScored1() < game.getGoalsScored2()) {
            team2.setWinsCount(1);
            team2.setPoints(3);
            team1.setDefeatsCount(1);
        } else {
            team1.setDrawsCount(1);
            team2.setDrawsCount(1);
            team1.setPoints(1);
            team2.setPoints(1);
        }

        game.setTeam1(team1);
        game.setTeam2(team2);
        return game;
    }
}
