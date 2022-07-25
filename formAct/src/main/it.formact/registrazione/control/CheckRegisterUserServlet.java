package registrazione.control;

import controller.control.AbstractController;
import controller.control.Service;
import registrazione.service.RegisterServices;

public class CheckRegisterUserServlet extends AbstractController{
	
	public CheckRegisterUserServlet() {
		
		super();
	}

	@Override
	protected Service newService() {
		// TODO Auto-generated method stub
		return new RegisterServices();
	}

}
