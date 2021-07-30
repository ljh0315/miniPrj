package sl063.web;

import javax.servlet.*;
import javax.servlet.http.*;

import member.InsertMember;
import member.LoginMember;
import member.LogoutMember;
import member.SelectMember;
import member.UpdateMember;

import java.io.*;
import java.util.*;
import sl063.web.product.EmptyOneBasket;
import sl063.web.product.EmptyAllBasket;
import sl063.web.product.PutOneBasket;
import sl063.web.product.SelectAllProduct;
import sl063.web.product.InsertBasketPurchaser;
import sl063.web.product.SelectProduct;
import sl063.exception.CommandException;
import sl063.web.product.DeleteBasket;
/**
 * 최초에 요청시 불려지는 서블릿 클래스이다.
 */
public class MainServlet extends HttpServlet {
    private HashMap commands;
    public void init() {
        initCommands();
    }
    /**  Life Cycle  service() 메서드
     *  Http method중 POST 요청을 처리해주는 service 메서드
     */
    public void doPost(HttpServletRequest req,HttpServletResponse res)
    throws ServletException,IOException {
        processRequest(req,res);
    }
    /**  Life Cycle  service() 메서드
     *  Http method중 GET 요청을 처리해주는 service 메서드
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException,IOException {
        processRequest(req,res);
    }
    
    public void processRequest(HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException{
        //request data encoding
        request.setCharacterEncoding("euc-kr");
        // Declare the dispatcher for the View
        RequestDispatcher view = null;
        String next;
        
        try {
            Command cmd = lookupCommand(request.getParameter("choice"));
            next = cmd.doExecute(request,response);
            CommandToken.set(request);
            System.out.println("next " + next);
            view = request.getRequestDispatcher(next);
            view.forward(request, response);
        }catch(Exception e) {
            request.setAttribute("javax.servlet.error.exception", e);
            request.setAttribute("javax.servlet.error.request_uri",request.getRequestURI());
            view = request.getRequestDispatcher("incl/exceptionDisplay.jsp");
            view.forward(request, response);
        }
        
    }
    
    private void initCommands() {
        commands = new HashMap();
        //로그인
        commands.put("login",new LoginMember("index.jsp"));
        //로그아웃
        commands.put("logout",new LogoutMember("index.jsp"));
        //회원 가입
        commands.put("insert-member",new InsertMember("thank_you.jsp") );
        //회원정보 조회
        commands.put("select-member",new SelectMember("member/updateMember.jsp") );
        //회원정보 갱신
        commands.put("update-member",new UpdateMember("thank_you.jsp") );
        
        
        //상품리스트조회
        commands.put("selectAll-product", new SelectAllProduct("product/area/seoul.jsp"));
        //상품정보상세조회
        commands.put("select-product", new SelectProduct("product/selectProduct.jsp"));
        //장바구니에 넣기
        commands.put("putOne-basket", new PutOneBasket("cart.jsp"));
        //장바구니의 상품삭제
        commands.put("emptyOne-basket", new EmptyOneBasket("cart.jsp"));
        //장바구니 전체삭제
        commands.put("emptyAll-basket", new EmptyAllBasket("cart.jsp"));
        
        //구매정보 등록하기전 회원정보 조회
        commands.put("select-memberPurchaser",new SelectMember("product/purchaseProduct.jsp"));
        //구매정보 등록
        commands.put("insert-basketPurchaser",new InsertBasketPurchaser("product/orderProduct.jsp"));
        //구매완료후에 장바구니 정보 삭제
        commands.put("delete-basket",new DeleteBasket("index.jsp"));
        
    }
    
    private Command lookupCommand(String cmd) throws Exception {
        //System.out.println("cmd "  + cmd + " " + cmd.toLowerCase());
        if( commands.containsKey(cmd) )
            return (Command)commands.get(cmd);
        else
            throw new Exception(new CommandException("Invalid Command Indetifier"));
    }
    
}