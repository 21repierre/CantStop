package cantstop;

import strategies.Strat49;

import java.util.Random;

/**
 * Lancement d'un tournoi ou d'une partie
 *
 * @author afleury
 */
public class Main {
    public static void main(String[] args) {
        var r1 = new Random();
        int d1 = r1.nextInt(6);
        int d2 = r1.nextInt(6);

        // Mettez dans ce tableau les strats à tester
        // Vous pouvez ajouter la vôtre avec votre numéro de stratégie (cf StratX.java pour voir comment le calculer)
        int[] stratToTest = {49, 148};
        // On instancie le jeu
        Jeu j = new Jeu();

        // On lance un tournoi avec 10 000 exécutions à chaque fois (entre chaque couple d'IA).
        for (int i = 0; i < 1; i++) {
            //System.out.println(Strat49.PARAM);
            j.runIA(stratToTest, 10000);
            Strat49.PARAM += 0.001;
        }
        //j.runIA(stratToTest, 10000);

        // Humain vs IA avec le numéro d'IA en paramètre
        //j.runHvsIA(0);

        // Partie "normale"
        //j.run();
    }
}
