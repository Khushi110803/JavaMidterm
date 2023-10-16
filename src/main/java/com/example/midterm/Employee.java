package com.example.midterm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Employee {
    private int id;
    private String name;
    private String birthday;
    private String department;
    private String email;
    private double salary;

    public Employee(int id, String name, String email, double salary, String birthday, String department, String birthday1, String department1) {


        setEmail(email);
        setName(name);
        setBirthday(birthday);
        setEmail(email);
        setId(id);
        setDepartment(department);
    }

    public  int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.trim().length() > 5 && !name.trim().isEmpty()) {
            this.name = name.trim();
        } else {
            throw new IllegalArgumentException("Name should not have leading/trailing whitespace and should be at least 6 characters long");
        }
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // You can adjust the date format as needed.
        Date currentDate = new Date();

        try {
            Date date = dateFormat.parse(birthday);
            if (date.before(currentDate) || date.equals(currentDate)) {
                this.birthday = birthday;
            } else {
                throw new IllegalArgumentException("Birthday cannot be in the future");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format for birthday");
        }
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        String[] validDepartments = {"sales", "marketing", "accounting", "human resources", "information technology", "research and development"};

        for (String validDept : validDepartments) {
            if (validDept.equalsIgnoreCase(department)) {
                this.department = department;
                return; // Valid department found, exit the loop.
            }
        }

        throw new IllegalArgumentException("Invalid department. Department must be one of: sales, marketing, accounting, human resources, information technology, or research and development.");
    }
    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.trim().length() >= 5 && email.trim().contains("@") && email.trim().contains(".")) {
            this.email = email.trim();
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
        } else {
            throw new IllegalArgumentException("Salary must be greater than 0");
        }
    }
}
