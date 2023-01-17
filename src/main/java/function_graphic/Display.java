/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function_graphic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author i7
 */
public final class Display {
    // Display logic definitions
    
    public int screenHeight;
    public int screenWidth;
    
    public int row;
    public int column;
    
    public char[][] matrix;
    
    private void makeXAxis(int row) {
        for(int i = 0; i < screenWidth - 1; i++) {
            matrix[row][i] = '-';
        }
        matrix[row][screenWidth - 1] = '>';
        this.row = row;
    }
    
    private void makeYAxis(int column) {
        matrix[0][column] = '^';
        for(int i = 1; i < screenHeight; i++) {
            matrix[i][column] = '|';
        }
        this.column = column;
    }
    
    private void makeOrigin() {
        matrix[this.row][this.column] = '+';
    }
    
    public void makeGraph(String input, int yScale) {
        Pattern pattern = Pattern.compile("\\s*([\\+-])?\\s*(\\d*)x\\^(\\d*)");
        if (input.matches("^\\s*\\d*x.*")) {
            input = "+" + input;
        }
        Matcher matcher = pattern.matcher(input);
        
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        int[][] monomials = new int[count][3];
        
        matcher.reset();
        count = 0;
        while (matcher.find()) {
            if (matcher.group(1).equals("+")) {
                monomials[count][0] = 1;
            }
            else {
                monomials[count][0] = -1;
            }
            monomials[count][1] = Integer.parseInt(matcher.group(2));
            monomials[count][2] = Integer.parseInt(matcher.group(3));
            count++;
        }
        int y = 0;
        for(int x = 0; x < screenWidth; x++) {
            for(int values[] : monomials) {
                y = y + values[0] * values[1]*(int)Math.pow(x - this.column, values[2]);
            }
            y = Math.round(y / yScale);
            if (this.row - y < this.screenHeight && this.row > y) {
                matrix[Math.round(this.row - y)][x] = '*';
            }
            y = 0;
        }
    } 
    
    
    public void setOrthogonalAxes(int rowXAxis, int columnYAxis) {
        makeXAxis(rowXAxis);
        makeYAxis(columnYAxis);
        makeOrigin();
    }
    
    public void generate() {
        for(char[] rowCharacters : this.matrix) {
            for(char columnCharacter : rowCharacters) {
                System.out.print(columnCharacter);
            }            
            System.out.println();
        }
    }
    public Display(int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.matrix = new char[screenHeight][screenWidth];
        for (int i = 0; i < screenHeight; i++) {
            for (int j = 0; j < screenWidth; j++) {
                this.matrix[i][j] = ' ';
            }
        }
    }
}
