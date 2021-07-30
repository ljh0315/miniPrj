<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "entity.QnAEntity" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("euc-kr");%>

<jsp:useBean id="board" scope="page" class="vo.QnAVO">
   <jsp:setProperty name="board" property="*"/>
</jsp:useBean>

<%
 
    String pageNum = request.getParameter("pageNum");
	
		QnAEntity qe = QnAEntity.getInstance();
    	int check = qe.updateBoard(board);
	
    if(check==1){
%>
	  <meta http-equiv="Refresh" content="0;url=qnaController?choice=getAll-board" >
<% }else{%>
                                                                                  
 <meta http-equiv="Refresh" content="0;url=qnaController?choice=getAll-board" >   
       <script language="JavaScript">      
      <!--      
        alert("비밀번호가 맞지 않습니다");A
        history.go(-1);
      -->
     
      </script>
     
<%
    }
 %> 