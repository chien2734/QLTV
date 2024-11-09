create database QLTV2;
use QLTV2;

create table TheLoai(
	MaLoai		varchar(10) primary key,
	TenLoai		nvarchar(100),
	TrangThai	int
)

create table NXB (
	MaNXB	varchar(10) primary key,
	TenNXB	nvarchar(100),
	TrangThai	int
)

create table DauSach(
	MaDS varchar(10) primary key,
	TenSach nvarchar(255),
	MaLoai	varchar(10)	foreign key references TheLoai(MaLoai),
	TacGia	nvarchar(255),
	MaNXB	varchar(10),
	NamXB	int,
	SoLuong	int,
	TrangThai	int
)

create table Sach(
	MaSach	varchar(10) primary key,
	MaDS	varchar(10) foreign key references DauSach(MaDS),
	Loai	nvarchar(50),	-- "Có thể mượn", "Đọc tại chỗ"
	GiaSach	int,
	GiaMuon int,				-- Tính tự động: Sách mới: Giá sách < 500: GiaSach*10% + GiaSach; Sách > 500; GiaSach*15% + GiaSach
							--	 Sách hư hỏng nhẹ: Giá sách < 500: GiaSach*8% + GiaSach; Sách > 500; GiaSach*12% + GiaSach
	TrangThai nvarchar(255),			-- "Nguyên vẹn", "Hư hỏng nhẹ", "Hư hỏng nặng", "Mất" ------ Trạng thái này chỉ hiển thị trên giao diện admin để tính GiaMuon tự động ko hiện trên giao diện user
	TinhTrang	nvarchar(50)	--"Hiện có", "Đã mượn", "Không phục vụ" (cho trường hợp bị mất); sách bị "Hư hỏng nặng" set Loai = "Đọc tại chỗ"
)

create table DocGia(
	TENDN	varchar(10) primary key,	-- TenDN là MaDG
	TenDG	nvarchar(255),	
	MatKhau varchar(50),
	SDT		varchar(45),
	Email	varchar(45),
	DiaChi	nvarchar(255),
	TrangThai int				
)

-- Thủ thư xác nhận, thẻ mượn đc tạo tự động
create table TheMuon(		
	MaTheMuon	varchar(10) primary key,
	MaDG		varchar(10) foreign key references DocGia(TENDN),
	NgayDK		datetime,
	--HanThe		datetime,
	TrangThai	int		-- 0: Chưa đc kích hoạt	
)

-- Chứa tối đa 5 quyển
create table GioDK (
	MaTheMuon	varchar(10) foreign key references THEMUON(MaTheMuon),
	MaSach		varchar(10),
	TenSach		nvarchar(255),
    GiaMuon         int
	primary key (MaTheMuon, MaSach),
	foreign key (MaTheMuon) references TheMuon(MaTheMuon),
	foreign key (MaSach) references Sach(MaSach)
)

create table PhieuMuon(
	MaPhieu		varchar(10) primary key,
	MaTheMuon	varchar(10) foreign key references THEMUON(MaTheMuon),
	TenDG		nvarchar(50),
	NgayDK		datetime,
	HanNhan		datetime,	-- Tối đa 3 ngày sau ngày đk
	NgayMuon	datetime,
	HanTra		datetime,	-- Tối đa 10 ngày sau ngày đk
	TienCoc		int,		-- Tự động tính tổng GiaMuon
	TrangThai	nvarchar(215)			-- "Quá hạn mượn" (có thể xoá), "Có thể sử dụng" (cho phép xác nhận mượn), "Đã nhận sách"
)

create table CT_PhieuMuon(
	MaPhieu	varchar(10),
	MaSach	varchar(10),
	TenSach	nvarchar(255),
	GiaMuon     int,
	TrangThai	nvarchar(215),
	primary key (MaPhieu, MaSach),
	foreign key (MaPhieu) references PhieuMuon(MaPhieu),
	foreign key (MaSach) references Sach(MaSach)
)

create table PhieuTra(
	MaPhieuTra	varchar(20)	primary key,
	MaPhieuMuon	varchar(10) foreign key references PhieuMuon(MaPhieu),
	MaTheMuon	varchar(10) foreign key references THEMUON(MaTheMuon),
	NgayMuon	datetime,
	TongTienPhat	int,
	TrangThai	int
)

