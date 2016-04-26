import static org.junit.Assert.*;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

abstract public class TrabajoPracticoTest {
	
	abstract public Object getObject();
	
	@Test
	public void debeImplementarToString() throws NoSuchMethodException, SecurityException {
		// verifica que el objeto que testeamos haya "pisado" el toString de Object.
		assertNotEquals(
			getObject()
				.getClass()
				.getMethod("toString")
				.getDeclaringClass(), 
			Object.class
		);
	}
	
	@Test
	public void debeImplementarComparable() {
		assertThat(getObject(), new IsInstanceOf(Comparable.class));
	}
}
