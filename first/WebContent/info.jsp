<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ import page="com.koreait.first.*"%>
    
    <% 
    Class.forName("oracle.jdbc.driver.OracleDriver");
    
    String url="jdbc:oracle:thin:@localhost:1521:orcl";
    String userName = "hr";
    String password = "koreait2020";
    
    
    List<CountriesVo> list = new ArrayList();
    
    try {
    	con = DiverMananager.getConnection(url, userName, password);
    	ps= con.prepareStatemenr(sql);
    	rs = ps.executeQuery();
    	
    	while(rs.next()){
    		String country_id = rs.gerString("country_id");
    		String country_name = rs.gerString("country_name");
    		int region_id = rs.getInt("region_id");
    		
    		CountriesVO vo = new CountriesVO();
    		vo.setCountry_id(country_id);
    		vo.setCountry_name(country_name);
    		vo.setRegion_id(region_id);
    		
    		
    	}
    } catch(Exception e) {
    	e.printStack
    }
  
    
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Info</title>
</head>
<body>
<div>나라정보</div>
<div>
<table>
<tr>
<th>country_id</th>
<th>나라명</th>
<th>지역ID</th>
<% for(CountriesVO vo : list) {%>
	<tr> 
	<td> <%=vo.getCountry_id() %> </td>
	<td> <%=vo.getCountry_name() %> </td>
	<td> <%=vo.getRrgion_id() %> </td>
	</tr>
	<% } %>
</table>
</div>

</body>
</html>