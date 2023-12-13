import java.io.*;
import java.util.UUID;

public class Homework {

    /**
     * Метод для сохранения объекта в файл.
     *
     * @param serializableObject Объект, реализующий интерфейс Serializable.
     * @return Название созданного файла.
     */
    public static String saveObjectToFile(Serializable serializableObject) {
        // Генерируем уникальное имя файла
        String fileName = serializableObject.getClass().getName() + "_" + UUID.randomUUID().toString();

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            // Записываем объект в файл
            outputStream.writeObject(serializableObject);
            System.out.println("Object saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving object to file: " + e.getMessage());
        }

        return fileName;
    }

    /**
     * Метод для загрузки объекта из файла и удаления файла.
     *
     * @param fileName Название файла, из которого нужно загрузить объект.
     * @return Загруженный объект или null в случае ошибки.
     */
    public static Serializable loadObjectFromFileAndDelete(String fileName) {
        Serializable loadedObject = null;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            // Читаем объект из файла
            loadedObject = (Serializable) inputStream.readObject();
            System.out.println("Object loaded from file: " + fileName);
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error loading object from file: " + e.getMessage());
        }

        // Удаляем файл после загрузки
        if (loadedObject != null) {
            File fileToDelete = new File(fileName);
            if (fileToDelete.delete()) {
                System.out.println("File deleted: " + fileName);
            } else {
                System.err.println("Error deleting file: " + fileName);
            }
        }

        return loadedObject;
    }

    public static void main(String[] args) {
        // Пример использования методов
        Serializable objectToSave = "Hello, Serialization!";
        String savedFileName = saveObjectToFile(objectToSave);

        Serializable loadedObject = loadObjectFromFileAndDelete(savedFileName);

        // Проверяем, что объект был успешно загружен
        if (loadedObject != null) {
            System.out.println("Loaded Object: " + loadedObject);
        }
    }
}
