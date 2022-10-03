import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    File inputFile = new File("plik.txt");
    File outputFile = new File("Fade.java");

    if(outputFile.exists()){
      outputFile.delete();
    }

    PrintWriter printer = new PrintWriter(new FileWriter(outputFile));
    Scanner inputScanner = new Scanner(inputFile);

    printer.println("public class Fade { public static void main(String[] args) {");

    HashMap<String, String> conversion = new HashMap<>();

    conversion.put("napisz", "System.out.println");
    conversion.put("liczba", "int");
    conversion.put("jezeli", "if");
    conversion.put("dopoki", "while");
    conversion.put("rob", "do");
    conversion.put("dla", "for");

    while(inputScanner.hasNextLine()){
      String line = inputScanner.nextLine();
      String[] words = line.split(" ");
      for(int i = 0; i < words.length; i++){
        if(conversion.containsKey(words[i])){
          words[i] = conversion.get(words[i]);
        }
      }

      StringBuilder stringBuilder = new StringBuilder("");
      for(String word : words){
        stringBuilder.append(word).append(" ");
      }

      printer.println(stringBuilder);
    }

    printer.println("}}");
    printer.close();
    Runtime runtime = Runtime.getRuntime();
    try {
      runtime.exec("javac Fade.java");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
