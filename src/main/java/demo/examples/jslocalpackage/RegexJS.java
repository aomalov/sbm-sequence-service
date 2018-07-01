package demo.examples.jslocalpackage;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexJS {

    public static Set<String> gelLocalPackages(String jsFile) {
        Pattern pattern = Pattern.compile("=\\s*require[(]\\s*['\"]([/~.]\\S+)['\"]");

        File file = new File(jsFile);
        Set<String> results=new HashSet<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Matcher matcher = pattern.matcher(scanner.nextLine());
                if(matcher.find()) results.add(matcher.group(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        FolderProcessor system = new FolderProcessor("./src/test/resources/js-libs", "js");

        pool.execute(system);
        System.out.println(system.join());
        pool.shutdown();
    }

}
