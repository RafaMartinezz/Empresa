package com.mycompany.empresa;

import java.util.ArrayList;

/**
 * The Plantilla class represents a collection of employees within a company.
 * It provides methods for managing and adding employees to the collection.
 * 
 * Author: rafa
 */
public class Plantilla {
    // List to store Employee objects
    ArrayList<Empleado> empleados;

    /**
     * Constructs a Plantilla instance with an initial list of employees.
     * 
     * @param empleados An ArrayList of Empleado objects representing the company's
     *                  employees.
     */
    public Plantilla(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    /**
     * Adds an employee to the Plantilla's collection.
     * 
     * @param e The Empleado object to be added to the list of employees.
     */
    public void addEmpleado(Empleado e) {
        this.empleados.add(e);
    }
}
