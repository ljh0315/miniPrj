package member;

import javax.servlet.*;
import javax.servlet.http.*;

import sl063.web.Command;
import service.MemberService;
import vo.MemberVO;



public class SelectMember extends Command {
	private String next;
	public SelectMember(String next) {
		this.next = next;
	}
  	/**
	*	회원(Member)정보를 조회한다(수정하기위해서).
	*	1.  로그인상태에서 Session객체로 부터 session.getAttribute("Member.id")  로 memId 를 읽어온다.
	*	2.  MemberService의 select()를 호출한다. memId를 argument 로 전달한다. 
	*		select()의 return value로 Member 객체를 받는다.
	*  3.  request Scope에 Member객체를 저장한다.
	*	4.  정상적이면 urlMapping 에 "updateMember" 를 넘겨주어 /updateMember.jsp로 분기한다. 
	*/
	public String execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		MemberService regMember = new MemberService();
		HttpSession session = request.getSession(false);

		String memId = null;
		if(session != null && (String)session.getAttribute("Member.id") != null) {
			//회원이 로그인했을때는 세션정보에서 회원ID를 가져와 상세정보 보려고 할때
			memId =  (String)session.getAttribute("Member.id");
		}
		System.out.println("updateMember memId : " + memId);
                
		MemberVO member = regMember.select(memId);
		request.setAttribute("member", member);

		return next;
	}//execute
}