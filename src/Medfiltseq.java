
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Medfiltseq {
    
    static long startTime = 0;
    private static void tick(){
        
            startTime = System.currentTimeMillis();
        
    }
    private static float tock(){
        
        return (System.currentTimeMillis() - startTime) / 1000.0f;
        
    }
    
    //public static void main(String [] args) throws IOException{
    
        Scanner scanf = new Scanner(System.in);
    
        String line = scanf.nextLine();
        String infname = line.substring(0, line.indexOf(" "));
        int fsize = Integer.parseInt(line.substring(line.indexOf(" ")+1, line.lastIndexOf(" ")));
        
        if (fsize < 3 || fsize%2 == 0){
            
            System.out.println("filter size not >= 3 or not odd"); 
            
        }
        
        String outfname = line.substring(line.lastIndexOf(" "));
        int linenum;
        double num; 
    
        try {
            scanf = new Scanner(new File(infname));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Medfiltseq.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int numlines = Integer.parseInt(scanf.nextLine().trim());
        
        Double[] input = new Double[numlines];
        
        while (scanf.hasNextLine()){
            
            line = scanf.nextLine();
            linenum = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            num = Double.parseDouble(line.substring(line.indexOf(" ")));
            input[linenum-1] = num;
        }
        
        
        Double[] output;
        
        
        //timing operations
        tick();
        output = medFilt(input, fsize);
        float time = tock();
        System.out.println("run took " + time + " seconds");
        
        PrintWriter writer = null;
        
        try{
            
            writer = new PrintWriter(outfname, "utf-8");
            writer.println(numlines);
            for(int i = 0; i < output.length; i++){
                
                writer.println((i+1) + " " + output[i]);
                
            }
            
            
        } catch(IOException ex){
            
            Logger.getLogger(Medfiltseq.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        finally{
            
            writer.close();
            
        }
        

    }
    
    public static Double[] medFilt(Double[] inputArr, int fsize){
        
        Double[] dub = new Double[inputArr.length];
        Double[] wip = new Double[fsize];
        int bnds;
        
        
        bnds = fsize/2; // 3/2 = 1 , 4/2 = 2 , 5/2 = 2 etc. 
        
        for(int i = 0; i < dub.length; i++){
            
            if(i + 1 <= bnds || dub.length - i <= bnds){
                
                dub[i] = inputArr[i];
                
            }
            else{
                
                for(int j = 0; j < fsize; j++){ //populate wip array with elements 
                
                    wip[j] = inputArr[i-bnds+j];//seems correct
                    
                }
                
                java.util.Arrays.sort(wip);
                dub[i] = wip[bnds];
            }
            
        }

        return dub;
    }
    
}
