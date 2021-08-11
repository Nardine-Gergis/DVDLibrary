import controller.DvdLibraryController;
import dao.ConsoleUserIO;
import dao.DvdLibrary;
import dao.UserIO;
import view.DvdLibraryView;

class DvdLibraryApp {
    public static void main(String[] args) {

        UserIO userIO = new ConsoleUserIO();
        DvdLibrary dvdLibrary = new DvdLibrary();
        DvdLibraryView dvdLibraryView = new DvdLibraryView(userIO);
        DvdLibraryController controller = new DvdLibraryController(dvdLibrary, dvdLibraryView);
        controller.run();
    }
}