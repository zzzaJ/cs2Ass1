
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to read input in the form inputfile filtersize outputfile and apply a median filter 
 * to the inputted values, outputting the filtered values to the output file. Operations are
 * completed in parallel, rather than fully sequentially.
 * @author dino
 */
public class Medfiltpar {
    
    static long startTime = 0;
    static final ForkJoinPool fjPool = new ForkJoinPool();
    
    
    /**
     * Method to determine running time, stores current system time in startTime variable
     * 
     */
    private static void tick(){
        
            startTime = System.currentTimeMillis();
        
    }
    /**
     * Method to determine how much time in milliseconds has passed since tick() was called.
     * @return float current time - startTime (determined by method tick())
     */
    private static float tock(){
        
        return (System.currentTimeMillis() - startTime) / 1000.0f;
        
    }
    
    /**
     * Method to start the creation of new threads to filter array.
     * @return double [] containing the filtered array, based on the input array
     */
    static Double[] medFilt(Double[] inArr, int fsize, int lo, int hi){
        Double[] outArr = new Double[inArr.length];
        fjPool.invoke(new MedFilt(inArr, outArr, fsize, 0, inArr.length));
        return outArr;
        
    }
    /**
     * Main method which handles the running of the program 
     */
    public static void main(String [] args){
        
        System.gc();//calling garbage collector, to minimise chance of call during timing
        Scanner scanf = new Scanner(System.in);//creation of new scanner object to read user input
    
        String line = scanf.nextLine();//intialising line variable to hold line value
        String infname = line.substring(0, line.indexOf(" "));//determining input file name
        int fsize = Integer.parseInt(line.substring(line.indexOf(" ")+1, line.lastIndexOf(" ")));//determining filter size
        
        if (fsize < 3 || fsize%2 == 0){//closing application if filter size not odd and greater than 3 
            
            System.out.println("filter size not >= 3 or not odd"); 
            
        }
        
        String outfname = line.substring(line.lastIndexOf(" "));//deteriming output file name
        int linenum;
        double num; 
    
        try {//try and catch that handles the reading of the input file, throws exception if file cannot be found 
            scanf = new Scanner(new File(infname));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Medfiltpar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int numlines = Integer.parseInt(scanf.nextLine().trim());
        
        Double[] input = new Double[numlines];
        
        while (scanf.hasNextLine()){//looping through file contents, adding to input array
            
            line = scanf.nextLine();
            linenum = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            num = Double.parseDouble(line.substring(line.indexOf(" ")));
            input[linenum-1] = num;
        }
        
        
        Double[] output;
        
        
        //timing operations
        //tick();
        output = medFilt(input, 3, 0, input.length);
        //float time = tock();
        //System.out.println("run took " + time + " seconds");
        
        PrintWriter writer = null;
        
        try{//try and catch to manage the writing of the filtered array to the output file
            
            writer = new PrintWriter(outfname, "utf-8");
            writer.println(numlines);
            for(int i = 0; i < output.length; i++){
                
                writer.println((i+1) + " " + output[i]);
                
            }
            
            
        } catch(IOException ex){
            
            Logger.getLogger(Medfiltpar.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        finally{
            
            writer.close();
            
        }
        

    }
        
    }
    

