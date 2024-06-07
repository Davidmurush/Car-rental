# Car Rental System

## Project Overview

The Car Rental System is a Java-based application that allows users to manage a car rental service. The application provides functionality to add, remove, modify, and display cars. Additionally, users can rent and return cars. The data is stored persistently using text files.

## Features

- **Add Car**: Add a new car to the system.
- **Remove Car**: Remove an existing car from the system.
- **Modify Car**: Modify the details of an existing car.
- **Display Cars**: Display a list of all available cars.
- **Rent Car**: Rent a car to a customer for a specified number of days.
- **Return Car**: Return a rented car and make it available again.

## File Structure

- `CarRentalSystem.java`: The main class that handles the overall car rental system functionality.
- `Car.java`: The class representing a car.
- `Customer.java`: The class representing a customer.
- `Rental.java`: The class representing a rental transaction.
- `cars.txt`: The file storing car information.
- `rentals.txt`: The file storing rental information.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- A text editor or an Integrated Development Environment (IDE) like IntelliJ IDEA, Eclipse, or NetBeans.

### Running the Application

1. Clone the repository to your local machine:
    ```sh
    git clone https://github.com/your-username/CarRentalSystem.git
    ```

2. Navigate to the project directory:
    ```sh
    cd CarRentalSystem
    ```

3. Compile the Java files:
    ```sh
    javac CarRentalSystem.java
    ```

4. Run the application:
    ```sh
    java CarRentalSystem
    ```

### Usage

1. Upon running the application, you will be presented with a menu:
    ```
    -----------------------------------------------------------------
                         CAPALOT CAR RENTAL AGENCY MENU
    -----------------------------------------------------------------
    
    1. Add Car
    2. Remove Car
    3. Rent Car
    4. Return Car
    5. Modify Car
    6. Display Cars
    0. Exit
    
    Enter your choice: 
    ```

2. Follow the prompts to interact with the system.

### Example Operations

#### Adding a Car
- Select `1` from the menu.
- Enter the license plate, make, and model of the car.

#### Removing a Car
- Select `2` from the menu.
- Enter the license plate of the car to remove.

#### Modifying a Car
- Select `5` from the menu.
- Enter the license plate of the car to modify.
- Enter the new make and model of the car.

#### Displaying Cars
- Select `6` from the menu to display all available cars.

#### Renting a Car
- Select `3` from the menu.
- Enter the customer ID, license plate of the car, and the number of days to rent.

#### Returning a Car
- Select `4` from the menu.
- Enter the customer ID and the license plate of the car to return.
