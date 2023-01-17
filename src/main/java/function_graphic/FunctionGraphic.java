/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function_graphic;
import java.util.Scanner;


/**
 *
 * @author i7
 */
public class FunctionGraphic {
    public static void main(String[] args) {
        // Set display size
        //
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                           To select the default config, just press enter.
                           Type the desired plane dimensions.
                           default: x=200, y=90:""");
        
        int yScale = 10; // The higher this value, the higher the number of points displayed but with less precision
        int planeX;
        int planeY;
        String input;
        
        System.out.print("X = ");
        input = scanner.nextLine();
        planeX = input.matches("^\\d+$") ? Integer.parseInt(input) : 200;
        
        System.out.print("Y = ");
        input = scanner.nextLine();
        planeY = input.matches("^\\d+$") ? Integer.parseInt(input) : 90;
        //
        
        // Set axes position
        //
        int xAxis;
        int yAxis;
        
        System.out.println("""
                           Type the row number, from top to bottom, and the column number, from left to right
                           to position the orthogonal axes.
                           default: row=45, column=100:""");
        
        System.out.print("row = ");
        input = scanner.nextLine();
        xAxis = input.matches("^\\d+$") ? Integer.parseInt(input) : 45;
        
        System.out.print("column = ");
        input = scanner.nextLine();
        yAxis = input.matches("^\\d+$") ? Integer.parseInt(input) : 100;
        //
        
        // It tests if the axes are or are not in the display
        //
        if (planeX > yAxis) {
            if (planeY <= xAxis) {
                planeY = 90;
                xAxis = 45;
                System.out.println("Some settings have changed due to inconsistencies.");
            }
        }
        else {
            if (planeY <= xAxis) {
                planeY = 90;
                xAxis = 45;
            }
            planeX = 200;
            yAxis = 100;
            System.out.println("Some settings have changed due to inconsistencies.");
        }
        //
        
        // It receives the function
        //
        System.out.println("""
                           enter the function
                           example: 1x^3 - 3x^1 + 1x^0""");
        input = scanner.nextLine();
        //
        
        Display display = new Display(planeY, planeX);
        display.setOrthogonalAxes(xAxis, yAxis);
        display.makeGraph(input, yScale);
        display.generate();
    }
}
