package ch.zhaw.ads;

import java.util.*;
import java.text.*;

public class FuzzySearchServer implements CommandExecutor {
	public static List<String> names = new ArrayList<>(); // List of all names
	public static Map<String, List<Integer>> trigrams = new HashMap<>(); // List of all Trigrams
	public static Map<Integer,Integer> counts = new HashMap<>(); // Key: index of 

	// load all names into names List
	// each name only once (i.e. no doublettes allowed
	public static void loadNames(String nameString) {
		names.clear();
		Set<String> namesSet = new LinkedHashSet<>();
		for (String line : nameString.split("\n")) {
			String[] splitted = line.split(";");
			namesSet.add(splitted[0]);
		}
		names.addAll(namesSet);
	}

	// add a single trigram to 'trigrams' index
	public static void addToTrigrams(int nameIdx, String trig) {
		List<Integer> trigram;
		if (trigrams.containsKey(trig)) {
			trigram = trigrams.get(trig);
		} else {
			trigram = new LinkedList<Integer>();
			trigrams.put(trig, trigram);
		}
		trigram.add(nameIdx);
	}

	// works better for flipped and short names if " " added and lowercase
	private static String nomalize(String name) {
		return " "+name.toLowerCase().trim()+" ";
	}

	// construct a list of trigrams for a name
	public static List<String> trigramForName(String name) {
		List<String> trigrams = new ArrayList<>();
		name = nomalize(name);
		for (int i = 0; i < name.length() - 3 + 1; i++) {
			// Add the substring
			trigrams.add(name.substring(i, i + 3));
		}
		return trigrams;
	}

	public static void constructTrigramIndex(List<String> names) {
		for (int nameIdx = 0; nameIdx < names.size(); nameIdx++) {
			List<String> trigs = trigramForName(names.get(nameIdx));
			for (String trig : trigs) {
				addToTrigrams(nameIdx,trig);
			}
		}
	}

	private static void incCount(int cntIdx) {
		Integer c = counts.get(cntIdx);
		c = (c == null)?  1 : c + 1;
		counts.put(cntIdx,c);
	}

	// find name index with most corresponding trigrams
	// if no trigram/name matches at all then return -1
	public static int findIdx(String name) {
		counts.clear();

		for (String s : trigramForName(name)) {
			if (trigrams.containsKey(s)) {
				for (int trig : trigrams.get(s)) {
					incCount(trig);
				}
			}
		}
		int maxIdx = -1;
		int maxVal = 0;
		for (int count : counts.keySet()) {
			if (counts.get(count) > maxVal) {
				maxIdx = count;
				maxVal = counts.get(count);
			}
		}
		return maxIdx;
	}
	// finde Namen gebe "" zur?ck wenn gefundener Name nicht gr?sser als verlangter score ist.
	public static String find(String searchName, int scoreRequired) {
		int found = findIdx(searchName);
		String foundName = "";
		if (found >= 0 && score(found) >= scoreRequired) {
			foundName = names.get(found);
		}
		return foundName;
	}

	private static int score(int found) {
		String foundName = names.get(found);
		int score = (int)(100.0*Math.min(counts.get(found),foundName.length())/(foundName.length()));
		return score;
	}

	public String execute(String searchName)  {
		int found = findIdx(searchName);
		if (found >= 0) {
			int score = score(found);
			String foundName = names.get(found);
			return searchName + " -> " + foundName + " "+ score + "%\n";
		} else {
			return "nothing found\n";
		}
	}

	public static void main(String[] args)  {
		FuzzySearchServer fs = new FuzzySearchServer();
		System.out.println(fs.execute("Kiptum Daniel"));
		System.out.println(fs.execute("Daniel Kiptum"));
		System.out.println(fs.execute("Kip Dan"));
		System.out.println(fs.execute("Dan Kip"));
	}

	static {
		String rangliste =
				"Mueller Stefan;02:31:14\n"+
						"Marti Adrian;02:30:09\n"+
						"Kiptum Daniel;02:11:31\n"+
						"Ancay Tarcis;02:20:02\n"+
						"Kreibuhl Christian;02:21:47\n"+
						"Ott Michael;02:33:48\n"+
						"Menzi Christoph;02:27:26\n"+
						"Oliver Ruben;02:32:12\n"+
						"Elmer Beat;02:33:53\n"+
						"Kuehni Martin;02:33:36\n";
		loadNames(rangliste);
		constructTrigramIndex(names);
	}
}
