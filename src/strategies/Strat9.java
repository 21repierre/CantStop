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
 * @author VOTRE NOM
 */

public class Strat9 implements Strategie {

    /**
     * @param j le jeu
     * @return toujours le 1er choix
     */


    int[][] tableau1 = {
            {2, 13},
            {3, 23},
            {4, 36},
            {5, 45},
            {6, 56},
            {7, 64},
            {8, 56},
            {9, 45},
            {10, 36},
            {11, 23},
            {12, 13}};

    int[][] tableau2 = {
            {2, 3, 32},
            {2, 4, 44},
            {2, 5, 53},
            {2, 6, 63},
            {2, 7, 71},
            {2, 8, 67},
            {2, 9, 56},
            {2, 10, 47},
            {2, 11, 36},
            {2, 12, 26},
            {3, 4, 47},
            {3, 5, 53},
            {3, 6, 64},
            {3, 7, 71},
            {3, 8, 68},
            {3, 9, 64},
            {3, 10, 56},
            {3, 11, 45},
            {3, 12, 36},
            {4, 5, 61},
            {4, 6, 72},
            {4, 7, 77},
            {4, 8, 75},
            {4, 9, 68},
            {4, 10, 67},
            {4, 11, 56},
            {4, 12, 47},
            {5, 6, 73},
            {5, 7, 78},
            {5, 8, 77},
            {5, 9, 69},
            {5, 10, 68},
            {5, 11, 64},
            {5, 12, 56},
            {6, 7, 84},
            {6, 8, 82},
            {6, 9, 77},
            {6, 10, 75},
            {6, 11, 68},
            {6, 12, 67},
            {7, 8, 84},
            {7, 9, 78},
            {7, 10, 77},
            {7, 11, 71},
            {7, 12, 71},
            {8, 9, 73},
            {8, 10, 72},
            {8, 11, 64},
            {8, 12, 63},
            {9, 10, 61},
            {9, 11, 53},
            {9, 12, 53},
            {10, 11, 47},
            {10, 12, 44},
            {11, 12, 32}};

    int MIN;
    int MAX;

    public int choix(Jeu j) {

        int[][] Choices = j.getLesChoix();
        int t = 0;
        int tt = 0;
        int ta = 0;
        int tata = 0;
        int TOT = 0;
        int index = 0;

        for (int i = 0; i < j.getNbChoix(); i++) {
            if (Choices[i][0] == Choices[i][1]) {
                return i;
            }
            if (Choices[i][0] > Choices[i][1]) {
                MAX = Choices[i][0];
                MIN = Choices[i][1];
            } else {
                MAX = Choices[i][1];
                MIN = Choices[i][0];
            }
            if (Choices[i][0] != 0 && Choices[i][1] != 0) {
                while (tt == 0) {

                    if (MIN == tableau2[t][0]) {
                        if (MAX == tableau2[t][1]) {
                            if (TOT < tableau2[t][2]) {
                                TOT = tableau2[t][2];
                                index = i;
                            }
                            tt++;
                        }
                    }
                    t++;
                }
            } else {
                while (tata == 0) {
                    if (Choices[i][0] == tableau1[ta][0]) {
                        if (TOT < tableau1[ta][1]) {
                            TOT = tableau1[ta][1];
                            index = i;

                        }
                        tata++;
                    }
                    ta++;
                }
            }


        }

        return index;
    }

    /**
     * @param j le jeu
     * @return toujours vrai (pour s'arrêter)
     */
    public boolean stop(Jeu j) {
        boolean[] stop = j.getBloque();
        int Nombre = j.getNbCoup();
        for (int i = 0; i < 11; i++) {
            if (stop[i] == true) {
                return true;
            }
        }
        if (Nombre == 60) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return vos noms
     */
    public String getName() {
        return "VOTRE NOM (SOUS FORME PRENOM NOM)";
    }
}