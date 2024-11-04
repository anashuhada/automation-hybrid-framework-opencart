package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    // data provider 1
    @DataProvider(name="LoginData")
    public String[][] getData() throws IOException {
        String path = "./testData/OpenCartLoginDDT.xlsx"; // taking xlsx file from testData

        ExcelUtility xlutil = new ExcelUtility(path); // creating an object for xlutility

        int totalRow = xlutil.getRowCount("Sheet1");
        int totalCol = xlutil.getCellCount("Sheet1", 1);

        String loginData[][] = new String[totalRow][totalCol]; // create 2D array to store data
        for (int i = 1; i <= totalRow; i++) { // read data from xl, storing in 2D array
            for (int j = 0; j < totalCol; j++) { // i is rows, j is columns
                loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }

        return loginData; // returning 2D array
    }

    // data provider 2
    @DataProvider(name="RegistrationData")
    public String[][] getRegistrationData() throws IOException {
        String path = "./testData/OpenCartAccountRegistrationDDT.xlsx"; // taking xlsx file from testData

        ExcelUtility xlutil = new ExcelUtility(path); // creating an object for xlutility

        int totalRow = xlutil.getRowCount("Sheet1");
        int totalCol = xlutil.getCellCount("Sheet1", 1);

        String regData[][] = new String[totalRow][totalCol]; // create 2D array to store data
        for (int i = 1; i <= totalRow; i++) { // read data from xl, storing in 2D array
            for (int j = 0; j < totalCol; j++) { // i is rows, j is columns
                regData[i-1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }

        return regData; // returning 2D array
    }

    // data provider 3

    // data provider 4

}
