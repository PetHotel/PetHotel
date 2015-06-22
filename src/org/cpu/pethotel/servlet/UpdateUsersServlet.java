package org.cpu.pethotel.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.cpu.pethotel.biz.IUsersBiz;
import org.cpu.pethotel.biz.impl.UsersBizImpl;
import org.cpu.pethotel.po.Users;


/**
 * Servlet implementation class RegisterServlet
 */
public class UpdateUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUsersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Users Suser = (Users) session.getAttribute("user");
		
		int uid = Suser.getUid();
		
		String uname = Suser.getUname();
		
		String password = Suser.getPassword();
		
		int reputation = Suser.getReputation();

		String fileUploadPath = this.getServletContext().getRealPath(
				"/upload/images/photo");
		System.out.println("[SingleFileUploadServlet] 设置服务器接受客户端上传文件的位置是："
				+ fileUploadPath);
		// 1-2：设置服务器临时缓冲区的位置（临时缓冲的文件夹）
		File fileUploadTempPath = new File(this.getServletContext()
				.getRealPath("/tempDir"));
		if (!fileUploadTempPath.exists()) {
			// 创建一个全新的
			fileUploadTempPath.mkdir();
		}
		System.out.println("[SingleFileUploadServlet] 设置服务器接受客户端上传文件的临时位置是："
				+ fileUploadTempPath.getPath());

		// 步骤2：判断表单是否符合上传要求
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// 步骤3：设置文件上传缓冲区对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 3-1:缓冲区对象与磁盘物理位置的绑定
			factory.setRepository(fileUploadTempPath);
			// 3-2：设置缓冲区对象的大小（4*1024 字节）
			factory.setSizeThreshold(4 * 1024);
			System.out
					.println("[SingleFileUploadServlet] 初始化服务器接受客户端上传文件的临时位置完毕！");

			// 步骤4：解析客户端表单待上传的数据
			// 4-1:创建一个ServletFileUpload对象完成对二进制表单数据的解析，并实现服务器上传功能
			ServletFileUpload sfu = new ServletFileUpload(factory);
			
			try {
				// ★★特别注意★★ 在循环遍历表单数据之前创建实体类对象
				Users user = new Users();	
				user.setUid(uid);
				user.setUname(uname);
				user.setPassword(password);
				user.setReputation(reputation);
				String ofid="";
				user.setReputation(reputation);;
				// 4-2:将解析到的二进制文件封装到FileItem的对象中
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = sfu.parseRequest(request);
				for (FileItem fileItem : fileItems) {
					// 扩展3：判断当前解析的请求数据是否为普通表单数据
					if (fileItem.isFormField()) {
						// 获取客户端表单输入元素的name属性的值
						String name = fileItem.getFieldName().trim();
						// 判断数据为哪个字段数据
						if ("province".equalsIgnoreCase(name)) {
							String province = fileItem.getString();
							province = new String(province.getBytes("iso8859-1"),
									"UTF-8");
							user.setProvince(province);
							System.out.println("测试-省份：> " + province);
						}
						if ("city".equalsIgnoreCase(name)) {
							String city = fileItem.getString();
							city = new String(city.getBytes("iso8859-1"),
									"UTF-8");
							user.setCity(city);
							System.out.println("测试-城市：> " + city);
						}
						if ("district".equalsIgnoreCase(name)) {
							String district = fileItem.getString();
							district = new String(district.getBytes("iso8859-1"),
									"UTF-8");
							user.setDistrict(district);
							System.out.println("测试-区：> " + district);
						}
						if ("address".equalsIgnoreCase(name)) {
							String address = fileItem.getString();
							address = new String(address.getBytes("iso8859-1"),
									"UTF-8");
							user.setAddress(address);
							System.out.println("测试-地址：> " + address);
						}
						if ("isAdopt".equalsIgnoreCase(name)) {
							int adopt = Integer.parseInt(fileItem.getString());
							boolean isAdopt = (adopt==0?false:true);
							user.setAdopt(isAdopt);
							System.out.println("测试-是否领养：> " + isAdopt);
						}
						if ("qq".equalsIgnoreCase(name)) {
							String qq = fileItem.getString();
							qq = new String(qq.getBytes("iso8859-1"),
									"UTF-8");
							user.setQq(qq);
							System.out.println("测试-qq：> " + qq);
						}
						if ("email".equalsIgnoreCase(name)) {
							String email = fileItem.getString();
							email = new String(email.getBytes("iso8859-1"),
									"UTF-8");
							user.setEmail(email);
							System.out.println("测试-邮箱：> " + email);
						}
						if ("tel".equalsIgnoreCase(name)) {
							String tel = fileItem.getString();
							tel = new String(tel.getBytes("iso8859-1"),
									"UTF-8");
							user.setTel(tel);
							System.out.println("测试-电话：> " + tel);
						}
						if("ofid".equalsIgnoreCase(name)){
							String temp = fileItem.getString();
							ofid = new String(temp.getBytes("iso8859-1"),
									"UTF-8");
							user.setHeadportrait(ofid);
							System.out.println("以前头像：>"+ofid);
						}
					} else {
						// 4-3:获取上传文件的名称
						String fileName = fileItem.getName().trim();
						// 扩展1：唯一命名
						if(!fileName.isEmpty()){
								String fileExtName = fileName.substring(fileName
										.lastIndexOf("."));
								fileName = generateUnqueName() + fileExtName;
		
								// ★★特别注意★★ 在这个位置为头像属性赋值
								user.setHeadportrait(fileName);
		                        
								// 扩展2：限制上传文件类型
								String[] allowedTypes = new String[] { ".jpg", ".jpeg",".gif",
										".png", ".bmp" };
								Arrays.sort(allowedTypes);
								int searchIndex = Arrays.binarySearch(allowedTypes,
										fileExtName);
								if (searchIndex < 0) {
									System.out
											.println("[SingleFileUploadServlet] 该类型文件不允许上传！");
									return;
								}
		
								System.out
										.println("[SingleFileUploadServlet] 获取上传文件的名称为: "
												+ fileName);
								// 4-4:封装上传文件对象并写入到服务器
								File saveFile = new File(fileUploadPath, fileName);
								fileItem.write(saveFile);
								System.out.println("[SingleFileUploadServlet] 上传文件成功！");
						}
						else{
							user.setHeadportrait(ofid);
							System.out.println("以前头像2：>"+ofid);
							System.out.println("未上传图片，保存原来图片");
						}
					}
				}

				// 调用Biz层的方法完成注册功能
				boolean flag = false;
				if (!(user.getProvince().isEmpty()|| user.getCity().isEmpty() || user.getDistrict().isEmpty() || user.getEmail().isEmpty()
						|| user.getTel().isEmpty() || user.getHeadportrait().isEmpty())) {
					
						IUsersBiz usersBiz = new UsersBizImpl();
						System.out.println(user.toString());
						flag = usersBiz.modify(user);
//						HttpSession session = request.getSession();
						session.setAttribute("user", user);
				}else{
					String error = "更改失败，请重试";
					request.setAttribute("error", error);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
					dispatcher.forward(request, response);
				}
				
				
				// 根据返回值结果进行页面跳转
				if (flag) {
					// 跳转至登录页面
					request.setAttribute("account", user.getUname());
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("jsp/PersonIndex.jsp");
					dispatcher.forward(request, response);
				} else {
					System.out.println("更改失败！！！！！");
					String error = "更改失败，请重试";
					request.setAttribute("error", error);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
					dispatcher.forward(request, response);
				}

			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String error = "客户端表单不符合上传要求，请重试";
			request.setAttribute("error", error);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
			dispatcher.forward(request, response);
		}
	}

	// 自定义方法完成上传文件名称的自动生成
	private synchronized String generateUnqueName() {
		return String.valueOf(System.nanoTime());
	}
}

