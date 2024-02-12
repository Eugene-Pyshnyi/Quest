package game;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class GameServlet extends HttpServlet {
    private Game game;

    @Override
    public void init() throws ServletException {
        super.init();
        game = new Game();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String initialMessage = game.startGame();
        request.setAttribute("message", initialMessage);
        request.setAttribute("options", game.getCurrentOptions());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String answer = request.getParameter("answer");

        if ("true".equals(request.getParameter("reset"))) {
            game = new Game();
            answer = null;
        }

        if (answer != null) {
            String nextQuestionOrResult = game.processAnswer(answer);
            request.setAttribute("message", nextQuestionOrResult);
            List<String> options = game.getCurrentOptions();
            request.setAttribute("options", options);

            if (game.isGameOver()) {
                request.setAttribute("showRestartButton", true);
                if (game.isDefeated()) {
                    request.setAttribute("defeatImage", request.getContextPath() + "/images/defeat.jpg");
                    game.resetGame();
                } else if (game.isVictory()) {
                    request.setAttribute("victoryImage", request.getContextPath() + "/images/win.jpg");
                    game.resetGame();
                }
            }
        } else {
            request.setAttribute("message", "You have lost memory. Accept the UFO call?");
            List<String> options = game.getCurrentOptions();
            request.setAttribute("options", options);
        }

        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }
}
