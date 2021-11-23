package hangman_package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class DictionaryFile {

	private static final String FILE_NAME_STATIC = "dictionary.txt";
	private static final String FILE_NAME_SERIALIZED = "dictionary.ser";
	private SinglyLinkedList<String> dictionaryList;
	private Dictionary dictionary;

	public DictionaryFile() {
		dictionary = new Dictionary();
	}// DictionaryFile()

	public boolean readDictionary() {
		boolean dictionaryCreated = false;

		if (deserializeDictionary()) {
			dictionaryCreated = true;
		} else if (readDictionaryFile()) {
			dictionaryCreated = true;
		}

		return dictionaryCreated;
	}// readDictionary()

	private boolean deserializeDictionary() {

		boolean dictionaryDeserialized;
		try {
			FileInputStream inStream = new FileInputStream(FILE_NAME_SERIALIZED);
			ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
			dictionary = (Dictionary) objectInputFile.readObject();
			dictionaryDeserialized = true;
			objectInputFile.close();
		} catch (FileNotFoundException e) {
			dictionaryDeserialized = false;
		} catch (IOException e) {
			dictionaryDeserialized = false;
		} catch (ClassNotFoundException e) {
			dictionaryDeserialized = false;
		}

		return dictionaryDeserialized;
	}// deserializeDictionary()

	private boolean readDictionaryFile() {
		File dictionaryFile = new File(FILE_NAME_STATIC);
		Scanner in = null;
		try {
			in = new Scanner(dictionaryFile);
			in.useDelimiter("\r\n");
			dictionaryList = new SinglyLinkedList<String>();
		} catch (FileNotFoundException e) {
			return false;
		}

		while (in.hasNext()) {
			String word = in.nextLine();
			if (!word.matches(".*\\d+.*"))
				dictionaryList.add(word);
		}

		in.close();

		if (dictionaryList.getLength() == 0) {
			return false;
		}

		dictionary.setDictionary(dictionaryList);
		return true;
	}// readDictionaryFile()

	public boolean saveDictionary(Dictionary dictionary) {

		boolean dictionarySerialized = false;

		try {
			FileOutputStream outStream = new FileOutputStream(FILE_NAME_SERIALIZED);
			ObjectOutputStream outputFile = new ObjectOutputStream(outStream);
			outputFile.writeObject(dictionary);
			dictionarySerialized = true;
			outputFile.close();
		} catch (FileNotFoundException e) {
			dictionarySerialized = false;
		} catch (IOException e) {
			dictionarySerialized = false;
		}

		return dictionarySerialized;
	}// saveDictionary(Dictionary)

	public Dictionary getDictionary() {
		return dictionary;
	}// getDictionary()

}// DictionaryFile()
