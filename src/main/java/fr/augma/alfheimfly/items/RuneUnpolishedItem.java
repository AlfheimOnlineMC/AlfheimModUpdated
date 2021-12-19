package fr.augma.alfheimfly.items;

import net.minecraft.item.Item;

public class RuneUnpolishedItem extends RuneItem {

    private final RuneItem runeUnpolished;

    public RuneUnpolishedItem(String name, Item unpolished) {
        super(name, ((RuneItem) unpolished).getRarity());
        this.runeUnpolished = (RuneItem) unpolished;
    }

    public RuneItem getPolished() {
        return this.runeUnpolished;
    }
}
