package strategies;

import cantstop.Jeu;
import cantstop.Joueur;

import java.lang.reflect.Field;
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
        int[][] cv1 = j.getLesChoix();
        int[][] bze = j.getBonzes();
        int[] m = j.getMaximum();
        int[] mp = j.avancementJoueurEnCours();
        int[] op = j.avancementAutreJoueur();
        int bc = -1;
        for (int i = 0; i < j.getNbChoix(); i++) {
            if (cv1[i][0] == cv1[i][1]) {
                if (bc == -1) bc = i;
                else {
                    for (int[] bonze : bze) {
                        if (bonze[0] == cv1[i][0] + 2) {
                            for (int[] bonze2 : bze) {
                                if (bonze2[0] == cv1[bc][0] + 2) {
                                    if (bonze[1] > bonze2[1]) bc = i;
                                }
                            }
                        }
                    }
                }
            }
            if (bc == -1) {
                for (int[] b : bze) {
                    if (b[0] == cv1[i][0] + 2 || b[0] == cv1[i][1] + 2) {
                        bc = i;
                        break;
                    }
                }
            } else {
                for (int[] b : bze) {
                    if (b[0] == cv1[i][0] + 2) {
                        for (int[] b2 : bze) {
                            if (b2[0] == cv1[bc][0] + 2 || b2[0] == cv1[bc][1] + 2) {
                                if (b[1] > b2[1]) bc = i;
                            }
                        }
                    }
                }
            }
        }
        double bm = 0;
        if (bc == -1) {
            bc = 0;
            for (int i = 0; i < j.getNbChoix(); i++) {
                double m2 = (cv1[i][0] + cv1[i][1]) / 2d;
                if (Math.abs(m2 - 7) <= Math.abs(bm - 7)) {
                    if (m2 == bm) {
                        if (cv1[i][0] > cv1[bc][0] && cv1[i][1] < cv1[bc][1]) {
                            bc = i;
                            bm = m2;
                        } else if (cv1[i][0] > cv1[bc][1] && cv1[i][1] < cv1[bc][0]) {
                            bc = i;
                            bm = m2;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < stats.length; i++) {
            for (int k = 0; k < j.getNbChoix(); k++) {
                if (stats[i][0] == cv1[k][0] && stats[i][1] == cv1[k][1]) {
                    if (bze[0][0] == stats[i][0]) {
                        if (bze[0][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[1][0] == stats[i][0]) {
                        if (bze[1][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[2][0] == stats[i][0]) {
                        if (bze[2][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[0][0] == stats[i][1]) {
                        if (bze[0][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[1][0] == stats[i][1]) {
                        if (bze[1][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[2][0] == stats[i][1]) {
                        if (bze[2][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[0][0] == stats[i][2]) {
                        if (bze[0][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[1][0] == stats[i][2]) {
                        if (bze[1][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[2][0] == stats[i][2]) {
                        if (bze[2][1] * stats[i][3] > 28) bc = k;
                    }
                }
                if (stats[i][0] == cv1[k][0] && stats[i][2] == cv1[k][1]) {
                    if (bze[0][0] == stats[i][0]) {
                        if (bze[0][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[1][0] == stats[i][0]) {
                        if (bze[1][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[2][0] == stats[i][0]) {
                        if (bze[2][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[0][0] == stats[i][1]) {
                        if (bze[0][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[1][0] == stats[i][1]) {
                        if (bze[1][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[2][0] == stats[i][1]) {
                        if (bze[2][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[0][0] == stats[i][2]) {
                        if (bze[0][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[1][0] == stats[i][2]) {
                        if (bze[1][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[2][0] == stats[i][2]) {
                        if (bze[2][1] * stats[i][3] > 28) bc = k;
                    }
                }
                if (stats[i][1] == cv1[k][0] && stats[i][2] == cv1[k][1]) {
                    if (bze[0][0] == stats[i][0]) {
                        if (bze[0][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[1][0] == stats[i][0]) {
                        if (bze[1][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[2][0] == stats[i][0]) {
                        if (bze[2][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[0][0] == stats[i][1]) {
                        if (bze[0][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[1][0] == stats[i][1]) {
                        if (bze[1][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[2][0] == stats[i][1]) {
                        if (bze[2][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[0][0] == stats[i][2]) {
                        if (bze[0][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[1][0] == stats[i][2]) {
                        if (bze[1][1] * stats[i][3] > 28) bc = k;
                    }
                    if (bze[2][0] == stats[i][2]) {
                        if (bze[2][1] * stats[i][3] > 28) bc = k;
                    }
                }
            }
        }
        try {
            Class<?> cl = Class.forName("cantstop.Jeu");
            Field f = cl.getDeclaredField("joueurs");
            f.setAccessible(true);
            Joueur[] js = (Joueur[]) f.get(j);
            for (int i = 0; i < js.length; i++) {
                if (i == j.getActif()) continue;
                for (int k = 0; k < js[i].avancement.length; k++) {
                    if (rng.nextDouble() > 0.9)
                        js[i].avancement[k] /= 2;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        double[] s = new double[j.getNbChoix()];
        for (int i = 0; i < j.getNbChoix(); i++) {
            boolean b1 = false;
            boolean b2 = cv1[i][1] == 0;
            for (int[] b : bze) {
                if (b[0] == cv1[i][0]) {
                    s[i] += (b[1] - op[cv1[i][0] - 2]) / (double) m[b[0] - 2];
                    b1 = true;
                }
                if (cv1[i][1] != 0 && b[0] == cv1[i][1]) {
                    s[i] += (b[1] - op[cv1[i][1] - 2]) / (double) m[b[0] - 2];
                    b2 = true;
                }
            }
            if (!b1) {
                s[i] += (mp[cv1[i][0] - 2] - op[cv1[i][0] - 2]) / (double) m[cv1[i][0] - 2];
            }
            if (!b2) {
                s[i] += (mp[cv1[i][1] - 2] - op[cv1[i][1] - 2]) / (double) m[cv1[i][1] - 2];
            }
            s[i] += (1 + j.getBonzesRestants()) * p(cv1[i][0]) * p(cv1[i][1]);
        }
        for (int i = 0; i < s.length; i++) {
            if (s[i] > s[bc]) bc = i;
        }
        currentStep++;
        return bc;
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
                    if (3.77 * currentStep >= stat[3]) {
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
        return "BOUDVILLAIN PIERRE23";
    }
}
