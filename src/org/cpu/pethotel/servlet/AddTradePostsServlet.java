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

import org.apache.catalina.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.cpu.pethotel.biz.IPostsBiz;
import org.cpu.pethotel.biz.impl.PostsBizImpl;
import org.cpu.pethotel.po.Posts;
import org.cpu.pethotel.po.Users;


/**
 * Servlet implementation class AddTradePostsServlet
 */
public class AddTradePostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTradePostsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  // 步骤1：设置上传文件的参数
		// 1-1：设置服务器接受上传文件的位置（服务器的文件夹）
		String petsImagesUploadPath = this.getServletContext().getRealPath(
				"/upload/images/posts");
		String senderPostsFileUploadPath=this.getServletContext().getRealPath(
				"/upload/postsAppendix");
		System.out.println("petsImagesUploadPat>"+petsImagesUploadPath);
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
		String erro="";
		if (isMultipart){
			// 步骤3：设置文件上传缓冲区对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 3-1:缓冲区对象与磁盘物理位置的绑定
			factory.setRepository(fileUploadTempPath);
			// 3-2：设置缓冲区对象的大小（4*1024 字节）
			factory.setSizeThreshold(100 * 1024);
			System.out.println("[SingleFileUploadServlet] 初始化服务器接受客户端上传文件的临时位置完毕！");

			// 步骤4：解析客户端表单待上传的数据
			// 4-1:创建一个ServletFileUpload对象完成对二进制表单数据的解析，并实现服务器上传功能
		   ServletFileUpload sfu = new ServletFileUpload(factory);
			try {
				// ★★特别注意★★ 在循环遍历表单数据之前创建实体类对象
				 Posts posts=new Posts();
				 String images="";
				 String pocontext="",tid="";
				 String pomark="";
				 
				// 4-2:将解析到的二进制文件封装到FileItem的对象中
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = sfu.parseRequest(request);
				boolean isRestFile=false;
				
				for (FileItem fileItem : fileItems) {
					// 扩展3：判断当前解析的请求数据是否为普通表单数据
					String s=fileItem.getFieldName().trim();
					System.out.println("Html-Name>"+s);
					if(s.equals("restfile")){
					   isRestFile=true;
					 }
					if (fileItem.isFormField()) {
						// 获取客户端表单输入元素的name属性的值
						String name = fileItem.getFieldName().trim();
						// 判断数据为哪个字段数据
						if ("pocontext".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							pocontext=value;
							
						}
						if ("tid".equalsIgnoreCase(name)) {
							String value = fileItem.getString();
							value = new String(value.getBytes("iso8859-1"),
									"UTF-8");
							tid=value;
							
						}
						
					}
					else {
						// 4-3:获取上传文件的名称
						String fileName = fileItem.getName().trim();
						String cFileName=new String(fileName);
						
						if(!fileName.isEmpty()){
							// 扩展1：唯一命名
							String fileExtName = fileName.substring(fileName
									.lastIndexOf("."));
							fileName = generateUnqieName() + fileExtName;
							// 扩展2：限制上传文件类型
							String[] allowedTypes = new String[] { ".jpg",
									".jpeg", ".png", ".bmp", ".gif" };
							Arrays.sort(allowedTypes);
							int searchIndex = Arrays.binarySearch(allowedTypes,
									fileExtName);
							
							System.out.println("cFileName>"+cFileName);
							System.out.println("fileItem"+fileItem.getName());
							
							if (searchIndex >= 0 && isRestFile==false) {
								// 图片
								images += fileName + ",";
								posts.setPomark(fileName);
								File saveFile = new File(petsImagesUploadPath,
										fileName);
								fileItem.write(saveFile);
								System.out.println(fileName + "上传"
										+ petsImagesUploadPath + "成功");
								

							} else if(isRestFile==true){
								// 附件
								pomark = fileName;
								File saveFile = new File(
										senderPostsFileUploadPath, fileName);
								fileItem.write(saveFile);
								System.out.println(fileName + "上传"
										+ senderPostsFileUploadPath + "成功");
								isRestFile=false;
							}
							else{
								System.out.println("文件格式不正确");
								erro=new String("文件格式不正确");
							}
						}
						
					}
				}

				// 测试封装对象
				HttpSession session = request.getSession();
				Users userC = (Users)session.getAttribute("user");
				posts.setUid(userC.getUid());  
				posts.setRetyeId(-Integer.parseInt(tid));
				if(!images.isEmpty()){
					posts.setPofilelist(images.substring(0,images.lastIndexOf(",")));
				}
				posts.setPotype("tradeDeal");
				posts.setAppreciate(0);
				posts.setPettype("无");
				posts.setPobegintime("2014-08-08");
				posts.setPoendtime("2014-08-08");
				posts.setPocontext(pocontext);
				posts.setPomark(pomark);
				System.out.println(posts);
				// 调用Biz层的方法完成注册功能
				 IPostsBiz postsBiz=new PostsBizImpl();
				boolean flag=postsBiz.addPosts(posts);
				
				// 根据返回值结果进行页面跳转
				if (flag) {
					// 
					System.out.println("successful");
					response.sendRedirect("/PetHotel/jsp/PersonMessage.jsp?type=2&s=1");
				} else {
					System.out.println("失败");
					erro="发布失败";
				}

			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!erro.isEmpty()){
			request.setAttribute("error", erro);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
			dispatcher.forward(request, response);
			
		}else{
			
		}
}
		private synchronized String generateUnqieName() {
			return String.valueOf(System.nanoTime());
          }

		
	

}
