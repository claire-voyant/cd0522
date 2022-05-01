package cd0522.database;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * A simplified read-only database to store a set of entries. Allows
 * for quick requests of data and a method of extending the set of
 * entries.
 */
public abstract sealed class Database<Entry> permits RentDatabase, ToolDatabase {
    private Map<Integer, Entry> database;

    public Database(Map<Integer, Entry> database) {
        this.database = database;
        for (Entry initialEntry : getInitialValues()) {
            storeEntry(initialEntry);
        }
    }

    public Optional<Entry> retrieveEntry(Entry entry) {
        if (database.containsKey(entry.hashCode())) {
            return Optional.of(database.get(entry.hashCode()));
        } else {
            return Optional.empty();
        }
    }

    public abstract Set<Entry> getInitialValues();

    private boolean storeEntry(Entry entry) {
        return database.putIfAbsent(entry.hashCode(), entry) != null;
    }
}
