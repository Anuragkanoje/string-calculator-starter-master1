package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;


class StringCalculatorShould {

    @Test
    void empty_string_should_return_0() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    void string_with_single_number_should_return_number_as_int() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(1, stringCalculator.add("1"));
    
    }
    
	@Test
	public void sumsSingleNumberToItself() {
		assertEquals(StringCalculator.sum("5"), 5);
		assertEquals(StringCalculator.sum("42"), 42);
	}

	@Test
	public void sumsTwoNumbersSeperatedByComma() {
		assertEquals(StringCalculator.sum("1,2"), 3);
		assertEquals(StringCalculator.sum("1,3"), 4);
	}

	@Test
	public void sumsThreeNumbersSeperatedByComma() {
		assertEquals(StringCalculator.sum("1,2,3"), 6);
	}

	@Test
	public void sumsNumbersDelimitedByNewline() {
		assertEquals(StringCalculator.sum("1\n2"), 3);
	}

	@Test
	public void sumsNumbersDelimitedByCommaOrNewline() {
		assertEquals(StringCalculator.sum("1,2\n3"), 6);
	}

	@Test
	public void USESDELIMITERSEPCIFIED() {
		assertEquals(StringCalculator.sum("//;\n1;2"), 3);
		assertEquals(StringCalculator.sum("//.\n2.3.1"), 6);
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void throwsOnNegativeNumber() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("negative number: -3");

		StringCalculator.sum("-3");
	}

	@Test
	public void throwsOnNegativeNumbersWithAllNumbersInExceptionMessage() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("negative number: -3,-5,-13");

		StringCalculator.sum("1,-3,5,-5,-13");
	}

	@Test
	public void mapsNumbersAbove1000ToLastThreeDigits() {
		assertEquals(StringCalculator.sum("1002"), 2);
		assertEquals(StringCalculator.sum("1040,10002"), 42);
	}

	@Test
	public void acceptsDelimiterOfArbitraryLength() {
		assertEquals(StringCalculator.sum("//[***]\n1***2***3"), 6);
	}

	@Test
	public void acceptsMultipleDelimiters() {
		assertEquals(StringCalculator.sum("//[-][;]\n1-2;3"), 6);
		assertEquals(StringCalculator.sum("//[--][...]\n2--3...4"), 9);
	}
    
    
    
}
