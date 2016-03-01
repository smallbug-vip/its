package edu.hpc.its.area.exception;

/**
 * 
 * @timestamp Feb 13, 2016 1:21:12 PM
 * @author smallbug
 */
public class MongoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 213853711583790108L;

	public MongoException() {

		this(null, null);

	}

	public MongoException(String message) {

		this(message, null);

	}

	public MongoException(Throwable throwable) {

		this(null, throwable);

	}

	public MongoException(String message, Throwable throwable) {

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

		StringBuffer sb = new StringBuffer("its.area -> MongoException:  ");
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
