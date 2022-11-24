package strategies;

import cantstop.Jeu;

import java.util.Arrays;
import java.util.Random;

public class Strat49 implements Strategie {

    public static double THRESHOLD = 0.66;
    private final Random rng = new Random();
    int maxStep = 4; // ok avec 5 aussi
    int[][] stats = new int[][]{
            new int[]{2, 11, 12, 4}, new int[]{2, 3, 12, 4}, new int[]{10, 11, 12, 5}, new int[]{2, 3, 4, 5}, new int[]{3, 11, 12, 5}, new int[]{2, 3, 11, 5}, new int[]{2, 10, 12, 5}, new int[]{2, 4, 12, 5}, new int[]{2, 10, 11, 5}, new int[]{3, 4, 12, 5}, new int[]{9, 11, 12, 5}, new int[]{2, 3, 5, 5}, new int[]{2, 9, 12, 6}, new int[]{2, 3, 10, 6}, new int[]{3, 10, 12, 6}, new int[]{4, 11, 12, 6}, new int[]{2, 4, 11, 6}, new int[]{2, 5, 12, 6}, new int[]{2, 9, 11, 6}, new int[]{3, 5, 12, 6}, new int[]{3, 10, 11, 6}, new int[]{3, 4, 11, 6}, new int[]{9, 10, 12, 6}, new int[]{2, 4, 5, 6}, new int[]{9, 10, 11, 6}, new int[]{3, 4, 5, 6}, new int[]{8, 11, 12, 6}, new int[]{2, 3, 6, 6}, new int[]{2, 9, 10, 7}, new int[]{3, 9, 11, 7}, new int[]{3, 5, 11, 7}, new int[]{4, 5, 12, 7}, new int[]{3, 9, 12, 7}, new int[]{5, 11, 12, 7}, new int[]{2, 5, 11, 7}, new int[]{2, 3, 9, 7}, new int[]{2, 8, 11, 7}, new int[]{3, 6, 12, 7}, new int[]{2, 4, 10, 7}, new int[]{4, 10, 12, 7}, new int[]{2, 8, 12, 7}, new int[]{2, 6, 12, 7}, new int[]{8, 10, 11, 7}, new int[]{3, 4, 6, 7}, new int[]{7, 11, 12, 7}, new int[]{2, 3, 7, 7}, new int[]{6, 11, 12, 7}, new int[]{4, 9, 12, 7}, new int[]{4, 10, 11, 7}, new int[]{3, 4, 10, 7}, new int[]{2, 6, 11, 7}, new int[]{2, 4, 9, 7}, new int[]{3, 8, 12, 7}, new int[]{2, 5, 10, 7}, new int[]{5, 10, 12, 7}, new int[]{2, 3, 8, 7}, new int[]{8, 10, 12, 7}, new int[]{3, 6, 11, 7}, new int[]{3, 8, 11, 7}, new int[]{4, 9, 11, 7}, new int[]{3, 5, 10, 7}, new int[]{2, 4, 6, 7}, new int[]{5, 9, 12, 7}, new int[]{2, 5, 9, 7}, new int[]{8, 9, 12, 7}, new int[]{8, 9, 11, 7}, new int[]{2, 5, 6, 7}, new int[]{3, 5, 6, 7}, new int[]{3, 7, 11, 7}, new int[]{5, 9, 11, 7}, new int[]{3, 5, 9, 7}, new int[]{2, 7, 11, 7}, new int[]{5, 10, 11, 7}, new int[]{3, 4, 9, 7}, new int[]{3, 9, 10, 7}, new int[]{4, 5, 11, 7}, new int[]{3, 7, 12, 7}, new int[]{2, 7, 12, 7}, new int[]{7, 9, 11, 7}, new int[]{3, 5, 7, 7}, new int[]{7, 10, 11, 7}, new int[]{3, 4, 7, 7}, new int[]{8, 9, 10, 7}, new int[]{6, 10, 11, 7}, new int[]{3, 4, 8, 7}, new int[]{4, 5, 6, 7}, new int[]{5, 9, 10, 7}, new int[]{4, 5, 9, 7}, new int[]{7, 10, 12, 8}, new int[]{2, 4, 7, 8}, new int[]{6, 9, 11, 8}, new int[]{3, 5, 8, 8}, new int[]{7, 9, 12, 8}, new int[]{2, 5, 7, 8}, new int[]{2, 6, 10, 8}, new int[]{4, 8, 12, 8}, new int[]{2, 8, 10, 8}, new int[]{6, 10, 12, 8}, new int[]{4, 6, 12, 8}, new int[]{2, 4, 8, 8}, new int[]{2, 8, 9, 8}, new int[]{4, 9, 10, 8}, new int[]{3, 6, 10, 8}, new int[]{4, 5, 10, 8}, new int[]{4, 8, 11, 8}, new int[]{5, 6, 12, 8}, new int[]{5, 8, 11, 8}, new int[]{3, 6, 9, 8}, new int[]{6, 9, 12, 8}, new int[]{2, 5, 8, 8}, new int[]{2, 7, 10, 8}, new int[]{3, 8, 10, 8}, new int[]{4, 6, 11, 8}, new int[]{5, 8, 12, 8}, new int[]{2, 6, 9, 8}, new int[]{4, 7, 12, 8}, new int[]{2, 7, 9, 8}, new int[]{3, 8, 9, 8}, new int[]{5, 6, 11, 8}, new int[]{4, 7, 11, 8}, new int[]{5, 7, 12, 8}, new int[]{3, 7, 10, 8}, new int[]{5, 7, 11, 8}, new int[]{3, 7, 9, 8}, new int[]{6, 9, 10, 8}, new int[]{4, 5, 8, 8}, new int[]{7, 9, 10, 8}, new int[]{4, 5, 7, 8}, new int[]{5, 7, 9, 8}, new int[]{6, 8, 11, 8}, new int[]{3, 6, 8, 8}, new int[]{5, 6, 10, 8}, new int[]{4, 8, 9, 8}, new int[]{5, 8, 10, 8}, new int[]{7, 8, 12, 8}, new int[]{4, 6, 9, 8}, new int[]{2, 6, 7, 8}, new int[]{7, 8, 11, 8}, new int[]{3, 6, 7, 8}, new int[]{5, 8, 9, 8}, new int[]{5, 6, 9, 8}, new int[]{4, 7, 10, 8}, new int[]{6, 8, 12, 8}, new int[]{4, 6, 10, 8}, new int[]{4, 8, 10, 8}, new int[]{2, 6, 8, 8}, new int[]{7, 8, 10, 8}, new int[]{4, 6, 7, 8}, new int[]{7, 8, 9, 8}, new int[]{5, 6, 7, 8}, new int[]{2, 7, 8, 8}, new int[]{6, 7, 12, 8}, new int[]{4, 7, 9, 8}, new int[]{6, 7, 11, 8}, new int[]{5, 7, 10, 8}, new int[]{3, 7, 8, 8}, new int[]{6, 8, 9, 8}, new int[]{5, 6, 8, 8}, new int[]{6, 7, 10, 9}, new int[]{4, 7, 8, 9}, new int[]{6, 8, 10, 9}, new int[]{4, 6, 8, 9}, new int[]{6, 7, 9, 9}, new int[]{5, 7, 8, 9}, new int[]{6, 7, 8, 9}
    };
    private int currentStep = 0;