create table CT_PhieuTra (
	MaPhieuTra	varchar(20),
	MaSach		varchar(10),
	TenSach		nvarchar(255),
	NgayTra		datetime,
	TinhTrang	nvarchar(50),			-- "nguyên vẹn", "hư hỏng nhẹ", "hư hỏng nặng", "mất" 
	TienPhat	int,					-- Tính tự động: "Hư hỏng nhẹ": GiaSach*15% "Hư hỏng nặng": GiaSach*80%, "Mất": GiaSach*120% 
	primary key (MaPhieuTra, MaSach),
	foreign key (MaPhieuTra) references PhieuTra(MaPhieuTra),
	foreign key (MaSach) references Sach(MaSach),
)

insert into THELOAI(MaLoai, TenLoai, TrangThai) values
('TL0001', N'Marketing', 1), 
('TL0002', N'Quản trị', 1),
('TL0003', N'Kinh tế', 1),
('TL0004', N'Kỹ năng sống', 1),
('TL0005', N'Tâm lý', 1),
('TL0006', N'Âm nhạc', 1),
('TL0007', N'Nghệ thuật', 1),
('TL0008', N'Vật lý', 1),
('TL0009', N'Ngoại ngữ', 1),
('TL0010', N'Kiến trúc', 1),
('TL0011', N'Địa lý', 1),
('TL0012', N'Thể thao', 1),
('TL0013', N'Văn học', 1);

insert into NXB(MaNXB, TenNXB, TrangThai) values 
('NXB0001', N'Thanh Niên', 1),
('NXB0002', N'Hội Nhà Văn', 1),
('NXB0003', N'Phụ Nữ Việt Nam', 1),
('NXB0004', N'Lao động', 1),
('NXB0005', N'Đà Nẵng', 1),
('NXB0006', N'Đại học Sư Phạm', 1),
('NXB0007', N'Dân Trí', 1),
('NXB0008', N'NXB Trẻ', 1);

insert into DauSach(MaDS, TenSach, MaLoai, TacGia, MaNXB, NamXB, SoLuong, TrangThai) values 
('DS0001', N'Tết ở làng địa ngục', 'TL0013', N'Thảo Trang', 'NXB0001', 2022, 4, 1),
('DS0002', N'Những chuyện lạ ở Tokyo', 'TL0013', N'Haruki Murakami', 'NXB0002', 2023, 5, 1),
('DS0003', N'Nhà giả kim', 'TL0013', 'Paulo Coelho', 'NXB0002', 2020, 3, 1),
('DS0004', N'Cây cam ngọt của tôi', 'TL0013', 'José Mauro de Vasconcelos', 'NXB0002', 2020, 3, 1),
('DS0005', N'Rèn luyện tư duy phản biện', 'TL0004', 'Albert Rutherford', 'NXB0003', 2000, 3, 1),
('DS0006', N'Mô hình kinh doanh nhóm', 'TL0005', 'Tim Clark, Bruce Hazen', 'NXB0004', 2018, 5, 1),
('DS0007', N'Giải Thích Ngữ Pháp Tiếng Anh', 'TL0009', N'Mai Lan Hương, Hà Thanh Uyên', 'NXB0005', 2022, 4, 1),
('DS0008', N'Giúp Trí Nhớ Ngữ Pháp Tiếng Anh Và Động Từ Bất Quy Tắc', 'TL0009', N'Chính Bình', 'NXB0006', 2020, 2, 1),
('DS0009', N'Làm Chủ Business Analytics ', 'TL0003', N'Trần Hùng Thiện, Tăng Thúy Nga', 'NXB0007', 2023, 2, 1),
('DS0010', N'Giải Mã Ngành Công Nghiệp Âm Nhạc', 'TL0006', 'Donald S Passman', 'NXB0004', 2000, 2, 1);

