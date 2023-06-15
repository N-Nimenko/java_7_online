package separation;

import separation.controller.Controller;
import separation.dao.DAO;

public class Main {
    public static void main(String[] args) {
        DAO dao = new DAO();
        Controller controller = new Controller(dao);
        controller.start();
    }
}
