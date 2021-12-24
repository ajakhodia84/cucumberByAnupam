package framework.shared;

import java.io.File;
import java.io.FileFilter;

public class CustomFileFilter implements FileFilter {

    private String[] acceptedFileExtension = new String[]{".json"};

   public CustomFileFilter(String[] fileExtensionArray) {
        this.acceptedFileExtension = fileExtensionArray;
    }


    @Override
    public boolean accept(File file) {
        for (String extension : acceptedFileExtension) {
            if (file.getName().toLowerCase().endsWith(extension))
                return true;
        }
        return false;

    }


}
