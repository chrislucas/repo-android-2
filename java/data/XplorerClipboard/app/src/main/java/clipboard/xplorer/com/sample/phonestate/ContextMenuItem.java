package clipboard.xplorer.com.sample.phonestate;

import android.util.ArrayMap;

public enum ContextMenuItem {
    COPY(1), PASTE(2), CUT(3);
    private int id;
    public static final int GROUP_ID = 0x01;

    ContextMenuItem(int id) {
        this.id = id;
    }

    public static final ArrayMap<ContextMenuItem, String> names = new ArrayMap<>();

    static {
        names.put(COPY, "Copiar");
        names.put(PASTE, "Colar");
        names.put(CUT, "Recortar");
    }

    public String getName() {
        return names.get(this);
    }

    public int getId() {
        return id;
    }

    public static ContextMenuItem get(int id) {
        ContextMenuItem [] v = values();
        for (int i=0; i<v.length; i++) {
            if (v[i].id == id)
                return v[i];
        }
        return null;
    }
}
