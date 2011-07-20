/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.converter;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.jcr.Binary;
import javax.jcr.Property;
import javax.jcr.PropertyType;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * Testet {@link PropertyToStringConverter}
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class PropertyToStringConverterTest {

	/** Testobjekt */
	private PropertyToStringConverter converter;

	/** Erwarte Exception */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/** jMock */
	private Mockery mockery = new JUnit4Mockery();

	/**
	 * Testet Converter mit null
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	@Test
	public void testNull() throws Exception {
		thrown.expect(IllegalArgumentException.class);

		converter = new PropertyToStringConverter(null);
	}

	/**
	 * Testet Converter mit unknown Type Property
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	@Test
	public void testUnknownType() throws Exception {
		final String string = "unknown Type";
		final Property property = mockery.mock(Property.class);

		mockery.checking(new Expectations() {
			{
				oneOf(property).isMultiple();
				oneOf(property).getType();
			}
		});

		converter = new PropertyToStringConverter(property);

		mockery.assertIsSatisfied();
		
		Assert.assertEquals(string, converter.getValue());
	}

	/**
	 * Testet Converter mit Binary Property
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	@Test
	public void testBinary() throws Exception {
		final Binary binary = mockery.mock(Binary.class);
		final Property property = mockery.mock(Property.class);

		mockery.checking(new Expectations() {
			{
				oneOf(property).isMultiple();
				will(returnValue(false));

				oneOf(property).getType();
				will(returnValue(PropertyType.BINARY));
				
				oneOf(property).getBinary();
				will(returnValue(binary));
				
				allowing(binary);
			}
		});

		converter = new PropertyToStringConverter(property);

		mockery.assertIsSatisfied();
		
//		FIXME: bessere Ausgabe
//		Assert.assertEquals("File", converter.getValue());
	}

	/**
	 * Testet Converter mit Boolean Property
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	@Test
	public void testBoolean() throws Exception {
		final String string = "true";
		final Property property = mockery.mock(Property.class);

		mockery.checking(new Expectations() {
			{
				oneOf(property).isMultiple();
				will(returnValue(false));

				oneOf(property).getType();
				will(returnValue(PropertyType.BOOLEAN));
				
				oneOf(property).getBoolean();
				will(returnValue(true));
			}
		});

		converter = new PropertyToStringConverter(property);

		mockery.assertIsSatisfied();
		
		Assert.assertEquals(string, converter.getValue());
	}

	/**
	 * Testet Converter mit Date Property
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	@Test
	public void testDate() throws Exception {
		final String string = "1988-04-27";
		final Property property = mockery.mock(Property.class);

		mockery.checking(new Expectations() {
			{
				oneOf(property).isMultiple();
				will(returnValue(false));

				oneOf(property).getType();
				will(returnValue(PropertyType.DATE));
				
				oneOf(property).getDate();
				will(returnValue(new GregorianCalendar(1988, 3, 27)));
			}
		});

		converter = new PropertyToStringConverter(property);

		mockery.assertIsSatisfied();
		
		Assert.assertEquals(string, converter.getValue());
	}

	/**
	 * Testet Converter mit Decimal Property
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	@Test
	public void testDecimal() throws Exception {
		final String string = "2704.1988";
		final Property property = mockery.mock(Property.class);

		mockery.checking(new Expectations() {
			{
				oneOf(property).isMultiple();
				will(returnValue(false));

				oneOf(property).getType();
				will(returnValue(PropertyType.DECIMAL));
				
				oneOf(property).getDecimal();
				will(returnValue(new BigDecimal(string)));
			}
		});

		converter = new PropertyToStringConverter(property);

		mockery.assertIsSatisfied();
		
		Assert.assertEquals(string, converter.getValue());
	}

	/**
	 * Testet Converter mit Double Property
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	@Test
	public void testDouble() throws Exception {
		final String string = "2704.1988";
		final Property property = mockery.mock(Property.class);

		mockery.checking(new Expectations() {
			{
				oneOf(property).isMultiple();
				will(returnValue(false));

				oneOf(property).getType();
				will(returnValue(PropertyType.DOUBLE));
				
				oneOf(property).getDouble();
				will(returnValue(2704.1988));
			}
		});

		converter = new PropertyToStringConverter(property);

		mockery.assertIsSatisfied();
		
		Assert.assertEquals(string, converter.getValue());
	}

	/**
	 * Testet Converter mit Long Property
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	@Test
	public void testLong() throws Exception {
		final String string = "27041988";
		final Property property = mockery.mock(Property.class);

		mockery.checking(new Expectations() {
			{
				oneOf(property).isMultiple();
				will(returnValue(false));

				oneOf(property).getType();
				will(returnValue(PropertyType.LONG));
				
				oneOf(property).getLong();
				will(returnValue(27041988L));
			}
		});

		converter = new PropertyToStringConverter(property);

		mockery.assertIsSatisfied();
		
		Assert.assertEquals(string, converter.getValue());
	}

	/**
	 * Testet Converter mit Name Property
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	@Test
	public void testName() throws Exception {
		final String string = "Test String";
		final Property property = mockery.mock(Property.class);

		mockery.checking(new Expectations() {
			{
				oneOf(property).isMultiple();
				will(returnValue(false));

				oneOf(property).getType();
				will(returnValue(PropertyType.NAME));
				
				oneOf(property).getName();
				will(returnValue(string));
			}
		});

		converter = new PropertyToStringConverter(property);

		mockery.assertIsSatisfied();
		
		Assert.assertEquals(string, converter.getValue());
	}

	/**
	 * Testet Converter mit Path Property
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	@Test
	public void testPath() throws Exception {
		final String string = "Test String";
		final Property property = mockery.mock(Property.class);

		mockery.checking(new Expectations() {
			{
				oneOf(property).isMultiple();
				will(returnValue(false));

				oneOf(property).getType();
				will(returnValue(PropertyType.PATH));
				
				oneOf(property).getPath();
				will(returnValue(string));
			}
		});

		converter = new PropertyToStringConverter(property);

		mockery.assertIsSatisfied();
		
		Assert.assertEquals(string, converter.getValue());
	}

	/**
	 * Testet Converter mit String Property
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	@Test
	public void testString() throws Exception {
		final String string = "Test String";
		final Property property = mockery.mock(Property.class);

		mockery.checking(new Expectations() {
			{
				oneOf(property).isMultiple();
				will(returnValue(false));

				oneOf(property).getType();
				will(returnValue(PropertyType.STRING));
				
				oneOf(property).getString();
				will(returnValue(string));
			}
		});

		converter = new PropertyToStringConverter(property);

		mockery.assertIsSatisfied();
		
		Assert.assertEquals(string, converter.getValue());
	}
}
