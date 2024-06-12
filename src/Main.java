import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {
    private static final SymbolList symbolList = new SymbolList(List.of(".", ":=", "PROGRAM", "BEGIN", "END"));

    public static void main(String[] args) {
        String fileName = args.length > 0 ? args[0] : "text.txt";
        StringBuilder content = new StringBuilder();
        try {
            FileReader reader = new FileReader(fileName);
            for(String line : new BufferedReader(reader).lines().toList()) {
                content.append(line);
            }
            Scanner scanner = new Scanner(SymbolList.copy(symbolList), content.toString());
            if(scanner.scan()) {
                System.out.println("Scanner succeeded!");
                System.out.println("SymbolList:");
                System.out.println(scanner.symbolList());
                System.out.println("TokenGrid:");
                System.out.println(scanner.tokenGrid());
            } else {
                System.err.println("Scanner failed!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
