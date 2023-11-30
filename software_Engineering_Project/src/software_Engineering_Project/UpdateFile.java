package software_Engineering_Project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UpdateFile {

	UpdateFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt"));
			BufferedWriter writer_2 = new BufferedWriter(new FileWriter("userbike.txt"));
			for (Bike bike : SecondFrame.availBikes) {
				if (SecondFrame.availBikes.size() > 0) {

					writer.write(bike.getBicycleID() + "_");
					writer.write(bike.getLocation() + "_");
					writer.write(bike.getUserRating() + "_");
					writer.write(bike.getCondition() + "_");
					writer.write(bike.getAvailable() + "_");
					writer.write(bike.getPrice() + "_");
					writer.write(bike.getlatefee() + "\n");
				}
			}
			if (SecondFrame.bikeOfuser == null) {

			}
			if (SecondFrame.bikeOfuser != null) {

				writer_2.write(SecondFrame.bikeOfuser.getBicycleID() + "_");
				writer_2.write(SecondFrame.bikeOfuser.getLocation() + "_");
				writer_2.write(SecondFrame.bikeOfuser.getUserRating() + "_");
				writer_2.write(SecondFrame.bikeOfuser.getCondition() + "_");
				writer_2.write(SecondFrame.bikeOfuser.getAvailable() + "_");
				writer_2.write(SecondFrame.bikeOfuser.getPrice() + "_");
				writer_2.write(SecondFrame.bikeOfuser.getlatefee() + "\n");

			}

			writer.close();
			writer_2.close();
		} catch (IOException ex) {
		}
	}

}
