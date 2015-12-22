package filter;

import com.beust.jcommander.Parameter;
import com.google.common.base.Strings;

import java.io.File;
import java.util.*;

/**
 *
 */
public class FileSearcher {

    @Parameter(names = {"-i", "--input"}, description = "Path to the input file", required = true)
    private String pathToFile;


    public String getPathToFile() {
        return pathToFile;
    }

    Filter filter;

    public void searcheFiles() {

        Scanner scanner = new Scanner(System.in);



        File file = new File("/home/invincible_g_d/Programs/IDEA/Gorobets/FileSearcher");

        List<File> files = Arrays.asList(file.listFiles());

//            System.out.println(files);
        Filter filter = getNameFilter(scanner);
        List<File> result = new ArrayList<>();
        for (File current : files) {
            if (filter.accept(current)) {
                result.add(current);
            }
        }
        System.out.println(result);
        getNameFilter(scanner);

    }

    private Filter getNameFilter(Scanner scanner) throws InputMismatchException{
        Filter result = null;
        System.out.println("> искать по имени файла ? (0\\1)");
        int a = scanner.nextInt();


        if (a == 1) {
            System.out.println("> Введите имя файла: ");
            String name = scanner.nextLine();
            if (!Strings.isNullOrEmpty(name)) {
                filter = new NameFilter(null, name);
                result = filter;
            } else {
                System.out.println("Вы не ввели имя файла!");
                result = getNameFilter(scanner);
            }

        } else if (a == 0) {
            result = getExtensionFilter(scanner);

        } else {
//            throw new InputMismatchException();
            System.out.println("Вы ввели неправельное значение! Введите (0\\1)!");
            result = getNameFilter(scanner);
        }


        return result;

    }


    private Filter getExtensionFilter(Scanner scanner) throws InputMismatchException{

        System.out.println("> искать по расширению файла ? (0\\1)");
        int a = scanner.nextInt();
        do {

            if (a == 1) {
                System.out.println("> Введите расширение файла: ");
                String extension = scanner.nextLine();
                if (!Strings.isNullOrEmpty(extension)) {
                    filter = new ExtensionFilter(getNameFilter(scanner), extension);
                    return filter;
                } else {
                    System.out.println("Вы не ввели расширение файла!");
                }

            } else if (a == 0) {
                getSizeFilter(scanner);
                return null;
            }
            System.out.println("Вы ввели неправельное значение! Введите (0\\1)!");
        } while (true);
    }


    private Filter getSizeFilter(Scanner scanner) throws InputMismatchException{

        System.out.println("> искать по размеру файла ? (0\\1)");
        int a = scanner.nextInt();
        do {

            if (a == 1) {
                System.out.println("> Введите размер файла: ");
                int size = scanner.nextInt();
                if (size != 0) {
                    filter = new SizeFilter(getExtensionFilter(scanner), size);
                    return filter;
                } else {
                    System.out.println("Вы не ввели размер файла!");
                }

            } else if (a == 0) {
                getDateFilter(scanner);
            }
            System.out.println("Вы ввели неправельное значение! Введите (0\\1)!");
        } while (true);
    }

    private Filter getDateFilter(Scanner scanner) throws InputMismatchException{

        System.out.println("> искать по дате последнего изменения ? (0\\1)");
        int a = scanner.nextInt();
        do {

            if (a == 1) {
                System.out.println("> Введите дату изменения файла: ");
                int date = scanner.nextInt();
                if (date != 0) {
                    filter = new DateFilter(getSizeFilter(scanner), date);
                    return filter;
                } else {
                    System.out.println("Вы не ввели дату изменения файла!");
                }

            } else if (a == 0) {
                System.out.println("Если хотите покинуть программу введите : exit ! " +
                        "Если хотите продолжить, то нажмите : Enter !");
                String exit = scanner.nextLine();
                if (exit.equals("exit")) {
                    System.exit(0);
                }
                getNameFilter(scanner);
            }
            System.out.println("Вы ввели неправельное значение! Введите (0\\1)!");
        } while (true);

    }

}




