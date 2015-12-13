package technology.tabula;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import technology.tabula.extractors.BasicExtractionAlgorithm;
import technology.tabula.writers.CSVWriter;

public class TestICDAR {
	
	
	@Test
	public void testFile1() throws IOException{
		
		Page page = UtilsForTesting.getAreaFromFirstPage("src/test/resources/technology/tabula/icdar/eu-001.pdf", 
                842f - 683, 73f, 842f - 208, 524f);
		
		System.out.println(page.getHeight());
		
		BasicExtractionAlgorithm bea = new BasicExtractionAlgorithm();
		
		List<Table> tables = bea.extract(page);
		
		assertFalse(tables.isEmpty());
		
        StringBuilder sb = new StringBuilder();
        (new CSVWriter()).write(sb, tables.get(0));
        
        System.out.println(sb);
	}

}
