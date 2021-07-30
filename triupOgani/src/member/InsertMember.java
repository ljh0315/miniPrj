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
     *	회원(Member)을 등록한다.
     *	1.  registerMember.jsp로 부터 request의 입력항목을 받아서 파싱한다.
     *	2.  파싱한 필수입력항목여부를 체크한다.
     *		- 필수입력항목을 입력하지 않은경우에
     *		    1) Status 객체에 Excepetion 객체를 저장한다
     *		    2) urlMapping 에 "userError" 를 넘겨주어 /incl/userError.jsp로 분기한다.
     *	3.  입력받은 항목을 Member 객체에 저장한다.
     *	4.  MemberService의 register()를 호출한다. Member객체의레퍼런스를 argument 로 전달한다.
     *	5.  request Scope에 Member객체를 저장한다. - thank_you.jsp 에서 Member객체를 사용함
     *	6.  정상적이면 urlMapping 에 "thankyou" 를 넘겨주어 /thank_you.jsp로 분기한다.
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
            errorMsgs.add("회원아이디를 입력해주세요.");
        }
        if ( (password == null) || (password.length() == 0) ) {
            errorMsgs.add("패스워드를 입력해주세요.");
        }
        
        if ( (name == null) || (name.length() == 0) ) {
            errorMsgs.add("이름을 입력해주세요.");
        }
        
        if ( (ssn == null) || (ssn.length() == 0) ) {
            errorMsgs.add("주민번호를 입력해주세요.");
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
        
        //thank_you.jsp 에서 Insert된  Member의  MemID와 이름을 보여주므로
        request.setAttribute("member", member);
        
        return next;
    }
}