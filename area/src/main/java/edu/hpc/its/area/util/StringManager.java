package edu.hpc.its.area.util;

import java.text.MessageFormat;
import java.util.Hashtable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 异常管理
 * 
 * @timestamp Feb 15, 2016 4:46:28 PM
 * @author smallbug
 */
public class StringManager {
	private ResourceBundle bundle;
	private Locale locale;
	/**
	 * 存放异常信息
	 */
	private static Hashtable<String, StringManager> managers = new Hashtable<String, StringManager>();

	/**
	 * 读取 packageName 包下的异常配置文件
	 * 
	 * @param packageName
	 */
	private StringManager(String packageName) {
		String bundleName = packageName + ".LocalStrings";
		try {
			bundle = ResourceBundle.getBundle(bundleName, Locale.getDefault());
		} catch (MissingResourceException ex) {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			if (cl != null) {
				try {
					bundle = ResourceBundle.getBundle(bundleName, Locale.getDefault(), cl);
				} catch (MissingResourceException ex2) {
					
				}
			}
		}
		if (bundle != null) {
			locale = bundle.getLocale();
		}
	}

	/**
	 * 通过键获取异常信息
	 * 
	 * @timestamp Feb 15, 2016 4:48:51 PM
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		if (key == null) {
			String msg = "key may not have a null value";

			throw new IllegalArgumentException(msg);
		}

		String str = null;

		try {
			str = bundle.getString(key);
		} catch (MissingResourceException mre) {
			str = null;
		}

		return str;
	}

	/**
	 * 格式化错误信息
	 * 
	 * @timestamp Feb 15, 2016 5:18:26 PM
	 * @param key
	 * @param args
	 * @return
	 */
	public String getString(final String key, final Object... args) {
		String value = getString(key);
		if (value == null) {
			value = key;
		}

		MessageFormat mf = new MessageFormat(value);
		mf.setLocale(locale);
		return mf.format(args, new StringBuffer(), null).toString();
	}

	/**
	 * 获取StringManager对象，没有就创建
	 * 
	 * @timestamp Feb 15, 2016 4:52:07 PM
	 * @param packageName
	 * @return
	 */
	public static final synchronized StringManager getManager(String packageName) {
		StringManager mgr = managers.get(packageName);
		if (mgr == null) {
			mgr = new StringManager(packageName);
			managers.put(packageName, mgr);
		}
		return mgr;
	}

}
