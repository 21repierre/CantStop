package strategies;

import cantstop.Jeu;

import java.util.Arrays;
import java.util.Random;

public class Strat49 implements Strategie {

    public static double DELEMITER = 0.66;
    private final Random rng = new Random();
    int maxStep = 4; // ok avec 5 aussi
    private int currentStep = 0;

    public Strat49() {
    }

    @Override
    public int choix(Jeu j) {
        int[][] choix = j.getLesChoix();
        int[][] bonzes = j.getBonzes();
        int[] maxs = j.getMaximum();

        int bestChoice = -1;

        // On choisit le meilleur parmis les bonzes deja places
        for (int i = 0; i < j.getNbChoix(); i++) {
            if (choix[i][0] == choix[i][1]) {
                if (bestChoice == -1) bestChoice = i;
                else {
                    for (int[] bonze : bonzes) {
                        if (bonze[0] == choix[i][0] + 2) {
                            for (int[] bonze2 : bonzes) {
                                if (bonze2[0] == choix[bestChoice][0] + 2) {
                                    if (bonze[1] > bonze2[1]) bestChoice = i;
                                }
                            }
                        }
                    }
                }
            }
            if (bestChoice == -1) {
                for (int[] bonze : bonzes) {
                    if (bonze[0] == choix[i][0] + 2 || bonze[0] == choix[i][1] + 2) {
                        bestChoice = i;
                        break;
                    }
                }
            } else {
                for (int[] bonze : bonzes) {
                    if (bonze[0] == choix[i][0] + 2) {
                        for (int[] bonze2 : bonzes) {
                            if (bonze2[0] == choix[bestChoice][0] + 2 || bonze2[0] == choix[bestChoice][1] + 2) {
                                if (bonze[1] > bonze2[1]) bestChoice = i;
                            }
                        }
                    }
                }
            }
        }

        double bestMoy = 0;
        if (bestChoice == -1) {
            bestChoice = 0;
            for (int i = 0; i < j.getNbChoix(); i++) {
                double moy = (choix[i][0] + choix[i][1]) / 2d;
                if (Math.abs(moy - 7) <= Math.abs(bestMoy - 7)) {
                    if (moy == bestMoy) {
                        System.out.println(Arrays.toString(choix[bestChoice]) + Arrays.toString(choix[i]));
                    }
                }
            }
        }

        currentStep++;
        return bestChoice == -1 ? rng.nextInt(j.getNbChoix()) : bestChoice;
    }

    @Override
    public boolean stop(Jeu j) {
        int[][] bonzes = j.getBonzes();

        /*
        Plus de chances de stop quand on joue bcp d'affile
        Mais c'est module en fonction de l'etat de la partie
         */
        double baseStop = rng.nextDouble(0, Math.max(maxStep - currentStep + 1, 1));

        /*
        Modulation en fonction des bonzes
         */
        for (int[] bonze : bonzes) {
            if (bonze[0] == 0) continue;
            // Pour chaque bonze qui a atteind le max on augmente la chance de stop
            if (bonze[1] == j.getMaximum()[bonze[0] - 2]) {
                baseStop -= 1.3;
            }
        }

        // On prend plus de risques quand le score de l'adversaire est haut
        baseStop += j.scoreAutreJoueur() / rng.nextDouble(1.3, 1.5);

        boolean shouldStop = baseStop < DELEMITER;
        if (shouldStop) {
            currentStep = 0;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getName() {
        return "Strat49";
    }
}
