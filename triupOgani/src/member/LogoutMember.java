package member;

import javax.servlet.http.*;

import sl063.web.Command;


public class LogoutMember extends Command {
	private String next;

	public LogoutMember(String next) {
		this.next = next;
	}
	public String execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
	    HttpSession session = request.getSession(false);
		if(session != null) {     
			System.out.println("logout called");
			//MemberId �� �����
			session.removeAttribute("Member.id");
			//��ٱ��� ���� �����
//		    BasketCart bc = (BasketCart)session.getAttribute("BasketCart");
//			System.out.println("logout bc " + bc);
//			if(bc != null) bc.clear();		
		}
		return next;
	}

}