package edu.hpc.its.area.exception;

/**
 * 
 * @timestamp Feb 13, 2016 1:21:12 PM
 * @author smallbug
 */
public class CarRunException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7097076021993087099L;

	public CarRunException() {

		this(null, null);

	}

	public CarRunException(String message) {

		this(message, null);

	}

	public CarRunException(Throwable throwable) {

		this(null, throwable);

	}

	public CarRunException(String message, Throwable throwable) {

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

		StringBuffer sb = new StringBuffer("its.area -> CarRunException:  ");
		if (message != null) {
			sb.append(message);
			if (throwable != null) {
				sb.append(":  ");
			}
		}
		if (throwable != null) {
			sb.append(throwable.toString());
		}
		return (sb.toString());

	}

}
