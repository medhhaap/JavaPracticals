package oop;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    double calculateSalary() {
        return salary;
    }
}

class FullTimeEmployee extends Employee {
    double basicPay, allowance;

    FullTimeEmployee(int id, String name, double basicPay, double allowance) {
        super(id, name);
        this.basicPay = basicPay;
        this.allowance = allowance;
    }

    @Override
    double calculateSalary() {
        salary = basicPay + allowance;
        return salary;
    }
}

class PartTimeEmployee extends Employee {
    int hoursWorked;
    double hourlyRate;
    PartTimeEmployee(int id, String name, int hoursWorked, double hourlyRate) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }
    @Override
    double calculateSalary() {
        salary = hoursWorked * hourlyRate;
        return salary;
    }
}
public class payrollSystem {
    public static void main(String[] args) {
        Employee e1 = new FullTimeEmployee(1, "Kim's", 70000, 2000);
        Employee e2 = new PartTimeEmployee(2, "Seok's", 80, 200);
        Employee e3 = new FullTimeEmployee(1, "Jin's", 5000, 90);

        System.out.println(e1.name + " Salary: " + e1.calculateSalary());
        System.out.println(e2.name + " Salary: " + e2.calculateSalary());
        System.out.println(e3.name + " Salary: " + e3.calculateSalary());
    }
}

