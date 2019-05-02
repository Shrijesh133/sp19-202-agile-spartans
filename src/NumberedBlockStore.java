/**
 * Write a description of class NumberedBlockStore here. 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.HashMap;
import java.util.Map;

public class NumberedBlockStore  
{
 private static Map<String, NumberedBlock> NumberedBlockMap = new HashMap<String, NumberedBlock>();  
       
    static 
    { 
        NumberedBlockMap.put("NumberedBlock2", new TwoNumberedBlock(2)); 
        NumberedBlockMap.put("NumberedBlock4", new TwoNumberedBlock(4)); 
        NumberedBlockMap.put("NumberedBlock8", new TwoNumberedBlock(8)); 
        NumberedBlockMap.put("NumberedBlock16", new TwoNumberedBlock(16)); 
        NumberedBlockMap.put("NumberedBlock32", new TwoNumberedBlock(32)); 
        NumberedBlockMap.put("NumberedBlock64", new TwoNumberedBlock(64)); 
        NumberedBlockMap.put("NumberedBlock128", new TwoNumberedBlock(128)); 
        NumberedBlockMap.put("NumberedBlock256", new TwoNumberedBlock(256)); 
        NumberedBlockMap.put("NumberedBlock512", new TwoNumberedBlock(512)); 
        NumberedBlockMap.put("NumberedBlock1024", new TwoNumberedBlock(1024)); 
        NumberedBlockMap.put("NumberedBlock2048", new TwoNumberedBlock(2048)); 
		NumberedBlockMap.put("NumberedBlock4096", new TwoNumberedBlock(4096)); 
	    NumberedBlockMap.put("NumberedBlock8192", new TwoNumberedBlock(8192));
    } 
       
    public static NumberedBlock getNumberedBlock(String colorName) 
    { 
        return (NumberedBlock) NumberedBlockMap.get(colorName).clone(); 
    } 
}
