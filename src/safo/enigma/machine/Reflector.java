package safo.enigma.machine;

public class Reflector {

    private String name;
    private int[] perm;
    public static final Reflector A = new Reflector("A", "EJMZALYXVBWFCRQUONTSPIKHGD");
    public static final Reflector B = new Reflector("B", "YRUHQSLDPXNGOKMIEBFZCWVJAT");
    public static final Reflector C = new Reflector("C", "FVPJIAOYEDRZXWGCTKUQSBNMHL");
    
    public Reflector() {
        perm = new int[26];
    }
    
    public Reflector(String name, String s) {
        this.name = name;
        this.setPermutation(s);
    }
    
    public void setPermutation(String s) {
        this.perm = new int[26];
        if (s.length() != 26) return;
        
        for (int i = 0; i < s.length(); i++) {
            int n = s.codePointAt(i) - 65;
            this.perm[i] = n;
        }
    }
    
    public String toString() {
        
        String s = "";
        
        for (int i = 0; i < perm.length; i++) {
            s = s + (char)(perm[i] + 65);
        }
        
        return "(Reflector '" + this.name + "' : " + s + ")";
    }
    
    public int getOutput(int in) {
        return perm[in];
    }
    
}
