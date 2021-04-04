package StudentMan;
import java.util.Scanner;

public class betterInput {
    protected static Scanner input = new Scanner(System.in);

    public static int getInt(){
        int number = 0;
        boolean valid = false;
        do{
            if(input.hasNextInt()){
                number = input.nextInt();
                valid = true;
            }
            else{
                System.out.print(" Không hợp lệ, nhập lại: ");
                input.next();
            }
        }while(!valid);
        return number;
    }

    public static float getFloat(){
        float number = 0;
        boolean valid = false;
        do{
            if(input.hasNextFloat()){
                number = input.nextFloat();
                valid = true;
            }
            else {
                System.out.print(" Không hợp lệ, nhập lại: ");
                input.next();
            }
        }while (!valid);
        return number;
    }
}
