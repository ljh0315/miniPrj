package member;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;

import sl063.web.Command;
import service.MemberService;
import vo.MemberVO;



public class LoginMember extends Command {
    private String next;
    
    public LoginMember(String next) {
        this.next = next;
    }
    public String execute(HttpServletRequest request,HttpServletResponse response)
    throws Exception {
    	
        List errorMsgs = new ArrayList();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        request.setAttribute("loginErrorMsgs", errorMsgs);
        
        HttpSession session = request.getSession();
        
        String memID = request.getParameter("memID");
        String passwd = request.getParameter("password");
        MemberService regMember = new MemberService();
        //Member member = regMember.compareID(memID.toUpperCase(), passwd);
        MemberVO member = regMember.compareID(memID, passwd);
        
        if(member.getCheck() == "N") {
            errorMsgs.add("���̵� �������� �ʽ��ϴ�.");
            System.out.println("���̵� �������� �ʽ��ϴ�.");
        }
        if(member.getCheck() == "P") {
            errorMsgs.add("�н����尡 ��ġ���� �ʽ��ϴ�.");
            System.out.println("�н����尡 ��ġ���� �ʽ��ϴ�.");
        }
        if ( ! errorMsgs.isEmpty() ) {
            return "index.jsp";
        }
        
        if(member.getCheck() == "Y") {
            session.setAttribute("Member.id",memID);  
            session.setAttribute("Member.Name",member.getName());
        }
        
        return next;
    }
    
}