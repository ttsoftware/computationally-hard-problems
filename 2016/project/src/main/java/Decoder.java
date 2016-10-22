import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Decoder {

    public Problem decode(List<String> contents){
        HashSet<Character> gammaLetters = new HashSet<Character>();
        Problem problem = new Problem();

        //"^(\\w{1}):([\\w+,]+\\w+)$\n"

        if(contents.size() < 4){
            System.out.println("Not enough lines in file to contain k, s, t and R.");
            System.exit(1);
        }

        try{
            int k = Integer.parseInt(contents.get(0));

            String s = contents.get(1);

            if(s.matches("^[a-z]+$")){
                System.out.println("Not all characters in s are in the alphabet.");
                System.exit(1);
            }

            List<String> t = new ArrayList<String>();

            for(int i = 2; i < k + 2; i++){
                String t_i = contents.get(i);

                if(t_i.matches("^[a-zA-Z]+$")){
                    System.out.println("String t_" + i + " is not valid.");
                    System.exit(1);
                }

                t.add(t_i);

                for(char c : t_i.toCharArray()){
                    if(Character.isUpperCase(c)) gammaLetters.add(c);
                }
            }


            //validate R values
            for(int i = 2 + k; i < contents.size(); i++){
                

            }


        } catch(NumberFormatException e) {
            System.out.println("Wrong format for k.");
            System.exit(1);
        } catch(IndexOutOfBoundsException e){
            System.out.println("Not enough files.");
            System.exit(1);
        }

    }
}
