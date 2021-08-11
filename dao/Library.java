package dao;

import dto.*;
import java.util.List;

public interface Library {

    List<Dvd> getAll() throws DvdLibraryException;

    Dvd get(String dvdTitle) throws DvdLibraryException;

    Dvd add(String dvdTitle, Dvd dvd) throws DvdLibraryException;

    Dvd remove(String dvdTitle) throws DvdLibraryException;
}
