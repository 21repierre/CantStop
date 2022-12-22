package strategies;

import cantstop.Jeu;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Pierre Boudvillain <pierre.boudvil1@gmail.com>
 * @project CorrectionTP4
 */
public class Strat10 implements Strategie {

    public static double PARAM = 7E-5;
    public static double failed = 0;
    public static int nbTours = 0;
    private static boolean againstMiddle = false;
    double cantStop = 0;
    double[] probas = new double[]{13.194444444444445, 23.30246913580247, 35.57098765432099, 44.75308641975309, 56.09567901234568, 64.35185185185185, 56.09567901234568, 44.75308641975309, 35.57098765432099, 23.30246913580247, 13.194444444444445};
    double[] probasLength = new double[]{22.970652220507546, 6.870837971640882, 7.205621855192848, 7.201234082129267, 17.307068100887772, 32.45589863607443, 17.307068100887772, 7.201234082129267, 7.205621855192848, 6.870837971640882, 22.970652220507546};
    private int midProg = 0;
    private int oProg = 0;

    @Override
    public int choix(Jeu j) {
        if (j.getBonzesRestants() == 3 && cantStop != 0) {
            cantStop = 0;
            nbTours++;
            failed++;
        }

        int[][] choix = j.getLesChoix();
        int[][] bonzes = j.getBonzes();
        int[] maxs = j.getMaximum();
        int[] myProgress = j.avancementJoueurEnCours();
        int[] otProgress = j.avancementAutreJoueur();

        int bestChoice = 0;

        double[] scores = new double[j.getNbChoix()];
        Map<Integer, Integer[]> finisher = new LinkedHashMap<>();

        // Chaque choix se voit attribuer un score
        for (int i = 0; i < j.getNbChoix(); i++) {

            // 1ere etape: avancement relatif par rapport a l'adversaire
            boolean b1 = false;
            boolean b2 = choix[i][1] == 0;
            for (int[] bonze : bonzes) {
                if (bonze[0] == choix[i][0]) {
                    if (choix[i][0] == choix[i][1]) {
                        if (bonze[1] + 2 == maxs[bonze[0] - 2]) finisher.put(i, new Integer[]{2});//return i;
                    } else if (bonze[1] + 1 == maxs[bonze[0] - 2]) {
                        finisher.put(i, new Integer[]{1, 0});
                    }
                    scores[i] += (bonze[1] - otProgress[choix[i][0] - 2]) / (double) maxs[bonze[0] - 2];
                    b1 = true;
                }
                if (choix[i][1] != 0 && bonze[0] == choix[i][1]) {
                    if (bonze[1] + 1 == maxs[bonze[0] - 2]) {
                        finisher.put(i, new Integer[]{1, 1});
                    }
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
            // Si je complete la colonne
            scores[i]=0;

            if (choix[i][0] == choix[i][1]) {
                if (myProgress[choix[i][0] - 2] + 2 == maxs[choix[i][0] - 2]) {
                    finisher.put(i, new Integer[]{2});
                    //scores[i] += 10;
                }
            } else if (myProgress[choix[i][0] - 2] + 1 == maxs[choix[i][0] - 2] || choix[i][1] != 0 && myProgress[choix[i][1] - 2] + 1 == maxs[choix[i][1] - 2]) {
                //scores[i] += 10;
                finisher.put(i, new Integer[]{1, (myProgress[choix[i][0] - 2] + 1 == maxs[choix[i][0] - 2] ? 0 : 1)});
            }
            // 2eme etape: probabilite de retirer cette colonne au prochina tour

            // FAvorise les 3 colonnes du milieu sauf si on est contre une strat full mid -> on elargie aux 2 colonnes de chaques cotes
            /*
            if (againstMiddle) {
                if (choix[i][0] >= 4 && choix[i][0] <= 5 || choix[i][0] >= 9 && choix[i][0] <= 10)
                    scores[i] *= probasLength[choix[i][0] - 2] / 80d;
                if (choix[i][1] >= 4 && choix[i][1] <= 5 || choix[i][1] >= 9 && choix[i][1] <= 10)
                    scores[i] *= probasLength[choix[i][1] - 2] / 80d;
            } else {
                if (choix[i][0] >= 6 && choix[i][0] <= 8) scores[i] += probasLength[choix[i][0] - 2] / 60d;
                if (choix[i][1] >= 6 && choix[i][1] <= 8) scores[i] += probasLength[choix[i][1] - 2] / 60d;
            }*/
            if (choix[i][1] != 0)
                scores[i] += probasLength[choix[i][1] - 2] / 33d;
            scores[i] += probasLength[choix[i][0] - 2] / 33d;
        }
        if (finisher.size() > 0) {
            if (finisher.values().stream().anyMatch(s -> s[0] == 2)) {
                final int[] bestI = {-1};
                finisher.forEach((i, s) -> {
                    if (s[0] == 2) {
                        if (bestI[0] == -1) bestI[0] = i;
                        else {
                            int[] cb = choix[bestI[0]];
                            int[] c = choix[i];
                            double pb = otProgress[cb[0] - 2] / (double) maxs[cb[0] - 2] + otProgress[cb[1] - 2] / (double) maxs[cb[1] - 2];
                            double p = otProgress[c[0] - 2] / (double) maxs[c[0] - 2] + otProgress[c[1] - 2] / (double) maxs[c[1] - 2];
                            if (p > pb) bestI[0] = i;
                        }
                    }
                });
                return bestI[0];
            } else {
                bestChoice = -1;
                double bestScore = 0;
                for (Integer i : finisher.keySet()) {
                    if (finisher.get(i)[0] == 2) continue;
                    if (bestChoice == -1) {
                        bestChoice = i;
                        bestScore = probas[choix[i][finisher.get(i)[1]] - 2];
                        continue;
                    }
                    double score = probas[choix[i][finisher.get(i)[1]] - 2];
                    if (score > bestScore) bestChoice = i;
                }
                return bestChoice;
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

        cantStop += (myProgress[choix[bestChoice][0] - 2] == 0 ? 2 : 1) * Math.ceil((70 - probas[choix[bestChoice][0] - 2]) / 10d);
        if (choix[bestChoice][1] != 0) {
            cantStop += (myProgress[choix[bestChoice][1] - 2] == 0 ? 2 : 1) * Math.ceil((70 - probas[choix[bestChoice][1] - 2]) / 10d);
        }

        return bestChoice;
    }

    @Override
    public boolean stop(Jeu j) {
        int[][] bonzes = j.getBonzes();
        int[] maxs = j.getMaximum();
        int[] prog = j.avancementJoueurEnCours();
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
            //System.out.println(prog[bonzes[0][0] - 2] + " - " + bonzes[0][1]);

            int l1 = bonzes[0][1] - prog[bonzes[0][0] - 2];
            double c1 = Math.pow(probas[bonzes[0][0] - 2] / 100d, l1);
            int l2 = bonzes[1][1] - prog[bonzes[1][0] - 2];
            double c2 = Math.pow(probas[bonzes[1][0] - 2] / 100d, l2);
            int l3 = bonzes[2][1] - prog[bonzes[2][0] - 2];
            double c3 = Math.pow(probas[bonzes[2][0] - 2] / 100d, l3);
            double prod = c1 * c2 * c3;
            double prod2 = prod;
            //System.out.println(prod);
            prod = prod * probas[bonzes[0][0] - 2] / 100d
                    + prod * probas[bonzes[1][0] - 2] / 100d
                    + prod * probas[bonzes[2][0] - 2] / 100d
                    - prod * probas[bonzes[0][0] - 2] / 100d * probas[bonzes[1][0] - 2] / 100d
                    - prod * probas[bonzes[0][0] - 2] / 100d * probas[bonzes[2][0] - 2] / 100d
                    - prod * probas[bonzes[1][0] - 2] / 100d * probas[bonzes[2][0] - 2] / 100d
                    + prod * probas[bonzes[0][0] - 2] / 100d * probas[bonzes[1][0] - 2] / 100d * probas[bonzes[2][0] - 2] / 100d
            ;
            double paramAdapt = 0;
            if (j.scoreJoueurEnCours() - j.scoreAutreJoueur() < 0) {
                //vrm en retard
                paramAdapt = -2E-5;
            } else if (j.scoreJoueurEnCours() - j.scoreAutreJoueur() > 0) {
                paramAdapt = PARAM;
            }
            //System.out.println(prod2 - prod);
            if (prod2 - prod <= 4.9E-5 + paramAdapt) {
                cantStop = 0;
                nbTours++;
                return true;
            }

            //if (j.scoreAutreJoueur() == 2 && j.scoreJoueurEnCours() <= 1) return false;
            /*if (cantStop > 28) {
                cantStop = 0;
                nbTours++;
                return true;
            }*/
        }
        return false;
    }

    @Override
    public String getName() {
        return "BP v2.49_3";
    }
}
