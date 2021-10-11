package ch.zhaw.ads;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;

public class RankingStreamServer implements CommandExecutor {


    public Stream<Competitor> createStream(String rankingText) {
    	Function<String,Competitor> createCompetitor = t -> new Competitor(0,t.substring(0,t.indexOf(";")), t.substring(t.indexOf(";")+ 1));
		return Arrays.stream(rankingText.split("\n"))
				.map(t -> createCompetitor.apply(t));
    }

    public String createSortedText(Stream<Competitor> competitorStream) {
		List <Competitor> sortedCompetitors =
				competitorStream.sorted(Competitor::compareTo)
				.collect(Collectors.toList());

		IntStream.range(1,sortedCompetitors.size()+1)
				.forEach(i -> sortedCompetitors.get(i - 1).setRank(i));

		String text = sortedCompetitors.toString();
		String textHelp1 = text.replaceAll(",", "\n");
		String textHelp2 = textHelp1.replaceAll("[\\[\\]]", "");
		return textHelp2;
    }

    public String execute(String rankingList) throws Exception { 
 		Stream<Competitor> competitorStream = createStream(rankingList);
        return "Rangliste (Stream)\n" + createSortedText(competitorStream); 
    }
	
	public static void main(String[] args) throws Exception {
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
		RankingStreamServer server = new RankingStreamServer();
		System.out.println(server.execute(rangliste));
	}
}
