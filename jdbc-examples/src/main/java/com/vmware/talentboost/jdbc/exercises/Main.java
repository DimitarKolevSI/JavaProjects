package com.vmware.talentboost.jdbc.exercises;


public class Main {


    public static void main(String[] args) throws Exception {

        try {
            JobsComponent jobsComponent = new JobsComponent();
            System.out.println("Full Jobs list:");
            jobsComponent.printJobList(); // should return 19 jobs
            System.out.println();
            System.out.println("Job list with salary from 5000 to 25000:");
            jobsComponent.printJobList(5000, 25000); // should return 7 jobs
            System.out.println();

            // addNewEmployee("Cristiano", "Ronaldo", "ronaldo@madrid.com");
            int employeeNumber = addNewEmployee("David", "Beckham", "david@man.com");
            deleteEmployee(employeeNumber);

            replaceEmployeesManagers(); // 5 employees should be re-assigned

        } catch (Exception exception) {
            util.ExceptionHandler.handleException(exception);
        }

    }

    private static void deleteEmployee(int employeeNumber) {
        try {

            HrComponent hrComponent = new HrComponent();

            boolean success = hrComponent.deleteEmployee(Integer.toString(employeeNumber));
            System.out.println(	"Employee "
                    + employeeNumber
                    + " has "
                    + (success ? "been deleted" : "not been deleted"));

        } catch (Exception exception) {
            util.ExceptionHandler.handleException(exception);
        }
    }

    private static int addNewEmployee(String firstName, String lastName, String email) throws Exception {
        HrComponent hrComponent = new HrComponent();

        int key = hrComponent.addEmployee(
                firstName, lastName, "0888104",
                email, "30000",
                "SA_REP", Long.toString(System.currentTimeMillis()));

        System.out.println("The auto-generated primary key for the new employee = " + key);
        return key;
    }

    private static void replaceEmployeesManagers() throws Exception {

        HrComponent hrComponent = new HrComponent();

        // first re-assign everyone to 103
        hrComponent.replaceManager("102", "103");
        // then re-assign every to 102
        int count = hrComponent.replaceManager("103", "102");
        System.out.println(count + " Employees have been reassigned");
    }

}
