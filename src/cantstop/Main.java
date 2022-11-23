package cantstop;

import strategies.Strat49;

/**
 * Lancement d'un tournoi ou d'une partie
 *
 * @author afleury
 */
public class Main {
    public static void main(String[] args) {
        // Mettez dans ce tableau les strats à tester
        // Vous pouvez ajouter la vôtre avec votre numéro de stratégie (cf StratX.java pour voir comment le calculer)
        int[] stratToTest = {1, 49};
        // On instancie le jeu
        Jeu j = new Jeu();

        // On lance un tournoi avec 10 000 exécutions à chaque fois (entre chaque couple d'IA).
        /*for (int i = 0; i < 11; i++) {
            Strat49.DELEMITER = 0.6d + i / 100d;
            System.out.println(Strat49.DELEMITER);
            j.runIA(stratToTest, 10000);
        }*/
        j.runIA(stratToTest, 10000);

        // Humain vs IA avec le numéro d'IA en paramètre
        //j.runHvsIA(0);

        // Partie "normale"
        //j.run();
    }
}
