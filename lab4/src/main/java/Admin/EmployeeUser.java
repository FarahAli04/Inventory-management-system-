package Admin;

import java.io.*;

public class EmployeeUser {

    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String lineRepresentation() {
        return this.employeeId + "," + this.name + "," + this.email + "," + this.address + "," + this.phoneNumber;
    }

    public String getSearchKey() {
        return this.employeeId;
    }

    public void saveToFile() {

        File file = new File("Employees.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            if (isDuplicateId(file)) {
                System.out.println("Error Employee Id " + employeeId + " already exists!");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(lineRepresentation());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file:" + e.getMessage());
        }
    }      
    

    private boolean isDuplicateId(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line= reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(employeeId)) {
                    return true;
                }
            }
        }
        return false;
    }

}
