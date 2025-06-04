package safo.enigma;

import safo.enigma.machine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnigmaUI {

    private Enigma enigma = new Enigma();
    private JTextField inputField;
    private JTextArea outputArea;
    private JTextField rotor0Field;
    private JTextField rotor1Field;
    private JTextField rotor2Field;

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Enigma Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel settingsPanel = new JPanel();
        settingsPanel.add(new JLabel("Rotor 0"));
        rotor0Field = new JTextField("A", 2);
        settingsPanel.add(rotor0Field);
        settingsPanel.add(new JLabel("Rotor 1"));
        rotor1Field = new JTextField("A", 2);
        settingsPanel.add(rotor1Field);
        settingsPanel.add(new JLabel("Rotor 2"));
        rotor2Field = new JTextField("A", 2);
        settingsPanel.add(rotor2Field);

        frame.add(settingsPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Message"));
        inputField = new JTextField(20);
        inputPanel.add(inputField);
        JButton encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                encryptMessage();
            }
        });
        inputPanel.add(encryptButton);
        frame.add(inputPanel, BorderLayout.CENTER);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private void encryptMessage() {
        String r0 = rotor0Field.getText().toUpperCase();
        String r1 = rotor1Field.getText().toUpperCase();
        String r2 = rotor2Field.getText().toUpperCase();
        if (r0.isEmpty() || r1.isEmpty() || r2.isEmpty()) return;
        enigma.setRotorState(0, r0.charAt(0));
        enigma.setRotorState(1, r1.charAt(0));
        enigma.setRotorState(2, r2.charAt(0));

        String msg = inputField.getText().toUpperCase();
        StringBuilder enc = new StringBuilder();
        outputArea.append("Input: " + msg + "\n");
        for (int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                int out = enigma.getOutput(Functions.charToDigit(ch));
                char oc = Functions.digitToChar(out);
                enc.append(oc);
                outputArea.append("" + ch + " -> " + oc + "  (");
                outputArea.append(Functions.digitToChar(enigma.getRotorState(0)) + " ");
                outputArea.append(Functions.digitToChar(enigma.getRotorState(1)) + " ");
                outputArea.append(Functions.digitToChar(enigma.getRotorState(2)) + ")\n");
            } else if (ch == ' ') {
                enc.append(' ');
                outputArea.append("(space)\n");
            }
        }
        outputArea.append("Encrypted: " + enc.toString() + "\n\n");
        rotor0Field.setText("" + Functions.digitToChar(enigma.getRotorState(0)));
        rotor1Field.setText("" + Functions.digitToChar(enigma.getRotorState(1)));
        rotor2Field.setText("" + Functions.digitToChar(enigma.getRotorState(2)));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EnigmaUI().createAndShowGUI();
            }
        });
    }
}
