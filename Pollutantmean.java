/*
 * pollutantmean
 *
 * @author Xarmanla
 *
 * @date January 10, 2015
 *
 */
import java.io.*;
import java.util.Scanner;

public class Pollutantmean
{
  private double sulfate;
  private double nitrate;
  private double sulfateCount;
  private double nitrateCount;

  public Pollutantmean()
  {
    sulfate = 0;
    nitrate = 0;
    sulfateCount = 0;
    nitrateCount = 0;
  }

  public double computeMeanSulfate()
  {
   return sulfate/sulfateCount;
  }

  private double computeMeanNitrate()
  {
   return nitrate/nitrateCount;
  }

  private void processFile(Scanner sc)
  {
   String lineOfInput = sc.nextLine();
   //System.out.println(lineOfInput); // header
   while (sc.hasNext())
   {
    lineOfInput = sc.nextLine();
    // parse the values
    // add to sulfate if not NA and increment countSulfate
    // add to nitrate if not NA and increment countNitrate
    String values[] = lineOfInput.split(",");
    if (!values[1].equals("NA") && !values[1].equals("NaN"))
    {
     double theSulfate = Double.parseDouble(values[1]);
     sulfate += theSulfate;
     sulfateCount += 1.0;
     //System.out.println(theSulfate);
    }
    if (values[2].equals("NULL") || values[1].equals("NULL")) 
     System.out.println(values[1] + " " + values[2]);
    if (!values[2].equals("NA") && !values[1].equals("NaN"))
    {
     double theNitrate = Double.parseDouble(values[2]);
     nitrate += theNitrate;
     nitrateCount += 1.0;
     //System.out.println(theNitrate);
    }
   } 
  }

  public static void main(String[] args) throws IOException
  {
   if (args.length < 1)
   {
    System.out.println("Usage:java Pollutantmean dirName id");
    System.exit(0);
   }
   String dirName = args[0];
   String id = args[1];
   // parse id for start:end
   String parts[] = id.split(":");
   int start = Integer.parseInt(parts[0]);
   int end = Integer.parseInt(parts[1]);

   Pollutantmean pm = new Pollutantmean();
   for (int i=start; i<=end; i++)
   {
    String fileName = String.format("%03d.csv", i);
    fileName = dirName + "/" + fileName;
    System.out.println(fileName);
    Scanner sc = new Scanner(new File(fileName));
    pm.processFile(sc);
    System.out.println("Nitrate " + pm.computeMeanNitrate());
    System.out.println("Sulfate " + pm.computeMeanSulfate());
  }
 } 
}
