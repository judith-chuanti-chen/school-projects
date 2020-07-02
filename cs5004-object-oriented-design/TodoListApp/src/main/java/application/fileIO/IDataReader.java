package application.fileIO;

/**
 * Represents DataReader Interface
 */
public interface IDataReader {
    /**
     * Read the file
     * @param path the location where the file is, given as a String
     * @throws Exception throw an exception if path is invalid
     */
    void read(String path) throws Exception;

}
