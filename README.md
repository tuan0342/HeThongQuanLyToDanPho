# HeThongQuanLyToDanPho
BTL môn Công nghệ phần mềm kì 2022.1 của nhóm "Hướng nội part time" ĐHBKHN

# Các thư viện, phần mềm sử dụng:
	Yêu cầu phần cứng: Bộ nhớ trong RAM: tối thiểu 4GB
	Yêu cầu phần mềm:
	+ IDE: Intellij Idea
	+ Phần mềm chạy database: Sql server
	+ Các thư viện, sdk: java 16, javafx 18
	+ File JDBC giúp kết nối Intellij Idea với Sql server: mssql-jdbc-9.4.1.jre16


## Lưu ý: do database chạy ở dưới local. vì vậy có thể file thực thi sẽ ko chạy được.


# Các bước cài đặt:
+ Bước 1: Giải nén file source code và cài đặt file cơ sở dữ liệu (sử dụng phần mềm sql server)
+ Bước 2: Mở project bằng Intellij Idea
+ Bước 3: Setup môi trường: Vào mục file -> project structure. Tại project structure:
	- Ở phần project: chọn SDK version 16
	- Ở phần libraries: chọn thư viện của javafx phiên bản 18
	- Ở phần modul: import file JDBC bằng cách chọn dấu +, sau đó chọn 
‘JARs or Directories…’, sau đó tìm và chọn file mssql-jdbc-9.4.1.jre16 
(file JDBC giúp kết nối java phiên bản 16 với phần mềm sql server)
+ Bước 4: Vào package controller -> mở file DBUtils.java -> tại phần user và 
pass đổi lại cho phù hợp với máy của bạn (user và pass là tài khoản và mật 
khẩu của phần mềm sql server)
+ Bước 5: Vào package main -> mở file Main.java -> Chạy file này để 
mở phần mềm
