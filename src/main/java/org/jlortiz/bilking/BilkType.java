package org.jlortiz.bilking;

public enum BilkType {
    COW("Milk"),
    SHEEP("Sheep Milk"),
    LLAMA("Llama Milk"),
    PIG("Pig Milk"),
    HORSE("Horse Milk"),
    DONKEY("Donkey Milk"),
    MULE("Mule Milk"),
    PLAYER("??? Milk", true),
    PLAYER_M("Bilk", true),
    PLAYER_F("Breast Milk", true);

    private final String name;
    private final boolean recordOrigin;

    BilkType(String name) {
        this(name, false);
    }
    BilkType(String name, boolean recordOrigin) {
        this.name = name;
        this.recordOrigin = recordOrigin;
    }

    public String getName() {
        return this.name;
    }

    public boolean shouldRecordOrigin() {
        return this.recordOrigin;
    }
}
