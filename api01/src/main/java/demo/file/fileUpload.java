package demo.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/uploadServlet")
@MultipartConfig
public class fileUpload extends HttpServlet {

	/**
	 * add comment
	 */
	private static final long serialVersionUID = 769851032030881192L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String fileName = req.getParameter("filename");
		
		File fl = new File("d://11.txt");
		if(fl.exists()) fl.delete();
		fl.createNewFile();
		
		byte[] bs = new byte[1024];
		
		InputStream is = req.getInputStream();
		FileOutputStream fos = new FileOutputStream(fl);
		int read = 0;
		while((read = is.read(bs))!=-1){
			fos.write(bs, 0, read);
		}
		
		fos.flush();
		fos.close();
		is.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
