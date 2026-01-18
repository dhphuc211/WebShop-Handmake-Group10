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
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");

            // Nếu cart chưa có gì thì thực hiện việc thêm vào
            if (cart == null) {
                cart = new Cart();
            }

            ProductService productService = new ProductService();
            Product product = productService.getProduct(productId);

            if (product != null) {
                cart.add(product, quantity);
                session.setAttribute("cart", cart);
                response.sendRedirect("shopping-cart.jsp");
                return;
            }

            request.setAttribute("msg", "Product not found");
            request.getRequestDispatcher("shopping-cart.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Xử lý lỗi nếu ID hoặc quantity không phải số
            e.printStackTrace();
            response.sendRedirect("products.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("shopping-cart.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}