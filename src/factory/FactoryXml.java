package factory;

public interface FactoryXml<T> {	
	T fromXml(final String filename);
}
