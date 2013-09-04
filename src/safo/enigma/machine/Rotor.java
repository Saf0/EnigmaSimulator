package safo.enigma.machine;

public class Rotor {
    
    private String name;
    private int number;
    private int[] perm;
    private int[] invPerm;
    private int notch;
    private int ringSetting;
    
    public static final Rotor I = new Rotor("Enigma", 1, "EKMFLGDQVZNTOWYHXUSPAIBRCJ", 17);
    public static final Rotor II = new Rotor("Enigma", 2, "AJDKSIRUXBLHWTMCQGZNPYFVOE", 5);
    public static final Rotor III = new Rotor("Enigma", 3, "BDFHJLCPRTXVZNYEIWGAKMUSQO", 22);
    public static final Rotor IV = new Rotor("Enigma", 4, "ESOVPZJAYQUIRHXLNFTGKDCMWB", 10);
    public static final Rotor V = new Rotor("Enigma", 5, "VZBRGITYUPSDNHLXAWMJQOFECK", 0);
    
    public Rotor(String name, int number, String s, int notch) {
        this.setPerm(s);
        this.setName(name);
        this.setNumber(number);
        this.setNotch(notch);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public int[] getPerm() {
        return perm;
    }
    
    public void setPerm(int[] perm) {
        this.perm = perm;
    }
    
    public void setPerm(String s) {
        
        this.perm = new int[26];
        if (s.length() != 26) return;
        
        for (int i = 0; i < s.length(); i++) {
            int n = s.codePointAt(i) - 65;
            this.perm[i] = n;
        }
        
        createInversePerm();
        
    }
    
    public int getNotch() {
        return notch;
    }

    public void setNotch(int notch) {
        this.notch = notch;
    }

    public int getRingSetting() {
        return ringSetting;
    }

    public void setRingSetting(int ringSetting) {
        this.ringSetting = ringSetting;
    }

    public String toString() {
        
        String s = "";
        
        for (int i = 0; i < perm.length; i++) {
            s = s + (char)(perm[i]+65);
        }
        
        return "(Rotor '" + this.name + " " + "#" + this.number + "' : " + s + ")";
    }
    
    private void createInversePerm() {
        
        invPerm = new int[26];
        
        for (int i = 0; i < perm.length; i++) {
            invPerm[perm[i]] = i;
        }
    }
    
    public int getOutput(int shift, int in) {
        int finalShift = shift - ringSetting;
        return (perm[(in + finalShift + 26) % 26] - finalShift + 26) % 26;
    }
    
    public int getInput(int shift, int out) {
        int finalShift = shift - ringSetting;      
        return (invPerm[(out + finalShift + 26) % 26] - finalShift + 26) % 26;
    }


}
