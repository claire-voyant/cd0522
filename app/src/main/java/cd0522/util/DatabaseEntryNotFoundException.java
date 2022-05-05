package cd0522.util;

/**
 * Thrown when an entry can not be found in the database.
 */
public class DatabaseEntryNotFoundException extends Exception {
    public DatabaseEntryNotFoundException(String message) {
        super(message);
    }
}
