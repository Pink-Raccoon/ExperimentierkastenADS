package ch.zhaw.ads;

import java.util.*;
import java.text.*;

public class MyCompetitor implements Comparable<MyCompetitor> {
    private String competitorName;
    private String competitorTime;
    private int competitorRank;

    public MyCompetitor(int rank, String name, String time) {
        this.competitorRank = rank;
        this.competitorName = name;
        this.competitorTime = time;
    }

    public void setCompetitorRank(int competitorRank) {
        this.competitorRank = competitorRank;
    }

    public String getCompetitorTime() {
        return competitorTime;
    }

    public String getCompetitorName() {
        return competitorName;
    }

    private static long parseTime(String s) {
        try {
            DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date date = sdf.parse(s);
            return date.getTime();
        } catch (Exception e) {
            System.err.println(e);
        }
        return 0;
    }

    private static String timeToString(int time) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(new Date(time));
    }

    public String toString() {
        return "" + competitorRank + " " + competitorName + " " + competitorTime;
    }

    @Override
    public int compareTo(MyCompetitor competitor) {
        if (!this.competitorName.equals(competitor.competitorName)) {
            return this.competitorName.compareTo(competitor.competitorName);
        } else {
            return this.competitorRank - competitor.competitorRank;
        }
    }

    @Override
    public boolean equals(Object competitor) {
        return competitor instanceof MyCompetitor
                && competitorRank == ((MyCompetitor) competitor).competitorRank
                && competitorName.equals(((MyCompetitor) competitor).competitorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competitorRank, competitorName);
    }
}
