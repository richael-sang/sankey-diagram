# Sankey Diagram Data Visualization

An interactive Sankey diagram visualization tool built with Java and JavaFX, demonstrating fundamental programming concepts including file I/O, data processing, and GUI development.

## Project Overview

This project creates dynamic Sankey diagrams from structured text data, visualizing hierarchical flow relationships in an intuitive and interactive way. It's perfect for visualizing financial data, resource allocation, or any process with multi-stage flows.

## Features

- **File-Based Data Input**: Read structured text files containing flow data
- **Interactive Visualization**: Dynamic Sankey diagrams with smooth rendering
- **Color Customization**: Click to change diagram colors
- **Multiple Data Examples**: Includes sample datasets for various use cases
- **Custom Components**: Built from scratch using JavaFX primitives

## Demo Examples

The project includes four example datasets:

1. **Example 1**: Year 1 financial flows (Resources → Invest/Sales/Profit)
2. **Example 2**: Student grades distribution across subjects
3. **Example 3**: Multi-year academic performance tracking
4. **Example 4**: Business expense breakdown (Budget allocation)

## Technical Implementation

### Core Components

- **FileReader.java**: Parses text files and extracts flow data
- **SankeyDiagram.java**: Main visualization engine with custom drawing logic
- **FileToSankeyDiagram.java**: JavaFX application controller with UI events
- **MyRectangle.java**: Custom rectangle component for flow blocks
- **MyText.java**: Custom text rendering for labels
- **Starter.java**: Application entry point

### Technologies

- **Java**: Core programming language
- **JavaFX**: GUI framework for rendering and interaction
- **Object-Oriented Design**: Modular architecture with clear separation of concerns

## Data Format

Input files follow a simple structure:
```
Title Line
Label Line
Category1 Value1
Category2 Value2
...
```

Example (`example1.txt`):
```
Year 1
Resources
Invest 4300
Sales 2400
Profit 2200
```

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- JavaFX library (included in JDK 8, separate for JDK 11+)

### Compilation and Execution

```bash
# Compile
javac Starter.java FileToSankeyDiagram.java SankeyDiagram.java FileReader.java MyRectangle.java MyText.java

# Run
java Starter
```

### Using the Application

1. The application loads a default example file
2. View the generated Sankey diagram
3. Click the "Change The Colors" button to randomize colors
4. To visualize different data, modify the file path in `FileToSankeyDiagram.java` (line 18)

## Project Structure

```
sankey-diagram/
├── README.md
├── Starter.java              # Entry point
├── FileToSankeyDiagram.java  # Main application
├── SankeyDiagram.java        # Visualization engine
├── FileReader.java           # Data parser
├── MyRectangle.java          # Custom rectangle component
├── MyText.java               # Custom text component
├── example1.txt              # Financial data example
├── example2.txt              # Education data example
├── example3.txt              # Multi-year data example
└── example4.txt              # Business expense example
```

## Key Concepts Demonstrated

- **File I/O**: Reading and parsing text files
- **Data Structures**: Using Maps to organize flow data
- **JavaFX GUI**: Creating interactive graphical interfaces
- **Custom Components**: Building reusable UI elements
- **Event Handling**: Responding to user interactions
- **Object-Oriented Design**: Clean, maintainable code structure

## Educational Value

This project demonstrates:
- Fundamental Java programming concepts
- GUI development with JavaFX
- Data visualization principles
- File handling and parsing
- Object-oriented design patterns

Perfect for students learning Java basics, GUI programming, or data visualization concepts.

## Author

**Rui Sang** (2251576)

## Course Information

- **Course**: CPT111 - Introduction to Computer Programming
- **Institution**: Xi'an Jiaotong-Liverpool University
- **Year**: 2024

## License

This project is developed for educational purposes as part of coursework.

