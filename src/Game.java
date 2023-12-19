import java.util.Objects;

public class Game {
    private Team team1;
    private Team team2;
    private int goalsScored1;
    private int goalsScored2;

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getGoalsScored1() {
        return goalsScored1;
    }

    public void setGoalsScored1(int goalsScored1) {
        this.goalsScored1 = goalsScored1;
    }

    public int getGoalsScored2() {
        return goalsScored2;
    }

    public void setGoalsScored2(int goalsScored2) {
        this.goalsScored2 = goalsScored2;
    }

    @Override
    public String toString() {
        return "Game{" +
                "team1=" + team1 +
                ", team2=" + team2 +
                ", goalsScored1=" + goalsScored1 +
                ", goalsScored2=" + goalsScored2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return goalsScored1 == game.goalsScored1 && goalsScored2 == game.goalsScored2 && team1.getName().equals(game.team1.getName()) && team2.getName().equals(game.team2.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(team1, team2, goalsScored1, goalsScored2);
    }
}
