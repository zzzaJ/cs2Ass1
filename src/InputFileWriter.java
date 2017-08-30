
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputFileWriter {
    
    
    
    public static void main(String [] args){
        
        Scanner scanf;
        String line;
        int linenum;
        Double num;
        
        System.out.println("How many lines in new file:");
        
        scanf = new Scanner(System.in);
        int lines = scanf.nextInt();
        
        try {
            scanf = new Scanner(new File("sampleinputfile.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Medfiltseq.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        Double[] output = new Double[lines];
        scanf.nextLine();
        
        for (int i = 0; i < lines; i++){
            
            line = scanf.nextLine();
            linenum = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            num = Double.parseDouble(line.substring(line.indexOf(" ")));
            output[linenum-1] = num;
        }
        
        
        PrintWriter writer = null;
        
        try{
            
            writer = new PrintWriter("outfile", "utf-8");
            writer.println(lines);
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
    
}
