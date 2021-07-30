package sl063.web.product;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;
import sl063.domain.Product;
import sl063.domain.Basket;
import sl063.domain.BasketCart;
import sl063.domain.ProductService;

import sl063.web.Command;
import sl063.web.CommandToken;

public class PutOneBasket extends Command {
    private String next;
    
    public PutOneBasket(String next) {
        this.next = next;
    }
    /**
     *	��ٱ��Ͽ� ��ǰ�� ��´�
     *	1.  detailProduct.jsp�� request�� �޴´�.
     *	2.  Session ��ü���� "Member.id" �� �ش��ϴ� ���� ������ check�Ѵ�.
     *     �� �α��ο��θ� üũ�ϰ� ���Ŀ� ��ٱ��Ͽ� ��ǰ�� ��ƾ� �Ѵ�.
     *   3. �α��� ���� ���� �����̸� "�α����� �����ϼ���!! "��� �޼����� �����ְ�
     *      "userError" �� return �Ѵ�.
     *	4.  Session ��ü���� BasketCart��ü�� ���۷����� ���´�
     *   5.  request��ü���� "productName" �� ���� �����´�.
     *	6.  productName�� parameter�� �Է� �޾� �� ��ǰ�� ��ǰ������ �������� listProduct(request, response, productName) �����.
     *       ProductDAO�� listProduct(productName)�� ȣ���Ѵ�.
     *		������� Basket��ü�� �����Ѵ�.
     *   7.  ��ǰ�� �̸��� Basket��ü�� argument �� �־� BasketCart�� add(productName, basket)�� ȣ���Ѵ�.
     */
    public String execute(HttpServletRequest request,HttpServletResponse response)
    throws Exception {
        System.out.println("InsertBasket execute called");
        List errorMsgs = new ArrayList();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        request.setAttribute("errorMsgs", errorMsgs);
        
        HttpSession session = request.getSession(false);
        if(session != null) {
            String memID =  (String)session.getAttribute("Member.id");
            System.out.println("memID : " + memID);
            if( memID == null  || memID.equals("") ) {
                errorMsgs.add("�α����� �����ϼ���!!");
                //System.out.println("login ERROR");
            }
        }
        String productId=request.getParameter("productId");
        String quantity = request.getParameter("quantity");
        int iquantity = Integer.parseInt(quantity);
        
        if ( ! errorMsgs.isEmpty() ) {
            return "userError.jsp";
        }
        //BasketCart bc = null;
        BasketCart bc = (BasketCart)session.getAttribute("BasketCart");
        Basket basket = listProduct(request,response,productId);
        if(bc==null){ 
            bc = new BasketCart();
        }
        if(CommandToken.isValid(request)){
            bc.add(productId, basket,iquantity);
        }
        session.setAttribute("BasketCart",bc);
        
        return next;
    }
    /**
     * ��ǰ���� parameter�� �Է� �޾� �� ��ǰ�� ��ٱ��� ������ ���� �´�.
     * @param String productName
     * @return Basket basket
     */
    public Basket listProduct(HttpServletRequest request,HttpServletResponse response,String productId) throws Exception {
        ProductService productservice = new ProductService();
        
        Product product = (Product)productservice.selectProduct(productId);
        
        String quantity = request.getParameter("quantity");
        System.out.println("quantity : " + quantity);
        Basket basket = new Basket();
        basket.setCompany(product.getCompany());
        basket.setMallId(product.getMallId());
        //basketOrderServlet�� �ٽ� ������
        basket.setOrderNum("1");
        basket.setPrice(product.getPrice2());
        basket.setProductId(product.getProductId());
        basket.setProductName(product.getProductName());
        basket.setQuantity(Integer.parseInt(quantity));
        //basket.setQtyPrice(basket.getQuantity() * basket.getPrice());
        return basket;
    }
    
}