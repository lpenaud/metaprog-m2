package factory;

public class FactorySingleton {
	
	public static CarnetFactory getCarnetFactory() {
		return carnetFactory;
	}
	
	private FactorySingleton() {}

	private static final CarnetFactory carnetFactory = new CarnetFactory();
	
}
