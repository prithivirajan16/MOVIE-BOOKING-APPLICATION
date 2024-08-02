This is the readme file for the project Movie Ticket Booking System

Movie Ticket Booking System

Description
This Java application simulates a movie ticket booking system. Users can log in, view available movies, make bookings, select seats, cancel bookings and view his/her booking history.

Requirements
1. Java Development Kit (JDK)

Installation
1. Clone the repository.
2. Ensure Java is installed.
3. Compile and run the `TicketSystem` class.

Steps to execute:
1. Once the TicketSystem class is compiled and run, it will ask you to login.
   (You have to use the data in users.txt to login (username,password))
2. Once logged in, you will be presented with a set of 4 options
	1. View Available Movies
	2. Book a Show
	3. Cancel Booking
	4. View Booking History
3. If 1 is entered, you will be able to see all the available movies.
4. If 2 is entered, you will be asked to enter the movie id, no.of seats, the seats and confirmation for booking. If successful, booking will be made and 	booking data will be stored in userhistory.txt.
5. If 3 is entered, you will be asked for the bookingId for cancellation. Cancellation will be done, and the no.of seats and the seat matrix will be 	updated.
6. If 4 is entered, you can see all the booking you have made.
7. If 0 is entered, you can logout.

While cloning the repository, 
Make sure that inside package, MovieSystem, 4 java files are present(i.e, User.java, Booking.java, TicketSystem.java and Movie.java) 
Make sure there are 6 text files(users.txt, seatmatrix.txt, kollywood.txt, hollywood.txt, userhistory.txt, last_booking_id.txt) and one java file which contains the main(MiniProject.java) are present outside the package but inside a folder.

