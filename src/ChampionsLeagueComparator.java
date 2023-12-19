import java.util.Comparator;
import java.util.Objects;

public class ChampionsLeagueComparator implements Comparator<Team> {
    @Override
    public int compare(Team t1, Team t2) {
        if (Objects.equals(t1.getGroupName(), t2.getGroupName())) {
            return Integer.compare(t2.getPoints(), t1.getPoints());
        }

        return t1.getGroupName().compareTo(t2.getGroupName());
    }
}
