package dataController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main data control file, all data controllers extend it to use its write and
 * read functions.
 * 
 * @author Chang En Kai
 *
 */
public class DataControl {

	protected static final String SEPARATOR = "|";
	protected static final SimpleDateFormat finalDateFormatter = new SimpleDateFormat(
			"dd/MM/yyyy");

	/**
	 * Read file.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	protected static ArrayList<String> read(String fileName) throws IOException {
		ArrayList<String> data = new ArrayList<String>();
		Scanner scanner = new Scanner(new FileInputStream(fileName));
		try {
			while (scanner.hasNextLine()) {
				data.add(scanner.nextLine());

			}
		} finally {
			scanner.close();
		}
		return data;
	}

	/**
	 * Append new lines to file, mostly use for creating
	 * 
	 * @param fileName
	 * @param data
	 * @throws IOException
	 */
	protected static void write(String fileName, List data) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName, true));

		try {
			for (int i = 0; i < data.size(); i++) {
				out.println((String) data.get(i));
			}
		} finally {
			out.close();
		}
	}

	/**
	 * Overwrite everything, mainly used for update/remove
	 * 
	 * @param fileName
	 * @param data
	 * @throws IOException
	 */
	protected static void writeB(String fileName, List data) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));

		try {
			for (int i = 0; i < data.size(); i++) {
				out.print((String) data.get(i));
			}
		} finally {
			out.close();
		}
	}

	private void removeLineFromFile(String file, String lineToRemove) {

		try {

			File inFile = new File(file);

			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}

			// Construct the new file that will later be renamed to the original
			// filename.
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			String line = null;

			// Read from the original file and write to the new
			// unless content matches data to be removed.
			while ((line = br.readLine()) != null) {

				if (!line.trim().equals(lineToRemove)) {

					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			br.close();

			// Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			// Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void replaceLineFromFile(String file, String lineToReplace,
			String lineToRefer) {

		try {

			File inFile = new File(file);

			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}

			// Construct the new file that will later be renamed to the original
			// filename.
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			String line = null;

			// Read from the original file and write to the new
			// unless content matches data to be removed.
			while ((line = br.readLine()) != null) {

				if (!line.trim().equals(lineToRefer)) {

					pw.println(line);
					pw.flush();
				} else {
					pw.println(lineToReplace);
					pw.flush();
				}
			}
			pw.close();
			br.close();

			// Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			// Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
