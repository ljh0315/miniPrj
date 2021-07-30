package member;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;

import sl063.web.Command;
import service.MemberService;
import vo.MemberVO;

public class InsertMember extends Command {
    private String next;
    public InsertMember(String next) {
        System.out.println("&&&&&&&&&&&member : " + next);
        this.next = next;
    }
    /**
     *	ȸ��(Member)�� ����Ѵ�.
     *	1.  registerMember.jsp�� ���� request�� �Է��׸��� �޾Ƽ� �Ľ��Ѵ�.
     *	2.  �Ľ��� �ʼ��Է��׸񿩺θ� üũ�Ѵ�.
     *		- �ʼ��Է��׸��� �Է����� ������쿡
     *		    1) Status ��ü�� Excepetion ��ü�� �����Ѵ�
     *		    2) urlMapping �� "userError" �� �Ѱ��־� /incl/userError.jsp�� �б��Ѵ�.
     *	3.  �Է¹��� �׸��� Member ��ü�� �����Ѵ�.
     *	4.  MemberService�� register()�� ȣ���Ѵ�. Member��ü�Ƿ��۷����� argument �� �����Ѵ�.
     *	5.  request Scope�� Member��ü�� �����Ѵ�. - thank_you.jsp ���� Member��ü�� �����
     *	6.  �������̸� urlMapping �� "thankyou" �� �Ѱ��־� /thank_you.jsp�� �б��Ѵ�.
     */
    public String execute(HttpServletRequest request,HttpServletResponse response)
    throws Exception {
        MemberService service = new MemberService();
        List errorMsgs = new ArrayList();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        request.setAttribute("errorMsgs", errorMsgs);
        
        // Extract HTML form parameters
        String memID = request.getParameter("memID");
        String password = request.getParameter("password");
        //String name = Han.engTokor( getTrim(request.getParameter("name")) );
        String name = request.getParameter("name");
        String ssn = request.getParameter("ssn");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String zipcode = request.getParameter("zipcode");
        String address = request.getParameter("address");
        int point = 0;
        
        // Verify 'Enter Member Information' form fields
        if ( (memID == null) || (memID.length() == 0) ) {
            errorMsgs.add("ȸ�����̵� �Է����ּ���.");
        }
        if ( (password == null) || (password.length() == 0) ) {
            errorMsgs.add("�н����带 �Է����ּ���.");
        }
        
        if ( (name == null) || (name.length() == 0) ) {
            errorMsgs.add("�̸��� �Է����ּ���.");
        }
        
        if ( (ssn == null) || (ssn.length() == 0) ) {
            errorMsgs.add("�ֹι�ȣ�� �Է����ּ���.");
        }
        
        // Return now, if any of the above verification failed
        if ( ! errorMsgs.isEmpty() ) {
            return "userError.jsp";
        }
        // Create and populate the Member object
        MemberVO member = service.getMember(memID);
        //member.setMemID(memID.toUpperCase());
        member.setMemID(memID);
        member.setPassword(password);
        //member.setName(name.toUpperCase());
        member.setName(name);
        member.setSsn(ssn);
        //member.setEmail(email.toUpperCase());
        member.setEmail(email);
        member.setTel(tel);
        member.setZipcode(zipcode);
        //member.setAddress(address.toUpperCase());
        member.setAddress(address);
        member.setPoint(point);
        
        // Now delegate the real work to the MemberService object
        member = service.register(member);
        
        //thank_you.jsp ���� Insert��  Member��  MemID�� �̸��� �����ֹǷ�
        request.setAttribute("member", member);
        
        return next;
    }
}