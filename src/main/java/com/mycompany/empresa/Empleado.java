package com.mycompany.empresa;

/**
 * The Empleado class represents an employee with a unique code, name, address,
 * salary, and commission.
 * It provides methods to access and modify these attributes.
 * 
 * Author: rafa
 */
public class Empleado {
    private int codigo; // Unique employee code
    private String nombre; // Name of the employee
    private String direccion; // Address of the employee
    private float salario; // Base salary of the employee
    private float comision; // Commission percentage or amount

    /**
     * Constructor initializes an Empleado instance with specified attributes.
     * 
     * @param codigo    The unique code assigned to the employee.
     * @param nombre    The name of the employee.
     * @param direccion The address of the employee.
     * @param salario   The base salary of the employee.
     * @param comision  The commission amount or rate for the employee.
     */
    public Empleado(int codigo, String nombre, String direccion, float salario, float comision) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.salario = salario;
        this.comision = comision;
    }

    /**
     * Gets the base salary of the employee.
     * 
     * @return The salary as a float.
     */
    public float getSalario() {
        return salario;
    }

    /**
     * Sets the base salary of the employee.
     * 
     * @param salario The new salary amount.
     */
    public void setSalario(float salario) {
        this.salario = salario;
    }

    /**
     * Gets the unique code of the employee.
     * 
     * @return The code as an integer.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Sets the unique code of the employee.
     * 
     * @param codigo The new code for the employee.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets the name of the employee.
     * 
     * @return The name as a String.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of the employee.
     * 
     * @param nombre The new name for the employee.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the address of the employee.
     * 
     * @return The address as a String.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets the address of the employee.
     * 
     * @param direccion The new address for the employee.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Gets the commission for the employee.
     * 
     * @return The commission as a float.
     */
    public float getComision() {
        return comision;
    }

    /**
     * Sets the commission for the employee.
     * 
     * @param comision The new commission amount or rate.
     */
    public void setComision(float comision) {
        this.comision = comision;
    }
}
