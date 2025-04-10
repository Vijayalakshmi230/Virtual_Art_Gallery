package myexceptions;

public class ArtWorkNotFoundException extends Exception {
    public ArtWorkNotFoundException(String message) {
        super("The Artwork is not there");
    }
}