// import java.util.Vector;
// import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private double[] runs;
//  private Vector<String> indices = new Vector<String>();
//  private Queue<String> erased = new Queue<String>();

  public PercolationStats(int n, int trials) {

    if ((n <= 0) || (trials <= 0))
      throw new IllegalArgumentException();
    else {
      this.runs = new double[trials];
      Percolation perc;
      
      while (trials > 0) {
        perc = new Percolation(n);
        while (!perc.percolates())
          perc.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));
        this.runs[trials-1] = (double) perc.numberOfOpenSites()/(n*n);
        trials--;
      }

// THIS CODE ASSURES THAT A COORDINATE IS ONLY PICKED ONCE
//      for (int i = 1; i <= n; i++)
//        for (int j = 1; j <= n; j++)
//          this.indices.addElement(i+","+j);
//      while (trials > 0) {
//        int randomInt;
//        while (!perc.percolates()) {
//          randomInt = StdRandom.uniform(this.indices.size());
//          String index = this.indices.get(randomInt);
//          erased.enqueue(this.indices.get(randomInt));
//          this.indices.remove(randomInt);
//          String[] coordinates = index.split(",");
//          perc.open(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
//        } 
//        this.runs[trials-1] = (double) perc.numberOfOpenSites()/(n*n);
//        while (!erased.isEmpty())
//          this.indices.addElement(erased.dequeue());
//        trials--;
//      }

    }
  }

  public double mean() { return StdStats.mean(this.runs); } 
  public double stddev() { return StdStats.stddev(this.runs); } 
  public double confidenceLo() { return StdStats.mean(this.runs)-((1.96*stddev())/Math.sqrt(this.runs.length)); } 
  public double confidenceHi() { return StdStats.mean(this.runs)+((1.96*stddev())/Math.sqrt(this.runs.length)); } 

  public static void main(String[] args) {

    PercolationStats percStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
//    PercolationStats percStats = new PercolationStats(100, 1000);
    System.out.println("Mean: " + percStats.mean());
    System.out.println("Sample Standard Deviation: " + percStats.stddev());
    System.out.println("95% Confidence Interval: [" + percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");
  }
}