# Queens Game Solver

This project reads a screenshot of a "Queens" board, detects regions automatically using OpenCV, maps each color region, and solves the board using a backtracking algorithm.

## Components

- `extractor` : Extract the board matrix from image input
- `mapper` : Map center pixel colors to region IDs
- `solver` : Solve the Queens Game with constraints
- `model` : Holds board and related data
- `service` : Facade to tie everything together
- `util` : Helper methods (OpenCV utils)

## How it works
1. User saves a screenshot into the `input` folder
2. Program detects outer box and divides into 9x9
3. Samples center pixel of each cell
4. Maps regions
5. Solves using backtracking
6. Prints solution

## Technologies
- Java 8
- OpenCV 4.x (Java bindings)
- Maven (for dependency management)

## How to Run
```bash
git clone https://github.com/satyam575/Queens.git
cd Queens
mvn clean install
java -cp target/Queens-1.0-SNAPSHOT.jar Main
