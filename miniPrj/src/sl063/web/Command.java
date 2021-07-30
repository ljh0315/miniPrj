package sl063.web;

import javax.servlet.*;
import javax.servlet.http.*;

/**
	template ���ϰ� Command ������ ����
*/
public abstract class Command {
	private Command nextCommand;
	
	//���������� �����ϴ� �޼���� ���� Ŭ�������� override ���־���Ѵ�.
	//template�޼���
	public String doExecute(HttpServletRequest request,HttpServletResponse response)
		throws Exception {
		String next;
		next = execute(request,response);
		if(nextCommand != null) 
			next = nextCommand.doExecute(request,response);

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