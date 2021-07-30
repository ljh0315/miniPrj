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
     *	회원(Member)정보를 수정한다.
     *	1.  로그인상태에서 updateMember.jsp 부터 request의 입력항목을 받아서 파싱한다.
     *	2.  MemberService의 update()를 호출한다. memberId를 argument 로 전달한다.
     *	3.  입력받은 항목을 Member 객체에 저장한다.
     *	4.  MemberService의 update()를 호출한다. Member객체를 argument 로 전달한다.
     *	5.  request Scope에 Member객체를 저장한다. - thank_you.jsp 에서 Member객체를 사용함
     *	6.  정상적이면 urlMapping 에 "thankyou" 를 넘겨주어 /thank_you.jsp로 분기한다.
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
        
        //thank_you.jsp 에서 Insert된  Member의  MemID와 이름을 보여주므로
        request.setAttribute("member", member);
        
        return next;
    }//execute
}