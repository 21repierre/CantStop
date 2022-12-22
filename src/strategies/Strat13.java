package strategies;

import cantstop.Jeu;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Pierre Boudvillain <pierre.boudvil1@gmail.com>
 * @project CorrectionTP4
 */
public class Strat13 implements Strategie {

    public static double PARAM = 300;
    public static double failed = 0;
    public static int nbTours = 0;


    private final double risk = 2.5;
    private final int[] maxCols = new int[]{3, 5, 7, 9, 11, 13, 11, 9, 7, 5, 3};
    private final int bonzesRestants = 3;
    private int[] cols = new int[11];
    private int[] bonzes = new int[11];
    private boolean hasCompleted = false;
    private double remainMult = 0;

    private int sliceN(Object[] arr) {
        return 6 - (arr.length + 1);
    }

    private double getDistanceToWin(int[] cols, int[] bonzes) {
        int ret = 0;
        List<Double> tmp = new ArrayList<>();
        for (int i = 0; i < cols.length; i++) {
            tmp.add(Math.max(0d, maxCols[i] - ((cols[i] + (bonzes != null ? bonzes[i] : 0))) / (double) maxCols[i]));
        }
        return tmp.stream().sorted().limit(3).reduce(0d, Double::sum);
    }

    private Map<Integer, Double> nextCols(int[] choix) {
        var ret = new HashMap<Integer, Double>();
        for (int i = 0; i < maxCols.length; i++) {
            final int tmp = i;
            double add = Math.max(0, maxCols[i] - (cols[i] + bonzes[i] + Arrays.stream(choix).filter(val -> val == tmp + 2).count()));
            ret.put(i, add * 13 / (maxCols[i] * bonzesRestants == 0 ? 1 : Math.sqrt(maxCols[i])));
        }
        return ret;
    }

    private double[] nextColsValues(int[] choix) {
        var tmp = nextCols(choix);

        var ret = tmp.values().stream().sorted().limit(sliceN(tmp.values().stream().filter(d -> d == 0).toArray()));

        return ret.mapToDouble(d -> d).toArray();
    }

    private int[] bestColsWith(int[] cols) {
        var sc = nextCols(cols);
        return sc.keySet().stream().sorted().limit(sliceN(sc.values().stream().filter(c -> c == 0).toArray())).mapToInt(i -> i).toArray();
    }

