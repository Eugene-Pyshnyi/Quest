import game.GameServlet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class GameServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private HttpSession session;
    @InjectMocks
    private GameServlet gameServlet;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        gameServlet.init();
        when(request.getSession(true)).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGet() throws Exception {
        gameServlet.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPost() throws Exception {
        when(request.getParameter("answer")).thenReturn("Accept the UFO call");
        gameServlet.doPost(request, response);
        verify(request).setAttribute(eq("message"), anyString());
        verify(request).setAttribute(eq("options"), any());
        verify(requestDispatcher).forward(request, response);
    }
}
