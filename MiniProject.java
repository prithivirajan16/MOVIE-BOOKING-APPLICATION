import java.util.Scanner;
import MovieSystem.*;

class MiniProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketSystem ticketSystem = new TicketSystem();
        System.out.println("\t\t\t\t\t============================================================================");
        System.out.println("\t\t\t\t\t\t\t   WELCOME TO MOVIE TICKET BOOKING SYSTEM!");
        System.out.println("\t\t\t\t\t============================================================================");
        // Login
        System.out.println("\n======== LOGIN ========");
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        User loggedInUser = ticketSystem.login(username, password);

        if (loggedInUser != null) {
            System.out.println("Login successful. Welcome, " + loggedInUser.getUsername() + "!");

            int choice;
            do {
                System.out.println("\n========== OPTIONS ==========\n");
                System.out.println("1. View Available Movies");
                System.out.println("2. Book a Show");
                System.out.println("3. Cancel Booking");
                System.out.println("4. View Booking History");
                System.out.println("0. Logout\n");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        // Display available movies with ticket prices and language
                        ticketSystem.displayAllMovies();
                        break;
                    case 2:
                    // Movie booking
                    System.out.println("================ BOOKING ===============");
                    System.out.print("Enter the ID of the movie you want to book tickets for: ");
                    int selectedMovieId = scanner.nextInt();
                    Movie selectedMovie = ticketSystem.findMovieById(selectedMovieId);
                
                    if (selectedMovie != null) {
                        System.out.print("Enter the number of tickets to book: ");
                        int numTickets = scanner.nextInt();
                
                        // Display seat matrix
                        ticketSystem.displaySeatMatrix(selectedMovieId);
                        System.out.println("\t\t---------------------------------");
                        System.out.println("\t\t           Screen here");
                
                        // Booking
                        System.out.print("Enter the seats you want to book (e.g., 1,3,5): ");
                        scanner.nextLine();  // Consume the newline character
                        String selectedSeats = scanner.nextLine();
                
                        if (numTickets == selectedSeats.split(",").length) {
                            double ticketPrice = ticketSystem.getTicketPrice(selectedMovieId);
                            String language = ticketSystem.getLanguage(selectedMovieId);
                
                            System.out.println("Ticket Price: " + (ticketPrice * numTickets));
                            System.out.println("Language: " + language);
                
                            System.out.print("Do you want to confirm the booking? (1 for Yes, 0 for No): ");
                            int confirmChoice = scanner.nextInt();
                
                            if (confirmChoice == 1) {
                                Booking booking = ticketSystem.makeBooking(loggedInUser, selectedMovie, numTickets, selectedSeats);
                                if (booking != null) {
                                    System.out.println("Booking successful. Booking ID: " + booking.getBookingId());
                                } else {
                                    System.out.println("Booking failed.");
                                }
                            } else {
                                System.out.println("Booking canceled.");
                            }
                            
                        } else {
                            System.out.println("Number of entered seats and selected seats must be equal. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid movie selection. Please try again.");
                    }
                    break;
                
                    case 3:
                        // Cancellation
                        System.out.println("=============== CANCELLATION ===============");
                        System.out.print("Enter the Booking ID to cancel: ");
                        int bookingIdToCancel = scanner.nextInt();
                        ticketSystem.cancelBooking(loggedInUser.getUserId(), bookingIdToCancel);
                        break;
                    case 4:
                        // History display
                        System.out.println("================= BOOKING HISTORY =================");
                        User currentUser = ticketSystem.login(username, password);
                        if (currentUser != null) {
                            ticketSystem.displayUserBookingHistoryFromFile(currentUser);
                        } else {
                            System.out.println("Invalid username or password.");
                        }
                        break;
                    case 0:
                        System.out.println("Logout successful. Goodbye, " + loggedInUser.getUsername() + "!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 0);
        } else {
            System.out.println("Login failed. Invalid email or password.");
        }
        scanner.close();
    }
}
