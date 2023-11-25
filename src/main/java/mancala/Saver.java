package mancala;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Saver {

    public void saveObject(final Serializable toSave, final String filename) throws IOException {
        // Extract the directory path from the filename
        File file = new File(filename);
        File directory = file.getParentFile();

        // Create the directory if it does not exist
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directory.getAbsolutePath());
            } else {
                throw new IOException("Failed to create directory: " + directory.getAbsolutePath());
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(file);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(toSave);
        } catch (IOException i) {
            throw new IOException("Error saving object to file", i);
        }
    }

    public Serializable loadObject(final String filename) throws IOException, ClassNotFoundException {
        try (FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (Serializable) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Could Not Load File", e);
        }
    }
}