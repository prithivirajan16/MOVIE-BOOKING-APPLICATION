package MovieSystem;

import java.io.*;

public class Movie {
    protected int id;
    protected String title;
    protected String genre;
    protected int duration;
    protected int availableSeats;
    protected double ticketPrice;
    protected String language;

    protected TicketSystem ticketSystem;

    public Movie(TicketSystem ticketSystem, int id, String title, String genre, int duration, int availableSeats, double ticketPrice, String language) {
        this.ticketSystem = ticketSystem;
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
        this.language = language;
    }

    //getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    // to update seats after booking
    public void updateAvailableSeats(int numTickets) {
        this.availableSeats -= numTickets;
        saveToFile();
        saveToFile_h();
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getLanguage() {
        return language;
    }

    // to save the contents of Kollywood file after booking
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ticketSystem.KOLLYWOOD_FILE_PATH))) {
            for (Movie movie : ticketSystem.movies) {
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

    // to save the contents of Hollywood file after booking
    public void saveToFile_h() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ticketSystem.HOLLYWOOD_FILE_PATH))) {
            for (Movie movie : ticketSystem.movies) {
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
}

