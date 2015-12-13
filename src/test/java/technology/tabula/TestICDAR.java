package technology.tabula;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import technology.tabula.extractors.BasicExtractionAlgorithm;
import technology.tabula.writers.CSVWriter;

public class TestICDAR {
	
	
	@Test
	public void testFile001() throws IOException{
		
		Float height = 842f; //TODO: get page height
		
		Page page = UtilsForTesting.getAreaFromFirstPage("src/test/resources/technology/tabula/icdar/eu-001.pdf", 
				height - 683, 73f, height - 208, 524f); //TODO: read bbox from eu-001-reg.xml
		
		BasicExtractionAlgorithm bea = new BasicExtractionAlgorithm();//TODO: always use Basic extraction?
		
		List<Table> tables = bea.extract(page);
		assertFalse(tables.isEmpty());
		assertEquals(1, tables.size());
		
		Table table = tables.get(0);
		
		System.out.println(table.getBounds());
		
		List<RectangularTextContainer> cells = table.getCells();
		for (RectangularTextContainer rectangularTextContainer : cells) {
			System.out.println(rectangularTextContainer);
			
			//TODO: assert rectangularTextContainer with cells defined in eu-001-str.xml
		}
		
        StringBuilder sb = new StringBuilder();
        (new CSVWriter()).write(sb, tables.get(0));
        
        System.out.println(sb);
	}

}
