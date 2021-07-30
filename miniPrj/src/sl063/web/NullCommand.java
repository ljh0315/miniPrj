package sl063.web;

import javax.servlet.*;
import javax.servlet.http.*;

public class NullCommand extends Command {
	private String next;

	public NullCommand(String next) {
		this.next = next;
	}
	public String execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		return next;
	}

}