/*
Lesson: Problem Set 5 (100%)
Author: Jim Li
Date Created: May 12, 2026
Date Last Modified: May 12, 2026
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
		text = text.toLowerCase();
		
		//sentence count to be used later; done now before punctuation gets removed
		int sentences = 0;
		for (int i = 0; i < text.length(); i++){
		    if (text.charAt(i) == '.' || text.charAt(i) == '!' || text.charAt(i) == '?'){
		        sentences++;
		    }
		}

		//part 1: words, characters, vowels, and spaces
		int characters = text.length();
		int words = text.split(" ").length;
		int vowels = 0, spaces = 0;
		for (int i = 0; i < text.length(); i++) { //goes through each char and counts
			char ch = text.charAt(i);
			if (ch == ' ') {
				spaces++;
			}
			if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
				vowels++;
			}
		}
		//output
		System.out.println("\nTotal Characters: " + characters);
		System.out.println("Total Words: " + words);
		System.out.println("Total Vowels: " + vowels);
		System.out.println("Total Spaces: " + spaces);


		//part 2: word frequencies
		//remove punctuation
		text = text.replace(".", "").replace("?", "").replace("!", "");
		text = text.replace(",", "").replace(";", "").replace(":", "");

		//turn the text into an array
		String[] wordArray = text.split(" ");

		//use method to turn the array into an arraylist
		ArrayList<String> list = wordList(wordArray);

		//use method to turn the array into a hashmap: <word(key), occurences>
		HashMap<String, Integer> frequencies = wordFrequency(wordArray);

		//output
		System.out.println("\nWord Frequency:\n");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i) + " - " + frequencies.get(list.get(i)));
		}


		//part 3: advanced statistics
		//longest words and shortest words
		int longest = 0, shortest = list.get(0).length(); //get length of longest and shortest words
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).length() < shortest) {
				shortest = list.get(i).length();
			}
			if (list.get(i).length() > longest) {
				longest = list.get(i).length();
			}
		}
		boolean firstWord = false;
		System.out.print("\nLongest Word(s): "); //print longest words
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).length() == longest) {
			    if (firstWord){
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
			    if (firstWord){
			        System.out.print(", ");
			    }
				System.out.print(list.get(i));
				firstWord = true;
			}
		}
		
		//average word length *ASK IF INCLUDES COMMON WORDS*
		double totalLength = 0; //word lengths combined
		int actualWords = 0; //amount of not common words
		for (int i = 0; i < list.size(); i++){
		    totalLength += list.get(i).length() * frequencies.get(list.get(i)); //since words are unique must multiply by occurences
		    actualWords += frequencies.get(list.get(i)); //sum up actual amount of words
		}
		System.out.println("\nAverage Word Length: " + totalLength / actualWords);
		
		//number of sentences
		System.out.println("Number of Sentences: " + sentences);
		
		//unique words
		System.out.println("Unique Words: " + list.size());
	}

	//method makes arraylist of every unique word, also removes common words and empty strings
	public static ArrayList<String> wordList(String[] wordArray) {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < wordArray.length; i++) {
			if (!list.contains(wordArray[i])) {
				list.add(wordArray[i]);
			}
		}
		//remove common words and empty strings
		list.remove("");
		list.remove("the");
		list.remove("a");
		list.remove("an");
		list.remove("and");
		list.remove("is");
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
		map.remove("");
		return map;
	}
}
