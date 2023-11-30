package software_Engineering_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bike {
	// Attributes
	private int bicycleID;
	private String userRating;
	private String location;
	private String condition;
	private boolean isAvailable;
	private double lateFee;
	private double price;
	private List<UserReview> userReviews;
	private static ArrayList<Bike> blist;

	// Constructor
	public Bike(int bicycleID, String location, String userRating, String condition, boolean isAvailable, double price,
			double lateFee) {
		this.bicycleID = bicycleID;
		this.location = location;
		this.condition = condition;
		this.isAvailable = isAvailable;
		this.price = price;
		this.lateFee = lateFee;
		this.userRating = userRating;

		// Initialize userReviews list
		this.userReviews = new ArrayList<>();
	}

	public static ArrayList<Bike> getBikelist() {
		return blist;
	}

	// Getter and Setter methods for each attribute
	public int getBicycleID() {
		return bicycleID;
	}

	public void setBicycleID(int bicycleID) {
		this.bicycleID = bicycleID;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return this.location;
	}

	public String getUserRating() {
		return this.userRating;
	}

	public void setUserRating(String userRating) {
		// Validate the userRating
		if (isValidRating(userRating)) {
			this.userRating = userRating;
		} else {
			System.out.println("Invalid user rating. Please choose A, B, C, E, or F.");
		}
	}

	private boolean isValidRating(String rating) {
		// Check if the provided rating is valid
		return (rating == "A" || rating == "B" || rating == "C" || rating == "D" || rating == "E");
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public boolean getAvailable() {
		return this.isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getlatefee() {
		return lateFee;
	}

	public void setlatefee(double latefee) {
		this.lateFee = latefee;
	}

//add the review to the bike's list of reviews 
	public void addReview(String userName, char rating, String comments) {
		UserReview review = new UserReview(userName, rating, comments);
		userReviews.add(review);
	}

//shows the bikes list of reviews
	public List<UserReview> getUserReviews() {
		return userReviews;
	}

//adds arraylist of bikes to the file of bikes
	public static ArrayList<Bike> addbikes(String bfile) {
		ArrayList<Bike> blist = new ArrayList<>();
		try (Scanner bscan = new Scanner(new File(bfile))) {
			while (bscan.hasNext()) {
				String[] nextLine = bscan.nextLine().split("_");

				// Extract bike attributes from the line
				int bicycleID = Integer.parseInt(nextLine[0]);
				String location = nextLine[1];
				String userRating = nextLine[2];
				String condition = nextLine[3];
				boolean isAvailable = Boolean.parseBoolean(nextLine[4]);
				double price = Double.parseDouble(nextLine[5]);
				double latefee = Double.parseDouble(nextLine[6]);

				// Create a Bike object and add it to the list
				Bike bike = new Bike(bicycleID, location, condition, userRating, isAvailable, price, latefee);
				blist.add(bike);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return blist;
	}

	// add bike to the arraylist
	public void addBike(Bike bike) {
		blist.add(bike);
	}

	private static class UserReview {
		private String userName;
		private char rating;
		private String comments;

		public UserReview(String userName, char rating, String comments) {
			this.userName = userName;
			this.rating = rating;
			this.comments = comments;
		}

		public String getUserName() {
			return userName;
		}

		public char getRating() {
			return rating;
		}

		public String getComments() {
			return comments;
		}
	}
}
