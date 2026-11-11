package project.model.menu;

import java.util.ArrayList;
import java.util.List;

public class LevelMenu {
    private final List<String> entries;
    private int currentEntry;

    public LevelMenu(int numLevels) {
        this.entries = new ArrayList<>();
        for (int i = 1; i <= numLevels; i++) {
            entries.add("Level " + i);
        }
    }

    public void nextEntry() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1) {
            currentEntry = 0;
        }
    }

    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0) {
            currentEntry = this.entries.size() - 1;
        }
    }

    public String getCurrentEntry(int i) {
        return entries.get(i);
    }

    public String getEntry(int i) {return entries.get(i);}

    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public int getSelectedLevel() {
        return currentEntry + 1;
    }

    public int getNumberEntries() {
        return this.entries.size();
    }

}
