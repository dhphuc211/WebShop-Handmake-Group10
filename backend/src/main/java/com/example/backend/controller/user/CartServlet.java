package com.example.backend.controller.user;

import com.example.backend.model.Cart;
import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action == null){
            action = "view"; // nếu người dùng không làm gì thì cho nó xem thôi
        }

        try {
            switch (action){
            case "add":
                addToCart(request,response);
                break;
                case "update":
                    updateCart(request,response);
                    break;
                case "remove":
                    removeFromCart(request,response);
                    break;
                case "view":
                default:
                    viewCart(request,response);
                    break;
            }
        } catch (NumberFormatException e) {
            e.getStackTrace();
            response.sendRedirect("shopping-cart.jsp");
        }
    }

    private void viewCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("shopping-cart.jsp").forward(request,response);
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        HttpSession session = request.getSession();
        Cart cart=(Cart) session.getAttribute("cart");

        // cart phải có sản phẩm mới thực hiện việc xóa được
        if(cart != null){
            cart.remove(productId);
            session.setAttribute("cart",cart);
        }

        response.sendRedirect("shopping-cart.jsp");

    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // cart đã có sản phẩm thì chúng ta mới thực hiện được việc tăng số lượng
        if(cart!=null){
            cart.update(productId,quantity);
            session.setAttribute("cart",cart);
        }

        response.sendRedirect("shopping-cart.jsp");

    }

//     Chưa có class ProductService của đồng đội đợi chừng nào gửi lên pull về test thử
    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // test chức năng giỏ hàng
        System.out.println("DEBUG: CartServlet -> addToCart");
        System.out.println("DEBUG: Product ID nhận được: " + productId);
        System.out.println("DEBUG: Quantity nhận được: " + quantity);

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // Nếu cart chưa có gì thì thực hiện việc thêm vào
        if(cart == null){
            cart = new Cart();
        }

//        ProductService productService = new ProductService();
//        Product product = productService.getProduct(productId);

        // (Do chưa có Database và ProductService tự tạo)
        Product product = new Product();
        product.setId(productId);
        product.setName("Sản phẩm Test (Giả lập)");
        product.setPrice(1688000); // Giá tiền giả định
        product.setStock(100);     // Set số lượng tồn kho giả định

        if(product!=null){
            cart.add(product,quantity);
            session.setAttribute("cart",cart);

            System.out.println("DEBUG: Đã lưu Cart vào Session thành công! Tổng SP: " + cart.getTotalQuantity());

            response.sendRedirect("shopping-cart.jsp");
//            response.sendRedirect("productdetail?id=" + productId);
            return;

        }

        request.setAttribute("msg","Product not found");
        request.getRequestDispatcher("shopping-cart.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}