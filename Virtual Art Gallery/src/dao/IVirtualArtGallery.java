package dao;

import java.util.List;
import entity.ArtWork;
import entity.User;
import entity.Artist;
import entity.Gallery;
import myexceptions.ArtWorkNotFoundException;
import myexceptions.UserNotFoundException;


public interface IVirtualArtGallery {
    boolean addArtwork(ArtWork artwork);
    boolean updateArtwork(ArtWork artwork);
    boolean removeArtwork(int artworkID);
    ArtWork getArtworkById(int artworkID) throws ArtWorkNotFoundException;
    boolean addUser(User user);
    boolean addArtist(Artist artist);
    boolean addGallery(Gallery gallery);
    boolean updateGallery(Gallery gallery);
    boolean removeGallery(int galleryID);
    
    List<ArtWork> searchArtworks(String keyword);
    List<Gallery> searchGallerys(String keyword);
    boolean addArtworkToFavorite(int userId, int artworkId) throws UserNotFoundException;
    boolean removeArtworkFromFavorite(int userId, int artworkId);
    List<ArtWork> getUserFavoriteArtworks(int userId);
}