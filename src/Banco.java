
public class Banco {
	private static final Banco instance = new Banco();

	public static Banco instance() {
		return instance;
	}
}
