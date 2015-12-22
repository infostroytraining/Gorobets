import com.beust.jcommander.JCommander;

import java.io.IOException;

/**
 * Runner class
 */
public class SearchRunner {

	public static void main(String[] args) {

		FileSearcher fs = new FileSearcher();
		JCommander jc = new JCommander(fs);
		jc.parse(args);

		try {
			fs.searchFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}