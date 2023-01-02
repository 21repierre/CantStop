package strategies;

import cantstop.Jeu;

public class Strat133 implements Strategie {
    public int count;
    public int finies;

    public int choix(Jeu j) {
        int[][] choua = j.getLesChoix();
        double[] poids = new double[j.getNbChoix()];
        int max = 0;
        for (int i = 0; i < j.getNbChoix(); i++) {
            poids[i] = poids[i] + 20d * (j.avancementJoueurEnCours()[choua[i][0] - 2]) / (j.getMaximum()[choua[i][0] - 2]);//l'avancement du joueur par rapport à la longueur de la colonne rajoute de l'imporance à son poids
            poids[i] = poids[i] - 3d * (j.avancementAutreJoueur()[choua[i][0] - 2]) / (j.getMaximum()[choua[i][0] - 2]);//on tient compte de l'avancement de l'autre joueur sur la colonne
            if (choua[i][1] != 0) {
                poids[i] += 20d * (j.avancementJoueurEnCours()[choua[i][1] - 2]) / (j.getMaximum()[choua[i][1] - 2]) - 3d * (j.avancementAutreJoueur()[choua[i][1] - 2]) / (j.getMaximum()[choua[i][1] - 2]);
            }

            if (choua[i][0] == choua[i][1]) {//si il y a un double, je choisis cette option
                return i;
            }
            if (choua[i][0] == 7 || choua[i][1] == 7) {//si il y a un choix qui a un 7, alors on va le favoriser
                poids[i] = poids[i] + 2;
            }
            if (choua[i][0] == 6 || choua[i][1] == 6) {//si il y a un choix qui possède un 6 , alors, cela sera important aussi
                poids[i] = poids[i] + 3;
            }
            if (choua[i][0] == 8 || choua[i][1] == 8) {//si il y a un choix qui possède un 8 , alors, cela sera important aussi
                poids[i] = poids[i] + 3;
            }
        }
        for (int t = 0; t < j.getNbChoix(); t++) {
            if (poids[t] > max) {
                max = t;
            } else
                max = max;
        }

        return max;
    }

    public boolean stop(Jeu j) {
        for (int o = 0; o < 10; o++) {
            if (j.getFini()[o]) {
                finies = finies + 1;
            }
        }
        if (j.getBonzesRestants() == 0) { // tous les bonzes ne sont pas posés, je souhaite continuer
            count = count + 1;
            if (finies < 2) {//Si 1 ou 0 colonnes sont terminées
                if (count > 3) {//Si j'ai joué plus de quatre fois avec je m'arrête
                    count = 0;
                    return true;
                }
            }
            if (finies > 1) {//Si deux colonnes sont terminées
                if (count > 2) {//Si j'ai joué plus de deux fois
                    count = 0;
                    return true;
                }
            }
            if (j.getBonzes()[0][1] == j.getMaximum()[j.getBonzes()[0][0] - 2]) {//Si le premier bonze a atteind le maximum, je souhaite arrêter
                count = 0;
                return true;
            }
            if (j.getBonzes()[1][1] == j.getMaximum()[j.getBonzes()[1][0] - 2]) {//Si le deuxième bonze a atteind le maximum, je souahite arrêter
                count = 0;
                return true;
            }
            if (j.getBonzes()[2][1] == j.getMaximum()[j.getBonzes()[2][0] - 2]) {//Si le troisième bonze a atteind le maximum, je souahite arrêter
                count = 0;
                return true;
            } else
                count = count + 1;
            return false;
        }
        count = count + 1;
        return false;
    }

    @Override
    public String getName() {
        return "null";
    }
}
