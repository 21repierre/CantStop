package strategies;

import cantstop.Jeu;


/**
 * Votre Stratégie (copie de la Strat0 pour l'instant)
 * <p>
 * Expliquez votre stratégie en une 20aine de lignes maximum.
 * <p>
 * RENDU: Ce fichier, correctement nommé et rempli.
 * Votre Stratégie aura un numéro (pour être similaire à Strat0 qui sera votre position dans l'alphabet de la promo + 14.
 * (attention calcul compliqué) Le premier aura donc pour numéro 15 et le dernier 334
 * Pour "préparer" votre stratégie, sur le fichier StratX.java, vous cliquez sur Bouton Droit, Refactor, Rename et vous
 * nommez bien votre stratégie genre Strat245.java (pour le 231e).
 *
 * @author GRATENS
 * <p>
 * lesChoix[i][0] + " et " + lesChoix[i][1] + ".");
 */
public class Strat148 implements Strategie {

    /**
     * @param j le jeu
     * @return toujours le 1er choix
     */
    int[][] Prob = {
            {2, 3, 4, 5220},
            {2, 3, 5, 5840},
            {2, 3, 6, 6840},
            {2, 3, 7, 7520},
            {2, 3, 8, 7560},
            {2, 3, 9, 7120},
            {2, 3, 10, 6340},
            {2, 3, 11, 5250},
            {2, 3, 12, 4380},
            {2, 4, 5, 6570},
            {2, 4, 6, 7580},
            {2, 4, 7, 8070},
            {2, 4, 8, 8160},
            {2, 4, 9, 7560},
            {2, 4, 10, 7380},
            {2, 4, 11, 6340},
            {2, 4, 12, 5520},
            {2, 5, 6, 7700},
            {2, 5, 7, 8090},
            {2, 5, 8, 8290},
            {2, 5, 9, 7600},
            {2, 5, 10, 7560},
            {2, 5, 11, 7120},
            {2, 5, 12, 6340},
            {2, 6, 7, 8640},
            {2, 6, 8, 8830},
            {2, 6, 9, 8330},
            {2, 6, 10, 8110},
            {2, 6, 11, 7560},
            {2, 6, 12, 7380},
            {2, 7, 8, 8900},
            {2, 7, 9, 8360},
            {2, 7, 10, 8330},
            {2, 7, 11, 7790},
            {2, 7, 12, 7810},
            {2, 8, 9, 8230},
            {2, 8, 10, 8160},
            {2, 8, 11, 7360},
            {2, 8, 12, 7380},
            {2, 9, 10, 7100},
            {2, 9, 11, 6370},
            {2, 9, 12, 6340},
            {2, 10, 11, 5790},
            {2, 10, 12, 5520},
            {2, 11, 12, 4380},
            {3, 4, 5, 6690},
            {3, 4, 6, 7420},
            {3, 4, 7, 7910},
            {3, 4, 8, 7960},
            {3, 4, 9, 7790},
            {3, 4, 10, 7560},
            {3, 4, 11, 6570},
            {3, 4, 12, 5790},
            {3, 5, 6, 7710},
            {3, 5, 7, 7870},
            {3, 5, 8, 8080},
            {3, 5, 9, 7760},
            {3, 5, 10, 7580},
            {3, 5, 11, 7100},
            {3, 5, 12, 6370},
            {3, 6, 7, 8650},
            {3, 6, 8, 8530},
            {3, 6, 9, 8260},
            {3, 6, 10, 8230},
            {3, 6, 11, 7580},
            {3, 6, 12, 7360},
            {3, 7, 8, 8930},
            {3, 7, 9, 8430},
            {3, 7, 10, 8360},
            {3, 7, 11, 7760},
            {3, 7, 12, 7790},
            {3, 8, 9, 8360},
            {3, 8, 10, 8330},
            {3, 8, 11, 7580},
            {3, 8, 12, 7560},
            {3, 9, 10, 7790},
            {3, 9, 11, 7100},
            {3, 9, 12, 7120},
            {3, 10, 11, 6570},
            {3, 10, 12, 6340},
            {3, 11, 12, 5250},
            {4, 5, 6, 7960},
            {4, 5, 7, 8480},
            {4, 5, 8, 8460},
            {4, 5, 9, 7990},
            {4, 5, 10, 8230},
            {4, 5, 11, 7790},
            {4, 5, 12, 7100},
            {4, 6, 7, 8860},
            {4, 6, 8, 9110},
            {4, 6, 9, 8640},
            {4, 6, 10, 8830},
            {4, 6, 11, 8330},
            {4, 6, 12, 8160},
            {4, 7, 8, 9030},
            {4, 7, 9, 8930},
            {4, 7, 10, 8770},
            {4, 7, 11, 8360},
            {4, 7, 12, 8330},
            {4, 8, 9, 8630},
            {4, 8, 10, 8830},
            {4, 8, 11, 8230},
            {4, 8, 12, 8110},
            {4, 9, 10, 8230},
            {4, 9, 11, 7580},
            {4, 9, 12, 7560},
            {4, 10, 11, 7560},
            {4, 10, 12, 7380},
            {4, 11, 12, 6340},
            {5, 6, 7, 8870},
            {5, 6, 8, 8950},
            {5, 6, 9, 8670},
            {5, 6, 10, 8630},
            {5, 6, 11, 8360},
            {5, 6, 12, 8230},
            {5, 7, 8, 9140},
            {5, 7, 9, 8530},
            {5, 7, 10, 8930},
            {5, 7, 11, 8430},
            {5, 7, 12, 8360},
            {5, 8, 9, 8670},
            {5, 8, 10, 8640},
            {5, 8, 11, 8260},
            {5, 8, 12, 8330},
            {5, 9, 10, 7990},
            {5, 9, 11, 7760},
            {5, 9, 12, 7600},
            {5, 10, 11, 7790},
            {5, 10, 12, 7560},
            {5, 11, 12, 7120},
            {6, 7, 8, 9200},
            {6, 7, 9, 9140},
            {6, 7, 10, 9030},
            {6, 7, 11, 8930},
            {6, 7, 12, 8900},
            {6, 8, 9, 8950},
            {6, 8, 10, 9110},
            {6, 8, 11, 8530},
            {6, 8, 12, 8830},
            {6, 9, 10, 8460},
            {6, 9, 11, 8080},
            {6, 9, 12, 8290},
            {6, 10, 11, 7960},
            {6, 10, 12, 8160},
            {6, 11, 12, 7560},
            {7, 8, 9, 8870},
            {7, 8, 10, 8860},
            {7, 8, 11, 8650},
            {7, 8, 12, 8640},
            {7, 9, 10, 8480},
            {7, 9, 11, 7870},
            {7, 9, 12, 8090},
            {7, 10, 11, 7910},
            {7, 10, 12, 8070},
            {7, 11, 12, 7520},
            {8, 9, 10, 7960},
            {8, 9, 11, 7710},
            {8, 9, 12, 7700},
            {8, 10, 11, 7420},
            {8, 10, 12, 7580},
            {8, 11, 12, 6840},
            {9, 10, 11, 6690},
            {9, 10, 12, 6570},
            {9, 11, 12, 5840},
            {10, 11, 12, 5220},
    };

