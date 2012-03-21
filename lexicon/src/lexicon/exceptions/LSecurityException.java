/**
 * A specialized Security Exception class for the Lexicon system. The class holds special 
 * refernces for the ServletRequest and the page name that were involved in the Exception.
 * @author Danny Shacham
 */
package lexicon.exceptions;

import javax.servlet.http.HttpServletRequest;

/**
 * A specialized <b>security</b> Exception class for the Lexicon system. The
 * class holds special about the page that was involved in the Exception.
 * 
 * @author Danny Shacham
 */
public class LSecurityException extends AException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3898543651671621594L;

	/**
	 * A variable which saves the name of the page
	 */
	private String page = null;

	/**
	 * A variable that holds the ServletRequest of the page.
	 */
	private HttpServletRequest servletRequest = null;

	/**
	 * If set to true, the user was denied access to a resource
	 */
	private final boolean denied = false;

	/**
	 * Empty constructor
	 */
	public LSecurityException() {
		super("");
	}

	/**
	 * A constructor that saves the reason and the page name
	 * 
	 * @param reason
	 *            What had happened.
	 * @param thePage
	 *            The page where the security problem occured
	 */
	public LSecurityException(String reason, HttpServletRequest req) {
		super(reason);
		this.setServletRequest(req);
		return;
	}

	/**
	 * A constructor that saves the reason and the page name
	 * 
	 * @param reason
	 *            What had happened.
	 * @param thePage
	 *            The page where the security problem occured
	 */
	public LSecurityException(String reason, HttpServletRequest req,
			boolean deniedAccess) {
		super(reason);
		this.setServletRequest(req);
		return;
	}

	/**
	 * Returns the name of the page.
	 * 
	 * @return The name of the page (might be null).
	 */
	public String getPage() {
		return this.page;
	}

	/**
	 * Returns the ServletRequest that was saved in the exception.
	 * 
	 * @return The ServletRequest.
	 */
	public HttpServletRequest getServletRequest() {
		return this.servletRequest;
	}

	/**
	 * If <code>true</code>, then the user was denied access to a resource.
	 * 
	 * @return The value of <code>denied</code>.
	 */
	public boolean isDeniedAccess() {
		return this.denied;
	}

	/**
	 * Sets the name of the page
	 * 
	 * @param thePage
	 *            THe name of the page where the security breach occured.
	 */
	public void setPage(String thePage) {
		if (thePage == null) {
			this.page = "no page";
		} else {
			this.page = thePage;
		}
		return;
	}

	/**
	 * Sets the ServletRequest object to be passed with this exception.
	 * 
	 * @param req
	 *            The ServletRequest.
	 */
	public void setServletRequest(HttpServletRequest req) {
		if (req != null) {
			this.servletRequest = req;
			this.page = this.servletRequest.getServletPath();
		}
	}
}
