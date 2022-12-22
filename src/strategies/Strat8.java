package strategies;

import cantstop.Jeu;

/**
 * StratÃ©gie alÃ©atoire
 *
 * @author humeau
 */
public class Strat8 implements Strategie {
    int[] Points = {0, 0, 600, 300, 350, 200, 800, 800, 800, 280, 350, 300, 600};
    int ComptFinish;
    int[] ListOver;
    /**
     * @param j le jeu
     * @return au hasard true ou false (Ã©quiprobabilitÃ©)
     */
    int score;
    int compt;
    int bab = 0;
    int[] myPog = new int[11];
    int[][] SaveRule = {{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12},
            {6, 5, 4, 3, 2, 1, 2, 3, 4, 5, 6}};

    public int MMax(int[] L) {
        int test = 0;
        for (int i = 0; i < L.length; i++) {
            if (test < L[i]) {
                test = L[i];
            }
        }
        for (int i = 0; i < L.length; i++) {

            if (L[i] == test) {
                return i;
            }

        }
        return 0;
    }

    /**
     * @param j le jeu
     * @return un choix tirÃ© au hasard de maniÃ¨re Ã©quiprobable dans [0,j.nbChoix[
     */
    public int choix(Jeu j) {
        int[][] lesChoix = j.getLesChoix();
        int[] Max = new int[lesChoix.length];
        int[][] bonzes = j.getBonzes();
        int Over = 0;
        int[] maxs = j.getMaximum();
        ListOver = new int[3];

        for (int pos = 0; pos < 3; pos++) {
            if (bonzes[pos][0] != 0) {
                ComptFinish = bonzes[pos][0];
                if (bonzes[pos][1] == maxs[ComptFinish - 2] - 1) {
                    ListOver[Over] = ComptFinish;
                    Over++;
                }
            }
        }

        if (Over == 2) {
            for (int o = 0; o < j.getNbChoix(); o++) {
                if ((lesChoix[o][0] == ListOver[0] && lesChoix[o][1] == ListOver[1]) || ((lesChoix[o][1] == ListOver[0] && lesChoix[o][0] == ListOver[1]))) {
                    return o;
                }
            }
        }
        if (Over == 1) {
            int aa = 0;
            for (int ii = 0; ii < j.getNbChoix(); ii++) {
                if (lesChoix[ii][0] == ListOver[0] || lesChoix[ii][1] == ListOver[0]) {
                    return ii;
                }
            }
        }
        for (int i = 0; i < lesChoix.length; i++) {
            Max[i] += Points[lesChoix[i][0]] + Points[lesChoix[i][1]];
        }
        return MMax(Max);
    }

    public boolean stop(Jeu j) {
        int NbCoup = j.getNbCoup();
        int[][] bonzes = j.getBonzes();
        int[] avancementJoueurEnCours = j.avancementJoueurEnCours();
        int[] avancementAutreJoueur = j.avancementAutreJoueur();
        int bonzesRestants = j.getBonzesRestants();
        boolean[] Bloque = j.getBloque();
        if (NbCoup == 0) {
            score = 0;
            compt = 0;
            bab = 0;
            for (int i = 0; i < 11; i++) {
                myPog[i] = avancementJoueurEnCours[i];
            }
        }
        if (bonzesRestants == 0) {
            for (int i = 0; i < Bloque.length; i++) {
                if (Bloque[i] == true) {
                    return true;
                }
            }
        }
        for (int[] bonze : bonzes) {
            if (bonze[0] != 0) {
                score += (SaveRule[1][bonze[0] - 2]) * (1 + avancementJoueurEnCours[bonze[0] - 2] - avancementAutreJoueur[bonze[0] - 2] - myPog[bonze[0] - 2]);
            }
        }
        for (int[] bonze : bonzes) {
            if (bonze[0] % 2 == 0) {
                compt++;
            }
        }
        if (compt == 3 && bab == 0) {
            score += -8;
            bab++;
        }
        if (score <= 42) {
            return false;
        }

        return true;
    }

    /**
     * @return "StratÃ©gie alÃ©atoire"
     */
    public String getName() {
        return "TEST";
    }
}