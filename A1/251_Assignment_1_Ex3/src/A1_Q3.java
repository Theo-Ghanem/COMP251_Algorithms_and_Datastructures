import java.util.*;

public class A1_Q3 {

	public static ArrayList<String> Discussion_Board(String[] post){

		HashMap<String, Integer> wordCounter = new HashMap<>();  //hashmap to associate a word and it's counter

		for (String sentence : post) {        //iterate through the different sentences of the post
			String[] words = sentence.split(" ");    //split each sentence into separate words
			words[0] = null;                            //remove the first element which is a Name
			for (String word : words) {                //iterate through the words in the sentence
				wordCounter.merge(word, 1, Integer::sum);    //if the key doesn't exist, add it, if it exists increment it
			}
		}
		HashMap<String, Set<String>> namesByWord = new HashMap<>(); 	//hashmap that associates a word with which person uses that word
		ArrayList<String> wordsInCommon = new ArrayList<>(); 			//array that lists all the words that are used by everyone
		LinkedHashSet<String> listOfNames = new LinkedHashSet<>();		//list of all the names
		for (String sentence : post) {        							//iterate through the different sentences of the post
			String[] words = sentence.split(" "); 				//split each sentence into separate words
			String name = words[0];    									//set the name to be the first word of the sentence
			listOfNames.add(name);    									//add the name to the list of names
			for (int idx = 1; idx < words.length; idx++) { 				//iterate through the words of the sentence starting after the name
				String word = words[idx];								//get all the words of the sentence one by one
				LinkedHashSet<String> set = new LinkedHashSet<>();
				if (namesByWord.containsKey(word)) {	// if the word has already appeared,
					set = (LinkedHashSet<String>) namesByWord.get(word); // we'll just grab the set to add the value to it
				}
				set.add(name);					//add the name to the set since this person uses that word
				namesByWord.put(word, set);		//the key is the word and the value is a set of the names that use that word
			}
		}
		for (String word : namesByWord.keySet()) {						//iterate through all the words in the post
			if(namesByWord.get(word).size()==listOfNames.size()){		//if a word is used by all the people
				wordsInCommon.add(word);									//then it is added to a list of words in common
			}
		}

		//Hashmap created to store the words in common and their counters
		TreeMap<String,Integer> commonWordsTreeMap = new TreeMap<String, Integer>();
		for(String word: wordsInCommon){								//iterate through the words in common
			commonWordsTreeMap.put(word, wordCounter.get(word));
		}
		Map sortedMap = sortByValues(commonWordsTreeMap);
		return new ArrayList<String>(sortedMap.keySet());
	}
	//Method for sorting the TreeMap based on values
	public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
		Comparator<K> valueComparator = new Comparator<K>() {
					public int compare(K k1, K k2) {
						int compareValue = map.get(k1).compareTo(map.get(k2));
						String myStr1 = (String) k1;
						String myStr2 = (String) k2;
						int compareKey = myStr1.compareTo(myStr2);
						if (compareValue == 0) {	//if the values (the counters) are the same
								return compareKey;	//then we compare the keys (words lexicographically)
						}
						else
							return -compareValue;	//since we want to reverse the sorting we put the "-"
					}
				};

		Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
		sortedByValues.putAll(map);
		return sortedByValues;
	}
}