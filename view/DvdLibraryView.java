package view;

import java.util.List;

import dao.UserIO;
import dto.Dvd;

public class DvdLibraryView {
    UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. Add a DVD");
        io.print("3. Remove a DVD");
        io.print("4. Edit a DVDs Information");
        io.print("5. Search and View Information by DVD Title");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public Dvd getNewDvdInfo() {
        String dvdName = io.readString("Please enter DVD Title");
        int releaseDate = io.readInt("Please enter release date", 0, 2018);
        String mpaaRating = io.readString("Please enter the DVD's MPAA Rating");
        String studio = io.readString("Please enter the Studio");
        String directorsName = io.readString("Please enter the director's name");
        String userRating = io.readString("Please enter user rating");

        Dvd currentDvd = new Dvd(dvdName);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setStudio(studio);
        currentDvd.setDirectorsName(directorsName);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    public Dvd getEditDvdInfo(Dvd dvd) {

        if (dvd != null) {

            int releaseDate = io.readInt("Please enter release date", 0, 2018);
            String mpaaRating = io.readString("Please enter the DVD's MPAA Rating");
            String directorsName = io.readString("Please enter the director's name");
            String studio = io.readString("Please enter the Studio");
            String userRating = io.readString("Please enter user rating");

            dvd.setReleaseDate(releaseDate);
            dvd.setMpaaRating(mpaaRating);
            dvd.setStudio(studio);
            dvd.setDirectorsName(directorsName);
            dvd.setUserRating(userRating);
        } else {
            io.print("DVD not found.");
        }
        return dvd;
    }

    public void displayDvdList(List<Dvd> dvdList) {

        for (Dvd currentDvd : dvdList) {
            io.print(currentDvd.getTitle() + ": " + currentDvd.getReleaseDate() + " " + currentDvd.getMpaaRating() + " "
                    + currentDvd.getStudio() + " " + currentDvd.getDirectorsName() + " " + currentDvd.getUserRating());
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle() + ": " + dvd.getReleaseDate() + " " + dvd.getMpaaRating() + " " + dvd.getStudio()
                    + " " + dvd.getDirectorsName() + " " + dvd.getUserRating());
        } else {
            io.print("DVD not found.");
        }

        io.readString("Please hit enter to continue.");
    }

    public void displayListAllDvdsBanner() {
        io.print("=== DVD Collection ===");
    }

    public void displayAddDvdBanner() {
        io.print("=== Add DVD ===");
    }

    public void displayAddDvdSuccessBanner() {
        io.readString("DVD successfully added.  Please press enter to continue");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove DVD ===");
    }

    public String getDvdTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }

    public void displayRemoveDvdSuccessBanner() {
        io.readString("DVD successfully removed.  Please press enter to continue");
    }

    public void displayEditDvdBanner() {
        io.readString("Please enter the DVD you wish to edit.  Please press enter to continue");
    }

    public void displayEditDvdSuccessBanner() {
        io.readString("DVD succcessfully edited.  Please press enter to continue");
    }

    public void displaySearchDvdByNameBanner() {
        io.print("=== Search DVD By Name ===");
    }

    public void displayPromptToContinueBanner() {
        io.print("Please press enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Invalid Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
