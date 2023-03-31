import java.util.Scanner;

public class ParkingAllotmentSystem {
    static Scanner input = new Scanner(System.in);
    static int numOfParkingLots = 0;
    static int numOfFloors = 0;
    static int availableParkingLots = 0;
    static int[][] parkingLots;
    static int floorNumber = 1;
    static int parkingLotNumber = 1;
    static int costPerHour = 10;
    static int totalEarnings = 0;

    public static void main(String[] args) {
        initializeParkingLots();
        int choice = 0;
        do {
            System.out.println("Parking Allotment System");
            System.out.println("1. Check availability");
            System.out.println("2. Book a parking lot");
            System.out.println("3. View status");
            System.out.println("4. Check out");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    checkAvailability();
                    break;
                case 2:
                    bookParkingLot();
                    break;
                case 3:
                    viewStatus();
                    break;
                case 4:
                    checkOut();
                    break;
                case 5:
                    System.out.println("Thank you for using Parking Allotment System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        } while (choice != 5);

        input.close();
    }

    public static void initializeParkingLots() {
        System.out.print("Enter the number of floors: ");
        numOfFloors = input.nextInt();
        System.out.print("Enter the number of parking lots per floor: ");
        numOfParkingLots = input.nextInt();
        availableParkingLots = numOfFloors * numOfParkingLots;
        parkingLots = new int[numOfFloors][numOfParkingLots];
        System.out.println("Parking lot initialized with " + numOfFloors + " floors and " + numOfParkingLots + " parking lots per floor.");
    }

    public static void checkAvailability() {
        if (availableParkingLots == 0) {
            System.out.println("Sorry, no parking lots available at the moment.");
        } else {
            System.out.println("There are " + availableParkingLots + " parking lots available.");
        }
    }

    public static void bookParkingLot() {
        if (availableParkingLots == 0) {
            System.out.println("Sorry, no parking lots available at the moment.");
            return;
        }

        System.out.println("Floor number starts from 1 and parking lot number starts from 1.");
        System.out.print("Enter the floor number: ");
        floorNumber = input.nextInt() - 1;
        System.out.print("Enter the parking lot number: ");
        parkingLotNumber = input.nextInt() - 1;
        if (parkingLots[floorNumber][parkingLotNumber] != 0) {
            System.out.println("Sorry, the parking lot is already occupied.");
            return;
        }

        parkingLots[floorNumber][parkingLotNumber] = 1;
        availableParkingLots--;
        System.out.println("Your parking lot number is " + (floorNumber + 1) + "-" + (parkingLotNumber + 1) + ".");
    }

    public static void viewStatus() {
        System.out.println("Total parking lots: " + (numOfFloors * numOfParkingLots));
        System.out.println("Available parking lots: " + availableParkingLots);
        System.out.println("Occupied parking lots:");
        for (int i = 0; i < numOfFloors; i++) {
            System.out.print("Floor " + (i + 1) + ": ");
            for (int j = 0; j < numOfParkingLots; j++) {
                if (parkingLots[i][j] == 1) {
                    System.out.print((j + 1) + " ");
                }
            }
            System.out.println();
        }
    }

    public static void checkOut() {
        System.out.print("Enter the floor number: ");
        floorNumber = input.nextInt() - 1;
        System.out.print("Enter the parking lot number: ");
        parkingLotNumber = input.nextInt() - 1;
        if (parkingLots[floorNumber][parkingLotNumber] == 0) {
            System.out.println("Sorry, the parking lot is not occupied.");
            return;
        }

        System.out.print("Enter the number of hours parked: ");
        int numOfHours = input.nextInt();
        int cost = numOfHours * costPerHour;
        System.out.println("The parking fee is: $" + cost);
        totalEarnings += cost;
        parkingLots[floorNumber][parkingLotNumber] = 0;
        availableParkingLots++;
    }
}

