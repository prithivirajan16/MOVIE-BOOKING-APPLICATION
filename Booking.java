package MovieSystem;
import java.io.*;

public class Booking {
    private static int lastBookingId = 0; //Initial bookingId
    private static final String LAST_BOOKING_ID_FILE = "last_booking_id.txt";

    private int bookingId;
    private User user;
    private Movie movie;
    private int numTickets;
    private String[] selectedSeats;

    public Booking(User user, Movie movie, int numTickets,String[] selectedSeats) {
        this.bookingId = getNextBookingId();
        this.user = user;
        this.movie = movie;
        this.numTickets = numTickets;
        this.selectedSeats = selectedSeats;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public String[] getSelectedSeats() {
        return selectedSeats;
    }
    
    // to generate unique BookingIds
     private int getNextBookingId() {
        if (lastBookingId == 0) {
            loadLastBookingIdFromFile();
        }
        lastBookingId++;
        saveLastBookingIdToFile();
        return lastBookingId;
    }

    private void loadLastBookingIdFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(LAST_BOOKING_ID_FILE))) {
            String line;
            if ((line = br.readLine()) != null) {
                lastBookingId = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading last booking ID from file: " + e.getMessage());
        }
    }

    private void saveLastBookingIdToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LAST_BOOKING_ID_FILE))) {
            writer.println(lastBookingId);
        } catch (IOException e) {
            System.out.println("Error saving last booking ID to file: " + e.getMessage());
        }
    }
    
}
