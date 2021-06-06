import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.print("\n");

        boolean[][] noReservedSeatsArray = CinemaRoom.createReservedSeatsArray(rows, seats);
        int action = -1;

        do {
            System.out.print("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit\n");
            action = scanner.nextInt();
            switch (action) {
                case 0:
                    return;
                case 1:
                    if (CinemaRoom.income == 0) {
                        CinemaRoom.displaySeats(rows, seats, noReservedSeatsArray);
                    } else {
                        CinemaRoom.displaySeats(rows, seats, CinemaRoom.reservedSeats);
                    }
                    System.out.print("\n");
                    break;
                case 2:
                    do {
                        System.out.println("Enter a row number:");
                        int chosenRow = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int chosenSeat = scanner.nextInt();

                        CinemaRoom.buyTicket(rows, seats, chosenRow, chosenSeat);
                    } while (!CinemaRoom.isCorrect);
                    break;
                case 3:
                    CinemaRoom.displayStatistics(rows, seats);
                    break;
            }
        } while (action != 0);

    }

}