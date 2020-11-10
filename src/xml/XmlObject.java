package xml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import utils.StringUtils;
import xml.annotations.XmlEntity;
import xml.annotations.XmlField;

class XmlObject<T> {

	XmlObject(final Class<T> c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		final var entity = c.getAnnotation(XmlEntity.class);
		this.con = c.getConstructor(entity.constuctor());
		this.tagName = StringUtils.defaultString(entity.tagName(), c.getSimpleName().toLowerCase());;
		this.mutateurs = new HashMap<>();
		for (final var field : c.getDeclaredFields()) {
			final var xmlField = field.getAnnotation(XmlField.class);
			if (xmlField == null) {
				continue;
			}
			this.mutateurs.put(StringUtils.defaultString(xmlField.tagName(), field.getName()), c.getDeclaredMethod(
					StringUtils.defaultString(xmlField.accesseur(), "set" + StringUtils.toTitleCase(field.getName())),
					xmlField.type() == void.class ? field.getType() : xmlField.type()));
		}
		this.mutateurCourant = null;
	}
	
	public void newInstance(Object...initargs) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.obj = this.con.newInstance(initargs);
	}
	
	public void setMutateurCourant(final String tagName) {
		this.mutateurCourant = this.mutateurs.get(tagName);
	}
	
	public void mute(final Object donnee) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (mutateurCourant != null) {
			mutateurCourant.invoke(obj, donnee);
		}
	}
	
	protected Map<String, Method> mutateurs;
	protected Constructor<T> con;
	protected T obj;
	protected String tagName;
	protected Method mutateurCourant;
}
