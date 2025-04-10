package Test;

import dao.IVirtualArtGalleryImpl;
import entity.ArtWork;
import entity.Gallery;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JT {

    static IVirtualArtGalleryImpl service;

    @BeforeAll
    static void setup() {
        service = new IVirtualArtGalleryImpl();
    }

    // ---------- ARTWORK MANAGEMENT ----------

    @Test
    @Order(1)
    void testAddArtwork() {
        ArtWork artwork = new ArtWork(101, "Starry Night", "A beautiful painting", "1889-12-12", "Oil on Canvas", "url", 1);
        boolean result = service.addArtwork(artwork);
        assertTrue(result, "Artwork should be added successfully");
    }

    @Test
    @Order(2)
    void testUpdateArtwork() {
        ArtWork updated = new ArtWork(101, "Starry Night - Updated", "Updated description");
        boolean result = service.updateArtwork(updated);
        assertTrue(result, "Artwork should be updated successfully");
    }

    @Test
    @Order(3)
    void testSearchArtwork() {
        List<ArtWork> results = service.searchArtworks("Starry");
        assertFalse(results.isEmpty(), "Search should return at least one artwork");
    }

    @Test
    @Order(4)
    void testRemoveArtwork() {
        boolean result = service.removeArtwork(101);
        assertTrue(result, "Artwork should be removed successfully");
    }

    // ---------- GALLERY MANAGEMENT ----------

    @Test
    @Order(5)
    void testAddGallery() {
        Gallery gallery = new Gallery(201, "Modern Art Gallery", "A modern collection", "New York", 1, "10 AM - 6 PM");
        boolean result = service.addGallery(gallery);
        assertTrue(result, "Gallery should be added successfully");
    }

    @Test
    @Order(6)
    void testUpdateGallery() {
        Gallery updated = new Gallery(201, "Modern Art Gallery Updated", "Updated info", "Los Angeles", 1, "11 AM - 7 PM");
        boolean result = service.updateGallery(updated);
        assertTrue(result, "Gallery should be updated successfully");
    }

    @Test
    @Order(7)
    void testSearchGallery() {
        List<Gallery> results = service.searchGallerys("Modern");
        assertFalse(results.isEmpty(), "Search should return at least one gallery");
    }

    @Test
    @Order(8)
    void testRemoveGallery() {
        boolean result = service.removeGallery(201);
        assertTrue(result, "Gallery should be removed successfully");
    }
}
