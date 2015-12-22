package filter;

import java.io.File;

import com.google.common.io.Files;

public class ExtensionFilter extends Filter {
    private String extention;

    public ExtensionFilter(Filter next, String extention) {
        super(next);
        this.extention = extention;
    }

    @Override
    public boolean currentAccept(File file) {
        if (file != null) {
            String fileExtention = Files.getFileExtension(file.getName());
            return fileExtention.equalsIgnoreCase(extention);
        }
        return false;
    }
}