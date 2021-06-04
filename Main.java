import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.print("\n");

        boolean[][] reservedSeats = new boolean[rows + 1][seats + 1];
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                reservedSeats[i][j] = false;
            }
        }
        int action = -1;
        int boughtTickets = 0;
        int income = 0;
        boolean isCorrect = true;

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
                    displaySeats(rows, seats, reservedSeats);
                    System.out.print("\n");
                    break;
                case 2:
                    do {
                        System.out.println("Enter a row number:");
                        int chosenRow = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int chosenSeat = scanner.nextInt();


                        if (chosenRow > rows || chosenSeat > seats) {
                            System.out.println("Wrong input!");
                            isCorrect = false;
                        } else if (reservedSeats[chosenRow][chosenSeat]) {
                            System.out.println("That ticket has already been purchased!");
                            isCorrect = false;
                        } else {
                            System.out.print("\n");

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

                    } while (!isCorrect);
                    break;
                case 3:
                    System.out.printf("Number of purchased tickets: %d%nPercentage: %.2f%c%nCurrent income: $%d%nTotal income: $%d%n%n",
                            boughtTickets, (float)(boughtTickets)/(seats * rows)*100, '%', income, calculateTotalIncome(rows, seats));
                    break;
            }
        } while (action != 0);

    }

    public static void displaySeats(int allRows, int allSeats, boolean[][] arrayOfSeats) {
        System.out.println("Cinema:");
        for (int i = 0; i <= allRows; i++) {
            for (int j = 0; j <= allSeats; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.print(j + " ");
                } else if (j == 0) {
                    System.out.print(i + " ");
                } else if (arrayOfSeats[i][j]) {
                    System.out.print("B ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.print("\n");
        }
    }

    public static void displaySeats(int allRows, int allSeats, int chosenRow, int chosenSeat, boolean[][] arrayOfSeats) {
        for (int i = 0; i <= allRows; i++) {
            for (int j = 0; j <= allSeats; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.print(j + " ");
                } else if (j == 0) {
                    System.out.print(i + " ");
                } else if (i == chosenRow && j == chosenSeat || arrayOfSeats[chosenRow][chosenSeat]) {
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