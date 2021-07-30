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
 * ���ʿ� ��û�� �ҷ����� ���� Ŭ�����̴�.
 */
public class MainServlet extends HttpServlet {
    private HashMap commands;
    public void init() {
        initCommands();
    }
    /**  Life Cycle  service() �޼���
     *  Http method�� POST ��û�� ó�����ִ� service �޼���
     */
    public void doPost(HttpServletRequest req,HttpServletResponse res)
    throws ServletException,IOException {
        processRequest(req,res);
    }
    /**  Life Cycle  service() �޼���
     *  Http method�� GET ��û�� ó�����ִ� service �޼���
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
        //�α���
        commands.put("login",new LoginMember("index.jsp"));
        //�α׾ƿ�
        commands.put("logout",new LogoutMember("index.jsp"));
        //ȸ�� ����
        commands.put("insert-member",new InsertMember("thank_you.jsp") );
        //ȸ������ ��ȸ
        commands.put("select-member",new SelectMember("member/updateMember.jsp") );
        //ȸ������ ����
        commands.put("update-member",new UpdateMember("thank_you.jsp") );
        
        
        //��ǰ����Ʈ��ȸ
        commands.put("selectAll-product", new SelectAllProduct("product/area/seoul.jsp"));
        //��ǰ��������ȸ
        commands.put("select-product", new SelectProduct("product/selectProduct.jsp"));
        //��ٱ��Ͽ� �ֱ�
        commands.put("putOne-basket", new PutOneBasket("cart.jsp"));
        //��ٱ����� ��ǰ����
        commands.put("emptyOne-basket", new EmptyOneBasket("cart.jsp"));
        //��ٱ��� ��ü����
        commands.put("emptyAll-basket", new EmptyAllBasket("cart.jsp"));
        
        //�������� ����ϱ��� ȸ������ ��ȸ
        commands.put("select-memberPurchaser",new SelectMember("product/purchaseProduct.jsp"));
        //�������� ���
        commands.put("insert-basketPurchaser",new InsertBasketPurchaser("product/orderProduct.jsp"));
        //���ſϷ��Ŀ� ��ٱ��� ���� ����
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