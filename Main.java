public class Passenger {
    private String name;
    private String passportNumber;

    public Passenger(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
}

public class Seat {
    private String seatNumber;
    private boolean isBooked;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
        this.isBooked = false;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        this.isBooked = true;
    }
}

import java.util.*;

public class Flight {
    private String flightNumber;
    private String destination;
    private List<Seat> seats;

    public Flight(String flightNumber, String destination, int seatCount) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= seatCount; i++) {
            seats.add(new Seat("S" + i));
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> available = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isBooked()) available.add(seat);
        }
        return available;
    }

    public Seat getSeat(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equalsIgnoreCase(seatNumber)) {
                return seat;
            }
        }
        return null;
    }
}

public class Booking {
    private Passenger passenger;
    private Flight flight;
    private Seat seat;

    public Booking(Passenger passenger, Flight flight, Seat seat) {
        this.passenger = passenger;
        this.flight = flight;
        this.seat = seat;
    }

    public void printTicket() {
        System.out.println("Booking Confirmed!");
        System.out.println("Passenger: " + passenger.getName());
        System.out.println("Passport: " + passenger.getPassportNumber());
        System.out.println("Flight: " + flight.getFlightNumber() + " to " + flight.getDestination());
        System.out.println("Seat: " + seat.getSeatNumber());
    }
}

public class BookingSystem {
    public Booking bookTicket(Passenger passenger, Flight flight, String seatNumber) {
        Seat seat = flight.getSeat(seatNumber);
        if (seat != null && !seat.isBooked()) {
            seat.book();
            return new Booking(passenger, flight, seat);
        } else {
            System.out.println("Seat " + seatNumber + " is not available.");
            return null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Setup
        Flight flight = new Flight("AI202", "New Delhi", 5);
        Passenger passenger = new Passenger("Subhani", "A1234567");

        // Booking process
        BookingSystem bookingSystem = new BookingSystem();
        Booking booking = bookingSystem.bookTicket(passenger, flight, "S2");

        if (booking != null) {
            booking.printTicket();
        }

        // Try booking the same seat again
        Passenger another = new Passenger("Ali", "B7654321");
        Booking failBooking = bookingSystem.bookTicket(another, flight, "S2");
    }
}
