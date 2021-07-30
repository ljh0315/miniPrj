<%@ page session="false" isErrorPage="true"
    import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=euc-kr" %>    

<%-- Using the implicit variable EXCEPTION
     extract the name of the exception --%>
<%
    Throwable ex
      = (Throwable) request.getAttribute("javax.servlet.error.exception");
    String expTypeFullName
      = ex.getClass().getName();
	System.out.println("#####expTypeFullName : " + expTypeFullName + " : " + expTypeFullName.lastIndexOf("."));
    String expTypeName
      = expTypeFullName.substring(expTypeFullName.lastIndexOf(".")+1);
	System.out.println("expTypeName : " + expTypeName);
    String request_uri
      = (String) request.getAttribute("javax.servlet.error.request_uri");
	System.out.println("request_uri : " + request_uri);
%>

<TD WIDTH='480' ALIGN='left'>
<!-- START of main conten t-->
<DIV STYLE='margin-top: 0.1in; margin-left: 0.1in;
			margin-bottom: 0.1in; margin-right: 0.1in'>

<TABLE BORDER='0' CELLSPACING='0' CELLPADDING='0' WIDTH='250'>
<TR>
  <TD ALIGN='center' VALIGN='center'>
  </TD>
  <TD BGCOLOR='pink' ALIGN='center' VALIGN='center'>
    <FONT SIZE='3' COLOR='red'><B><%= expTypeName %></B></FONT>
  </TD>
</TR>

<TR HEIGHT='15'><TD HEIGHT='15'><!-- vertical space --></TD></TR>

<TR>
  <TD></TD>
  <TD>
    <B><%=ex.getMessage() %></B><BR><BR>
    This was the request URI: <BR>
    <CODE><%= request_uri %></CODE><BR><BR>
    This is the complete backtrace of the exception:
    <FONT SIZE='3'>
	<PRE>
	<% ex.printStackTrace(new PrintWriter(out)); %>
	</PRE>
    </FONT>
  </TD>
</TR>
</TABLE>

</DIV>
<!-- END of main conten t-->
</TD>
