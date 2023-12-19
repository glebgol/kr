import java.util.Comparator;

public class TeamComparator implements Comparator<Team> {
    @Override
    public int compare(Team o1, Team o2) {
        int comparison = Integer.compare(o1.getPoints(), o2.getPoints());
        return comparison == 0 ? Integer.compare(o2.getWinsCount() + o2.getDrawsCount() + o2.getDefeatsCount(),
                o1.getWinsCount() + o1.getDrawsCount() + o1.getDefeatsCount()) : -comparison;
    }
}
