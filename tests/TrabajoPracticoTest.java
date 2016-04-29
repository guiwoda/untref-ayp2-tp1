import static org.junit.Assert.*;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

abstract public class TrabajoPracticoTest<C> {
	
	abstract protected C getObject() throws Exception;
	
	@Test
	public void debeImplementarToString() throws Exception {
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
	public void debeImplementarComparable() throws Exception {
		assertThat(getObject(), new IsInstanceOf(Comparable.class));
	}
}
