package com.example.backend.util;

import com.example.backend.dao.OrderDao;
import com.example.backend.model.Cart;
import com.example.backend.model.Order;
import com.example.backend.model.User;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

public final class CheckoutSessionUtil {
    public static final String CHECKOUT_FORM_SESSION_KEY = "checkoutForm";
    public static final int SHIPPING_FEE = 30000;

    private CheckoutSessionUtil() {
    }

    public static int placeOrderFromSession(HttpSession session) {
        if (session == null) {
            return 0;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return 0;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getTotalQuantity() == 0) {
            return 0;
        }

        Object formObj = session.getAttribute(CHECKOUT_FORM_SESSION_KEY);
        if (!(formObj instanceof Map)) {
            return 0;
        }

        @SuppressWarnings("unchecked")
        Map<String, String> formData = (Map<String, String>) formObj;

        String fullName = getFormValue(formData, "fullname", user.getFullName());
        String phone = getFormValue(formData, "phone", user.getPhone());
        String address = getFormValue(formData, "address", null);
        if (address == null) {
            return 0;
        }

        String fullAddress = address;
        fullAddress = appendPart(fullAddress, formData.get("ward"));
        fullAddress = appendPart(fullAddress, formData.get("district"));
        fullAddress = appendPart(fullAddress, formData.get("province"));

        Order order = new Order();
        order.setUser_id(user.getId());
        order.setShipping_name(fullName != null ? fullName : "");
        order.setShipping_phone(phone != null ? phone : "");
        order.setShipping_address(fullAddress);
        order.setShipping_fee(SHIPPING_FEE);
        order.setNote(formData.get("note"));
        order.setTotal_amount(cart.getTotalMoney() + SHIPPING_FEE);

        OrderDao orderDao = new OrderDao();
        int orderId = orderDao.saveOrder(order, cart);

        if (orderId > 0) {
            session.removeAttribute("cart");
            session.removeAttribute(CHECKOUT_FORM_SESSION_KEY);
        }

        return orderId;
    }

    private static String getFormValue(Map<String, String> formData, String key, String fallback) {
        String value = formData.get(key);
        if (value == null) {
            return fallback;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? fallback : trimmed;
    }

    private static String appendPart(String base, String part) {
        if (part == null) {
            return base;
        }
        String trimmed = part.trim();
        if (trimmed.isEmpty()) {
            return base;
        }
        return base + ", " + trimmed;
    }
}
