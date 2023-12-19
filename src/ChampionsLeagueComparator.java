import java.util.Comparator;
import java.util.Objects;

public class ChampionsLeagueComparator implements Comparator<Team> {
    @Override
    public int compare(Team t1, Team t2) {
        if (!Objects.equals(t1.getName(), t2.getName())) {
            return t1.getName().compareTo(t2.getName());
        }

        return  Integer.compare(t1.getPoints(), t2.getPoints());
    }
}
