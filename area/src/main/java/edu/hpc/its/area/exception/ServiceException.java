package edu.hpc.its.area.exception;

/**
 * 
 * @timestamp Feb 13, 2016 1:21:12 PM
 * @author smallbug
 */
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6376525739674175224L;

	public ServiceException() {

		this(null, null);

	}

	public ServiceException(String message) {

		this(message, null);

	}

	public ServiceException(Throwable throwable) {

		this(null, throwable);

	}

	public ServiceException(String message, Throwable throwable) {

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

		StringBuffer sb = new StringBuffer("its.area -> ServiceException:  ");
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
