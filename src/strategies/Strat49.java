package strategies;

import cantstop.Jeu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *     <ul>
 *         <li>
 *             Choix du bonze:
 *             <br>
 *             Chaque choix se voit attribuer un score dependant de
 *             <ol>
 *                 <li>
 *                     ma position par rapport a celle de l'adversaire divise par la longueur de la colonne
 *                 </li>
 *                 <li>
 *                     la probabilite d'obtenir cette colonne et la longueur de la colonne
 *                 </li>
 *             </ol>
 *             Pour les choix qui finissent une colonne:
 *             <ol>
 *                 <li>
 *                     1 colonne avec un choix egal (ex: 7 & 7): celui ou l'adversaire est le plus avance pour le bloquer
 *                 </li>
 *                 <li>
 *                     1 colonne avec un choix different (ex: 7 & X) ou 2 colonnes avec un choix different (7 & 8): celui de proba la plus elevee
 *                 </li>
 *             </ol>
 *             Puis on enregistre un pas de tour dependant du choix (choix du centre => pas faible)
 *         </li>
 *         <li>
 *             Stop:
 *             <br>
 *             Si on a plus de bonze a poser:
 *             <ul>
 *                 <li>
 *                     Si au moins 1 colonne est finie on stop
 *                 </li>
 *                 <li>
 *                     Si le pas de tours total est plus grand qu'une limite on stop
 *                     La limite est adaptee si on est en tete (on prend moins de risque) ou si on est derriere (on en prend plus)
 *                 </li>
 *             </ul>
 *             Sinon on continue
 *         </li>
 *     </ul>
 *
 *
 * </p>
 *
 * @author Pierre Boudvillain <pierre.boudvil1@gmail.com>
 * @project CorrectionTP4
 */
public class Strat49 implements Strategie {
    public static double PARAM = 12;
    public static double failed = 0;
    public static int nbTours = 0;
    double cantStop = 0;
    double[] probas = new double[]{13.194444444444445, 23.30246913580247, 35.57098765432099, 44.75308641975309, 56.09567901234568, 64.35185185185185, 56.09567901234568, 44.75308641975309, 35.57098765432099, 23.30246913580247, 13.194444444444445};
    double[] probasLength = new double[]{22.970652220507546, 6.870837971640882, 7.205621855192848, 7.201234082129267, 17.307068100887772, 32.45589863607443, 17.307068100887772, 7.201234082129267, 7.205621855192848, 6.870837971640882, 22.970652220507546};
    boolean b = false;

