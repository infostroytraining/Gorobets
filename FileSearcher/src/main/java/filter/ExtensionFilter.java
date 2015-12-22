package filter;

import java.io.File;

import com.google.common.io.Files;

/**
 * This class compare an extension parameter from the command line with an extension of file.
 */
public class ExtensionFilter extends Filter {
    private String extension;

    public ExtensionFilter(Filter next, String extension) {
        super(next);
        this.extension = extension;
    }

    @Override
    public boolean currentAccept(File file) {
        if (file != null) {
            String fileExtension = Files.getFileExtension(file.getName());
            return fileExtension.equalsIgnoreCase(extension);
        }
        return false;
    }
}
