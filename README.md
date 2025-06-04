EnigmaSimulator
===============

Simulator for rotor cipher machine Enigma.

## Graphical User Interface

A simple Swing based interface is available to try out the machine and observe how the rotor positions change while a message is encrypted.

To run the UI:

```bash
javac $(find src -name '*.java')
java -cp src safo.enigma.EnigmaUI
```

The window allows you to set the starting positions of the three rotors and encrypt a message. For each character the resulting rotor state is shown so the stepping mechanism can be followed.
