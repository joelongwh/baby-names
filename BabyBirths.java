
/**
 * Write a description of class BabyBirths here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths
{
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int noOfGirlsNames = 0;
        int noOfBoysNames = 0;
        int totalNoOfNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNoOfNames ++;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                noOfBoysNames ++;
            }
            else {
                totalGirls += numBorn;
                noOfGirlsNames ++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total no. of names = " + totalNoOfNames);
        System.out.println("total no. of girls names = " + noOfGirlsNames);
        System.out.println("total no. of boys names = " + noOfBoysNames);        
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender) {
        String file = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(file);
        int get = 0;
        int rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank++;
                if (rec.get(0).equals(name)) {
                    get = 1;
                    break;
                }
            }
        }
        if (get == 1) {
            return rank;
        }
        else {
            return -1;
        }
    }
    
    public void testGetRank() {
        int rankResult = getRank(1971, "Frank", "M");
        System.out.println(rankResult);
    }
    
    public String getName(int year, int rank, String gender) {
        String file = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(file);
        String name = null;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                    rank -= 1;
            }
            if (rank == 0) {
                name = rec.get(0);
            }
        }
        if (name == null) {
            return "NO NAME";
        }
        else {
            return name;
        }
    }
    
    public void testGetName() {
        String nameResult = getName(1982, 450, "M");
        System.out.println(nameResult);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 0;
        int highestRankYear = 0;
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            String yearString = fileName.substring(3,7);
            int year = Integer.parseInt(yearString);
            int rank = getRank(year, name, gender);
            
            if (rank == -1) {
                continue;
            }
            
            if (highestRank == 0) {
                highestRank = rank;
                highestRankYear = year;
            }
            
            if (rank < highestRank) {
                highestRank = rank;
                highestRankYear = year;
            }
        }
        
        if (highestRankYear == 0) {
            return -1;
        }
        else {
            return highestRankYear;
        }
    }
    
    public void testYearOfHighestRank() {
        int yearOfHighestRank = yearOfHighestRank("Mich", "M");
        System.out.println(yearOfHighestRank);
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double noOfFiles = 0.0;
        double totalRank = 0.0;
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            String yearString = fileName.substring(3,7);
            int year = Integer.parseInt(yearString);
            int rank = getRank(year, name, gender);
            
            if (rank == -1) {
                continue;
            }
            
            noOfFiles ++;
            totalRank += rank;
        }
        
        if (totalRank == 0.0) {
            return -1.0;
        }
        else {
            return totalRank / noOfFiles;
        }
    }
    
    public void testGetAverageRank() {
        double AverageRank = getAverageRank("Robert", "M");
        System.out.println(AverageRank);
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String file = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(file);
        int totalBirths = 0;
        int overCount = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (rec.get(1).equals(gender)) {
                totalBirths += numBorn;
                if (rec.get(0).equals(name)) {
                    overCount = Integer.parseInt(rec.get(2));
                    break;
                }
            }
        }
        return totalBirths - overCount;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        int totalBirthsRankedHigher = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println(totalBirthsRankedHigher);
    }
}
