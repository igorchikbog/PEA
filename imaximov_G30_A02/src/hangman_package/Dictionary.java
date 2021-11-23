package hangman_package;

import java.io.Serializable;

public class Dictionary implements Serializable {

	private static final long serialVersionUID = 1L;
	private SinglyLinkedList<String> dictionary;
	private transient DictionaryFile file;

	public Dictionary() {
		dictionary = null;
	}// Dictionary()

	public SinglyLinkedList<String> getDictionary() {
		return dictionary;
	}// getDictionary()

	public void setDictionary(SinglyLinkedList<String> dictionary) {
		this.dictionary = dictionary;
	}// setDictionary(SinglyLinkedList<String>)

	public boolean initializeDictionary() {
		boolean dictionaryInitialized = false;
		file = new DictionaryFile();
		if (file.readDictionary()) {
			dictionary = file.getDictionary().getDictionary();
			dictionaryInitialized = true;
		}

		return dictionaryInitialized;
	}// initializeDictionary()

	public boolean saveDictionary() {
		return file.saveDictionary(this);
	}// saveDictionary()

	public String getRandomWord() {
		String word = null;
		if (dictionary.getLength() >= 1) {
			int randNum = (int) (Math.random() * dictionary.getLength() - 1);
			word = dictionary.getElementAt(randNum);
			dictionary.remove(randNum);
		}
		return word;
	}// getRandomWord()

	public String toString() {
		String dictionaryString = "";

		for (int i = 0; i < dictionary.getLength(); i++) {
			dictionaryString += dictionary.getElementAt(i);
			dictionaryString += "\n";
		}

		return dictionaryString;
	}// toString()

}// Dictionary class
