package task1;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BackupUtility {

    public static void main(String[] args) {
        createBackup();
    }

    public static void createBackup() {
        File currentDirectory = new File(".");
        File backupDirectory = new File("./backup");

        if (!backupDirectory.exists()) {
            backupDirectory.mkdir();
        }

        File[] files = currentDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        Files.copy(file.toPath(), new File(backupDirectory, file.getName()).toPath(),
                                StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.err.println("Failed to create a backup of " + file.getName());
                    }
                }
            }
            System.out.println("Backup created successfully.");
        }
    }
}