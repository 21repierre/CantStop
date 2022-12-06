package strategies;

import cantstop.Jeu;

import java.util.Arrays;
import java.util.Random;

public class Strat49 implements Strategie {

    public static double PARAM = 0.87;
    private final Random rng = new Random();
    int maxStep = 4; // ok avec 5 aussi
    int[][] stats = new int[][]{
            new int[]{2, 11, 12, 4}, new int[]{2, 3, 12, 4}, new int[]{10, 11, 12, 5}, new int[]{2, 3, 4, 5}, new int[]{3, 11, 12, 5}, new int[]{2, 3, 11, 5}, new int[]{2, 10, 12, 5}, new int[]{2, 4, 12, 5}, new int[]{2, 10, 11, 5}, new int[]{3, 4, 12, 5}, new int[]{9, 11, 12, 5}, new int[]{2, 3, 5, 5}, new int[]{2, 9, 12, 6}, new int[]{2, 3, 10, 6}, new int[]{3, 10, 12, 6}, new int[]{4, 11, 12, 6}, new int[]{2, 4, 11, 6}, new int[]{2, 5, 12, 6}, new int[]{2, 9, 11, 6}, new int[]{3, 5, 12, 6}, new int[]{3, 10, 11, 6}, new int[]{3, 4, 11, 6}, new int[]{9, 10, 12, 6}, new int[]{2, 4, 5, 6}, new int[]{9, 10, 11, 6}, new int[]{3, 4, 5, 6}, new int[]{8, 11, 12, 6}, new int[]{2, 3, 6, 6}, new int[]{2, 9, 10, 7}, new int[]{3, 9, 11, 7}, new int[]{3, 5, 11, 7}, new int[]{4, 5, 12, 7}, new int[]{3, 9, 12, 7}, new int[]{5, 11, 12, 7}, new int[]{2, 5, 11, 7}, new int[]{2, 3, 9, 7}, new int[]{2, 8, 11, 7}, new int[]{3, 6, 12, 7}, new int[]{2, 4, 10, 7}, new int[]{4, 10, 12, 7}, new int[]{2, 8, 12, 7}, new int[]{2, 6, 12, 7}, new int[]{8, 10, 11, 7}, new int[]{3, 4, 6, 7}, new int[]{7, 11, 12, 7}, new int[]{2, 3, 7, 7}, new int[]{6, 11, 12, 7}, new int[]{4, 9, 12, 7}, new int[]{4, 10, 11, 7}, new int[]{3, 4, 10, 7}, new int[]{2, 6, 11, 7}, new int[]{2, 4, 9, 7}, new int[]{3, 8, 12, 7}, new int[]{2, 5, 10, 7}, new int[]{5, 10, 12, 7}, new int[]{2, 3, 8, 7}, new int[]{8, 10, 12, 7}, new int[]{3, 6, 11, 7}, new int[]{3, 8, 11, 7}, new int[]{4, 9, 11, 7}, new int[]{3, 5, 10, 7}, new int[]{2, 4, 6, 7}, new int[]{5, 9, 12, 7}, new int[]{2, 5, 9, 7}, new int[]{8, 9, 12, 7}, new int[]{8, 9, 11, 7}, new int[]{2, 5, 6, 7}, new int[]{3, 5, 6, 7}, new int[]{3, 7, 11, 7}, new int[]{5, 9, 11, 7}, new int[]{3, 5, 9, 7}, new int[]{2, 7, 11, 7}, new int[]{5, 10, 11, 7}, new int[]{3, 4, 9, 7}, new int[]{3, 9, 10, 7}, new int[]{4, 5, 11, 7}, new int[]{3, 7, 12, 7}, new int[]{2, 7, 12, 7}, new int[]{7, 9, 11, 7}, new int[]{3, 5, 7, 7}, new int[]{7, 10, 11, 7}, new int[]{3, 4, 7, 7}, new int[]{8, 9, 10, 7}, new int[]{6, 10, 11, 7}, new int[]{3, 4, 8, 7}, new int[]{4, 5, 6, 7}, new int[]{5, 9, 10, 7}, new int[]{4, 5, 9, 7}, new int[]{7, 10, 12, 8}, new int[]{2, 4, 7, 8}, new int[]{6, 9, 11, 8}, new int[]{3, 5, 8, 8}, new int[]{7, 9, 12, 8}, new int[]{2, 5, 7, 8}, new int[]{2, 6, 10, 8}, new int[]{4, 8, 12, 8}, new int[]{2, 8, 10, 8}, new int[]{6, 10, 12, 8}, new int[]{4, 6, 12, 8}, new int[]{2, 4, 8, 8}, new int[]{2, 8, 9, 8}, new int[]{4, 9, 10, 8}, new int[]{3, 6, 10, 8}, new int[]{4, 5, 10, 8}, new int[]{4, 8, 11, 8}, new int[]{5, 6, 12, 8}, new int[]{5, 8, 11, 8}, new int[]{3, 6, 9, 8}, new int[]{6, 9, 12, 8}, new int[]{2, 5, 8, 8}, new int[]{2, 7, 10, 8}, new int[]{3, 8, 10, 8}, new int[]{4, 6, 11, 8}, new int[]{5, 8, 12, 8}, new int[]{2, 6, 9, 8}, new int[]{4, 7, 12, 8}, new int[]{2, 7, 9, 8}, new int[]{3, 8, 9, 8}, new int[]{5, 6, 11, 8}, new int[]{4, 7, 11, 8}, new int[]{5, 7, 12, 8}, new int[]{3, 7, 10, 8}, new int[]{5, 7, 11, 8}, new int[]{3, 7, 9, 8}, new int[]{6, 9, 10, 8}, new int[]{4, 5, 8, 8}, new int[]{7, 9, 10, 8}, new int[]{4, 5, 7, 8}, new int[]{5, 7, 9, 8}, new int[]{6, 8, 11, 8}, new int[]{3, 6, 8, 8}, new int[]{5, 6, 10, 8}, new int[]{4, 8, 9, 8}, new int[]{5, 8, 10, 8}, new int[]{7, 8, 12, 8}, new int[]{4, 6, 9, 8}, new int[]{2, 6, 7, 8}, new int[]{7, 8, 11, 8}, new int[]{3, 6, 7, 8}, new int[]{5, 8, 9, 8}, new int[]{5, 6, 9, 8}, new int[]{4, 7, 10, 8}, new int[]{6, 8, 12, 8}, new int[]{4, 6, 10, 8}, new int[]{4, 8, 10, 8}, new int[]{2, 6, 8, 8}, new int[]{7, 8, 10, 8}, new int[]{4, 6, 7, 8}, new int[]{7, 8, 9, 8}, new int[]{5, 6, 7, 8}, new int[]{2, 7, 8, 8}, new int[]{6, 7, 12, 8}, new int[]{4, 7, 9, 8}, new int[]{6, 7, 11, 8}, new int[]{5, 7, 10, 8}, new int[]{3, 7, 8, 8}, new int[]{6, 8, 9, 8}, new int[]{5, 6, 8, 8}, new int[]{6, 7, 10, 9}, new int[]{4, 7, 8, 9}, new int[]{6, 8, 10, 9}, new int[]{4, 6, 8, 9}, new int[]{6, 7, 9, 9}, new int[]{5, 7, 8, 9}, new int[]{6, 7, 8, 9}
    };
    int[][] probas1 = new int[][]{
            new int[]{2, 13, 0}, new int[]{3, 23, 0}, new int[]{4, 36, 0}, new int[]{5, 45, 1}, new int[]{6, 56, 1}, new int[]{7, 64, 2}, new int[]{8, 56, 1}, new int[]{9, 45, 1}, new int[]{10, 36, 0}, new int[]{11, 23, 0}, new int[]{12, 13,}
    };
    int[][] probas2 = new int[][]{
            new int[]{2, 3, 32, 0}, new int[]{2, 4, 44, 1}, new int[]{2, 5, 53, 1}, new int[]{2, 6, 63, 2}, new int[]{2, 7, 71, 2}, new int[]{2, 8, 67, 2}, new int[]{2, 9, 56, 1}, new int[]{2, 10, 47, 1}, new int[]{2, 11, 36, 1}, new int[]{2, 12, 26, 0}, new int[]{3, 4, 47, 1}, new int[]{3, 5, 53, 1}, new int[]{3, 6, 64, 2}, new int[]{3, 7, 71, 2}, new int[]{3, 8, 68, 2}, new int[]{3, 9, 64, 2}, new int[]{3, 10, 56, 1}, new int[]{3, 11, 45, 1}, new int[]{3, 12, 36, 1}, new int[]{4, 5, 61, 2}, new int[]{4, 6, 72, 3}, new int[]{4, 7, 77, 3}, new int[]{4, 8, 75, 3}, new int[]{4, 9, 68, 2}, new int[]{4, 10, 67, 2}, new int[]{4, 11, 56, 1}, new int[]{4, 12, 47, 1}, new int[]{5, 6, 73, 3}, new int[]{5, 7, 78, 4}, new int[]{5, 8, 77, 3}, new int[]{5, 9, 69, 2}, new int[]{5, 10, 68, 2}, new int[]{5, 11, 64, 2}, new int[]{5, 12, 56, 1}, new int[]{6, 7, 84, 5}, new int[]{6, 8, 82, 5}, new int[]{6, 9, 77, 3}, new int[]{6, 10, 75, 3}, new int[]{6, 11, 68, 2}, new int[]{6, 12, 67, 2}, new int[]{7, 8, 84, 5}, new int[]{7, 9, 78, 4}, new int[]{7, 10, 77, 3}, new int[]{7, 11, 71, 2}, new int[]{7, 12, 71, 2}, new int[]{8, 9, 73, 3}, new int[]{8, 10, 72, 3}, new int[]{8, 11, 64, 2}, new int[]{8, 12, 63, 2}, new int[]{9, 10, 61, 2}, new int[]{9, 11, 53, 1}, new int[]{9, 12, 53, 1}, new int[]{10, 11, 47, 1}, new int[]{10, 12, 44, 1}, new int[]{11, 12, 32, 0}
    };
    int[][] probas3 = new int[][]{
            new int[]{3, 4, 10, 76, 3}, new int[]{3, 4, 11, 66, 2}, new int[]{3, 4, 12, 58, 1}, new int[]{3, 5, 6, 77, 3}, new int[]{3, 5, 7, 79, 4}, new int[]{3, 5, 8, 81, 4}, new int[]{3, 5, 9, 78, 4}, new int[]{3, 5, 10, 76, 3}, new int[]{3, 5, 11, 71, 2}, new int[]{3, 5, 12, 64, 2}, new int[]{3, 6, 7, 86, 6}, new int[]{3, 6, 8, 85, 6}, new int[]{3, 6, 9, 83, 5}, new int[]{3, 6, 10, 82, 5}, new int[]{3, 6, 11, 76, 3}, new int[]{3, 6, 12, 74, 3}, new int[]{3, 7, 8, 89, 8}, new int[]{3, 7, 9, 84, 5}, new int[]{3, 7, 10, 84, 5}, new int[]{3, 7, 11, 78, 4}, new int[]{3, 7, 12, 78, 4}, new int[]{3, 8, 9, 84, 5}, new int[]{3, 8, 10, 83, 5}, new int[]{3, 8, 11, 76, 3}, new int[]{3, 8, 12, 76, 3}, new int[]{3, 9, 10, 78, 4}, new int[]{3, 9, 11, 71, 2}, new int[]{3, 9, 12, 71, 2}, new int[]{3, 10, 11, 66, 2}, new int[]{3, 10, 12, 63, 2}, new int[]{3, 11, 12, 53, 1}, new int[]{4, 5, 6, 80, 4}, new int[]{4, 5, 7, 85, 6}, new int[]{4, 5, 8, 85, 6}, new int[]{4, 5, 9, 80, 4}, new int[]{4, 5, 10, 82, 5}, new int[]{4, 5, 11, 78, 4}, new int[]{4, 5, 12, 71, 2}, new int[]{4, 6, 7, 89, 8}, new int[]{4, 6, 8, 91, 10}, new int[]{4, 6, 9, 86, 6}, new int[]{4, 6, 10, 88, 7}, new int[]{4, 6, 11, 83, 5}, new int[]{4, 6, 12, 82, 5}, new int[]{4, 7, 8, 90, 9}, new int[]{4, 7, 9, 89, 8}, new int[]{4, 7, 10, 88, 7}, new int[]{4, 7, 11, 84, 5}, new int[]{4, 7, 12, 83, 5}, new int[]{4, 8, 9, 86, 6}, new int[]{4, 8, 10, 88, 7}, new int[]{4, 8, 11, 82, 5}, new int[]{4, 8, 12, 81, 4}, new int[]{4, 9, 10, 82, 5}, new int[]{4, 9, 11, 76, 3}, new int[]{4, 9, 12, 76, 3}, new int[]{4, 10, 11, 76, 3}, new int[]{4, 10, 12, 74, 3}, new int[]{4, 11, 12, 63, 2}, new int[]{5, 6, 7, 89, 8}, new int[]{5, 6, 8, 90, 9}, new int[]{5, 6, 9, 87, 7}, new int[]{5, 6, 10, 86, 6}, new int[]{5, 6, 11, 84, 5}, new int[]{5, 6, 12, 82, 5}, new int[]{5, 7, 8, 91, 10}, new int[]{5, 7, 9, 85, 6}, new int[]{5, 7, 10, 89, 8}, new int[]{5, 7, 11, 84, 5}, new int[]{5, 7, 12, 84, 5}, new int[]{5, 8, 9, 87, 7}, new int[]{5, 8, 10, 86, 6}, new int[]{5, 8, 11, 83, 5}, new int[]{5, 8, 12, 83, 5}, new int[]{5, 9, 10, 80, 4}, new int[]{5, 9, 11, 78, 4}, new int[]{5, 9, 12, 76, 3}, new int[]{5, 10, 11, 78, 4}, new int[]{5, 10, 12, 76, 3}, new int[]{5, 11, 12, 71, 2}, new int[]{6, 7, 8, 92, 12}, new int[]{6, 7, 9, 91, 10}, new int[]{6, 7, 10, 90, 9}, new int[]{6, 7, 11, 89, 8}, new int[]{6, 7, 12, 89, 8}, new int[]{6, 8, 9, 90, 9}, new int[]{6, 8, 10, 91, 10}, new int[]{6, 8, 11, 85, 6}, new int[]{6, 8, 12, 88, 7}, new int[]{6, 9, 10, 85, 6}, new int[]{6, 9, 11, 81, 4}, new int[]{6, 9, 12, 83, 5}, new int[]{6, 10, 11, 80, 4}, new int[]{6, 10, 12, 82, 5}, new int[]{6, 11, 12, 76, 3}, new int[]{7, 8, 9, 89, 8}, new int[]{7, 8, 10, 89, 8}, new int[]{7, 8, 11, 86, 6}, new int[]{7, 8, 12, 86, 6}, new int[]{7, 9, 10, 85, 6}, new int[]{7, 9, 11, 79, 4}, new int[]{7, 9, 12, 81, 4}, new int[]{7, 10, 11, 79, 4}, new int[]{7, 10, 12, 81, 4}, new int[]{7, 11, 12, 75, 3}, new int[]{8, 9, 10, 80, 4}, new int[]{8, 9, 11, 77, 3}, new int[]{8, 9, 12, 77, 3}, new int[]{8, 10, 11, 74, 3}, new int[]{8, 10, 12, 76, 3}, new int[]{8, 11, 12, 68, 2}, new int[]{9, 10, 11, 67, 2}, new int[]{9, 10, 12, 66, 2}, new int[]{9, 11, 12, 58, 1}, new int[]{10, 11, 12, 52, 1}
    };
    private double startGameParam = 1.255;
    private int currentStep = 0;

