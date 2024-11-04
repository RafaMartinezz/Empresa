# Employee Management System

This project implements a simple Employee Management System for a company, providing functionalities to manage employee data, generate XML reports, and parse XML documents.

## Project Overview

The application handles employee records using an in-memory list and random-access files. It includes the following core functionalities:

1. **Employee Data Management**: The `Empleado` class represents an individual employee, containing essential information like name, address, salary, and commission.
  
2. **Employee Collection**: The `Plantilla` class manages a list of employees, allowing dynamic additions.

3. **Random Access File Operations**: The `Empresa` class writes employee data to a random-access file (`empleados.dat`) for persistence. This format ensures that data can be efficiently retrieved and managed without the constraints of sequential storage.

4. **XML Generation**: The employee data from the random-access file is also stored in an XML document (`empleados.xml`). The XML structure allows for a human-readable and structured view of the employee data.

5. **XML Parsing and Display**: An external parser (`DomParser`) is used to read and display XML content in a hierarchical format, providing a structured view of the XML document.

## Classes and Responsibilities

### `Empleado`
Represents individual employee details, including:
- `codigo`: Employee code.
- `nombre`: Name of the employee.
- `direccion`: Address.
- `salario`: Monthly salary.
- `comision`: Commission percentage.

### `Plantilla`
Manages the list of employees:
- Provides a method to add employees to the list.
- Holds an `ArrayList` of `Empleado` objects.

### `Empresa`
Handles file operations and XML generation:
- Writes employee data to a random-access file.
- Reads from the file and generates an XML document representing the data.
- Uses the `crearElemento` utility method to create and populate XML elements.
- Writes the XML to an output file (`empleados.xml`).

### `DomParser`
A custom XML parser that extends `DefaultHandler`:
- Implements methods for handling start and end of document and elements.
- Prints each XML element, attribute, and text node, with indentation for readability.
