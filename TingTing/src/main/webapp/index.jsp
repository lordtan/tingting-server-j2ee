<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>

</head>

<body>

    <form action="/TingTing/user/login" method="post">


      <input type="hidden" name="user" value="585858"/>

        userName:<input type="text" name="account"/><br>

        password:<input type="password" name="password"/><br>

        <input type="submit" value="submit"/>

    </form>

 

   <hr>

    <h1>test file upload</h1>

    <form action="/TingTing/upload"  method="post"  enctype="multipart/form-data">

<!--         <input type="hidden" name="method" value="upload"/> -->

        <input type="text"  name="attach"/>
        <input type="file" name="file"/>

        <input type="submit"  value="文件上传"/>
    </form>

</body>

</html>

