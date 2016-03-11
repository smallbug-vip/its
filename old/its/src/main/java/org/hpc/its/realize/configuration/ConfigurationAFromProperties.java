package org.hpc.its.realize.configuration;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * load configuration from properties file
 * 
 * @author smallbug
 * @createTime Nov 8, 2015 11:30:09 AM
 */
public class ConfigurationAFromProperties extends AbstractAConfiguration {

	private static ConfigurationAFromProperties cfg = null;
	Logger log = Logger.getLogger(ConfigurationAFromProperties.class);

	/******************************* Singleton begin *******************************/
	private ConfigurationAFromProperties() {
		log.info("load realize/configuration_a.properties");
		loadProperties("realize/configuration_a.properties", this);
		init();
	}

	private ConfigurationAFromProperties(String cfgPath) {
		log.info("load " + cfgPath);
		loadProperties(cfgPath, this);
		init();
	}

	public static ConfigurationAFromProperties getConfiguration() {

		if (cfg == null) {
			cfg = getCfg();
		}
		return cfg;
	}

	public static ConfigurationAFromProperties getConfiguration(String cfgPath) {

		if (cfg == null) {
			cfg = getCfg(cfgPath);
		}
		return cfg;
	}

	private static synchronized ConfigurationAFromProperties getCfg() {
		return new ConfigurationAFromProperties();
	}

	private static synchronized ConfigurationAFromProperties getCfg(String cfgPath) {
		return new ConfigurationAFromProperties(cfgPath);
	}

	/******************************* Singleton end *******************************/

	/**
	 * load configure from properties file
	 * 
	 * @param cfgPath
	 */

	protected void loadProperties(String cfgPath, ConfigurationAFromProperties configuration) {

		InputStream ins = ConfigurationAFromProperties.class//
				.getClassLoader()//
				.getResourceAsStream(cfgPath);
		Properties prop = new Properties();
		try {
			prop.load(ins);
			this.prop_ = prop;
			this.clazz_ = configuration.getClass();
		} catch (Exception e) {
			throw new RuntimeException("failed to load properties file");
		}
	}

	private Properties prop_ = null;
	@SuppressWarnings("rawtypes")
	private Class clazz_ = null;

	@Override
	public void initProperties() {
		try {
			inject(prop_, clazz_);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("recursive inject failed!");
		}
	}

	/**
	 * recursive inject properties to class and super class.this method can
	 * initialize properties of himself and super class.the properties can be
	 * injected include basic data types and their wrapper classes and String
	 * 
	 * @param prop
	 * @param clazz
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	private void inject(Properties prop, Class clazz) throws IllegalAccessException {

		Class superClass = clazz.getSuperclass();

		if (!"java.lang.Object".equals(superClass.getName()))
			this.inject(prop, superClass);

		Field[] fileds = clazz.getDeclaredFields();
		for (Field f : fileds) {
			if (prop.getProperty(f.getName().toLowerCase()) == null)
				continue;
			f.setAccessible(true);
			if ("int".equals(f.getType().getName()) || "java.lang.Integer".equals(f.getType().getName())) {
				f.set(null, new Integer(//
						prop.getProperty(f.getName().toLowerCase())));
				continue;
			} else if ("long".equals(f.getType().getName()) || "java.lang.Long".equals(f.getType().getName())) {
				f.set(null, Long.parseLong(//
						prop.getProperty(f.getName().toLowerCase())));
				continue;
			} else if ("float".equals(f.getType().getName()) || "java.lang.Float".equals(f.getType().getName())) {
				f.set(null, Float.parseFloat(//
						prop.getProperty(f.getName().toLowerCase())));
				continue;
			} else if ("double".equals(f.getType().getName()) || "java.lang.Double".equals(f.getType().getName())) {
				f.set(null, Double.parseDouble(//
						prop.getProperty(f.getName().toLowerCase())));
				continue;
			} else if ("java.lang.String".equals(f.getType().getName())) {
				f.set(null, prop.getProperty(f.getName().toLowerCase()));
			}
		}
	}
}
