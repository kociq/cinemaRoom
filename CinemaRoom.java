public class CinemaRoom {
    public static boolean isCorrect = true;
    public static int boughtTickets = 0;
    public static int income = 0;
    public static boolean[][] reservedSeats;

    public static boolean[][] createReservedSeatsArray(int rows, int seats) {
        reservedSeats = new boolean[rows + 1][seats + 1];
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                reservedSeats[i][j] = false;
            }
        }
        return reservedSeats;
    }


    public static void buyTicket(int rows, int seats, int chosenRow, int chosenSeat) {

        if (chosenRow > rows || chosenSeat > seats) {
            System.out.println("Wrong input!");
            isCorrect = false;
        } else if (createReservedSeatsArray(rows, seats)[chosenRow][chosenSeat]) {
            System.out.println("That ticket has already been purchased!");
            isCorrect = false;
        } else {
            System.out.print("\n");
            if (income == 0) {
                reservedSeats = createReservedSeatsArray(rows, seats);
            }
            if (calculateTicketPrice(rows, seats, chosenRow) == 10) {
                System.out.println("Ticket price: $10");
                income += 10;
            } else {
                System.out.println("Ticket price: $8");
                income += 8;
            }

            reservedSeats[chosenRow][chosenSeat] = true;
            boughtTickets++;
            isCorrect = true;

            System.out.print("\n Cinema:\n");

            displaySeats(rows, seats, chosenRow, chosenSeat, reservedSeats);
        }
    }

    public static void displayStatistics(int rows, int seats) {
        System.out.printf("Number of purchased tickets: %d%nPercentage: %.2f%c%nCurrent income: $%d%nTotal income: $%d%n%n",
                boughtTickets, (float) (boughtTickets) / (seats * rows) * 100, '%', income, calculateTotalIncome(rows, seats));

    }

    public static void displaySeats(int allRows, int allSeats, boolean[][] reservedSeats) {
        System.out.println("Cinema:");
        for (int i = 0; i <= allRows; i++) {
            for (int j = 0; j <= allSeats; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.print(j + " ");
                } else if (j == 0) {
                    System.out.print(i + " ");
                } else if (reservedSeats != null && reservedSeats[i][j]) {
                    System.out.print("B ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.print("\n");
        }
    }

    public static void displaySeats(int allRows, int allSeats, int chosenRow, int chosenSeat, boolean[][] reservedSeats) {
        for (int i = 0; i <= allRows; i++) {
            for (int j = 0; j <= allSeats; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.print(j + " ");
                } else if (j == 0) {
                    System.out.print(i + " ");
                } else if (i == chosenRow && j == chosenSeat || reservedSeats[i][j]) {
                    System.out.print("B ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.print("\n");
        }
    }

    public static int calculateTicketPrice(int allRows, int allSeats, int chosenRow) {
        if (allSeats * allRows < 60) {
            return 10;
        } else {
            if (chosenRow <= allRows / 2) {
                return 10;
            } else {
                return 8;
            }
        }
    }

    public static int calculateTotalIncome(int allRows, int allSeats) {
        int totalIncome = 0;
        for (int i = 1; i <= allRows; i++) {
            for (int j = 1; j <= allSeats; j++) {
                if (calculateTicketPrice(allRows, allSeats, i) == 10) {
                    totalIncome += 10;
                } else {
                    totalIncome += 8;
                }
            }
        }
        return totalIncome;
    }

}
