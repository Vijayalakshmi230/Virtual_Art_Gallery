package main;

import dao.IVirtualArtGalleryImpl;
import entity.Artist;
import entity.ArtWork;
import entity.User;
import myexceptions.UserNotFoundException;
import myexceptions.ArtWorkNotFoundException;
import entity.Gallery;
import java.util.List;
import java.util.Scanner;

public class MainModule {
	public static void main(String[] args) {
		IVirtualArtGalleryImpl service = new IVirtualArtGalleryImpl();
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n=======Virtual Art Gallery Menu=========\n");
			System.out.println("1.  Add User");
			System.out.println("2.  Add Artist");
			System.out.println("3.  Add Artwork");
			System.out.println("4.  Add Gallery");
			System.out.println("5.  Update Artwork");
			System.out.println("6.  Update Gallery");
			System.out.println("7.  View Artwork by ID");
			System.out.println("8.  Search Artworks");
			System.out.println("9.  Search Gallery");
			System.out.println("10. Add to Favorites");
			System.out.println("11. View Favorites");
			System.out.println("12. Remove from Favorites");
			System.out.println("13. Remove Artwork");
			System.out.println("14. Remove Gallery");
			System.out.println("0.  Exit");
			System.out.print("\nEnter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
//			Add User
			case 1:
				System.out.print("Enter User ID: ");
				int uid = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Username: ");
				String uname = scanner.nextLine();
				System.out.print("Enter Password: ");
				String pwd = scanner.nextLine();
				System.out.print("Enter Email: ");
				String email = scanner.nextLine();
				System.out.print("Enter First Name: ");
				String firstName = scanner.nextLine();
				System.out.print("Enter Last Name: ");
				String lastName = scanner.nextLine();
				System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
				String dob = scanner.nextLine();
				System.out.print("Enter Profile Picture URL (or path): ");
				String profilePic = scanner.nextLine();

				User user = new User(uid, uname, pwd, email, firstName, lastName, dob, profilePic);
				System.out.println("User added: " + service.addUser(user));
				break;

//			Add Artist
			case 2:
				System.out.print("Enter Artist ID: ");
				int artistId = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Name: ");
				String name = scanner.nextLine();
				System.out.print("Enter Biography: ");
				String bio = scanner.nextLine();
				System.out.print("Enter Birth Date (YYYY-MM-DD): ");
				String birthDate = scanner.nextLine();
				System.out.print("Enter Nationality: ");
				String nationality = scanner.nextLine();
				System.out.print("Enter Website: ");
				String website = scanner.nextLine();
				System.out.print("Enter Contact Info: ");
				String contact = scanner.nextLine();

				Artist artist = new Artist(artistId, name, bio, birthDate, nationality, website, contact);
				System.out.println("Artist added: " + service.addArtist(artist));
				break;

//			Add Artwork
			case 3:
				System.out.print("Enter Artwork ID: ");
				int addId = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Title: ");
				String title = scanner.nextLine();
				System.out.print("Enter Description: ");
				String desc = scanner.nextLine();
				System.out.print("Enter Creation Date: ");
				String date = scanner.nextLine();
				System.out.print("Enter Medium: ");
				String medium = scanner.nextLine();
				System.out.print("Enter Image URL: ");
				String imgUrl = scanner.nextLine();
				System.out.print("Enter Artist ID: ");
				int artistRefId = scanner.nextInt();
				ArtWork newFullArt = new ArtWork(addId, title, desc, date, medium, imgUrl, artistRefId);
				System.out.println("Artwork Added: " + service.addArtwork(newFullArt));
				break;

//          Add Gallery
			case 4:
				System.out.print("Enter Gallery ID: ");
				int galleryId = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Gallery Name: ");
				String gName = scanner.nextLine();
				System.out.print("Enter Description: ");
				String gDesc = scanner.nextLine();
				System.out.print("Enter Location: ");
				String location = scanner.nextLine();
				System.out.print("Enter Curator (Artist ID): ");
				int curatorID = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Opening Hours: ");
				String openingHours = scanner.nextLine();

				Gallery gallery = new Gallery(galleryId, gName, gDesc, location, curatorID, openingHours);
				System.out.println("Gallery added: " + service.addGallery(gallery));
				break;

//			Update ArtWork
			case 5:
				System.out.print("Enter Artwork ID to update: ");
				int updateId = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter New Title: ");
				String newTitle = scanner.nextLine();
				System.out.print("Enter New Description: ");
				String newDesc = scanner.nextLine();

				ArtWork updatedArt = new ArtWork(updateId, newTitle, newDesc);
				System.out.println("Artwork Updated: " + service.updateArtwork(updatedArt));
				break;

//			Update Gallery
			case 6:
				System.out.print("Enter Gallery ID to update: ");
				int updateGid = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter New Name: ");
				String newGName = scanner.nextLine();
				System.out.print("Enter New Description: ");
				String newGDesc = scanner.nextLine();
				System.out.print("Enter New Location: ");
				String newLocation = scanner.nextLine();
				System.out.print("Enter New Curator ID: ");
				int newCurator = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter New Opening Hours: ");
				String newHours = scanner.nextLine();

				Gallery updatedGallery = new Gallery(updateGid, newGName, newGDesc, newLocation, newCurator, newHours);
				System.out.println("Gallery Updated: " + service.updateGallery(updatedGallery));
				break;

//         View Artwork by ID
			case 7:
				try {
					System.out.print("Enter Artwork ID to view: ");
					int viewId = scanner.nextInt();
					ArtWork art = service.getArtworkById(viewId); // may throw exception

					System.out.println("\nArtwork Details:");
					System.out.println("ID: " + art.getArtworkID());
					System.out.println("Title: " + art.getTitle());
					System.out.println("Description: " + art.getDescription());
				} catch (ArtWorkNotFoundException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;

//          Search Artwork				
			case 8:
				System.out.print("Enter keyword to search: ");
				String keyword = scanner.nextLine();
				List<ArtWork> results = service.searchArtworks(keyword);

				if (results.isEmpty()) {
					System.out.println("No artworks found for the keyword: " + keyword);
				} else {
					System.out.println("Search Results:");
					for (ArtWork a : results) {
						System.out.println("- " + a.getArtworkID() + ": " + a.getTitle() + " | " + a.getDescription());
					}
				}
				break;

//			   Search Gallery
			case 9:
				System.out.print("Enter keyword to search gallery: ");
				String galleryKeyword = scanner.nextLine();
				List<Gallery> galleries = service.searchGallerys(galleryKeyword);

				if (galleries.isEmpty()) {
					System.out.println("No galleries found for the keyword: " + galleryKeyword);
				} else {
					System.out.println("Search Results:");
					for (Gallery g : galleries) {
						System.out.println("- " + g.getGalleryID() + ": " + g.getName() + " | " + g.getLocation()
								+ " | " + g.getDescription());
					}
				}
				break;

//				Add to Favorites
			case 10:
				System.out.print("Enter User ID: ");
				int favUserId = scanner.nextInt();
				System.out.print("Enter Artwork ID to favorite: ");
				int favArtId = scanner.nextInt();

				try {
					boolean success = service.addArtworkToFavorite(favUserId, favArtId);
					if (success) {
						System.out.println("Artwork added to favorites successfully.");
					} else {
						System.out.println("Failed to add to favorites.");
					}
				} catch (UserNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;

//			View Favorites
			case 11:
				System.out.print("Enter User ID to view favorites: ");
				int viewFavUserId = scanner.nextInt();
				List<ArtWork> favs = service.getUserFavoriteArtworks(viewFavUserId);
				System.out.println("Favorites:");
				for (ArtWork a : favs) {
					System.out.println("- " + a.getArtworkID() + ": " + a.getTitle());
				}
				break;

//			Remove from Favorites
			case 12:
				System.out.print("Enter User ID: ");
				int rmUserId = scanner.nextInt();
				System.out.print("Enter Artwork ID to remove from favorites: ");
				int rmArtId = scanner.nextInt();
				System.out.println("Removed from Favorites: " + service.removeArtworkFromFavorite(rmUserId, rmArtId));
				break;

//				Remove Artwork
			case 13:
				System.out.print("Enter Artwork ID to delete: ");
				int delId = scanner.nextInt();
				System.out.println("Artwork Deleted: " + service.removeArtwork(delId));
				break;

//				Remove Gallery
			case 14:
				System.out.print("Enter Gallery ID to delete: ");
				int removeGid = scanner.nextInt();
				System.out.println("Gallery Deleted: " + service.removeGallery(removeGid));
				break;

//          Exit 
			case 0:
				System.out.println("Exiting Virtual Art Gallery. Goodbye!");
				break;
//          default case
			default:
				System.out.println("Invalid choice. Try again.");
			}

		} while (choice != 0);
	}
}