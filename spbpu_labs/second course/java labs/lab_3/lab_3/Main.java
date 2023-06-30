package lab_3;

import animals.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
	
	public static void segregate(Collection<? extends Mammals> srcCollection,
			Collection<? super CommonHedgehog> collection1, Collection<? super Manul> collection2,
			Collection<? super Lynx> collection3) {
		for (Mammals animal : srcCollection) {
			if (animal instanceof CommonHedgehog) {
				collection1.add((CommonHedgehog) animal);
			} else if (animal instanceof Manul) {
				collection2.add((Manul) animal);
			} else if (animal instanceof Lynx) {
				collection3.add((Lynx) animal);
			}
		}
	}
	
	public static void main(String[] args) {
	
		
		
		List<Mammals> srcColl1 = new ArrayList<>();
		srcColl1.add(new Manul("Alice", 5, 12.4));
		srcColl1.add(new CommonHedgehog("Mark", 1, 0.8));
		srcColl1.add(new Lynx("Sam", 8, 7.8));
		srcColl1.add(new CommonHedgehog("Jhon", 3, 1.5));
		srcColl1.add(new Lynx("Mary", 4, 6.9));
		
		List<Hedgehogs> coll1 = new ArrayList<>();
		List<Feline> coll2 = new ArrayList<>();
		List<Predatory> coll3 = new ArrayList<>();
		
		segregate(srcColl1, coll1, coll2, coll3);
		
		System.out.println("Collection 1:");
		for(Hedgehogs animal : coll1) animal.print();
		System.out.println("Collection 2:");
		for(Feline animal : coll2) animal.print();
		System.out.println("Collection 3:");
		for(Predatory animal : coll3) animal.print();
		
		System.out.println("----------------------------------------------------------------");
		List<Predatory> srcColl2 = new ArrayList<>();
		srcColl2.add(new Manul("Alice", 5, 12.4));
		srcColl2.add(new Lynx("Sam", 8, 7.8));
		srcColl2.add(new Lynx("Mary", 4, 6.9));
		
		List<Chordates> coll1_2 = new ArrayList<>();
		List<Manul> coll2_2 = new ArrayList<>();
		List<Feline> coll3_2 = new ArrayList<>();
		
		segregate(srcColl2, coll1_2, coll2_2, coll3_2);
		
		System.out.println("Collection 1:");
		for(Chordates animal : coll1_2) animal.print();
		System.out.println("Collection 2:");
		for(Manul animal : coll2_2) animal.print();
		System.out.println("Collection 3:");
		for(Feline animal : coll3_2) animal.print();
		
		System.out.println("----------------------------------------------------------------");
		List<Hedgehogs> srcColl3 = new ArrayList<>();
		srcColl3.add(new CommonHedgehog("Mark", 1, 0.8));
		srcColl3.add(new CommonHedgehog("Jhon", 3, 1.5));
		
		List<Insectivores> coll1_3 = new ArrayList<>();
		List<Predatory> coll2_3 = new ArrayList<>();
		List<Predatory> coll3_3 = new ArrayList<>();
		
		segregate(srcColl3, coll1_3, coll2_3, coll3_3);
		
		System.out.println("Collection 1:");
		for(Insectivores animal : coll1_3) animal.print();
		System.out.println("Collection 2:");
		for(Predatory animal : coll2_3) animal.print();
		System.out.println("Collection 3:");
		for(Predatory animal : coll3_3) animal.print();
	}
}
