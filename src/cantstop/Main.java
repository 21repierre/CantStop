package cantstop;

/**
 * Lancement d'un tournoi ou d'une partie
 *
 * @author afleury
 */
public class Main {
    public static void main(String[] args) {

        int[] stratToTest = {49, 148};
        // On instancie le jeu
        Jeu j = new Jeu();

        j.runIA(stratToTest, 10000);

    }
}
