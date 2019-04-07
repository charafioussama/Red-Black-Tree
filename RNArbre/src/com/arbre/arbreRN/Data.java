package com.arbre.arbreRN;

public class Data implements Comparable<Data> {
	
    private int value;
    
    public Data(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    @Override
    public int compareTo(Data d) {
        return value - d.getValue();
    }
}
