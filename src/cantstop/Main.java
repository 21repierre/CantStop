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
        int[] stratToTest = {49, 8};
        // On instancie le jeu
        Jeu j = new Jeu();

        // On lance un tournoi avec 10 000 exécutions à chaque fois (entre chaque couple d'IA).
        for (int i = 0; i < 1; i++) {
            //System.out.println(Strat49.PARAM);
            j.runIA(stratToTest, 100000);
            Strat49.PARAM += 0.25;
        }
        //System.out.println(Strat10.failed / Strat10.nbTours * 100);
    }
}
