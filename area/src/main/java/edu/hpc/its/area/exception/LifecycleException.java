package edu.hpc.its.area.exception;

/**
 * 
 * @timestamp Feb 13, 2016 1:21:12 PM
 * @author smallbug
 */
public class LifecycleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 213853711583790108L;

	public LifecycleException() {

		this(null, null);

	}

	public LifecycleException(String message) {

		this(message, null);

	}

	public LifecycleException(Throwable throwable) {

		this(null, throwable);

	}

	public LifecycleException(String message, Throwable throwable) {

		super();
		this.message = message;
		this.throwable = throwable;

	}

	// ------------------------------------------------------ Instance Variables

	protected String message = null;

	protected Throwable throwable = null;

	// ---------------------------------------------------------- Public Methods

	public String getMessage() {

		return (message);

	}

	public Throwable getThrowable() {

		return (throwable);

	}

	public String toString() {

		StringBuffer sb = new StringBuffer("its.area -> LifecycleException:  ");
		if (message != null) {
			sb.append(message);
			if (throwable != null) {
				sb.append(":  ");
			}
		}
		if (throwable != null) {
			throwable.printStackTrace();
			sb.append(throwable.toString());
		}
		return (sb.toString());

	}

}
