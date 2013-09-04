package safo.enigma.machine;

public class PlugBoard {
    
    private Pair[] pairs;
    
    public PlugBoard() {
        pairs = new Pair[0];
    }

    public Pair getPair(int num) {
        return pairs[num];
    }
    
    // addPair

    public void setPair(int num, Pair p) {
        this.pairs[num] = p;
    }
    
    public void setNumberOfPairs(int n) {
        pairs = new Pair[n];
    }
    
    public int getOutput(int input) {
        for (int i = 0; i < pairs.length; i++) {
            if (pairs[i].getC1() == input) return pairs[i].getC2();
            if (pairs[i].getC2() == input) return pairs[i].getC1();
        }
        return input;
    }
    
    public String toString() {
        
        String s = "";
        
        for (int i = 0; i < pairs.length; i++) {
            s += pairs[i];
            if (i < pairs.length-1) s += "  ";
        }
        
        return "(Plugboard: " + s + ")";
        
    }
    

}
