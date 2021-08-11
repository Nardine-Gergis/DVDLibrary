package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import dto.Dvd;

public class DvdLibrary implements Library {

    public static final String DVDLIBRARY_FILE = new File("").getAbsolutePath() + "/model/DvdLibraryData.txt";
    public static final String DELIMITER = "::";
    private Map<String, Dvd> dvds = new HashMap<>();

    @Override
    public List<Dvd> getAll() throws DvdLibraryException {
        readDvdLibraryData();
        return new ArrayList<>(dvds.values());
    }

    @Override
    public Dvd get(String dvdTitle) throws DvdLibraryException {
        readDvdLibraryData();
        return dvds.get(dvdTitle);
    }

    @Override
    public Dvd add(String dvdTitle, Dvd dvd) throws DvdLibraryException {
        Dvd newDvd = dvds.put(dvdTitle, dvd);
        updateDvdLibraryData();
        return newDvd;
    }

    @Override
    public Dvd remove(String dvdTitle) throws DvdLibraryException {
        Dvd removedDvd = dvds.remove(dvdTitle);
        updateDvdLibraryData();
        return removedDvd;
    }

    private void readDvdLibraryData() throws DvdLibraryException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(DVDLIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryException("Sorry an error occured while loading DVD Library Data!", e);
        }

        String[] currentDvdData;

        while (scanner.hasNextLine()) {
            currentDvdData = scanner.nextLine().split(DELIMITER);

            Dvd currentDvd = new Dvd(currentDvdData[0]);
            currentDvd.setReleaseDate(Integer.parseInt(currentDvdData[1]));
            currentDvd.setMpaaRating(currentDvdData[2]);
            currentDvd.setStudio(currentDvdData[3]);
            currentDvd.setDirectorsName((currentDvdData[4]));
            currentDvd.setUserRating(currentDvdData[5]);

            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }

    private void updateDvdLibraryData() throws DvdLibraryException {

        PrintWriter writer;

        try {
            writer = new PrintWriter(new FileWriter(DVDLIBRARY_FILE));
        } catch (IOException e) {
            throw new DvdLibraryException("Sorry an error occured while saving DVD Library Data!", e);
        }

        List<Dvd> dvdList = this.getAll();
        for (Dvd currentDvd : dvdList) {

            writer.println(currentDvd.getTitle() + DELIMITER + currentDvd.getReleaseDate() + DELIMITER
                    + currentDvd.getMpaaRating() + DELIMITER + currentDvd.getStudio() + DELIMITER
                    + currentDvd.getDirectorsName() + DELIMITER + currentDvd.getUserRating());
            writer.flush();
        }
        writer.close();
    }

}