    int[] PointArray;
    int pourc;
    double rd;
    int ComptFinish;
    int Over;
    int[] ListOver;
    int[] ListPointOver;
    int[] ListPair;
    int[] ListPos;
    int[] ListBonzesRestants;
    int ComptPremier;
    int[] OneArray;
    int bou;

    public int[] tri(int[] tab) {
        int taille = tab.length;
        int tmp = 0;
        for (int i = 0; i < taille; i++) {
            for (int j = 1; j < (taille - i); j++) {
                if (tab[j - 1] > tab[j]) {
                    tmp = tab[j - 1];
                    tab[j - 1] = tab[j];
                    tab[j] = tmp;
                }
            }
        }
        return tab;
    }

    public int GetIndexMax(int[] Liste) {
        int a = 0;
        for (int i = 0; i < Liste.length; i++) {
            if (a < Liste[i]) {
                a = Liste[i];
            }
        }
        for (int ii = 0; ii < Liste.length; ii++) {
            if (Liste[ii] == a) {
                return ii;
            }
        }
        return 0;
    }

    public int choix(Jeu j) {

        int[][] lesChoix = j.getLesChoix();
        int[][] bonzes = j.getBonzes();
        int[] maximum = j.getMaximum();
        int bonzesRestants = j.getBonzesRestants();
        int[][] Desc = lesChoix;
        int[] avancementJoueurEnCours = j.avancementJoueurEnCours();
        int[] avancementAutreJoueur = j.avancementAutreJoueur();
        int[] Points = {0, 0, 350, 300, 280, 280, 250, 250, 250, 280, 280, 300, 350};
        PointArray = new int[Desc.length];
        for (int i = 0; i < avancementAutreJoueur.length; i++) {
            Points[i + 2] = Points[i + 2] + avancementJoueurEnCours[i] * 30;
            if (avancementAutreJoueur[i] >= maximum[i] - (i + 2)) {
                Points[i + 2] = Points[i + 2] - avancementAutreJoueur[i] * 20;
            }
        }

        for (int z = 0; z < lesChoix.length; z++) {
            Desc[z][0] = Points[lesChoix[z][0]];
            Desc[z][1] = Points[lesChoix[z][1]];
        }
        for (int i = 0; i < Desc.length; i++) {
            PointArray[i] = Desc[i][0] + Desc[i][1];
        }
        Over = 0;
        ListOver = new int[3];
        ListPointOver = new int[8];
        ListPair = new int[8];
        ListPos = new int[3];
        int ComptPremier = 0;
        for (int cc = 0; cc < bonzes.length; cc++) {
            if (bonzes[cc][0] == 0) {
                ComptPremier++;
            }
        }
        if (ComptPremier == 3) {
            for (int p = 0; p < Desc.length; p++) {
                if (Desc[p][0] == Desc[p][1]) {
                    ListPair[p] = PointArray[p];
                }
            }
            return GetIndexMax(ListPair);
        }
        for (int pos = 0; pos < 3; pos++) {
            if (bonzes[pos][0] != 0) {
                ComptFinish = bonzes[pos][0];
                if (bonzes[pos][1] == maximum[ComptFinish - 2] - 1) {
                    Over++;
                    ListOver[pos] = ComptFinish;
                }
            }
        }
        if (Over == 2) {
            for (int o = 0; o < lesChoix.length; o++) {
                if ((lesChoix[o][0] == ListOver[0] && lesChoix[o][1] == ListOver[1]) || ((lesChoix[o][1] == ListOver[0] && lesChoix[o][0] == ListOver[1]))) {
                    return o;
                }
            }
        }
        if (Over == 1) {
            int aa = 0;
            for (int ii = 0; ii < lesChoix.length; ii++) {
                if (lesChoix[ii][0] == ListOver[0] || lesChoix[ii][1] == ListOver[0]) {
                    ListPointOver[ii] = PointArray[ii];
                }
            }
            for (int i = 0; i < ListPointOver.length; i++) {
                if (ListPointOver[i] == 0) {
                    aa++;
                }
                if (aa != ListPointOver.length) {
                    return GetIndexMax(ListPointOver);
                }
            }
        }
        if (bonzesRestants == 2) {
            for (int y = 0; y < bonzes.length; y++) {
                ListPos[y] = bonzes[y][0];
            }
            OneArray = new int[lesChoix.length];
            for (int yy = 0; yy < lesChoix.length; yy++) {
                if (ListPos[0] == lesChoix[yy][0] || ListPos[0] == lesChoix[yy][1]) {
                    OneArray[yy] = Desc[yy][0] + Desc[yy][1];
                }
            }
        } else if (bonzesRestants == 1) {
            for (int y = 0; y < bonzes.length; y++) {
                ListPos[y] = bonzes[y][0];
            }
            for (int yy = 0; yy < lesChoix.length; yy++) {
                if ((ListPos[0] == lesChoix[yy][0] && ListPos[1] == lesChoix[yy][1]) || (ListPos[0] == lesChoix[yy][1] && ListPos[1] == lesChoix[yy][0])) {
                    return yy;
                }
            }
        } else {
            return GetIndexMax(PointArray);
        }
        return 0;
    }


