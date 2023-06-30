package lab_5;
import streamMethods.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		List<Integer> list1 = new ArrayList<>();
		list1.add(5);
		list1.add(3);
		list1.add(8);
		list1.add(2);
		double averagevalue = StreamMethods.getAverageValue(list1);
		System.out.println("getAverageValue method: " + averagevalue);
		
		List<String> list2 = new ArrayList<>();
		list2.add("apple");
		list2.add("go home");
		list2.add("honey");
		list2 = StreamMethods.makeListUppercase(list2);
		System.out.println("makeListUppercase method: " + list2);
		
		List<Integer> list3 = new ArrayList<>();
		list3.add(23);
		list3.add(45);
		list3.add(13);
		list3.add(45);
		list3 = StreamMethods.getListOfSquares(list3);
		System.out.println("getListOfSquares method: " + list3);
		
		List<String> list4 = new ArrayList<>();
		list4.add("apple pie");
		list4.add("amaizing animals");
		list4.add("brown hair");
		list4 = StreamMethods.getStringsStartingWithLetter(list4, 'a');
		System.out.println("getStringsStartingWithLetter method: " + list4);
		
		List<Integer> list5 = new ArrayList<>();
		list5.add(5);
		list5.add(3);
		list5.add(8);
		list5.add(2);
		try {
			int num = StreamMethods.getLastElement(list5);
			System.out.println("getLastElement method: " + num);
		} catch (EmptyCollectionException e) {
			e.printStackTrace();
		}
		
		int array1[] = new int[] {1, 2, 3, 4};
		int sum = StreamMethods.getSumOfEvenElements(array1);
		System.out.println("getSumOfEvenElements method: " + sum);
		
		List<String> list6 = new ArrayList<>();
		list6.add("g go went going gone");
		list6.add("d drink drank drunk drinking");
		list6.add("b bite bitting bit bitten");
		Map<Character, List<String>> map1 = new HashMap<>();
		map1 = StreamMethods.makeMapFromList(list6);
		System.out.println("makeMapFromList method: " + map1);
	}
}
