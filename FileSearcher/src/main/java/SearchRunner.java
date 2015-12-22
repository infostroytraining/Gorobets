import java.io.File;
import java.util.*;

import com.beust.jcommander.JCommander;
import filter.*;

public class SearchRunner {

	public static void main(String[] args) {

		FileSearcher fs = new FileSearcher();
		fs.searcheFiles();
//		JCommander jc = new JCommander(fs);

//		if (ta.isHelp()) {
//			jc.usage();
//		}

//		IFrequency iFre = new Frequency();
//		IDuplicates iDup = new Duplicates();
//		ILength iLen = new Length();

//		Command frequencyCommand = new FrequencyCommand(iFre, ta);
//		Command duplicatesCommand = new DuplicatesCommand(iDup, ta);
//		Command lengthCommand = new LengthCommand(iLen, ta);


//
//        /* Создается карта имя-команда */
//		Map<String, Command> map = new HashMap<>();
//		map.put("frequency", frequencyCommand);
//		map.put("duplicates", duplicatesCommand);
//		map.put("length", lengthCommand);

        /* Разбираются аргументы запуска */
//
//		jc.addCommand(frequencyCommand);
//		jc.addCommand(duplicatesCommand);
//		jc.addCommand(lengthCommand);

//		jc.parse(args);

        /* Выполняется указанная в аргументах команда */
//		String taskName = jc.getParsedCommand();
//		map.get(taskName).executeCommand();

//		Filter filter = new NameFilter(null, "super");
//		filter = new ExtensionFilter(filter, "txt");
//		filter = new SizeFilter(filter, 10);
//
//		File file = new File("D:\\filter");
//
//		List<File> files = Arrays.asList(file.listFiles());
//
//		System.out.println(files);
//		List<File> result = new ArrayList<>();
//		for (File current : files) {
//			if (filter.accept(current)) {
//				result.add(current);
//			}
//		}
//		System.out.println(result);
	}
}