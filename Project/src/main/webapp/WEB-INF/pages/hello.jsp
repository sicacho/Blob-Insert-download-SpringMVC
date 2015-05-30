<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<h1>${message}</h1>
    <table>
        <c:forEach var="material" items="${materials}">
            <tr>
                <td>${material.name}</td>
                <td><a href="/download/${material.id}">Download</a> </td>
            </tr>
        </c:forEach>
    </table>
    <form method="post" action="/upload" enctype="multipart/form-data">
        <table border="0">
            <tr>
                <td>Pick file #1:</td>
                <td><input type="file" name="fileUpload" size="50" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Upload" /></td>
            </tr>
        </table>
    </form>
</body>
</html>