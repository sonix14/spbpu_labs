package lab_4;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import exceptions.FileReadException;
import exceptions.InvalidFileFormatException;

public class Translator {
	private Dictionary dictionary;
	
	public Translator(File fileName) {
		try {
			setDictionary(fileName);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			dictionary = null;
		}
	}
	
	private void setDictionary(File fileName) throws FileReadException, InvalidFileFormatException {
		dictionary = new Dictionary(fileName);
	}

	public void translateText(File fileName) {
		if (dictionary == null) {
			System.out.println("The dictionary is empty!");
			return;
		}
		try {
			ArrayList<String> text = new ArrayList<String>();
			transformFile(fileName, text);
			
			for (int i = 0; i < text.size(); i++) {
				String word = text.get(i);
				Map<ArrayList<String>, String> tmpDict = new HashMap<ArrayList<String>, String>();
				
				for(Map.Entry<ArrayList<String>, String> entry: dictionary.entrySet()) {
					if(word.equals(entry.getKey().get(0))) {
						if(entry.getKey().size() == 1) {
							tmpDict.put(entry.getKey(), entry.getValue());
						} else {
							int index = i;
							int count = 1;
							for (int j = 1; j < entry.getKey().size(); j++) {
								if (index + 1 == text.size()) {
									break;
								}
								index++;
								word = text.get(index);
								if (word.equals(entry.getKey().get(j))) {
									count++;
								} else {
									break;
								}
							}
							if (count == entry.getKey().size()) {
								tmpDict.put(entry.getKey(), entry.getValue());
							}
							word = text.get(i);
						}
					}
				}
				
				if(tmpDict.size() == 0) {
					System.out.println(text.get(i));
				} else if (tmpDict.size() == 1) {
					for(Map.Entry<ArrayList<String>, String> tmpEntry: tmpDict.entrySet()) {
						System.out.println(tmpEntry.getKey().toString() + " | " + tmpEntry.getValue());
					}
				} else { 
					int size = 0;
					for(Map.Entry<ArrayList<String>, String> tmpEntry: tmpDict.entrySet()) {
						if (tmpEntry.getKey().size() > size) {
							size = tmpEntry.getKey().size();
						}
					}
					i = i + size - 1;
					for(Map.Entry<ArrayList<String>, String> tmpEntry: tmpDict.entrySet()) {
						if (tmpEntry.getKey().size() == size) {
							System.out.println(tmpEntry.getKey().toString() + " | " + tmpEntry.getValue());
						}
					}
				}
			}
		} catch (FileReadException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
	
	private void transformFile(File fileName, ArrayList<String> array) throws FileReadException {
		try(FileReader reader = new FileReader(fileName)) {
			Scanner in = new Scanner(reader);
			while(in.hasNext()) {
				String str = in.next();
				str.toLowerCase();
				array.add(str);
			}
			in.close();
		} catch (IOException e) {
	        throw new FileReadException(e.getMessage());
		}
	}
	
	public void printDictionary() {
		dictionary.print();
	}
}