    /**
     * @param j le jeu
     * @return toujours vrai (pour s'arrêter)
     */

    public boolean stop(Jeu j) {
        int NbCoup = j.getNbCoup();
        int[][] lesChoix = j.getLesChoix();
        int[][] bonzes = j.getBonzes();
        int[] maximum = j.getMaximum();
        int scoreJoueurEnCours = j.scoreJoueurEnCours();
        int scoreAutreJoueur = j.scoreAutreJoueur();
        int bonzesRestants = j.getBonzesRestants();
        int[] avancementJoueurEnCours = j.avancementJoueurEnCours();
        int[] avancementAutreJoueur = j.avancementAutreJoueur();
        boolean[] Bloque = j.getBloque();
        boolean[] fini = j.getFini();

        if (bonzesRestants == 0) {
            for (int i = 0; i < Bloque.length; i++) {
                if (Bloque[i] == true) {
                    return true;
                }
            }
            int a = bonzes[0][0];
            int b = bonzes[1][0];
            int c = bonzes[2][0];
            int[] Pos = {a, b, c};
            tri(Pos);
            a = Pos[0];
            b = Pos[1];
            c = Pos[2];
            int t = 0;
            int ta = 0;
            while (t == 0) {
                if (a == Prob[ta][0]) {
                    if (b == Prob[ta][1]) {
                        if (c == Prob[ta][2]) {
                            pourc = Prob[ta][3];
                            t++;
                        }
                    }
                }
                ta++;
            }
            rd = Math.random();
            if (pourc > 9000) {
                if (NbCoup < 4) {
                    return false;
                } else {
                    return true;
                }
            } else if (pourc > 8000) {
                if (NbCoup < 2) {
                    return false;
                } else {
                    return true;
                }
            } else if (pourc > 7000 && rd > 0.7) {
                if (NbCoup < 2) {
                    return false;
                } else {
                    return true;
                }
            } else if (pourc > 6000 && rd > 0.85) {
                if (NbCoup < 1) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    /**
     * @return vos noms
     */
    public String getName() {
        return "BAPTISTE GRATENS";
    }

    @Override
    public void end(boolean victoire) {

    }
}
