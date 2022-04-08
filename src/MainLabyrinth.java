import java.io.IOException;

public class MainLabyrinth {
    public static void main(String[] args) throws InterruptedException, IOException {
        Labyrinth laby = new Labyrinth();

        laby.printLaby();
        laby.solve();
    }
}
