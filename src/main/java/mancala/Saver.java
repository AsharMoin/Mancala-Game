package mancala;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Saver {

    public void saveObject(final Serializable toSave, final String filename) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(toSave);
        } catch (IOException i) {
            i.printStackTrace();
            throw new IOException("Error saving object to file", i);
        }
    }

    public Serializable loadObject(final String filename) throws IOException, ClassNotFoundException {
        try (FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (Serializable) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions, e.g., if the file doesn't exist or cannot be deserialized
            throw new IOException("Could Not Load File", e);
        }
    }
}