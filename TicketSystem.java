package MovieSystem;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicketSystem{
    public List<Movie> movies;
    private List<User> users;
    private List<Booking> bookings;

    private static final int MAX_TICKETS = 5;
    private static final String USERS_FILE_PATH = "users.txt";
    public static final String KOLLYWOOD_FILE_PATH = "kollywood.txt";
    public static final String HOLLYWOOD_FILE_PATH = "hollywood.txt";
    public static final String SEAT_FILE_PATH = "seat_matrix.txt";
    private static final String USER_HISTORY_FILE_PATH = "userhistory.txt";

    public TicketSystem(){
        this.movies = new ArrayList<>();
        this.users = new ArrayList<>();
        this.bookings = new ArrayList<>();
        loadUsersFromFile();
        loadMoviesFromFile();
    }

    //login
    public User login(String username, String password){
        for(User user : users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // To load user details from file
    private void loadUsersFromFile(){
        try(Scanner scanner = new Scanner(new File(USERS_FILE_PATH))){
            while(scanner.hasNextLine()){
                String[] userData = scanner.nextLine().split(",");
                User user = new User(Integer.parseInt(userData[0]), userData[1], userData[2].trim());
                users.add(user);
            }
        } 
        catch (FileNotFoundException e){
            System.out.println("File not found!!");
        }
    }

    // To load Kollywood movies details from file
    private void loadKollywoodMoviesFromFile() {
        try (Scanner scanner = new Scanner(new File(KOLLYWOOD_FILE_PATH))) {
            while (scanner.hasNextLine()) {
            String[] movieData = scanner.nextLine().split(",");

            if (movieData.length == 7 && "Tamil".equalsIgnoreCase(movieData[6].trim())) {
                Movie movie = new Movie(
                        this,
                        Integer.parseInt(movieData[0].trim()),
                        movieData[1],
                        movieData[2],
                        Integer.parseInt(movieData[3].trim()),
                        Integer.parseInt(movieData[4].trim()),
                        Double.parseDouble(movieData[5].trim()),  
                        movieData[6].trim() 
                );
                movies.add(movie);
            }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + KOLLYWOOD_FILE_PATH);
        }
    }
// To save the contents in the file
private void saveKollywoodMoviesToFile() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(KOLLYWOOD_FILE_PATH))) {
        for (Movie movie : movies) {
            if ("Tamil".equalsIgnoreCase(movie.getLanguage())) {
                writer.printf("%d,%s,%s,%d,%d,%.2f,%s%n",
                        movie.getId(),
                        movie.getTitle(),
                        movie.getGenre(),
                        movie.getDuration(),
                        movie.getAvailableSeats(),
                        movie.getTicketPrice(),
                        movie.getLanguage());
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// To load Hollywood movies details from file
private void loadHollywoodMoviesFromFile() {
    try (Scanner scanner = new Scanner(new File(HOLLYWOOD_FILE_PATH))) {
        while (scanner.hasNextLine()) {
            String[] movieData = scanner.nextLine().split(",");

            if (movieData.length == 7 && "English".equalsIgnoreCase(movieData[6].trim())) {
                Movie movie = new Movie(
                        this,
                        Integer.parseInt(movieData[0].trim()),
                        movieData[1],
                        movieData[2],
                        Integer.parseInt(movieData[3].trim()),
                        Integer.parseInt(movieData[4].trim()),
                        Double.parseDouble(movieData[5].trim()),  
                        movieData[6].trim() 
                );
                movies.add(movie);
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + HOLLYWOOD_FILE_PATH);
    }
}

// To save the contents in the file
private void saveHollywoodMoviesToFile() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(HOLLYWOOD_FILE_PATH))) {
        for (Movie movie : movies) {
            if ("English".equalsIgnoreCase(movie.getLanguage())) {
                writer.printf("%d,%s,%s,%d,%d,%.2f,%s%n",
                        movie.getId(),
                        movie.getTitle(),
                        movie.getGenre(),
                        movie.getDuration(),
                        movie.getAvailableSeats(),
                        movie.getTicketPrice(),
                        movie.getLanguage());
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    private void loadMoviesFromFile() {
        loadKollywoodMoviesFromFile();
        loadHollywoodMoviesFromFile();
    }

    private void saveMoviesToFile() {
        saveKollywoodMoviesToFile();
        saveHollywoodMoviesToFile();
    }

    public double getTicketPrice(int movieId) {
        Movie movie = findMovieById(movieId);
        if (movie != null) {
            return movie.getTicketPrice();
        }
        return -1; 
    }

    public String getLanguage(int movieId) {
        Movie movie = findMovieById(movieId);
        if (movie != null) {
            return movie.getLanguage();
        }
        return ""; 
    }

    //To search movie by its ID
    public Movie findMovieById(int movieId){
        for(Movie movie : movies){
            if(movie.getId()==movieId){
                return movie;
            }
        }
        return null;
    }

    // To search user by their ID
    public User findUserById(int userId){
        for(User user : users) {
            if(user.getUserId()==userId){
                return user;
            }
        }
        return null;
    }

    //To search for Booking by ID
    public Booking findBookingById(int bookingId){
        for(Booking booking : bookings) {
            if(booking.getBookingId() ==bookingId){
                return booking;
            }
        }
        return null;
    }

    public List<Movie> getAllMovies(){
        return movies;
    }

    public List<User> getAllUsers(){
        return users;
    }

    public List<Booking> getAllBookings(){
        return bookings;
    }

    //Display Kollywood
    public void displayKollywoodMovies() {
        System.out.println("\t\t\t\t\tKOLLYWOOD MOVIES\n");
        System.out.printf("%-10s%-30s%-20s%-15s%-15s%-15s%-15s\n", "MOVIE ID", "TITLE", "GENRE", "DURATION", "NO. OF SEATS", "TICKET PRICE", "LANGUAGE");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        
        for (Movie movie : movies) {
            if ("Tamil".equalsIgnoreCase(movie.getLanguage())) {
                System.out.printf("%-10d%-30s%-20s%-15d%-15d%-15.2f%-15s\n", movie.getId(), movie.getTitle(), movie.getGenre(), movie.getDuration(), movie.getAvailableSeats(), movie.getTicketPrice(), movie.getLanguage());
            }
        }
        System.out.println("\n");
    }
    
    //Display Hollywood
    public void displayHollywoodMovies() {
        System.out.println("\t\t\t\t\tHOLLYWOOD MOVIES\n");
        System.out.printf("%-10s%-30s%-20s%-15s%-15s%-15s%-15s\n", "MOVIE ID", "TITLE", "GENRE", "DURATION", "NO. OF SEATS", "TICKET PRICE", "LANGUAGE");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        for (Movie movie : movies) {
            if ("English".equalsIgnoreCase(movie.getLanguage())) {
                System.out.printf("%-10d%-30s%-20s%-15d%-15d%-15.2f%-15s\n", movie.getId(), movie.getTitle(), movie.getGenre(), movie.getDuration(), movie.getAvailableSeats(), movie.getTicketPrice(), movie.getLanguage());
            }
        }
        System.out.println("\n");
    }
     
    public void displayAllMovies() {
        displayKollywoodMovies();
        displayHollywoodMovies();
    }
    
    public void displayAllUsers(){
        System.out.println("All Users:");
        for(User user : users){
            System.out.println(user.getUserId()+": "+user.getUsername() );
        }
    }

    public void displayAllBookings(){
        System.out.println("All Bookings:");
        for(Booking booking : bookings){
            System.out.println("Booking ID: "+ booking.getBookingId()+", User: "+ booking.getUser().getUsername()+", Movie: "+ booking.getMovie().getTitle() +", Tickets: "+ booking.getNumTickets());
        }
    }

    //To Book a ticket
    public Booking makeBooking(User user, Movie movie, int numTickets, String selectedSeats) {
        int availableSeats = movie.getAvailableSeats();

        if (availableSeats >= numTickets && (numTickets <= MAX_TICKETS)) {
            String[] selectedSeatsArray = selectedSeats.split(",");
            if (areSeatsAvailable(Integer.toString(movie.getId()), selectedSeatsArray)) {
                if (numTickets == selectedSeatsArray.length) {
                    Booking booking = new Booking(user, movie, numTickets, selectedSeatsArray);
                    bookings.add(booking);
                    movie.updateAvailableSeats(numTickets);
                    saveUserBookingHistoryToFile(user, booking);
                    return booking;
                } else {
                    System.out.println("Number of entered seats and selected seats must be equal. Please try again.");
                }
            } else {
                System.out.println("Selected seat(s) not available.");
            }
        } else {
            System.out.println("Not enough available seats for the booking.");
        }
        return null;
    }

    //Check the availability of seats in seat matrix
    private boolean areSeatsAvailable(String movieId, String[] selectedSeats) {
        try (BufferedReader br = new BufferedReader(new FileReader(SEAT_FILE_PATH))) {
            StringBuilder updatedSeatMatrix = new StringBuilder();
            String line;
            boolean movieFound = false;
            boolean anySeatAvailable = false; // New variable to track if any seat is available
    
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String currentMovieId = parts[0];
                if (currentMovieId.equals(movieId)) {
                    movieFound = true;
                    String seatMatrix = parts[1];
    
                    for (String seat : selectedSeats) {
                        int seatIndex = Integer.parseInt(seat) - 1;
    
                        if (seatIndex < 0 || seatIndex >= seatMatrix.length() || seatMatrix.charAt(seatIndex) == 'x') {
                            anySeatAvailable = false; // Seat not available or already booked
                            break;
                        } else {
                            anySeatAvailable = true; // At least one seat is available
                            seatMatrix = seatMatrix.substring(0, seatIndex) + 'x' + seatMatrix.substring(seatIndex + 1);
                        }
                    }
    
                    updatedSeatMatrix.append(currentMovieId).append(",").append(seatMatrix).append("\n");
                } else {
                    updatedSeatMatrix.append(line).append("\n");
                }
            }
    
            if (!movieFound) {
                System.out.println("Movie ID not found.");
                return false;
            }
    
            // Write the updated seat matrix back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(SEAT_FILE_PATH))) {
                bw.write(updatedSeatMatrix.toString());
            } catch (IOException e) {
                System.out.println("Error updating seat matrix: " + e.getMessage());
            }
    
            return anySeatAvailable; // Return true if at least one seat is available
    
        } catch (IOException e) {
            System.out.println("Error reading seat matrix file: " + e.getMessage());
        }
        return false; // Error occurred, assume no seat is available
    }
    
    public void displaySeatMatrix(int movieId){
        try (BufferedReader br = new BufferedReader(new FileReader(SEAT_FILE_PATH))) {
            String line;
            boolean movieFound = false;
    
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String currentMovieId = parts[0];
                if (currentMovieId.equals(Integer.toString(movieId))) {
                    movieFound = true;
                    System.out.println("Seats Available ");
                    System.out.print("\t\t");
                    for (char c : parts[1].toCharArray()) {
                        System.out.print("   "+ c + "   ");
                    }
                    System.out.print("\n");
                    break;
                }
            }
    
            if (!movieFound) {
                System.out.println("Movie ID not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading seat matrix file: " + e.getMessage());
        }
    }

    public Booking findBookingByIdFromFile(int userId, int bookingId) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_HISTORY_FILE_PATH))) {
            String line;
    
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
    
                // Check if there are enough parts in the line and the user ID and booking ID match
                if (parts.length >= 5) {  
                    try {
                        int fileUserId = Integer.parseInt(parts[0]);
                        int fileBookingId = Integer.parseInt(parts[1]);
    
                        if (fileUserId == userId && fileBookingId == bookingId) {
                            int movieId = Integer.parseInt(parts[2]);
                            int numTickets = Integer.parseInt(parts[3]);
                            String[] selectedSeatsArray = Arrays.copyOfRange(parts, 4, parts.length);

    
                            Movie movie = findMovieById(movieId);
                            if (movie != null) {
                                return new Booking(findUserById(userId), movie, numTickets, selectedSeatsArray);
                            } else {
                                System.out.println("Movie not found for Booking ID: " + bookingId);
                                return null;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing data: " + e.getMessage());
                        return null;
                    }
                } else {
                    System.out.println("Invalid data format in the user history file.");
                    return null;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user booking history file: " + e.getMessage());
        }
    
        return null; // Booking not found
    }
    
    //To cancel booking
    public void cancelBooking(int userId, int bookingId) {
        Booking bookingToCancel = findBookingByIdFromFile(userId, bookingId);
    
        if (bookingToCancel != null) {
            bookings.remove(bookingToCancel);
            Movie movie = bookingToCancel.getMovie();
            movie.updateAvailableSeats(-bookingToCancel.getNumTickets());
            String[] selectedSeats = bookingToCancel.getSelectedSeats();
            updateSeatMatrix(movie.getId(), selectedSeats);
            removeUserBookingHistoryFromFile(bookingId);
            saveMoviesToFile();
            System.out.println("Booking canceled successfully. Seats added back.");
        } else {
            System.out.println("Invalid Booking ID or the booking does not belong to the current user.");
        }
    }
    
    //Update seat matrix after cancellation
    private void updateSeatMatrix(int movieId, String[] selectedSeats) {
        try (BufferedReader br = new BufferedReader(new FileReader(SEAT_FILE_PATH))) {
            StringBuilder updatedSeatMatrix = new StringBuilder();
            String line;
    
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String currentMovieId = parts[0];
    
                if (currentMovieId.equals(Integer.toString(movieId))) {
                    String seatMatrix = parts[1];
    
                    for (String seat : selectedSeats) {
                        int seatIndex = Integer.parseInt(seat) - 1;
                        seatMatrix = seatMatrix.substring(0, seatIndex) + seat + seatMatrix.substring(seatIndex + 1);
                    }
    
                    updatedSeatMatrix.append(currentMovieId).append(",").append(seatMatrix).append("\n");
                } else {
                    updatedSeatMatrix.append(line).append("\n");
                }
            }
    
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(SEAT_FILE_PATH))) {
                bw.write(updatedSeatMatrix.toString());
            } catch (IOException e) {
                System.out.println("Error updating seat matrix: " + e.getMessage());
            }
    
        } catch (IOException e) {
            System.out.println("Error reading seat matrix file: " + e.getMessage());
        }
    }    

    private void removeUserBookingHistoryFromFile(int bookingId) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_HISTORY_FILE_PATH))) {
            StringBuilder updatedHistory = new StringBuilder();
            String line;
    
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
    
                // Check if the current line corresponds to the canceled booking
                if (parts.length >= 2 && Integer.parseInt(parts[1]) == bookingId) {
                    System.out.println("Cancelling booking: " + line);
                } else {
                    updatedHistory.append(line).append("\n");
                }
            }
    
            // Write the updated user booking history back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_HISTORY_FILE_PATH))) {
                bw.write(updatedHistory.toString());
            } catch (IOException e) {
                System.out.println("Error updating user booking history file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading user booking history file: " + e.getMessage());
        }
    }
    
    // To display all the booking history of a user
    public void displayUserBookingHistoryFromFile(User user) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_HISTORY_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
    
                // Check if there are enough parts in the line and the user ID matches
                if (parts.length >= 2 && Integer.parseInt(parts[0]) == user.getUserId()) {
                    int bookingId = Integer.parseInt(parts[1]);
                    int movieId = Integer.parseInt(parts[2]);
                    int numTickets = Integer.parseInt(parts[3]);
    
                    Movie movie = findMovieById(movieId);
                    if (movie != null) {
                        System.out.println("Booking ID: " + bookingId + ", Movie: " + movie.getTitle() +", Tickets: " + numTickets);
                    } else {
                        System.out.println("Movie not found for Booking ID: " + bookingId);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user booking history file: " + e.getMessage());
        }
    }
    
    private void saveUserBookingHistoryToFile(User user, Booking booking) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_HISTORY_FILE_PATH, true))) {
            // Convert the selectedSeats array to a readable string
            String selectedSeatsString = String.join(",", booking.getSelectedSeats());
            writer.printf("%d,%d,%d,%d,%s%n", user.getUserId(), booking.getBookingId(), booking.getMovie().getId(), booking.getNumTickets(), selectedSeatsString);
        } catch (IOException e) {
            System.out.println("Error saving user booking history to file: " + e.getMessage());
        }
    }
    
}