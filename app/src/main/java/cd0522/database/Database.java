package cd0522.database;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import cd0522.data.Indexable;

/**
 * A simplified read-only database to store a set of entries. Allows for quick requests of data and
 * a method of extending the set of entries.
 */
public abstract sealed class Database<Key extends Indexable, Entry extends Indexable> permits RentDatabase, ToolDatabase {
    private Map<Integer, Entry> database;

    /**
     * Construct a data base from the intial values supplied by the implementing class.
     */
    public Database() {
        this.database = new HashMap<>();
        for (Entry initialEntry : getInitialValues()) {
            storeEntry(initialEntry);
        }
    }

    /**
     * Get the entry associated with the given key.
     * 
     * @param key the key to look up in the database
     * @return the entry associated with the key in the database
     */
    public Optional<Entry> retrieveEntry(Key key) {
        if (database.containsKey(key.getIndex())) {
            return Optional.of(database.get(key.getIndex()));
        } else {
            return Optional.empty();
        }
    }

    /**
     * The set of initial values to load into the database at creation.
     * 
     * @return set of initial values
     */
    public abstract Set<Entry> getInitialValues();

    /**
     * Store a given entry in the database if it's not present.
     * 
     * @param entry the entry being stored
     * @return true if the entry was successfully stored false otherwise
     */
    private boolean storeEntry(Entry entry) {
        return database.putIfAbsent(entry.getIndex(), entry) != null;
    }
}
