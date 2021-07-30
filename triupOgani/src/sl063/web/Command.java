package sl063.web;

import javax.servlet.http.*;

import sl063.domain.Product;

/**
	template ���ϰ� Command ������ ����
*/
public abstract class Command {
	private Command nextCommand;
	//���������� �����ϴ� �޼���� ���� Ŭ�������� override ���־���Ѵ�.
	//template�޼���
	public String doExecute(HttpServletRequest request,HttpServletResponse response)
		throws Exception {
		System.out.println("Command");
		String next;
		next = execute(request,response);
		System.out.println("doExecute :: " + next);
		if(nextCommand != null) 
			next = nextCommand.doExecute(request,response);

		
		Product product = new Product();
		System.out.println("doExecute :: " + product.getCateNum());
		
		return next;
	}

	//�߻� �޼���
	//hook �޼��� : �� ��ü�� �⺻���� �������� �����س��� �޼���
	public abstract String execute(HttpServletRequest request,HttpServletResponse response)
		throws Exception ;

	public void setNextCommand(Command cmd) {
		this.nextCommand = cmd;
	}
	public Command getNextCommand() {
		return nextCommand;
	}

}