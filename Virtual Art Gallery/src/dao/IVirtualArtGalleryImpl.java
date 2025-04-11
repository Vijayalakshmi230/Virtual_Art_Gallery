package dao;

import java.sql.*;
import java.util.*;
import entity.ArtWork;
import entity.Artist;
import entity.User;
import entity.Gallery;
import util.DBConnection;
import myexceptions.*;

public class IVirtualArtGalleryImpl implements IVirtualArtGallery {
    private static Connection con;

    public IVirtualArtGalleryImpl() {
        con = DBConnection.getConnection();
    }

    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO User (UserID, Username, Password, Email, FirstName, LastName, DateOfBirth, ProfilePicture) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, user.getUserID());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getFirstName());
            stmt.setString(6, user.getLastName());
            stmt.setString(7, user.getDateOfBirth());
            stmt.setString(8, user.getProfilePicture());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addArtist(Artist artist) {
        try {
            String sql = "INSERT INTO Artist (ArtistID, Name, Biography, BirthDate, Nationality, Website, ContactInformation) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, artist.getArtistID());
            stmt.setString(2, artist.getName());
            stmt.setString(3, artist.getBiography());
            stmt.setString(4, artist.getBirthDate());
            stmt.setString(5, artist.getNationality());
            stmt.setString(6, artist.getWebsite());
            stmt.setString(7, artist.getContactInfo());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addArtwork(ArtWork artwork) {
        try {
            String sql = "INSERT INTO Artwork (ArtworkID, Title, Description, CreationDate, Medium, ImageURL, ArtistID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, artwork.getArtworkID());
            stmt.setString(2, artwork.getTitle());
            stmt.setString(3, artwork.getDescription());
            stmt.setString(4, artwork.getCreationDate());
            stmt.setString(5, artwork.getMedium());
            stmt.setString(6, artwork.getImageURL());
            stmt.setInt(7, artwork.getArtistID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addGallery(Gallery gallery) {
        try {
            String sql = "INSERT INTO Gallery (GalleryID, Name, Description, Location, ArtistID, OpeningHours) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, gallery.getGalleryID());
            stmt.setString(2, gallery.getName());
            stmt.setString(3, gallery.getDescription());
            stmt.setString(4, gallery.getLocation());
            stmt.setInt(5, gallery.getCuratorID());
            stmt.setString(6, gallery.getOpeningHours());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateArtwork(ArtWork artwork) {
        try {
            String sql = "UPDATE Artwork SET Title = ?, Description = ? WHERE ArtworkID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, artwork.getTitle());
            stmt.setString(2, artwork.getDescription());
            stmt.setInt(3, artwork.getArtworkID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateGallery(Gallery gallery) {
        try {
            String sql = "UPDATE Gallery SET Name = ?, Location = ?, ArtistID = ? WHERE GalleryID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, gallery.getName());
            stmt.setString(2, gallery.getLocation());
            stmt.setInt(3, gallery.getCuratorID());
            stmt.setInt(4, gallery.getGalleryID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeArtwork(int artworkID) {
        try {
            String sql = "DELETE FROM Artwork WHERE ArtworkID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, artworkID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeGallery(int galleryID) {
        try {
            String sql = "DELETE FROM Gallery WHERE GalleryID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, galleryID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArtWork getArtworkById(int artworkID) throws ArtWorkNotFoundException {
        try {
            String sql = "SELECT * FROM Artwork WHERE ArtworkID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, artworkID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ArtWork(
                    rs.getInt("ArtworkID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getString("CreationDate"),
                    rs.getString("Medium"),
                    rs.getString("ImageURL"),
                    rs.getInt("ArtistID")
                );
            } else {
                throw new ArtWorkNotFoundException("Artwork not found with ID:" + artworkID);
            }
        } catch (SQLException e) {
            
            throw new RuntimeException("Database error while fetching artwork with ID: " + artworkID, e);
        }
    }

    @Override
    public List<ArtWork> searchArtworks(String keyword) {
        List<ArtWork> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Artwork WHERE Title LIKE ? OR Description LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new ArtWork(
                    rs.getInt("ArtworkID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getString("CreationDate"),
                    rs.getString("Medium"),
                    rs.getString("ImageURL"),
                    rs.getInt("ArtistID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

  

    @Override
    public boolean addArtworkToFavorite(int userId, int artworkId) throws UserNotFoundException {
        try {
            // Check if user exists
            String checkUserSql = "SELECT COUNT(*) FROM User WHERE UserID = ?";
            PreparedStatement checkUserStmt = con.prepareStatement(checkUserSql);
            checkUserStmt.setInt(1, userId);
            ResultSet rsUser = checkUserStmt.executeQuery();
            if (rsUser.next() && rsUser.getInt(1) == 0) {
                throw new UserNotFoundException("User with ID " + userId + " not found.");
            }

            String sql = "INSERT INTO FavoriteArtworks (UserID, ArtworkID) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, artworkId);
            return stmt.executeUpdate() > 0;

        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeArtworkFromFavorite(int userId, int artworkId) {
        try {
            String sql = "DELETE FROM FavoriteArtworks WHERE UserID = ? AND ArtworkID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, artworkId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ArtWork> getUserFavoriteArtworks(int userId) {
        List<ArtWork> favorites = new ArrayList<>();
        try {
            String sql = "SELECT a.ArtworkID, a.Title, a.Description, a.CreationDate, a.Medium, a.ImageURL, a.ArtistID "
                       + "FROM Artwork a JOIN FavoriteArtworks f ON a.ArtworkID = f.ArtworkID "
                       + "WHERE f.UserID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                favorites.add(new ArtWork(
                    rs.getInt("ArtworkID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getString("CreationDate"),
                    rs.getString("Medium"),
                    rs.getString("ImageURL"),
                    rs.getInt("ArtistID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favorites;
    }
    
    @Override
    public List<Gallery> searchGallerys(String keyword) {
        List<Gallery> lists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Gallery WHERE Name LIKE ? OR Description LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lists.add(new Gallery(
                    rs.getInt("GalleryID"),
                    rs.getString("Name"),
                    rs.getString("Description"),
                    rs.getString("Location"),
                    rs.getInt("ArtistID"),
                    rs.getString("OpeningHours")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }
}