    public Strat49() {
    }

    @Override
    public int choix(Jeu j) {
        int[][] choix = j.getLesChoix();
        int[][] bonzes = j.getBonzes();
        int[] maxs = j.getMaximum();
        int[] myProgress = j.avancementJoueurEnCours();
        int[] otProgress = j.avancementAutreJoueur();

        int bestChoice = 0;

        double[] scores = new double[j.getNbChoix()];

        // Chaque choix se voit attribuer un score
        for (int i = 0; i < j.getNbChoix(); i++) {

            // 1ere etape: avancement relatif par rapport a l'adversaire
            boolean b1 = false;
            boolean b2 = choix[i][1] == 0;
            for (int[] bonze : bonzes) {
                if (bonze[0] == choix[i][0]) {
                    scores[i] += (bonze[1] - otProgress[choix[i][0] - 2]) / (double) maxs[bonze[0] - 2];
                    b1 = true;
                }
                if (choix[i][1] != 0 && bonze[0] == choix[i][1]) {
                    scores[i] += (bonze[1] - otProgress[choix[i][1] - 2]) / (double) maxs[bonze[0] - 2];
                    b2 = true;
                }
            }
            if (!b1) {
                scores[i] += (myProgress[choix[i][0] - 2] - otProgress[choix[i][0] - 2]) / (double) maxs[choix[i][0] - 2];
            }
            if (!b2) {
                scores[i] += (myProgress[choix[i][1] - 2] - otProgress[choix[i][1] - 2]) / (double) maxs[choix[i][1] - 2];
            }

            // 2eme etape: probabilite de retirer cette colonne au prochina tour
            scores[i] += (1 + j.getBonzesRestants()) * p(choix[i][0]) * (choix[i][1] != 0 ? p(choix[i][1]) : 0);

        }

        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > scores[bestChoice]) bestChoice = i;
        }

        currentStep++;
        return bestChoice;
    }

    private double p(int n) {
        if (n < 2) return 0;
        if (n <= 7) return (n - 1) / 36d;
        else return (12 - n + 1) / 36d;
    }


    @Override
    public boolean stop(Jeu j) {
        int[][] bonzes = j.getBonzes();

        if (j.getBonzesRestants() == 0) {

            int[] ids = Arrays.stream(new int[]{bonzes[0][0], bonzes[1][0], bonzes[2][0]}).sorted().toArray();

            for (int[] stat : stats) {
                if (stat[0] == ids[0] && stat[1] == ids[1] && stat[2] == ids[2]) {
                    if (3.5 * currentStep >= stat[3]) {
                        currentStep = 0;
                        return true;
                    } else return false;
                }
            }
        }

        /*
        //Plus de chances de stop quand on joue bcp d'affile
        //Mais c'est module en fonction de l'etat de la partie

        double baseStop = rng.nextDouble(0, Math.max(maxStep - currentStep + 1, 1));


        //Modulation en fonction des bonzes
        for (int[] bonze : bonzes) {
            if (bonze[0] == 0) continue;
            // Pour chaque bonze qui a atteind le max on augmente la chance de stop
            if (bonze[1] == j.getMaximum()[bonze[0] - 2]) {
                baseStop -= 1.3;
            }
        }

        // On prend plus de risques quand le score de l'adversaire est haut
        baseStop += j.scoreAutreJoueur() / rng.nextDouble(1.3, 1.5);

        boolean shouldStop = baseStop < THRESHOLD;
        if (shouldStop) {
            currentStep = 0;
            return true;
        } else {
            return false;
        }*/
        return false;
    }

    @Override
    public String getName() {
        return "BOUDVILLAIN PIERRE2";
    }
}
