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
                /*
                on recupere les bonzes du choix i et du bestChoice
                on compart les ecart (maximum - bonze) modules par les probabilites de tirer la colonne
                 */
                int[] b1 = null;
                int[] b2 = null;
                int[] bc1 = null;
                int[] bc2 = null;
                for (int[] bonze : bonzes) {
                    if (bonze[0] == choix[i][0] + 2) {
                        b1 = bonze;
                    }
                    if (bonze[0] == choix[i][1] + 2) {
                        b2 = bonze;
                    }
                    if (bonze[0] == choix[bestChoice][0] + 2) {
                        bc1 = bonze;
                    }
                    if (bonze[0] == choix[bestChoice][1] + 2) {
                        bc2 = bonze;
                    }
                }
                int[] diffs = new int[4];
                if (b1 != null) {
                    int ec1 = maxs[b1[0] - 2] - b1[1];
                    if (bc1 != null) {
                        int ecc1 = maxs[bc1[0] - 2] - bc1[1];
                        diffs[0] = ec1 * (b1[0] - 1) / 36 - ecc1 * (bc1[0] - 1) / 36; // >0 on prefere bestChoix[0]
                    }
                    if (bc2 != null) {
                        int ecc2 = maxs[bc2[0] - 2] - bc2[1];
                        diffs[2] = ec1 * (b1[0] - 1) / 36 - ecc2 * (bc2[0] - 1) / 36;
                    }
                }
                if (b2 != null) {
                    int ec2 = maxs[b2[0] - 2] - b2[1];
                    if (bc1 != null) {
                        int ecc1 = maxs[bc1[0] - 2] - bc1[1];
                        diffs[1] = ec2 * (b2[0] - 1) / 36 - ecc1 * (bc1[0] - 1) / 36;
                    }
                    if (bc2 != null) {
                        int ecc2 = maxs[bc2[0] - 2] - bc2[1];
                        diffs[3] = ec2 * (b2[0] - 1) / 36 - ecc2 * (bc2[0] - 1) / 36;
                    }
                }

                if (diffs[0] >= 0 && diffs[1] > 0 || diffs[2] >= 0 && diffs[3] > 0) {
                    // bestChoix mieux dans les 2 cas
                } else if (diffs[0] < 0 && diffs[1] < 0 || diffs[2] < 0 && diffs[3] < 0) {
                    // i meilleur
                    bestChoice = i;
                } else if (diffs[0] + diffs[1] > 0 || diffs[2] + diffs[3] > 0) {

                } else if (diffs[0] + diffs[1] < 0 || diffs[2] + diffs[3] < 0) {
                    bestChoice = i;
                } else {
                    if (rng.nextDouble() > 0.5) bestChoice = i;
                }
            }
        }
        if (bestChoice == -1) {
            bestChoice = 0;
            double bestMoy = (choix[0][0] + choix[0][1]) / 2d;
            for (int i = 0; i < j.getNbChoix(); i++) {
                double moy = (choix[i][0] + choix[i][1]) / 2d;
                if (Math.abs(moy - 7) <= Math.abs(bestMoy - 7)) {
                    if (moy == bestMoy) {
                        if (Math.abs(choix[i][0] - 7) + Math.abs(choix[i][1] - 7) < Math.abs(choix[bestChoice][0] - 7) + Math.abs(choix[bestChoice][1] - 7)) {
                            bestChoice = i;
                            bestMoy = moy;
                        }
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
        return "BOUDVILLAIN PIERRE";
    }
}
