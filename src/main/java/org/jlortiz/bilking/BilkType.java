package org.jlortiz.bilking;

public enum BilkType {
    COW("Milk"),
    SHEEP("Sheep Milk"),
    LLAMA("Llama Milk"),
    PIG("Pig Milk"),
    HORSE("Horse Milk"),
    DONKEY("Donkey Milk"),
    MULE("Mule Milk"),
    PLAYER("Human Milk"),
    PLAYER_M("Bilk"),
    PLAYER_F("Breast Milk");

    private String name;

    BilkType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
