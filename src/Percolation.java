import java.io.BufferedReader;
import java.io.FileReader;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private boolean[][] grid;
  private WeightedQuickUnionUF uf;
  private WeightedQuickUnionUF isFullUF;
  private int openSites = 0;

  public Percolation(int n) {
    if (n <= 0)
      throw new IllegalArgumentException();
    else {
      this.uf = new WeightedQuickUnionUF((n*n) + 2);
      this.isFullUF = new WeightedQuickUnionUF((n*n)+1);
      this.grid = new boolean[n][n];
      for (int row = 0; row < n; row++)
        for (int col = 0; col < n; col++)
          this.grid[row][col] = false;
      }
    }

  public void open(int row, int col) {
    try {
      row = row-1;
      col = col-1;

      if (this.grid[row][col])
        return;

      this.grid[row][col] = true;
      this.openSites++;
      
      int n = this.grid.length;

      int index = ((row*n) + col)+1;
      
      if (row == 0 && row == n-1) {
        this.uf.union(index, 0);
        this.isFullUF.union(index, 0);
        this.uf.union(index, (n*n)+1);
        return;
      }

      if (row == 0) {
        this.uf.union(index, 0);
        this.isFullUF.union(index, 0);
        if (col == 0) {
          if (this.grid[row+1][col]) {
            this.uf.union(index, index+n);
            this.isFullUF.union(index, index+n);
          }
          if (this.grid[row][col+1]) {
            this.uf.union(index, index+1);
            this.isFullUF.union(index, index+1);
          }
          return;
        } 
        if (col == n-1) {
          if (this.grid[row+1][col]) {
            this.uf.union(index, index+n);
            this.isFullUF.union(index, index+n);
          }
          if (this.grid[row][col-1]) {
            this.uf.union(index, index-1);
            this.isFullUF.union(index, index-1);
          }
          return;
        } 
        if (this.grid[row][col-1]) {
          this.uf.union(index, index-1);
          this.isFullUF.union(index, index-1);
        }
        if (this.grid[row+1][col]) {
          this.uf.union(index, index+n);
          this.isFullUF.union(index, index+n);
        }
        if (this.grid[row][col+1]) {
          this.uf.union(index, index+1);
          this.isFullUF.union(index, index+1);
        }
        return;
      } 
      if (row == n-1) {
        this.uf.union(index, (n*n)+1);
        if (col == 0) { 
          if (this.grid[row-1][col]) {
            this.uf.union(index, index-n);
            this.isFullUF.union(index, index-n);
          }
          if (this.grid[row][col+1]) {
            this.uf.union(index, index+1);
            this.isFullUF.union(index, index+1);
          }
          return;
        } 
        if (col == n-1) {
          if (this.grid[row-1][col]) {
            this.uf.union(index, index-n);
            this.isFullUF.union(index, index-n);
          }
          if (this.grid[row][col-1]) {
            this.uf.union(index, index-1);
            this.isFullUF.union(index, index-1);
          }
          return;
        } 
        if (this.grid[row][col-1]) {
          this.uf.union(index, index-1);
          this.isFullUF.union(index, index-1);
        }
        if (this.grid[row-1][col]) {
          this.uf.union(index, index-n);
          this.isFullUF.union(index, index-n);
        }
        if (this.grid[row][col+1]) {
          this.uf.union(index, index+1);
          this.isFullUF.union(index, index+1);
        }
        return;
      } 
      if (col == 0) {
        if (this.grid[row-1][col]) {
          this.uf.union(index, index-n);
          this.isFullUF.union(index, index-n);
        }
        if (this.grid[row][col+1]) {
          this.uf.union(index, index+1);
          this.isFullUF.union(index, index+1);
        }
        if (this.grid[row+1][col]) {
          this.uf.union(index, index+n);
          this.isFullUF.union(index, index+n);
        }
        return;
      } 
      if (col == n-1) {
        if (this.grid[row-1][col]) {
          this.uf.union(index, index-n);
          this.isFullUF.union(index, index-n);
        }
        if (this.grid[row][col-1]) {
          this.uf.union(index, index-1);
          this.isFullUF.union(index, index-1);
        }
        if (this.grid[row+1][col]) {
          this.uf.union(index, index+n);
          this.isFullUF.union(index, index+n);
        }
        return;
      } 
      if (this.grid[row-1][col]) {
        this.uf.union(index, index-n);
        this.isFullUF.union(index, index-n);
      }
      if (this.grid[row][col-1]) {
        this.uf.union(index, index-1);
        this.isFullUF.union(index, index-1);
      }
      if (this.grid[row+1][col]) {
        this.uf.union(index, index+n);
        this.isFullUF.union(index, index+n);
      }
      if (this.grid[row][col+1]) {
        this.uf.union(index, index+1);
        this.isFullUF.union(index, index+1);
      }
    } catch (IndexOutOfBoundsException e) { throw new IndexOutOfBoundsException(); } 
  } 

  public boolean isOpen(int row, int col) {
    try {
      return this.grid[row-1][col-1];
    } catch (IndexOutOfBoundsException e) { throw new IndexOutOfBoundsException(); } 
  }

  public boolean isFull(int row, int col) {
    try {
      row = row-1;
      col = col-1;
      int index = ((row*this.grid.length) + col)+1;

      if (this.grid[row][col])
        if (this.isFullUF.connected(index, 0))
          return true;
      return false;
    } catch (IndexOutOfBoundsException e) { throw new IndexOutOfBoundsException(); } 
  } 

  public int numberOfOpenSites() { return this.openSites; } 

  public boolean percolates() {
    if (this.uf.connected(0, (this.grid.length*this.grid.length)+1))
      return true;
    return false;
  } 
  
  public static void main(String args[]){
    try {
      BufferedReader reader = new BufferedReader(new FileReader("/Users/fabianherrera/Documents/CourseraTests/Percolation/sedgewick60.txt"));
      String line;
      String[] split;
      int n = Integer.parseInt(reader.readLine().trim().split("\\s+")[0]);
      Percolation percolation = new Percolation( n );
      while ( (line = reader.readLine()) != null) {
        split = line.trim().split("\\s+");
        percolation.open(Integer.parseInt(split[0]), Integer.parseInt(split[1]) );
      }
      reader.close();
      for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
          if ( percolation.isOpen(i, j) )
            System.out.print("X ");
          else
            System.out.print("- ");
        }
        System.out.println();
      }
      System.out.println(percolation.percolates());
    } catch (Exception e) {
        System.err.println("Error occured");
        e.printStackTrace();
    }
  }
}