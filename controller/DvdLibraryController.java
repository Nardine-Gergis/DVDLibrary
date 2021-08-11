package controller;

import dao.DvdLibrary;
import dao.DvdLibraryException;
import view.DvdLibraryView;
import dto.Dvd;
import java.util.List;

public class DvdLibraryController {

    DvdLibrary dvdLibrary;
    DvdLibraryView dvdLibraryView;

    public DvdLibraryController(DvdLibrary dvdLibrary, DvdLibraryView dvdLibraryView) {
        this.dvdLibrary = dvdLibrary;
        this.dvdLibraryView = dvdLibraryView;
    }

    public void run() {

        boolean running = true;
        int menuSelection = 0;

        try {
            while (running) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        addDvd();
                        break;
                    case 3:
                        removeDvd();
                        break;
                    case 4:
                        editDvd();
                        break;
                    case 5:
                        searchAndViewDvd();
                        break;
                    case 6:
                        running = false;
                        break;
                    default:
                        unknownCommand();
                }

            }

        } catch (DvdLibraryException e) {
            dvdLibraryView.displayErrorMessage(e.getMessage());
        }
        exitMessage();
    }

    private int getMenuSelection() {
        return dvdLibraryView.printMenuAndGetSelection();
    }

    private void listDvds() throws DvdLibraryException {
        dvdLibraryView.displayListAllDvdsBanner();
        List<Dvd> dvdList = dvdLibrary.getAll();
        dvdLibraryView.displayDvdList(dvdList);
    }

    private void addDvd() throws DvdLibraryException {
        dvdLibraryView.displayAddDvdBanner();
        Dvd newDvd = dvdLibraryView.getNewDvdInfo();
        dvdLibrary.add(newDvd.getTitle(), newDvd);
        dvdLibraryView.displayAddDvdSuccessBanner();
    }

    private void removeDvd() throws DvdLibraryException {
        dvdLibraryView.displayRemoveDvdBanner();
        String dvdTitle = dvdLibraryView.getDvdTitleChoice();
        dvdLibrary.remove(dvdTitle);
        dvdLibraryView.displayRemoveDvdSuccessBanner();
    }

    private void editDvd() throws DvdLibraryException {
        dvdLibraryView.displayEditDvdBanner();
        String dvdTitle = dvdLibraryView.getDvdTitleChoice();
        Dvd dvd = dvdLibrary.get(dvdTitle);
        dvdLibraryView.getEditDvdInfo(dvd);
        dvdLibrary.add(dvdTitle, dvd);
        dvdLibraryView.displayEditDvdSuccessBanner();
    }

    private void searchAndViewDvd() throws DvdLibraryException {
        dvdLibraryView.displaySearchDvdByNameBanner();
        String dvdTitle = dvdLibraryView.getDvdTitleChoice();
        Dvd dvd = dvdLibrary.get(dvdTitle);
        dvdLibraryView.displayDvd(dvd);
    }

    private void unknownCommand() {
        dvdLibraryView.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        dvdLibraryView.displayExitBanner();
    }
}