    @Override
    public int choix(Jeu j) {
        if (j.getBonzesRestants() == 3 && cantStop != 0) {
            cantStop = 0;
            nbTours++;
            failed++;
            b = true;
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
                        if (bonze[1] + 2 == maxs[bonze[0] - 2]) finisher.put(i, new Integer[]{2});
                    } else if (bonze[1] + 1 == maxs[bonze[0] - 2]) {
                        finisher.put(i, new Integer[]{1, 0});
                    }
                    scores[i] += (bonze[1] - otProgress[choix[i][0] - 2]) / (double) maxs[bonze[0] - 2];
                    b1 = true;
                }
                if (choix[i][1] != 0 && bonze[0] == choix[i][1]) {
                    if (bonze[1] + 1 == maxs[bonze[0] - 2]) {
                        if (finisher.containsKey(i)) finisher.get(i)[1] = 2;
                        else finisher.put(i, new Integer[]{1, 1});
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

            if (choix[i][0] == choix[i][1]) {
                if (myProgress[choix[i][0] - 2] + 2 == maxs[choix[i][0] - 2]) {
                    finisher.put(i, new Integer[]{2});
                }
            } else if (myProgress[choix[i][0] - 2] + 1 == maxs[choix[i][0] - 2] || choix[i][1] != 0 && myProgress[choix[i][1] - 2] + 1 == maxs[choix[i][1] - 2]) {
                if (finisher.containsKey(i)) finisher.get(i)[1] = 2;
                else
                    finisher.put(i, new Integer[]{1, (myProgress[choix[i][0] - 2] + 1 == maxs[choix[i][0] - 2] ? 0 : 1)});
            }
            // 2eme etape: probabilite de retirer cette colonne au prochin tour

            if (choix[i][1] != 0)
                scores[i] += probasLength[choix[i][1] - 2] / 23d;
            scores[i] += probasLength[choix[i][0] - 2] / 23d;
        }
        if (finisher.size() > 0) {
            /*
            Si on a des finishers, on favorise d'abord ceux qui finissent 2 colonnes puis 1 colonne avec un +2 puis 1 colonne avec +1
            Parmis chaques, on chosit le choix qui finit les colonnes sur lesquelles l'adversaire etait le plus avance pour lui faire perdre sa progression
             */
            if (finisher.values().stream().anyMatch(s -> s.length == 2 && s[1] == 2)) {
                final int[] bestI = {-1};
                finisher.forEach((i, s) -> {
                    if (s.length == 2 && s[1] == 2) {
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
            } else if (finisher.values().stream().anyMatch(s -> s[0] == 2)) {
                final int[] bestI = {-1};
                finisher.forEach((i, s) -> {
                    if (s[0] == 2) {
                        if (bestI[0] == -1) bestI[0] = i;
                        else {
                            int[] cb = choix[bestI[0]];
                            int[] c = choix[i];
                            double pb = otProgress[cb[0] - 2] / (double) maxs[cb[0] - 2];
                            double p = otProgress[c[0] - 2] / (double) maxs[c[0] - 2];
                            if (p > pb) bestI[0] = i;
                        }
                    }
                });
                return bestI[0];
            } else {
                final int[] bestI = {-1};
                final double[] bestIS = {-1};
                finisher.forEach((i, s) -> {
                    if (s[0] == 1 && s[1] != 2) {
                        if (bestI[0] == -1) {
                            bestI[0] = i;
                            bestIS[0] = otProgress[choix[i][s[1]] - 2] / (double) maxs[choix[i][s[1]] - 2];
                        } else {
                            int[] c = choix[i];
                            double p = otProgress[c[s[1]] - 2] / (double) maxs[c[s[1]] - 2];
                            if (p > bestIS[0]) {
                                bestI[0] = i;
                                bestIS[0] = p;
                            }
                        }
                    }
                });
                return bestI[0];
            }
        }

        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == scores[bestChoice]) {
                // Si scores egaux on favorise les nombres pairs
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

    //@Override
    public boolean stop(Jeu j) {
        int[][] bonzes = j.getBonzes();
        int[] maxs = j.getMaximum();
        boolean[] blocks = j.getBloque();
        if (j.getBonzesRestants() == 0) {
            int nbPairs = 0;
            int finished = 0;
            List<Integer> close = new ArrayList<>();
            for (int[] bonze : bonzes) {
                if (bonze[0] == 0) continue;
                if (bonze[0] % 2 == 0) nbPairs++;
                if (blocks[bonze[0] - 2]) {
                    //cantStop = 0;
                    //nbTours++;
                    //return true;
                    finished++;
                } else {
                    if (bonze[1] + 1 == maxs[bonze[0] - 2]) close.add(bonze[0]);
                }
            }
            if (finished >= 2) {
                cantStop = 0;
                nbTours++;
                return true;
            } else if (finished == 1) {
                if (close.size() == 0) {
                    cantStop = 0;
                    nbTours++;
                    return true;
                } else {
                    boolean mid = false;
                    for (Integer i : close) {
                        if (i >= 6 && i <= 8) {
                            mid = Math.random() < probas[i - 2] / 100d;
                            break;
                        }
                    }
                    if (!mid) {
                        cantStop = 0;
                        nbTours++;
                        return true;
                    }
                }
            }

            int limit = 42;
            if (j.scoreJoueurEnCours() < j.scoreAutreJoueur())
                limit += j.scoreAutreJoueur() - j.scoreJoueurEnCours() + 3;
            else if (j.scoreJoueurEnCours() > j.scoreAutreJoueur())
                limit -= j.scoreJoueurEnCours() - j.scoreAutreJoueur() + 2;

            if (nbPairs == 3 && !b) {
                cantStop -= 0;
                b = true;
            }

            if (cantStop > limit) {
                cantStop = 0;
                nbTours++;
                return true;
            }
        }
        return false;
    }


    @Override
    public String getName() {
        return "PIERRE BOUDVILLAIN";
    }
}