package compiler;

import compiler.parse.Parser;
import compiler.scan.Scanner;
import compiler.scan.SymbolList;
import compiler.scan.TokenType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class Main {
    private static final SymbolList symbolList = new SymbolList(Map.of(
            List.of(".", ":="), TokenType.SYMBOL,
            List.of("PROGRAM", "BEGIN", "END"), TokenType.KEYWORD
    ));

    public static void main(String[] args) {
        //read
        String fileName = args.length > 0 ? args[0] : "text.txt";
        StringBuilder content = new StringBuilder();
        try {
            FileReader reader = new FileReader(fileName);
            for(String line : new BufferedReader(reader).lines().toList()) {
                if(!content.isEmpty()) content.append(" ");
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        //scan
        Scanner scanner = new Scanner(SymbolList.copy(symbolList), content.toString());
        if(scanner.scan()) {
            System.out.println("Scan succeeded!");
        } else {
            System.err.println("Scan failed!");
            return;
        }

        //parse
        Parser parser = new Parser(scanner.tokenOrder());
        if(parser.parse()) {
            System.out.println("Parse succeeded!");
        } else {
            System.err.println("Parse failed!");
        }
    }
}
