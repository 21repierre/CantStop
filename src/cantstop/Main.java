package cantstop;

import com.github.chen0040.rl.models.QModel;
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
        int[] stratToTest = {49, 148};
        // On instancie le jeu
        Jeu j = new Jeu();

        // On lance un tournoi avec 10 000 exécutions à chaque fois (entre chaque couple d'IA).
        for (int i = 0; i < 1; i++) {
            //System.out.println(Strat49.PARAM);
            j.runIA(stratToTest, 1000000);
            System.out.println(Strat49.learner.toJson());
        }
        //j.runIA(stratToTest, 10000);

        // Humain vs IA avec le numéro d'IA en paramètre
        //j.runHvsIA(0);

        // Partie "normale"
        //j.run();
    }
}
