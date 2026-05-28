[README.md](https://github.com/user-attachments/files/28357040/README.md)
# GenealogyTree-DataStructures-or-FamilyRelations-DataStructures
A Java application that parses family data from a CSV file, evaluates complex genealogical relationships, and implements fundamental tree data structures (BST and AVL Trees).
# Genealogy-Network-Analyzer
### Data Structures & Algorithms (CN5005) Project

A specialized Java application designed to analyze family trees and kinship relations using advanced data structures (BST and AVL Trees). This project focuses on efficient data retrieval and complex relationship mapping from large datasets.

## 🚀 Key Features
- **Family Relationship Mapping:** Identifies complex kinship relations including Sibling, Grandparent, First Cousin, and more.
- **Advanced Data Structures:** Implements **Binary Search Trees (BST)** and **AVL Trees** (Self-Balancing) to ensure $O(\log n)$ performance for data lookups.
- **CSV Data Processing:** Loads and parses large-scale genealogical data from CSV files into memory-efficient structures.
- **CLI Query Engine:** A command-line interface that allows users to query relationships between any two individuals instantly.

## 🛠️ Tech Stack
- **Language:** Java 17
- **Data Structures:** AVL Tree, BST, HashMaps
- **Build Tool:** Maven
- **Testing:** JUnit 5

## 🔧 Installation & Usage
1. Ensure you have **Java 17** and **Maven** installed.
2. Clone the repository and navigate to the project root.
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the relationship query:
   ```bash
   java -cp target/coursework-1.0.jar Main relation "Person Name 1" "Person Name 2"
   ```

## 📊 Performance Optimization
To handle thousands of records efficiently, the project utilizes an **AVL Tree implementation** to maintain perfect balance during data insertion, preventing the performance degradation typical of standard BSTs.
