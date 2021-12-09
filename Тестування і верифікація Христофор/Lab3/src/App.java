import java.util.ArrayList;

public class App
{
    public static void main(String[] args) {
        try {
            Parser parser = new Parser("D:/Бурса/Тестування і верифікація Христофор/test.txt");
            ArrayList<String> res = parser.getResult();
            for (String s : res) {
                System.out.println(s);
            }
        }
        catch (Exception e){
            System.out.println("Program terminated after an error.");
        }
        System.out.println("Finished");
    }
}