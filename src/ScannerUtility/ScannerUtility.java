/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScannerUtility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Sergey
 */
public class ScannerUtility {

    public static List<String> Scan(String path) {
        List<String> result = null;
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {

            result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());
            System.out.println("Find files:");
            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
