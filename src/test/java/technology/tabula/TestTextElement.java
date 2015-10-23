package technology.tabula;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Assert;
import org.junit.Test;

public class TestTextElement {
	
	
	@Test
	public void createTextElement() throws IOException {
		
		TextElement textElement = new TextElement(5f, 15f, 10f, 20f, PDType1Font.HELVETICA, 1f, "A", 1f);
		
		Assert.assertNotNull(textElement);
		Assert.assertEquals("A", textElement.getText());
		Assert.assertEquals(1f, textElement.getFontSize(), 0);
		Assert.assertEquals(15f, textElement.getLeft(), 0);
		Assert.assertEquals(5f, textElement.getTop(), 0);
		Assert.assertEquals(10f, textElement.getWidth(), 0);
		Assert.assertEquals(20f, textElement.getHeight(), 0);
		Assert.assertEquals(PDType1Font.HELVETICA, textElement.getFont());
		Assert.assertEquals(1f, textElement.getWidthOfSpace(), 0);
		Assert.assertEquals(0f, textElement.getDirection(), 0);
		
		
	}
	
	@Test
	public void createTextElementWithDirection() throws IOException {
		
		TextElement textElement = new TextElement(5f, 15f, 10f, 20f, PDType1Font.HELVETICA, 1f, "A", 1f, 6f);
		
		Assert.assertNotNull(textElement);
		Assert.assertEquals("A", textElement.getText());
		Assert.assertEquals(1f, textElement.getFontSize(), 0);
		Assert.assertEquals(15f, textElement.getLeft(), 0);
		Assert.assertEquals(5f, textElement.getTop(), 0);
		Assert.assertEquals(10f, textElement.getWidth(), 0);
		Assert.assertEquals(20f, textElement.getHeight(), 0);
		Assert.assertEquals(PDType1Font.HELVETICA, textElement.getFont());
		Assert.assertEquals(1f, textElement.getWidthOfSpace(), 0);
		Assert.assertEquals(6f, textElement.getDirection(), 0);
		
		
	}
	
	@Test
	public void mergeFourElementsIntoFourWords() {
		
		List<TextElement> elements = new ArrayList<TextElement>();
		elements.add(new TextElement(0f, 15f, 10f, 20f, PDType1Font.HELVETICA, 1f, "A", 1f, 6f));
		elements.add(new TextElement(20f, 15f, 10f, 20f, PDType1Font.HELVETICA, 1f, "B", 1f, 6f));
		elements.add(new TextElement(40f, 15f, 10f, 20f, PDType1Font.HELVETICA, 1f, "C", 1f, 6f));
		elements.add(new TextElement(60f, 15f, 10f, 20f, PDType1Font.HELVETICA, 1f, "D", 1f, 6f));
		
		List<TextChunk> words = TextElement.mergeWords(elements);
		
		List<TextChunk> expectedWords = new ArrayList<TextChunk>();
		expectedWords.add(new TextChunk(new TextElement(0f, 15f, 10f, 20f, PDType1Font.HELVETICA, 1f, "A", 1f, 6f)));
		expectedWords.add(new TextChunk(new TextElement(20f, 15f, 10f, 20f, PDType1Font.HELVETICA, 1f, "B", 1f, 6f)));
		expectedWords.add(new TextChunk(new TextElement(40f, 15f, 10f, 20f, PDType1Font.HELVETICA, 1f, "C", 1f, 6f)));
		expectedWords.add(new TextChunk(new TextElement(60f, 15f, 10f, 20f, PDType1Font.HELVETICA, 1f, "D", 1f, 6f)));
		
		Assert.assertEquals(expectedWords, words);
		
	}
	
	@Test
	public void mergeFourElementsIntoOneWord() {
		
		List<TextElement> elements = new ArrayList<TextElement>();
		elements.add(new TextElement(0f, 15f, 10f, 20f, PDType1Font.HELVETICA, 1f, "A", 1f, 6f));
		elements.add(new TextElement(0f, 25f, 10f, 20f, PDType1Font.HELVETICA, 1f, "B", 1f, 6f));
		elements.add(new TextElement(0f, 35f, 10f, 20f, PDType1Font.HELVETICA, 1f, "C", 1f, 6f));
		elements.add(new TextElement(0f, 45f, 10f, 20f, PDType1Font.HELVETICA, 1f, "D", 1f, 6f));
		
		List<TextChunk> words = TextElement.mergeWords(elements);
		
		List<TextChunk> expectedWords = new ArrayList<TextChunk>();
		TextChunk textChunk = new TextChunk(new TextElement(0f, 15f, 10f, 20f, PDType1Font.HELVETICA, 1f, "A", 1f, 6f));
		textChunk.add(new TextElement(0f, 25f, 10f, 20f, PDType1Font.HELVETICA, 1f, "B", 1f, 6f));
		textChunk.add(new TextElement(0f, 35f, 10f, 20f, PDType1Font.HELVETICA, 1f, "C", 1f, 6f));
		textChunk.add(new TextElement(0f, 45f, 10f, 20f, PDType1Font.HELVETICA, 1f, "D", 1f, 6f));
		expectedWords.add(textChunk);
		
		Assert.assertEquals(expectedWords, words);
		
	}
	
	

}