    @Override
    public int choix(Jeu j) {
        if (j.getBonzesRestants() == 3) {
            //cantStop = 0;
            nbTours++;
            failed++;
            hasCompleted = false;
        }
        int[][] choix = j.getLesChoix();
        int[][] bz = j.getBonzes();

        bonzes = new int[11];
        for (int[] ints : bz) {
            if (ints[0] == 0) continue;
            bonzes[ints[0] - 2] = ints[1];
        }

        int[] maxs = j.getMaximum();
        cols = j.avancementJoueurEnCours();
        int[] otProgress = j.avancementAutreJoueur();
        int bestChoice;

        if (j.getNbChoix() == 1) return 0;

        double myRemaining = getDistanceToWin(cols, bonzes);
        double otRemaining = getDistanceToWin(otProgress, null);
        remainMult = (myRemaining > otRemaining) ? 1 : (otRemaining - myRemaining) / 6 + 1;

        List<Double> scores = Arrays.stream(choix).map(ch -> {
            int nbNewBonzes = (int) Arrays.stream(ch).filter(c -> c != 0).filter(c -> bonzes[c - 2] == 0).count();
            return Arrays.stream(nextColsValues(ch)).reduce(0d, Double::sum) * (1 + 0.1 * nbNewBonzes);
        }).toList();

        if (j.getBonzesRestants() > 0) {
            List<Integer> tmp = new ArrayList<>();
            for (int[] b : bz) {
                if (b[0] != 0) {
                    for (int i = 0; i < b[1]; i++) {
                        tmp.add(b[0]);
                    }
                }
            }
            int[] tmp2 = new int[tmp.size()];
            for (int i = 0; i < tmp.size(); i++) {
                tmp2[i] = tmp.get(i);
            }
            int[] bestCols = bestColsWith(tmp2);
            boolean test = false;

            for (int i = 0; i < j.getNbChoix(); i++) {
                if (bonzes[choix[i][0] - 2] != 0 || choix[i][1] != 0 && bonzes[choix[i][1] - 2] != 0) {
                    test = true;
                    break;
                }
                for (int k = 0; k < bestCols.length; k++) {
                    if (choix[i][0] == bestCols[k] || choix[i][1] == bestCols[k]) {
                        test = true;
                        break;
                    }
                }
                if (test) break;
            }
            for (Double sc : scores) {
                if (sc > 0) {
                    test = true;
                    break;
                }
            }
            if (test) {
                List<Integer> doubles = new ArrayList<>();
                for (int i = 0; i < j.getNbChoix(); i++) {
                    if (choix[i][0] == choix[i][1]) doubles.add(i);
                }
                if (doubles.size() > 0) {
                    int best = doubles.get(0);
                    //recup les doubles les plus proches de 7
                    for (Integer db : doubles) {
                        if (maxCols[choix[db][0] - 2] > maxCols[choix[best][0] - 2]) best = db;
                    }
                    return best;
                }


                if (hasCompleted) {
                    final var scEmpty = nextCols(new int[1]);
                    int best = 0;
                    double progBest = 0;

                    for (int i = 0; i < j.getNbChoix(); i++) {
                        double prog = scEmpty.get(choix[i][0] - 2) + (choix[i][1] == 0 ? 0 : scEmpty.get(choix[i][1] - 2));

                        if (prog > progBest) {
                            best = i;
                            progBest = prog;
                        }
                    }
                    return best;
                } else {
                    int best = 0;
                    //recup les choix les plus proches de 7
                    for (int i = 0; i < j.getNbChoix(); i++) {
                        if (maxCols[choix[i][0] - 2] + (choix[i][1] == 0 ? maxCols[choix[i][0] - 2] : maxCols[choix[i][1] - 2]) < maxCols[choix[best][0] - 2] + (choix[best][1] == 0 ? maxCols[choix[best][0] - 2] : maxCols[choix[best][1] - 2]))
                            best = i;
                    }
                    return best;
                }
            }
        }
        bestChoice = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) < scores.get(bestChoice)) bestChoice = i;
        }
        return bestChoice;
    }

    @Override
    public boolean stop(Jeu j) {

        int nbPlayed = Arrays.stream(bonzes).sum();

        bonzes = new int[11];
        for (int[] ints : j.getBonzes()) {
            if (ints[0] == 0) continue;
            bonzes[ints[0] - 2] = ints[1];
        }
        nbPlayed = Arrays.stream(bonzes).sum() - nbPlayed;

        int colsAvailable = 0;
        int colsCompleted = j.scoreJoueurEnCours();
        for (boolean b : j.getBloque()) {
            if (!b) colsAvailable++;
        }

        for (int i = 0; i < maxCols.length; i++) {
            if (cols[i] + bonzes[i] >= maxCols[i]) {
                colsAvailable--;
                colsCompleted++;
                hasCompleted = true;
            }
        }

        if (colsCompleted >= 3) {
            nbTours++;
            return true;
        }

        var r = new Random();
        if (j.getBonzesRestants() == 0) {
            if (hasCompleted) {

                nbTours++;
                return true;
            }
            double rnd = r.nextDouble();
            if (rnd < 0.9) {
                int position = Arrays.stream(j.getBonzes()).map(bonze -> maxCols[bonze[0] - 2]).reduce(0, Integer::sum);
                if (position < 11) {
                    double half = r.nextDouble(0.5, 0.7) * remainMult * (0.67 + colsAvailable * 0.03) * risk;
                    boolean test = false;

                    for (int i = 0; i < bonzes.length; i++) {
                        if (bonzes[i] > half * maxCols[i]) {
                            test = true;
                            break;
                        }
                    }
                    if (test) {
                        nbTours++;
                        return true;
                    }
                }
            }
            AtomicInteger ij = new AtomicInteger();
            double s = Arrays.stream(bonzes).filter(i -> i != 0).map(i -> maxCols[ij.getAndIncrement()]).reduce(0, Integer::sum);
            if (nbPlayed > s * r.nextDouble(0.9, 1.1) * risk * remainMult * (0.67 + 0.03 * colsAvailable)) {
                nbTours++;
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return "BP v2.13_1";
    }
}
