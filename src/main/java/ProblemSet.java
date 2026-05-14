/*
Lesson: Problem Set 5 (100%)
Author: Jim Li
Date Created: May 12, 2026
Date Last Modified: May 13, 2026
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class ProblemSet {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//welcome message and text input
		System.out.println("Welcome to the Text Analyzer.\n\nPlease enter a sentence or paragraph:\n");
		String text = input.nextLine();
		text = text.toLowerCase(); //no case sensitivity

		//***part 1: words, characters, vowels, and spaces***
		int characters = text.length();
		int vowels = 0, spaces = 0;
		for (int i = 0; i < text.length(); i++) { //space and vowel counter
			char ch = text.charAt(i);
			if (ch == ' ') {
				spaces++;
			}
			if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
				vowels++;
			}
		}
		String[] wordArray = text.split("[^a-zA-Z0-9]+"); //split everything
		int words = 0;
		for (int i = 0; i < wordArray.length; i++){ //word counter because deleting empty strings is too annoying
		    if (!wordArray[i].isEmpty()){
		        words++;
		    }
		}
		//output
		System.out.println("\nTotal Characters: " + characters);
		System.out.println("Total Words: " + words);
		System.out.println("Total Vowels: " + vowels);
		System.out.println("Total Spaces: " + spaces);

		//***part 2: word frequencies***
		//turn word array into an arraylist with unique words (no empty strings)
		ArrayList<String> list = wordList(wordArray);

		//assign occurences of each word to respective word
		HashMap<String, Integer> frequencies = wordFrequency(wordArray);

		//get sum of word length and amount of unique words (for later, before common words get removed)
		double totalLength = 0; //sum of word lengths
		for (int i = 0; i < list.size(); i++) {
			totalLength += list.get(i).length() * frequencies.get(list.get(i)); //since words are unique must multiply by occurences
		}
		int uniqueWords = list.size();

		//remove common words
		list.remove("a");
		list.remove("an");
		list.remove("and");
		list.remove("the");
		list.remove("is");

		//output
		System.out.println("\nWord Frequency:\n");
		if (list.isEmpty()) {
			System.out.println("no uncommon words");
		} else {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i) + " - " + frequencies.get(list.get(i)));
			}
		}

		//***part 3: advanced statistics***
		if (list.isEmpty()){ // in case you only put common words or nothing for some reason
		    list.add("");
		}
		int longest = 0, shortest = list.get(0).length(); //get length of longest and shortest words
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).length() < shortest) {
				shortest = list.get(i).length();
			}
			if (list.get(i).length() > longest) {
				longest = list.get(i).length();
			}
		}
		boolean firstWord = false; //prevents bad comma printing
		System.out.print("\nLongest Word(s): "); //print longest words
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).length() == longest) {
				if (firstWord) {
					System.out.print(", ");
				}
				System.out.print(list.get(i));
				firstWord = true;
			}
		}
		firstWord = false;
		System.out.print("\nShortest Word(s): "); //print shortest words
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).length() == shortest) {
				if (firstWord) {
					System.out.print(", ");
				}
				System.out.print(list.get(i));
				firstWord = true;
			}
		}

		//average word length
		System.out.println("\nAverage Word Length: " + totalLength / words);

		//number of sentences
		int sentences = 0;
		String[] sentenceArray = text.split("[.?!]");
		for (int i = 0; i < sentenceArray.length; i++) {
			if (!sentenceArray[i].isBlank()) {
				sentences++;
			}
		}
		System.out.println("Number of Sentences: " + sentences);

		//unique words
		System.out.println("Unique Words: " + uniqueWords);
	}

	//method makes arraylist of every unique word, also removes empty strings
	public static ArrayList<String> wordList(String[] wordArray) {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < wordArray.length; i++) {
			if (!list.contains(wordArray[i])) {
				list.add(wordArray[i]);
			}
		}
		//remove empty strings
		list.remove("");
		return list;
	}

	//method finds unique words and their occurences; returns as hashmap
	public static HashMap<String, Integer> wordFrequency(String[] wordArray) {
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < wordArray.length; i++) {
			if (!map.containsKey(wordArray[i])) {
				map.put(wordArray[i], 1);
			} else {
				map.put(wordArray[i], map.get(wordArray[i]) + 1);
			}
		}
		return map;
	}
}
