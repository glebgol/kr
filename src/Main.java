public class Main {
    private static final int N = 1;
    private static final String INPUT_GAMES_FILE = "Game.txt";
    private static final String INPUT_GROUPS_FILE = "Groups.txt";
    private static final String OUTPUT_GROUPS_FILE = "GroupsOut.txt";


    public static void main(String[] args) {
        InterfBD interfBD = new ClassBD(INPUT_GAMES_FILE, INPUT_GROUPS_FILE, OUTPUT_GROUPS_FILE, "");
        interfBD.generateReport();
    }
}