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
	*	ȸ��(Member)������ ��ȸ�Ѵ�(�����ϱ����ؼ�).
	*	1.  �α��λ��¿��� Session��ü�� ���� session.getAttribute("Member.id")  �� memId �� �о�´�.
	*	2.  MemberService�� select()�� ȣ���Ѵ�. memId�� argument �� �����Ѵ�. 
	*		select()�� return value�� Member ��ü�� �޴´�.
	*  3.  request Scope�� Member��ü�� �����Ѵ�.
	*	4.  �������̸� urlMapping �� "updateMember" �� �Ѱ��־� /updateMember.jsp�� �б��Ѵ�. 
	*/
	public String execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		MemberService regMember = new MemberService();
		HttpSession session = request.getSession(false);

		String memId = null;
		if(session != null && (String)session.getAttribute("Member.id") != null) {
			//ȸ���� �α����������� ������������ ȸ��ID�� ������ ������ ������ �Ҷ�
			memId =  (String)session.getAttribute("Member.id");
		}
		System.out.println("updateMember memId : " + memId);
                
		MemberVO member = regMember.select(memId);
		request.setAttribute("member", member);

		return next;
	}//execute
}