    public Strat49() {
    }

    @Override
    public int choix(Jeu j) {
        boolean startOfTheGame = true;
        if (j.scoreJoueurEnCours() + j.scoreAutreJoueur() >= 4) startOfTheGame = false;

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


            if (choix[i][0] > choix[i][1]) {
                int tmp = choix[i][0];
                choix[i][0] = choix[i][1];
                choix[i][1] = tmp;
            }
            // 2eme etape: probabilite de retirer cette colonne au prochina tour
            //scores[i] += (1 + j.getBonzesRestants()) * p(choix[i][0]) * (choix[i][1] != 0 ? p(choix[i][1]) : 0);

            // En "debut" de partie favorise les colonnes courtes / apres favorise les colonnes longues
            if (choix[i][0] <= 5 || choix[i][0] >= 9) {
                scores[i] *= startOfTheGame ? startGameParam : 1d / startGameParam;
            } else {
                scores[i] *= !startOfTheGame ? startGameParam : 1d / startGameParam;
            }

            if (choix[i][1] <= 5 || choix[i][1] >= 9) {
                scores[i] *= startOfTheGame ? startGameParam : 1d / startGameParam;
            } else {
                scores[i] *= !startOfTheGame ? startGameParam : 1d / startGameParam;
            }

            // Si je complete la colonne
            if (myProgress[choix[i][1] - 2] + 1 == maxs[choix[i][1] - 2] || choix[i][0] != 0 && myProgress[choix[i][0] - 2] + 1 == maxs[choix[i][0] - 2]) {
                scores[i] *= 100;
            }
            if (choix[i][0] == choix[i][1]) {
                if (myProgress[choix[i][0] - 2] + 2 >= maxs[choix[i][0] - 2]) {
                    scores[i] *= 100;
                } else scores[i] *= probas1[choix[i][0] - 2][1] / 100d;
            }
            // Cas generaux
            else if (j.getBonzesRestants() == 3) {
                for (int[] proba : probas2) {
                    if (proba[0] == choix[i][0] && proba[1] == choix[i][1]) {
                        scores[i] *= proba[2] / 100d;
                        break;
                    }
                }
            } else if (j.getBonzesRestants() == 1) {
                if (bonzes[0][0] == choix[i][0] && bonzes[0][1] == choix[i][1] || bonzes[0][0] == choix[i][1] && bonzes[0][1] == choix[i][0]) {
                    for (int[] proba : probas2) {
                        if (proba[0] == choix[i][0] && proba[1] == choix[i][1]) {
                            scores[i] *= proba[2] / 100d;
                            break;
                        }
                    }
                } else {
                    int[] tmp = new int[3];
                    tmp[0] = choix[i][0];
                    tmp[1] = choix[i][1];
                    if (bonzes[0][0] == tmp[0] || bonzes[0][0] == tmp[1]) tmp[2] = bonzes[1][0];
                    else tmp[2] = bonzes[0][0];
                    tmp = Arrays.stream(tmp).sorted().toArray();

                    for (int[] proba : probas3) {
                        if (proba[0] == tmp[0] && proba[1] == tmp[1] && proba[2] == tmp[2]) {
                            scores[i] *= proba[3] / 100d;
                            break;
                        }
                    }
                }
            } else {
                int[] tmp = new int[3];
                tmp[0] = choix[i][0];
                tmp[1] = choix[i][1];
                if (bonzes[0][0] == tmp[0] || bonzes[0][0] == tmp[1]) tmp[2] = bonzes[1][0];
                else tmp[2] = bonzes[0][0];
                tmp = Arrays.stream(tmp).sorted().toArray();

                for (int[] proba : probas3) {
                    if (proba[0] == tmp[0] && proba[1] == tmp[1] && proba[2] == tmp[2]) {
                        scores[i] *= proba[3] / 100d;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == scores[bestChoice]) {
                // Si score egaux on favorise les nombres pairs
                int c1 = 0;
                int c2 = 0;
                if (choix[i][0] % 2 == 0) c1++;
                if (choix[i][1] % 2 == 0) c1++;
                if (choix[bestChoice][0] % 2 == 0) c2++;
                if (choix[bestChoice][1] % 2 == 0) c2++;
                if (c1 > c2) bestChoice = i;
            }
            if (scores[i] > scores[bestChoice]) bestChoice = i;
        }

        currentStep++;
        return bestChoice;
    }

    @Override
    public boolean stop(Jeu j) {
        int[][] bonzes = j.getBonzes();
        int[] maxs = j.getMaximum();

        for (int[] bonze : bonzes) {
            if (bonze[0] == 0) continue;
            if (maxs[bonze[0] - 2] == bonze[1]) return true;
        }

        if (j.getBonzesRestants() == 0) {
            if (j.scoreAutreJoueur() == 2) return false;
            int[] ids = Arrays.stream(new int[]{bonzes[0][0], bonzes[1][0], bonzes[2][0]}).sorted().toArray();
            for (int[] stat : probas3) {
                if (stat[0] == ids[0] && stat[1] == ids[1] && stat[2] == ids[2]) {
                    if (stat[3] < 65 && currentStep >= stat[4]) {
                        currentStep = 0;
                        return rng.nextFloat() > 0.33;
                    } else if (0.87 * currentStep >= stat[4]) {
                        currentStep = 0;
                        return true;
                    } else return false;
                }
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return "BOUDVILLAIN PIERRE";
    }
}
