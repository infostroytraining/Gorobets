import com.beust.jcommander.Parameter;
import filter.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 *This Class do all work for finding a necessarily file
 * It get <filePath> using JCommander library
 */
public class FileSearcher {

    @Parameter(names = {"-i", "--input"}, description = "Path to the input file", required = true)
    String pathToFile;

    /**This method search in the dir recursively and find all files in all directories
     * and after that pass through the list of files and  finds necessarily life by using
     * chosen filter.
     * @throws IOException - can be thrown by <Files.walk()>
     */
    public void searchFiles() throws IOException {

        List<File> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        Path dir = Paths.get(pathToFile);

        List<File> files = Files.walk(dir)
                .map(Path::toString).map(File::new)
                .filter(File::isAbsolute).collect(Collectors.toList());

        Filter filter = getNameFilter(scanner);

        try {
            files.stream().filter(filter::accept).forEach(result::add);
        } catch (NullPointerException e) {
            System.out.println("Вы ввели неправельное значение! ");
            searchFiles();
        }


        System.out.println(result);

        searchFiles();

    }

    /**
     * All methods below parse commands from command line and use it for
     * necessarily actions like find file by a specified filter or skip these method.
     * @param scanner - it's a Scanner instance
     * @return Filter instance
     */
    private Filter getNameFilter(Scanner scanner) {
        Filter result = null;
        System.out.println("> искать по имени файла ? (0\\1)");
        try {

            int a = scanner.nextInt();


            if (a == 1) {
                System.out.println("> Введите имя файла: ");
                Scanner scs = new Scanner(System.in);
                String name = scs.nextLine();
                result = new NameFilter(null, name);
            } else if (a == 0) {
                result = getExtensionFilter(scanner);

            }
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели неправельное значение! Введите (0\\1)!");
            Scanner sc = new Scanner(System.in);
            getNameFilter(sc);
        }

        return result;

    }


    private Filter getExtensionFilter(Scanner scanner) {
        Filter result = null;


        System.out.println("> искать по расширению файла ? (0\\1)");
        try {
            int a = scanner.nextInt();


            if (a == 1) {
                System.out.println("> Введите расширение файла: ");
                Scanner scs = new Scanner(System.in);
                String name = scs.nextLine();
                result = new ExtensionFilter(null, name);
            } else if (a == 0) {
                result = getSizeFilter(scanner);

            }
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели неправельное значение! Введите (0\\1)!");
            Scanner sc = new Scanner(System.in);
            getExtensionFilter(sc);
        }

        return result;

    }


    private Filter getSizeFilter(Scanner scanner) {
        Filter result = null;

        int a = 0;
        System.out.println("> искать по размеру файла ? (0\\1)");
        try {
            a = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели неправельное значение! Введите (0\\1)!");
            Scanner sc = new Scanner(System.in);
            getSizeFilter(sc);
        }

        if (a == 1) {
            System.out.println("> Введите размер файла: ");
            scanner = new Scanner(System.in);
            int size = scanner.nextInt();
            result = new SizeFilter(null, size);
        } else if (a == 0) {
            result = getDateFilter(scanner);

        }

        return result;

    }

    private Filter getDateFilter(Scanner scanner) {
        Filter result = null;

        int a = 0;
        System.out.println("> искать по дате последнего изменения ? (0\\1)");
        try {
            a = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели неправельное значение! Введите (0\\1)!");
            scanner = new Scanner(System.in);
            getDateFilter(scanner);
        }

        if (a == 1) {
            System.out.println("> Введите дату изменения файла: ");
            scanner = new Scanner(System.in);
            int size = scanner.nextInt();
            result = new DateFilter(null, size);
        } else if (a == 0) {
            System.out.println("Если хотите покинуть программу введите : exit ! " +
                    "Если хотите продолжить, то введите :  enter ");
            scanner = new Scanner(System.in);
            String exit = scanner.nextLine();
            if (exit.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else if (exit.equalsIgnoreCase("enter")) {
                try {
                    searchFiles();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                scanner = new Scanner(System.in);
                getDateFilter(scanner);
            }


        }

        return result;

    }


}




