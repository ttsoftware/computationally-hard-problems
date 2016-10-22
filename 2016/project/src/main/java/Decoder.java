import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decoder {

    public Problem decode(List<String> contents){
        HashSet<Character> gammaLetters = new HashSet<>();

        if(contents.size() < 4){
            System.out.println("Not enough lines in file to contain k, s, t and R.");
            System.exit(1);
        }

        try{
            int k = Integer.parseInt(contents.get(0));

            String s = contents.get(1);

            if(!s.matches("^[a-z]+$")){
                System.out.println(s);
                System.out.println("Not all characters in s are in the alphabet.");
                System.exit(1);
            }

            List<String> t = new ArrayList<>();

            for(int i = 2; i < k + 2; i++){
                String t_i = contents.get(i);

                if(!t_i.matches("^[a-zA-Z]+$")){
                    System.out.println("String t_" + i + " is not valid.");
                    System.exit(1);
                }

                t.add(t_i);

                for(char c : t_i.toCharArray()){
                    if(Character.isUpperCase(c)) gammaLetters.add(c);
                }
            }

            HashMap<String, List<String>> map = new HashMap<>();

            //validate R values
            for(int i = 2 + k; i < contents.size(); i++){
                Pattern pattern = Pattern.compile("^([A-Z]{1}):([a-z,]+[a-z]+)$");
                Matcher matcher = pattern.matcher(contents.get(i));

                if (matcher.matches()) {
                    String R = matcher.group(1);
                    List<String> extensions = Arrays.asList(matcher.group(2).split(","));

                    if(gammaLetters.contains(R.charAt(0))){
                       gammaLetters.remove(R.charAt(0));
                    }

                map.put(R, new ArrayList<>(extensions));

                } else {
                    System.out.println("Did not found a matching pattern for r.");
                    System.exit(0);
                }
            }

            if(!gammaLetters.isEmpty()){
                System.out.println("Did not find extension sets for all letters in Gamma.");
                System.exit(1);
            }

            return new Problem(s, t, map);

        } catch(NumberFormatException e) {
            System.out.println("Wrong format for k.");
        } catch(IndexOutOfBoundsException e){
            System.out.println("Not enough files.");
        }

        System.out.println("Lol gg");
        return null;
    }
}
