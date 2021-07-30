package sl063.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.security.*;

/**
	������ Ʈ����� ��ū�� �ϳ� ������ �Ŀ� �������session�� request ���� 16������ �ٲپ �����Ѵ�.
	������� ����ID�� ����ý��۽ð��� ���ļ� ����� �� MD5Ÿ���� �޽��� digest(checksum�� ����)�μ�
	����ID�� �ý��۽ð����� ��������� ������ �����Ǵ� ��� ��ū�� ����ڸ��� �������� �� �ִ�.

	� ����Ÿ�� ���� MessageDigest�� ���ϴ� ����� Hash�� ����Ѵ�.
	update() : ����Ÿ�� �ؽ��Ѵ�.
	digest() : ����Ʈ�迭�� �ؽ��� ��ȯ�Ѵ�.
*/
public class CommandToken {
	public static void set(HttpServletRequest req) {
		HttpSession session = req.getSession();
		long systime = System.currentTimeMillis();
		byte[] time = new Long(systime).toString().getBytes();
		byte[] id = session.getId().getBytes();

		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(id);
			md5.update(time);
			String token = toHex(md5.digest());
			req.setAttribute("token",token);
			session.setAttribute("token",token);
		}catch(Exception e) {
		}
	}
/*
	request �� session�� ����Ǿ� �ִ� ��ū�� ã�Ƴ��� ���� ������ ���Ѵ�
	������ true�� return �ƴϸ� false�� return �Ѵ�.
*/
	public static boolean isValid(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String requestToken = req.getParameter("token");
		String sessionToken = (String)session.getAttribute("token");

		System.out.println("aaa :" + (String)req.getAttribute("token"));
		System.out.println("requestToken : " + requestToken);
		System.out.println("sessionToken : " + sessionToken);
		if(requestToken == null || sessionToken == null)
			return false;
		else
			return requestToken.equals(sessionToken);

	}
	private static String toHex(byte[] digest) {
		StringBuffer buf = new StringBuffer();
		for(int i=0;i<digest.length;i++)
			buf.append(Integer.toHexString((int)digest[i]& 0x00ff));
		return buf.toString();
	}
}