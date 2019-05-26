package helper;

import java.util.Optional;
import java.util.Scanner;

public class Parse {

    static public Optional<Long> parseLong(String input){
        Scanner scanner = new Scanner(input);
        return scanner.hasNextLong() ? Optional.ofNullable(scanner.nextLong()) : Optional.empty();
    }
}
