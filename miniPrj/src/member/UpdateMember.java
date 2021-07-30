package member;

import javax.servlet.*;
import javax.servlet.http.*;

import sl063.web.Command;
import service.MemberService;
import vo.MemberVO;


public class UpdateMember extends Command {
    private String next;
    public UpdateMember(String next) {
        this.next = next;
    }
    /**
     *	ȸ��(Member)������ �����Ѵ�.
     *	1.  �α��λ��¿��� updateMember.jsp ���� request�� �Է��׸��� �޾Ƽ� �Ľ��Ѵ�.
     *	2.  MemberService�� update()�� ȣ���Ѵ�. memberId�� argument �� �����Ѵ�.
     *	3.  �Է¹��� �׸��� Member ��ü�� �����Ѵ�.
     *	4.  MemberService�� update()�� ȣ���Ѵ�. Member��ü�� argument �� �����Ѵ�.
     *	5.  request Scope�� Member��ü�� �����Ѵ�. - thank_you.jsp ���� Member��ü�� �����
     *	6.  �������̸� urlMapping �� "thankyou" �� �Ѱ��־� /thank_you.jsp�� �б��Ѵ�.
     */
    public String execute(HttpServletRequest request,HttpServletResponse response)
    throws Exception {
        
        MemberService service = new MemberService();
        String memID =  request.getParameter("memID");
        String password =  request.getParameter("password");
        String name =  request.getParameter("name");
        String ssn= request.getParameter("ssn");
        String email =  request.getParameter("email");
        String tel =  request.getParameter("tel");
        String zipcode =  request.getParameter("zipcode");
        String address =  request.getParameter("address");
        
        MemberVO member = service.getMember(memID);
        member.setMemID(memID);
        member.setPassword(password);
        member.setName(name);
        member.setSsn(ssn);
        member.setEmail(email);
        member.setTel(tel);
        member.setZipcode(zipcode);
        member.setAddress(address);
        
        service.update(member);
        
        //thank_you.jsp ���� Insert��  Member��  MemID�� �̸��� �����ֹǷ�
        request.setAttribute("member", member);
        
        return next;
    }//execute
}