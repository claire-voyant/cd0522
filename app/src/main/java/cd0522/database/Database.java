package cd0522.database;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import cd0522.data.Indexable;

/**
 * A simplified read-only database to store a set of entries. Allows
 * for quick requests of data and a method of extending the set of
 * entries.
 */
public abstract sealed class Database<Key extends Indexable, Entry extends Indexable> permits RentDatabase, ToolDatabase {
    private Map<Integer, Entry> database;

    public Database() {
        this.database = new HashMap<>();
        for (Entry initialEntry : getInitialValues()) {
            storeEntry(initialEntry);
        }
    }

    public Optional<Entry> retrieveEntry(Key key) {
        if (database.containsKey(key.getIndex())) {
            return Optional.of(database.get(key.getIndex()));
        } else {
            return Optional.empty();
        }
    }

    public abstract Set<Entry> getInitialValues();

    private boolean storeEntry(Entry entry) {
        return database.putIfAbsent(entry.getIndex(), entry) != null;
    }
}