insert into Sach(MaSach, MaDS, Loai, GiaSach, GiaMuon, TrangThai, TinhTrang) values
('S0001', 'DS0001', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Đã mượn'),  
('S0002', 'DS0001', N'Đọc tại chỗ', 77000, 84700, N'Nguyên vẹn', N'Hiện có'),  
('S0003', 'DS0001', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0004', 'DS0002', N'Đọc tại chỗ', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0005', 'DS0003', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Đã mượn'),  
('S0006', 'DS0003', N'Đọc tại chỗ', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0007', 'DS0003', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Đã mượn'),  
('S0008', 'DS0004', N'Đọc tại chỗ', 80000, 88000, N'Nguyên vẹn', N'Hiện có'), 
('S0009', 'DS0004', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Đã mượn'),  
('S0010', 'DS0004', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0011', 'DS0005', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0012', 'DS0005', N'Đọc tại chỗ', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0013', 'DS0006', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0014', 'DS0006', N'Đọc tại chỗ', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0015', 'DS0006', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Đã mượn'),  
('S0016', 'DS0006', N'Đọc tại chỗ', 80000, 88000, N'Nguyên vẹn', N'Hiện có'), 
('S0017', 'DS0007', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0018', 'DS0008', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0019', 'DS0008', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0020', 'DS0009', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0021', 'DS0009', N'Đọc tại chỗ', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0022', 'DS0010', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Đã mượn'),  
('S0023', 'DS0010', N'Đọc tại chỗ', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),
('S0024', 'DS0006', N'Đọc tại chỗ', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),
('S0025', 'DS0007', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0026', 'DS0007', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0027', 'DS0001', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Hiện có'), 
('S0028', 'DS0002', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0029', 'DS0005', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Hiện có'),  
('S0030', 'DS0007', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Đã mượn'),
('S0031', 'DS0002', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Đã mượn'),  
('S0032', 'DS0002', N'Có thể mượn', 80000, 88000, N'Nguyên vẹn', N'Đã mượn'),
('S0033', 'DS0002', N'Có thể mượn', 80000, 88000, N'Hư hỏng nhẹ', N'Hiện có');
--4 5 7 11 12
insert into DocGia(TENDN,TenDG,MatKhau,SDT,Email,DiaChi,TrangThai) values
('long1',N'Nguyễn Phi Long','12345678','0944681222','long1@gmail.com',N'Hà Nội',1),
('huyen1',N'Nguyễn Thị Huyền','12345678','0822666913','huyen1@gmail.com',N'Đà Nẵng',1),
('hoangt',N'Nguyễn Văn Hoàng','12345678','0988681214','nvh@gmail.com',N'An Giang',1),
('sao2',N'Phan Khắc Hồng Sao','12345678','0933882711','saokhac@gmail.com',N'Hà Nội',1),
('dien00',N'Trần Đình Diễn','12345678','0921661822','dientran@gmail.com',N'Bắc Giang',1),
('duyc',N'Lê Huỳnh Công Duy','12345678','0143633555','congduy@gmail.com',N'TP.Hồ Chí Minh',1),
('trinh',N'Nguyễn Thị Kiều Trinh','12345678','0964512538','trinhlove@gmail.com',N'Hà Nội',1),
('nhung1',N'Văn Hồng Nhung','12345678','0833691224','nhungvanhong@gmail.com',N'Hà Nội',1),
('ronaldo',N'Lê Quang Rô','12345678','0911463921','ronaldo@gmail.com',N'Hà Nội',1),
('jimmy',N'Nguyễn Quý Hồng','12345678','0328991644','jimmynguyen@gmail.com',N'Hà Nội',1),
('huydz',N'Nguyễn Quốc Huy','12345678','0873681993','huy1993@gmail.com',N'Kiên Giang',1),
('longdz',N'Hoàng Long','12345678','0345692881','hoanglongqb@gmail.com',N'Quảng Bình',1),
('thow1',N'Trịnh Khánh Thơ','12345678','0356112689','thotrinh@gmail.com',N'Cần Thơ',1),
('chau01',N'Phan Hoàng Bảo Châu','12345678','0174624413','chaubao@gmail.com',N'Cà Mau',1),
('vule',N'Lê Hoàng Vũ','12345678','0861668668','vulehoang@gmail.com',N'TP.Hồ Chí Minh',1),
('duccop',N'Nguyễn Minh Đức','12345678','0391555666','nvduc@gmail.com',N'Quảng Bình',1),
('linh1',N'Nguyễn Ngọc Linh','12345678','0911665225','linhngoc@gmail.com',N'Hà Nội',1),
('linh2',N'Lê Huyền Linh','12345678','0932456333','huyenlinh@gmail.com',N'Hà Nội',1),
('linh3',N'Phan Bảo Linh','12345678','0377559166','phanbaolinh@gmail.com',N'Hà Nội',1),
('thaibb',N'Nguyễn Văn Thái','12345678','0183445551','thainguyenvan@gmail.com',N'Đà Nẵng',1);

insert into TheMuon(MaTheMuon, MaDG, NgayDK, TrangThai) values
('TM0001','long1','2024-04-24', 1),
('TM0002','huyen1','2024-04-24', 1),
('TM0003','hoangt','2024-04-24', 1),
('TM0004','sao2','2024-04-24', 1),
('TM0005','dien00','2024-04-24', 1),
('TM0006','duyc','2024-04-24', 1),
('TM0007','trinh','2024-04-24', 1),
('TM0008','nhung1','2024-04-24', 1),
('TM0009','ronaldo','2024-04-24', 1),
('TM00010','jimmy','2024-04-24', 1),
('TM00011','huydz','2024-04-24', 1),
('TM00012','longdz','2024-04-24', 1),
('TM00013','thow1','2024-04-24', 1),
('TM00014','chau01','2024-04-24', 1),
('TM00015','vule','2024-04-24', 1),
('TM00016','duccop','2024-04-24', 1),
('TM00017','linh1','2024-04-24', 1),
('TM00018','linh2','2024-04-24', 1),
('TM00019','linh3','2024-04-24', 1),
('TM00020','thaibb','2024-04-24', 1);

insert PhieuMuon(MaPhieu, MaTheMuon, TenDG, NgayDK, HanNhan, NgayMuon, HanTra, TienCoc, TrangThai) values  
('PM0001', 'TM0001', N'Nguyễn Phi Long', '2024-05-13', '2024-05-16', null, null, 176000, N'Có thể sử dụng'),
('PM0002', 'TM0002', N'Nguyễn Thị Huyền', null, null, '2024-05-04', '2024-05-14', 176000, N'Đã trả hết'), --
('PM0003', 'TM0003', N'Nguyễn Văn Hoàng', '2024-05-01', '2024-05-04', null, null, 88000, N'Quá hạn mượn'),
('PM0004', 'TM0004', N'Phan Khắc Hồng Sao', null, null, '2024-04-20', '2024-04-30', 176000, N'Quá hạn trả'),
('PM0005', 'TM0002', N'Nguyễn Thị Huyền', '2024-05-02', '2024-05-05', '2024-05-04', '2024-05-14', 88000, N'Đã mượn'), --
('PM0006', 'TM0003', N'Nguyễn Văn Hoàng', null, null, '2024-04-20', '2024-04-30', 176000, N'Đã trả hết'),
('PM0007', 'TM0001', N'Nguyễn Phi Long', null, null, '2024-05-10', '2024-05-20', 352000, N'Đã mượn'), 
('PM0008', 'TM0002', N'Nguyễn Thị Huyền', '2024-05-14', '2024-05-17', null, null, 88000, N'Có thể sử dụng'), --
('PM0009', 'TM0002', N'Nguyễn Thị Huyền', '2024-05-04', '2024-05-07', null, null, 88000, N'Quá hạn mượn'), -- 
('PM0010', 'TM0002', N'Nguyễn Thị Huyền', null, null, '2024-05-04', '2024-05-14', 88000, N'Đã trả hết'); -- 


insert CT_PhieuMuon(MaPhieu, MaSach, TenSach, GiaMuon, TrangThai) values
('PM0001', 'S0001', N'Tết ở làng địa ngục',  88000, N'Nguyên vẹn'),
('PM0001', 'S0015', N'Mô hình kinh doanh nhóm',  88000, N'Nguyên vẹn'),
('PM0002', 'S0017', N'Giải Thích Ngữ Pháp Tiếng Anh',  88000, N'Nguyên vẹn'),
('PM0002', 'S0013', N'Mô hình kinh doanh nhóm',  88000, N'Nguyên vẹn'),
('PM0003', 'S0007', N'Nhà giả kim',  88000, N'Nguyên vẹn'),
('PM0004', 'S0009', N'Cây cam ngọt của tôi',  88000, N'Nguyên vẹn'),
('PM0004', 'S0019', N'Giúp Trí Nhớ Ngữ Pháp Tiếng Anh Và Động Từ Bất Quy Tắc',  88000, N'Nguyên vẹn'),
('PM0005', 'S0022', N'Giải Mã Ngành Công Nghiệp Âm Nhạc',  88000, N'Nguyên vẹn'),
('PM0006', 'S0027', N'Tết ở làng địa ngục',  88000, N'Nguyên vẹn'),
('PM0006', 'S0032', N'Những chuyện lạ ở Tokyo',  88000, N'Nguyên vẹn'),
('PM0007', 'S0031', N'Những chuyện lạ ở Tokyo',  88000, N'Nguyên vẹn'),
('PM0007', 'S0030', N'Giải Thích Ngữ Pháp Tiếng Anh',  88000, N'Nguyên vẹn'),
('PM0007', 'S0029', N'Rèn luyện tư duy phản biện',  88000, N'Nguyên vẹn'),
('PM0007', 'S0028', N'Những chuyện lạ ở Tokyo',  88000, N'Nguyên vẹn'),
('PM0008', 'S0005', N'Nhà giả kim',  88000, N'Nguyên vẹn'),
('PM0009', 'S0032', N'Những chuyện lạ ở Tokyo',  88000, N'Nguyên vẹn'),
('PM0010', 'S0027', N'Tết ở làng địa ngục',  88000, N'Nguyên vẹn');

insert PhieuTra(MaPhieuTra, MaPhieuMuon, MaTheMuon, NgayMuon, TongTienPhat) values 
('PT0001', 'PM0002', 'TM0002', '2024-05-04', -176000),
('PT0002', 'PM0002', 'TM0002', '2024-05-04', -88000),
('PT0003', 'PM0004', 'TM0004', '2024-04-20', -100000),
--('PT0004', 'PM0005', 'TM0002', '2024-05-04', -84000),
('PT0005', 'PM0006', 'TM0003', '2024-04-20', -48000),
('PT0006', 'PM0006', 'TM0003', '2024-04-20', -97000),
('PT0007', 'PM0007', 'TM0001', '2024-04-20', -176000),
('PT0008', 'PM0010', 'TM0002', '2024-05-04', -88000);

insert CT_PhieuTra(MaPhieuTra, MaSach, TenSach, NgayTra, TinhTrang, TienPhat) values 
('PT0001', 'S0017', N'Giải Thích Ngữ Pháp Tiếng Anh', '2024-05-07', N'Nguyên vẹn', -88000),
('PT0001', 'S0013', N'Mô hình kinh doanh nhóm', '2024-05-07', N'Nguyên vẹn', -88000),
--('PT0002', 'S0022', N'Giải Mã Ngành Công Nghiệp Âm Nhạc', '2024-05-07', N'Nguyên vẹn', -88000),
--('PT0003', 'S0009', N'Cây cam ngọt của tôi', '2024-05-01', N'Nguyên vẹn', -88000),											--
('PT0003', 'S0019', N'Giúp Trí Nhớ Ngữ Pháp Tiếng Anh Và Động Từ Bất Quy Tắc', '2024-05-07', N'Nguyên vẹn', -88000),		--
--('PT0004', 'S0022', N'Giải Mã Ngành Công Nghiệp Âm Nhạc', '2024-05-07', N'Hư hỏng nhẹ', -84000),
('PT0005', 'S0032', N'Những chuyện lạ ở Tokyo', '2024-04-25', N'Hư hỏng nặng', -48000),
('PT0006', 'S0027', N'Tết ở làng địa ngục', '2024-04-25', N'Hư hỏng nhẹ', -84000),
('PT0007', 'S0028', N'Những chuyện lạ ở Tokyo', '2024-04-25', N'Nguyên vẹn', -88000),
('PT0007', 'S0029', N'Rèn luyện tư duy phản biện', '2024-04-25', N'Nguyên vẹn', -88000),
('PT0008', 'S0027', N'Tết ở làng địa ngục', '2024-05-14', N'Hư hỏng nhẹ', -88000);