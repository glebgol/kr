import java.util.Objects;

public class Team {
    private String name;
    private int points = 0;
    private int winsCount = 0;
    private int drawsCount = 0;
    private int defeatsCount = 0;
    private int goalsScored = 0;
    private int goalsConceded = 0;
    private String groupName;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWinsCount() {
        return winsCount;
    }

    public void setWinsCount(int winsCount) {
        this.winsCount = winsCount;
    }

    public int getDrawsCount() {
        return drawsCount;
    }

    public void setDrawsCount(int drawsCount) {
        this.drawsCount = drawsCount;
    }

    public int getDefeatsCount() {
        return defeatsCount;
    }

    public void setDefeatsCount(int defeatsCount) {
        this.defeatsCount = defeatsCount;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", winsCount=" + winsCount +
                ", drawsCount=" + drawsCount +
                ", defeatsCount=" + defeatsCount +
                ", goalsScored=" + goalsScored +
                ", goalsConceded=" + goalsConceded +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
