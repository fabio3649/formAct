package amministrazione.control;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import controller.control.AbstractController;
import controller.control.Service;


@WebServlet("/AdminServlet/*")
public class AdminServlet extends AbstractController{

	@Override
	protected Map<String, Service> initServices() {
		// TODO Auto-generated method stub
		return null;
	}

}
