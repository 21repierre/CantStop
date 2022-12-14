package strategies;

import cantstop.Jeu;

/**
 * @author Pierre Boudvillain <pierre.boudvil1@gmail.com>
 * @project CorrectionTP4
 */
public class Strat11 implements Strategie {

    private static boolean againstMiddle = false;
    double cantStop = 0;
    int[] cantStops = new int[]{6, 5, 4, 3, 2, 1, 2, 3, 4, 5, 6};
    double[] stats = new double[]{13.194444444444445, 23.30246913580247, 35.57098765432099, 44.75308641975309, 56.09567901234568, 64.35185185185185, 56.09567901234568, 44.75308641975309, 35.57098765432099, 23.30246913580247, 13.194444444444445};
    private int nbTours = 0;
    private int midProg = 0;
    private int oProg = 0;

    @Override
    public int choix(Jeu j) {
        if (j.getBonzesRestants() == 3 && cantStop != 0) {
            cantStop = 0;
            nbTours++;
        }

        int[][] choix = j.getLesChoix();
        int[][] bonzes = j.getBonzes();
        int[] maxs = j.getMaximum();
        int[] myProgress = j.avancementJoueurEnCours();
        int[] otProgress = j.avancementAutreJoueur();


        for (int i = 0; i < otProgress.length; i++) {
            if (i >= 4 && i <= 6) midProg += otProgress[i];
            else oProg += otProgress[i];
        }
        if (nbTours >= 20) {
            againstMiddle = midProg > oProg;
        }

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
            if (b1 && b2) scores[i] += 5;


            if (choix[i][0] > choix[i][1]) {
                int tmp = choix[i][0];
                choix[i][0] = choix[i][1];
                choix[i][1] = tmp;
            }
            // 2eme etape: probabilite de retirer cette colonne au prochina tour
            //scores[i] += (1 + j.getBonzesRestants()) * p(choix[i][0]) * (choix[i][1] != 0 ? p(choix[i][1]) : 0);

            // FAvorise les 3 colonnes du milieu sauf si on est contre une strat full mid -> on elargie aux 2 colonnes de chaques cotes
            if (againstMiddle) {
                if (choix[i][0] >= 4 && choix[i][0] <= 5 || choix[i][0] >= 9 && choix[i][0] <= 10)
                    scores[i] *= stats[choix[i][0] - 2] / 23d;
                if (choix[i][1] >= 4 && choix[i][1] <= 5 || choix[i][1] >= 9 && choix[i][1] <= 10)
                    scores[i] *= stats[choix[i][1] - 2] / 23d;
            } else {
                if (choix[i][0] >= 6 && choix[i][0] <= 8) scores[i] *= stats[choix[i][0] - 2] / 23d;
                if (choix[i][1] >= 6 && choix[i][1] <= 8) scores[i] *= stats[choix[i][1] - 2] / 23d;
            }

            // Si je complete la colonne
            if (myProgress[choix[i][1] - 2] + 1 == maxs[choix[i][1] - 2] || choix[i][0] != 0 && myProgress[choix[i][0] - 2] + 1 == maxs[choix[i][0] - 2]) {
                scores[i] += 10;
            }
            if (choix[i][0] == choix[i][1]) {
                if (myProgress[choix[i][0] - 2] + 2 >= maxs[choix[i][0] - 2]) {
                    scores[i] += 10;
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

        /*if (myProgress[choix[bestChoice][1] - 2] == 0) cantStop += 2 * cantStops[choix[bestChoice][1] - 2];
        else cantStop += cantStops[choix[bestChoice][1] - 2];
        if (choix[bestChoice][0] != 0) {
            if (myProgress[choix[bestChoice][0] - 2] == 0) cantStop += 2 * cantStops[choix[bestChoice][0] - 2];
            else cantStop += cantStops[choix[bestChoice][0] - 2];
        }*/
        cantStop += (100 - stats[choix[bestChoice][1] - 2]);
        if (choix[bestChoice][0] != 0) {
            cantStop += (100 - stats[choix[bestChoice][0] - 2]);
        }

        return bestChoice;
    }

    @Override
    public boolean stop(Jeu j) {
        int[][] bonzes = j.getBonzes();
        int[] maxs = j.getMaximum();
        if (j.getBonzesRestants() == 0) {
            int bonzeFinish = 0;
            for (int[] bonze : bonzes) {
                if (bonze[0] == 0) continue;
                if (maxs[bonze[0] - 2] == bonze[1]) {
                    cantStop = 0;
                    nbTours++;
                    return true;
                }
            }
            if (j.scoreAutreJoueur() == 2 && j.scoreJoueurEnCours() <= 1) return false;
            if (cantStop > 525) {
                cantStop = 0;
                nbTours++;
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return "PB v2";
    }
}
