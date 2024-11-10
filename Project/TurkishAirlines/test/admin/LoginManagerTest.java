import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginManagerTest {

    @InjectMocks
    private LoginManager loginManager;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private ServletContext context;

    @Mock
    private RequestDispatcher requestDispatcher;

    private ArrayList<Customer> customers;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        loginManager = new LoginManager();
        loginManager.setServletContext(context);

        customers = new ArrayList<>(Arrays.asList(
                new Customer("customer1@example.com"),
                new Customer("customer2@example.com")
        ));

        when(context.getAttribute("customers")).thenReturn(customers);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testAdminRoleRedirectsToChangeFeatures() throws Exception {
        when(request.isUserInRole("Admin")).thenReturn(true);

        loginManager.doGet(request, response);

        verify(response).sendRedirect("ChangeFeatures.jsp");
    }

    @Test
    public void testManagerRoleRedirectsToApproveFeatures() throws Exception {
        when(request.isUserInRole("Manager")).thenReturn(true);

        loginManager.doGet(request, response);

        verify(response).sendRedirect("ApproveFeatures.jsp");
    }

    @Test
    public void testCustomerRoleWithNoSessionAttributeSetsCustomerInSession() throws Exception {
        when(request.isUserInRole("Customer")).thenReturn(true);
        when(request.getRemoteUser()).thenReturn("customer1@example.com");
        when(session.getAttribute("customer")).thenReturn(null);
        when(request.getRequestDispatcher("CurrentBooking.do")).thenReturn(requestDispatcher);

        loginManager.doGet(request, response);

        verify(session).setAttribute("customer", customers.get(0));
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testCustomerRoleWithSessionAttributeDoesNotSetCustomer() throws Exception {
        when(request.isUserInRole("Customer")).thenReturn(true);
        when(session.getAttribute("customer")).thenReturn(customers.get(0));
        when(request.getRequestDispatcher("CurrentBooking.do")).thenReturn(requestDispatcher);

        loginManager.doGet(request, response);

        verify(session, never()).setAttribute(anyString(), any());
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testUnauthenticatedUserRedirectsToHome() throws Exception {
        when(request.isUserInRole("Admin")).thenReturn(false);
        when(request.isUserInRole("Manager")).thenReturn(false);
        when(request.isUserInRole("Customer")).thenReturn(false);

        loginManager.doGet(request, response);

        verify(response).sendRedirect("home.jsp");
    }
}
