package strategies;

import cantstop.Jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Strat491 implements Strategie {
    int[][] stats = new int[][]{
            new int[]{2, 11, 12, 4}, new int[]{2, 3, 12, 4}, new int[]{10, 11, 12, 5}, new int[]{2, 3, 4, 5}, new int[]{3, 11, 12, 5}, new int[]{2, 3, 11, 5}, new int[]{2, 10, 12, 5}, new int[]{2, 4, 12, 5}, new int[]{2, 10, 11, 5}, new int[]{3, 4, 12, 5}, new int[]{9, 11, 12, 5}, new int[]{2, 3, 5, 5}, new int[]{2, 9, 12, 6}, new int[]{2, 3, 10, 6}, new int[]{3, 10, 12, 6}, new int[]{4, 11, 12, 6}, new int[]{2, 4, 11, 6}, new int[]{2, 5, 12, 6}, new int[]{2, 9, 11, 6}, new int[]{3, 5, 12, 6}, new int[]{3, 10, 11, 6}, new int[]{3, 4, 11, 6}, new int[]{9, 10, 12, 6}, new int[]{2, 4, 5, 6}, new int[]{9, 10, 11, 6}, new int[]{3, 4, 5, 6}, new int[]{8, 11, 12, 6}, new int[]{2, 3, 6, 6}, new int[]{2, 9, 10, 7}, new int[]{3, 9, 11, 7}, new int[]{3, 5, 11, 7}, new int[]{4, 5, 12, 7}, new int[]{3, 9, 12, 7}, new int[]{5, 11, 12, 7}, new int[]{2, 5, 11, 7}, new int[]{2, 3, 9, 7}, new int[]{2, 8, 11, 7}, new int[]{3, 6, 12, 7}, new int[]{2, 4, 10, 7}, new int[]{4, 10, 12, 7}, new int[]{2, 8, 12, 7}, new int[]{2, 6, 12, 7}, new int[]{8, 10, 11, 7}, new int[]{3, 4, 6, 7}, new int[]{7, 11, 12, 7}, new int[]{2, 3, 7, 7}, new int[]{6, 11, 12, 7}, new int[]{4, 9, 12, 7}, new int[]{4, 10, 11, 7}, new int[]{3, 4, 10, 7}, new int[]{2, 6, 11, 7}, new int[]{2, 4, 9, 7}, new int[]{3, 8, 12, 7}, new int[]{2, 5, 10, 7}, new int[]{5, 10, 12, 7}, new int[]{2, 3, 8, 7}, new int[]{8, 10, 12, 7}, new int[]{3, 6, 11, 7}, new int[]{3, 8, 11, 7}, new int[]{4, 9, 11, 7}, new int[]{3, 5, 10, 7}, new int[]{2, 4, 6, 7}, new int[]{5, 9, 12, 7}, new int[]{2, 5, 9, 7}, new int[]{8, 9, 12, 7}, new int[]{8, 9, 11, 7}, new int[]{2, 5, 6, 7}, new int[]{3, 5, 6, 7}, new int[]{3, 7, 11, 7}, new int[]{5, 9, 11, 7}, new int[]{3, 5, 9, 7}, new int[]{2, 7, 11, 7}, new int[]{5, 10, 11, 7}, new int[]{3, 4, 9, 7}, new int[]{3, 9, 10, 7}, new int[]{4, 5, 11, 7}, new int[]{3, 7, 12, 7}, new int[]{2, 7, 12, 7}, new int[]{7, 9, 11, 7}, new int[]{3, 5, 7, 7}, new int[]{7, 10, 11, 7}, new int[]{3, 4, 7, 7}, new int[]{8, 9, 10, 7}, new int[]{6, 10, 11, 7}, new int[]{3, 4, 8, 7}, new int[]{4, 5, 6, 7}, new int[]{5, 9, 10, 7}, new int[]{4, 5, 9, 7}, new int[]{7, 10, 12, 8}, new int[]{2, 4, 7, 8}, new int[]{6, 9, 11, 8}, new int[]{3, 5, 8, 8}, new int[]{7, 9, 12, 8}, new int[]{2, 5, 7, 8}, new int[]{2, 6, 10, 8}, new int[]{4, 8, 12, 8}, new int[]{2, 8, 10, 8}, new int[]{6, 10, 12, 8}, new int[]{4, 6, 12, 8}, new int[]{2, 4, 8, 8}, new int[]{2, 8, 9, 8}, new int[]{4, 9, 10, 8}, new int[]{3, 6, 10, 8}, new int[]{4, 5, 10, 8}, new int[]{4, 8, 11, 8}, new int[]{5, 6, 12, 8}, new int[]{5, 8, 11, 8}, new int[]{3, 6, 9, 8}, new int[]{6, 9, 12, 8}, new int[]{2, 5, 8, 8}, new int[]{2, 7, 10, 8}, new int[]{3, 8, 10, 8}, new int[]{4, 6, 11, 8}, new int[]{5, 8, 12, 8}, new int[]{2, 6, 9, 8}, new int[]{4, 7, 12, 8}, new int[]{2, 7, 9, 8}, new int[]{3, 8, 9, 8}, new int[]{5, 6, 11, 8}, new int[]{4, 7, 11, 8}, new int[]{5, 7, 12, 8}, new int[]{3, 7, 10, 8}, new int[]{5, 7, 11, 8}, new int[]{3, 7, 9, 8}, new int[]{6, 9, 10, 8}, new int[]{4, 5, 8, 8}, new int[]{7, 9, 10, 8}, new int[]{4, 5, 7, 8}, new int[]{5, 7, 9, 8}, new int[]{6, 8, 11, 8}, new int[]{3, 6, 8, 8}, new int[]{5, 6, 10, 8}, new int[]{4, 8, 9, 8}, new int[]{5, 8, 10, 8}, new int[]{7, 8, 12, 8}, new int[]{4, 6, 9, 8}, new int[]{2, 6, 7, 8}, new int[]{7, 8, 11, 8}, new int[]{3, 6, 7, 8}, new int[]{5, 8, 9, 8}, new int[]{5, 6, 9, 8}, new int[]{4, 7, 10, 8}, new int[]{6, 8, 12, 8}, new int[]{4, 6, 10, 8}, new int[]{4, 8, 10, 8}, new int[]{2, 6, 8, 8}, new int[]{7, 8, 10, 8}, new int[]{4, 6, 7, 8}, new int[]{7, 8, 9, 8}, new int[]{5, 6, 7, 8}, new int[]{2, 7, 8, 8}, new int[]{6, 7, 12, 8}, new int[]{4, 7, 9, 8}, new int[]{6, 7, 11, 8}, new int[]{5, 7, 10, 8}, new int[]{3, 7, 8, 8}, new int[]{6, 8, 9, 8}, new int[]{5, 6, 8, 8}, new int[]{6, 7, 10, 9}, new int[]{4, 7, 8, 9}, new int[]{6, 8, 10, 9}, new int[]{4, 6, 8, 9}, new int[]{6, 7, 9, 9}, new int[]{5, 7, 8, 9}, new int[]{6, 7, 8, 9}
    };

    int[] maxs;
    int[] bonzesN = new int[3];
    int bonzeRestants;
    State currentState = new State();
    List<Arc> currentArcs = new ArrayList<>();

    public Strat491() {
    }

    @Override
    public int choix(Jeu jeu) {
        int[][] choix = jeu.getLesChoix();
        int[][] bonzes = jeu.getBonzes();
        bonzesN[0] = bonzes[0][0];
        bonzesN[1] = bonzes[1][0];
        bonzesN[2] = bonzes[2][0];
        bonzeRestants = jeu.getBonzesRestants();
        this.maxs = jeu.getMaximum();
        int[] myProgress = jeu.avancementJoueurEnCours();
        int[] otProgress = jeu.avancementAutreJoueur();
        Graph cG = new Graph();
        currentArcs.clear();

        if (jeu.getBonzesRestants() == 0) {
            // tt les etats finaux sont ceux avec les 3 bonzes maxes
            int[] l = new int[8];
            int j = 0;
            for (int i = 0; i < maxs.length; i++) {
                if (i != bonzes[0][0] - 2 && i != bonzes[1][0] - 2 && i != bonzes[2][0] - 2) {
                    l[j] = maxs[i];
                    j++;
                    currentState.state[j] = myProgress[j];
                }
            }
            currentState.state[bonzes[0][0] - 2] = bonzes[0][1];
            currentState.state[bonzes[1][0] - 2] = bonzes[1][1];
            currentState.state[bonzes[2][0] - 2] = bonzes[2][1];

            for (int i0 = 0; i0 < l[0] - 1; i0++) {
                for (int i1 = 0; i1 < l[1] - 1; i1++) {
                    for (int i2 = 0; i2 < l[2] - 1; i2++) {
                        for (int i3 = 0; i3 < l[3] - 1; i3++) {
                            for (int i4 = 0; i4 < l[4] - 1; i4++) {
                                for (int i5 = 0; i5 < l[5] - 1; i5++) {
                                    for (int i6 = 0; i6 < l[6] - 1; i6++) {
                                        for (int i7 = 0; i7 < l[7] - 1; i7++) {
                                            State s = new State();
                                            s.isFinal = true;
                                            s.state[bonzes[0][0] - 2] = maxs[bonzes[0][0] - 2];
                                            s.state[bonzes[1][0] - 2] = maxs[bonzes[1][0] - 2];
                                            s.state[bonzes[2][0] - 2] = maxs[bonzes[2][0] - 2];
                                            j = 0;
                                            for (int i = 0; i < s.state.length; i++) {
                                                if (s.state[i] == 0) {
                                                    if (j == 0) s.state[i] = i0;
                                                    if (j == 1) s.state[i] = i1;
                                                    if (j == 2) s.state[i] = i2;
                                                    if (j == 3) s.state[i] = i3;
                                                    if (j == 4) s.state[i] = i4;
                                                    if (j == 5) s.state[i] = i5;
                                                    if (j == 6) s.state[i] = i6;
                                                    if (j == 7) s.state[i] = i7;
                                                    j++;
                                                }
                                            }
                                            //cG.U1.add(s);
                                            Arc a = new Arc(s, null);
                                            //cG.E.add(a);
                                            buildChoices(a);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(cG.U1.size() + " - " + cG.V1.size() + " - " + cG.E.size());
        }

        return new Random().nextInt(0, jeu.getNbChoix());
    }

    private Arc buildChoices(Arc arc) {
        if (bonzeRestants == 0) {
            Choice[] choices = new Choice[]{
                    new Choice(bonzesN[0]),
                    new Choice(bonzesN[0], bonzesN[0]),
                    new Choice(bonzesN[1]),
                    new Choice(bonzesN[1], bonzesN[1]),
                    new Choice(bonzesN[2]),
                    new Choice(bonzesN[2], bonzesN[2]),
                    new Choice(bonzesN[0], bonzesN[1]),
                    new Choice(bonzesN[0], bonzesN[2]),
                    new Choice(bonzesN[1], bonzesN[2])};
            State state = (State) arc.from;
            for (Choice choice : choices) {
                Arc nArc = new Arc(choice, state);
                nArc.weight = p(choice.d1);
                if (choice.d2 != 0) nArc.weight += p(choice.d2);
                Arc rArc = buildStates(nArc);
                if (rArc != null) currentArcs.add(rArc);
            }
        }
        return null;
    }

    private Arc buildStates(Arc arc) {
        Choice cChoice = (Choice) arc.from;
        State cState = (State) arc.to;
        State preState = new State();
        preState.state = Arrays.copyOf(cState.state, cState.state.length);
        preState.state[cChoice.d1 - 2]--;
        if (cChoice.d2 != 0) preState.state[cChoice.d2 - 2]--;
        if (preState.equals(currentState)) return new Arc(preState, cChoice);
        return buildChoices(new Arc(preState, cChoice));
    }

    private double p(int n) {
        if (n < 2) return 0;
        if (n <= 7) return (n - 1) / 36d;
        else return (12 - n + 1) / 36d;
    }


    @Override
    public boolean stop(Jeu j) {
        int[][] bonzes = j.getBonzes();

        return new Random().nextBoolean();
    }

    @Override
    public String getName() {
        return "BOUDVILLAIN PIERRE2";
    }
}

class Graph {

    public List<State> U1 = new ArrayList<>();
    public List<Choice> V1 = new ArrayList<>();
    public List<State> U2 = new ArrayList<>();
    public List<Choice> V2 = new ArrayList<>();
    public List<Arc> E = new ArrayList<>();

}

class State {
    int[] state = new int[11];
    boolean isFinal = false;

    @Override
    public String toString() {
        return "State{" +
                "state=" + Arrays.toString(state) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state1 = (State) o;
        return Arrays.equals(state, state1.state);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(state);
    }
}

class Choice {
    int d1 = 0;
    int d2 = 0;

    public Choice(int d1) {
        this.d1 = d1;
    }

    public Choice(int d1, int d2) {
        this.d1 = d1;
        this.d2 = d2;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "d1=" + d1 +
                ", d2=" + d2 +
                '}';
    }
}

class Arc {
    public Object from;
    public Object to;
    public double weight = 1;

    public Arc(Choice c, State state) {
        from = c;
        to = state;
    }

    public Arc(State state, Choice c) {
        from = state;
        to = c;
    }
}
