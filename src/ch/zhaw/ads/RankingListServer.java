package ch.zhaw.ads;

import java.util.*;
import java.util.stream.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;

public class RankingListServer implements CommandExecutor {
    
    public List<Competitor> createList(String rankingText) {
    	List <Competitor> list = new ArrayList();
        String [] competitors = rankingText.split("\n");
        for (int i = 0; i < competitors.length; ++i){
        	String c = competitors[i];
        	int splitIndex = c.indexOf(";");
        	Competitor competitor = new Competitor(0,c.substring(0,splitIndex),c.substring(splitIndex + 1));
        	list.add(competitor);
		}
        return list;
    }
    
    public String createSortedText(List<Competitor> competitorList) {
        competitorList.sort(Competitor::compareTo);
		for (int i = 0; i < competitorList.size(); ++i) {
			competitorList.get(i).setRank(i+1);
		}
		String text =  competitorList.toString();
        String textHelp1 = text.replaceAll(",", "\n");
        String textHelp2 = textHelp1.replaceAll("[\\[\\]]", "");
		return textHelp2;
    }
    
	public String execute(String rankingList) throws Exception {
		List<Competitor> competitorList = createList(rankingList);
        return "Rangliste (List)\n" + createSortedText(competitorList);
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
		RankingListServer server = new RankingListServer();
		System.out.println(server.execute(rangliste));
	}


}



