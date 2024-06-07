import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
    private static final String CAR_FILE = "cars.txt";
    private static final String RENTAL_FILE = "rentals.txt";
    private List<Customer> customers;

    public CarRentalSystem() {
        customers = new ArrayList<>();
    }
    
    public void addCar(Car car) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAR_FILE, true))) {
            writer.write(car.getLicensePlate() + "," + car.getMake() + "," + car.getModel() + "," + car.isAvailable());
            writer.newLine();
            System.out.println("Car added successfully!");
        } catch (IOException e) {
            System.out.println("Error adding car: " + e.getMessage());
        }
    }
    
    public void removeCar(String licensePlate) {
        List<Car> cars = readCars();
        Car car = findCar(licensePlate, cars);
        if (car != null) {
            cars.remove(car);
            writeCars(cars);
            System.out.println("Car removed successfully!");
        } else {
            System.out.println("Car not found!");
        }
    }
    
    public void modifyCar(String licensePlate, String newMake, String newModel) {
        List<Car> cars = readCars();
        Car car = findCar(licensePlate, cars);
        if (car != null) {
            car.setMake(newMake);
            car.setModel(newModel);
            writeCars(cars);
            System.out.println("Car modified successfully!");
        } else {
            System.out.println("Car not found!");
        }
    }

    public void displayCars() {
        List<Car> cars = readCars();
        if (cars.isEmpty()) {
            System.out.println("No cars available.");
        } else {
            System.out.println("Available Cars:");
            for (Car car : cars) {
                System.out.println("License Plate: " + car.getLicensePlate() + ", Make: " + car.getMake() + ", Model: " + car.getModel() + ", Available: " + car.isAvailable());
            }
        }
    }
    
    public void rentCar(Customer customer, Car car, int days) {
        List<Car> cars = readCars();
        List<Rental> rentals = readRentals();
        Car carToRent = findCar(car.getLicensePlate(), cars);
        if (carToRent != null && carToRent.isAvailable()) {
            Rental rental = new Rental(customer, carToRent, days);
            rentals.add(rental);
            carToRent.setAvailable(false);
            writeRentals(rentals);
            writeCars(cars);
            System.out.println("Car rented successfully!");
        } else {
            System.out.println("Invalid car or car is not available!");
        }
    }
    
    public void returnCar(Customer customer, Car car) {
        List<Rental> rentals = readRentals();
        Rental rental = findRental(customer, car, rentals);
        if (rental != null) {
            rentals.remove(rental);
            List<Car> cars = readCars();
            Car rentedCar = findCar(car.getLicensePlate(), cars);
            if (rentedCar != null) {
                rentedCar.setAvailable(true);
            }
            writeRentals(rentals);
            writeCars(cars);
            System.out.println("Car returned successfully!");
        } else {
            System.out.println("Invalid car!");
        }
    }
    
    private Car findCar(String licensePlate, List<Car> cars) {
        for (Car car : cars) {
            if (car.getLicensePlate().equals(licensePlate)) {
                return car;
            }
        }
        return null;
    }
    
    private Rental findRental(Customer customer, Car car, List<Rental> rentals) {
        for (Rental rental : rentals) {
            if (rental.getCustomer().equals(customer) && rental.getCar().equals(car)) {
                return rental;
            }
        }
        return null;
    }
    
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\t\t-----------------------------------------------------------------");
            System.out.println("\t\t\t\t CAPALOT CAR RENTAL AGENCY MENU");
            System.out.println("\t\t-----------------------------------------------------------------");
            System.out.println("\t\t\n1. Add Car");
            System.out.println("\t\t\n2. Remove Car");
            System.out.println("\t\t\n3. Rent Car");
            System.out.println("\t\t\n4. Return Car");
            System.out.println("\t\t\n5. Modify Car");
            System.out.println("\t\t\n6. Display Cars");
            System.out.println("\t\t\n0. Exit");
            System.out.print("\n\t\t\nEnter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    System.out.print("Enter license plate: ");
                    String licensePlate = scanner.nextLine();
                    System.out.print("Enter make: ");
                    String make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    Car newCar = new Car(licensePlate, make, model);
                    addCar(newCar);
                    break;
                case 2:
                    System.out.print("Enter license plate of the car to remove: ");
                    String plateToRemove = scanner.nextLine();
                    removeCar(plateToRemove);
                    break;
                case 3:
                    System.out.print("Enter customer ID: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter license plate of the car to rent: ");
                    String plateToRent = scanner.nextLine();
                    System.out.print("Enter number of days: ");
                    int days = scanner.nextInt();
                    scanner.nextLine(); 
                    Customer customerToRent = new Customer(customerId, "CustomerName");
                    Car carToRent = findCar(plateToRent, readCars());
                    if (carToRent != null) {
                        rentCar(customerToRent, carToRent, days);
                    } else {
                        System.out.println("Invalid car!");
                    }
                    break;
                case 4:
                    System.out.print("Enter customer ID: ");
                    int customerIdReturn = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter license plate of the car to return: ");
                    String plateToReturn = scanner.nextLine();
                    Customer customerToReturn = new Customer(customerIdReturn, "CustomerName");
                    Car carToReturn = findCar(plateToReturn, readCars());
                    if (carToReturn != null) {
                        returnCar(customerToReturn, carToReturn);
                    } else {
                        System.out.println("Invalid car!");
                    }
                    break;
                case 5:
                    System.out.print("Enter license plate of the car to modify: ");
                    String plateToModify = scanner.nextLine();
                    System.out.print("Enter new make: ");
                    String newMake = scanner.nextLine();
                    System.out.print("Enter new model: ");
                    String newModel = scanner.nextLine();
                    modifyCar(plateToModify, newMake, newModel);
                    break;
                case 6:
                    displayCars();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);
        
        scanner.close();
    }

    private List<Car> readCars() {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAR_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    cars.add(new Car(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading cars: " + e.getMessage());
        }
        return cars;
    }

    private void writeCars(List<Car> cars) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAR_FILE))) {
            for (Car car : cars) {
                writer.write(car.getLicensePlate() + "," + car.getMake() + "," + car.getModel() + "," + car.isAvailable());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing cars: " + e.getMessage());
        }
    }

    private List<Rental> readRentals() {
        List<Rental> rentals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RENTAL_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Customer customer = new Customer(Integer.parseInt(parts[0]), "CustomerName");
                    Car car = findCar(parts[1], readCars());
                    if (car != null) {
                        rentals.add(new Rental(customer, car, Integer.parseInt(parts[2])));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading rentals: " + e.getMessage());
        }
        return rentals;
    }

    private void writeRentals(List<Rental> rentals) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RENTAL_FILE))) {
            for (Rental rental : rentals) {
                writer.write(rental.getCustomer().getId() + "," + rental.getCar().getLicensePlate() + "," + rental.getDays());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing rentals: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();
        rentalSystem.menu();
    }
}

class Car {
    private String licensePlate;
    private String make;
    private String model;
    private boolean available;
    
    public Car(String licensePlate, String make, String model) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.available = true;
    }

    public Car(String licensePlate, String make, String model, boolean available) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.available = available;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }
    
    public String getMake() {
        return make;
    }
    
    public void setMake(String make) {
        this.make = make;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Customer {
    private int id;
    private String name;
    
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}

class Rental {
    private Customer customer;
    private Car car;
    private int days;
    
    public Rental(Customer customer, Car car, int days) {
        this.customer = customer;
        this.car = car;
        this.days = days;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public Car getCar() {
        return car;
    }
    
    public int getDays() {
        return days;
    }
}