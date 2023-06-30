package lab_4;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import exceptions.*;

public class Dictionary {

	public Map<ArrayList<String>, String> dictionary;
	
	public Dictionary() {
		dictionary = new HashMap<ArrayList<String>, String>();
	}
	
	public Dictionary(File fileName) throws FileReadException, InvalidFileFormatException {
		dictionary = new HashMap<ArrayList<String>, String>();
		try(FileReader reader = new FileReader(fileName)) {
			Scanner in = new Scanner(reader);
			while(in.hasNextLine()) {
				String str = in.nextLine();
				str.toLowerCase();
				String[] line = new String[2];
				int ind = str.indexOf(" | ");
				if (ind == -1) {
					in.close();
					throw new InvalidFileFormatException();
				}
				line[0] = str.substring(0, ind);
				line[1] = str.substring(ind + 2);
				ArrayList<String> words = new ArrayList<String>();
				
				while(!line[0].isEmpty()) {
					line[0] = line[0].trim();
					int space = line[0].indexOf(' ');
					if (space != -1) {
						words.add(line[0].substring(0, space));
						line[0] = line[0].replace(line[0].substring(0, space), "");
					} else {
						words.add(line[0]);
						break;
					}
				}
				
				dictionary.put(words, line[1].trim());
			}
			in.close();
		} catch (IOException e) {
	        throw new FileReadException(e.getMessage());
		}
	}
	
	public Set<Map.Entry<ArrayList<String>, String>> entrySet(){
		return dictionary.entrySet();
	}
	
	public void print() {
        dictionary.forEach((word, translation) -> System.out.println(word + " | " + translation));
    }
}
