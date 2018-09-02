package com.yang.admin.adminServlet;

import com.lanou.commons.CommonUtils;
import com.yang.book.bean.BookBean;
import com.yang.book.service.BookServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by dllo on 18/6/25.
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * My Dear Taoism's Friend .Please SitDown.
 */
@WebServlet(name = "AdminBookServlet", urlPatterns = "/adminBookServlet")
public class AdminBookServlet extends HttpServlet {
    BookServiceImpl bookService = new BookServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        switch (method) {
            case "queryAll":
                queryAllbook(request, response);
                break;
            case "load":
                load(request, response);
                break;
            case "mod":
                mod(request, response);
                break;
            case "del":
                del(request, response);
                break;
            case "add":
                add(request, response);
                break;
        }


    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //生成一个随机bid
        String bid = CommonUtils.uuid();
        // 创建FileItem 对象的工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 判断前端form表单是否为multipart/form-data属性
        boolean flag = ServletFileUpload.isMultipartContent(request);
        if (flag) {
            //如果是
            // 使用解析器解析上传的表单数据，每个FileItem对应一个表单项
            List<FileItem> fileItemList = null;
            try {
                fileItemList = upload.parseRequest(request);

            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            BookBean bookBean = new BookBean();
            bookBean.setBid(bid);

            for (FileItem fileItem : fileItemList) {
                //找出集合中哪一个是上传的文件
                if (!fileItem.isFormField()) {
                    //获取文件名称
                    String fileName = fileItem.getName();
                    //如果文件名是空 那么拜拜
//                   if (fileName.isEmpty()) {
//                       return;
//                   }
                    //如果不是空
                    //获取上传文件输入流
                    InputStream ips = fileItem.getInputStream();
                    //创建输出流
                    String fun = CommonUtils.uuid();
                    OutputStream ops = new FileOutputStream("/Users/dllo/Desktop/xmimage/" + fun + fileName);
                    byte[] bytes = new byte[1024];
                    int length = 0;
                    while ((length = ips.read(bytes)) != -1) {
                        ops.write(bytes,0,length);
                    }
                    ops.close();
                    ips.close();
                    bookBean.setImage("/photo/" + fun + fileName);
                    fileItem.delete();
//                    System.out.println(fileName);
//                    System.out.println("1");
                }else {


//                    System.out.println("2");
                    if ("bname".equals(fileItem.getFieldName())) {
                        bookBean.setBname(fileItem.getString("UTF-8"));
                    }
                    if ("price".equals(fileItem.getFieldName())) {
                        bookBean.setPrice(fileItem.getString("UTF-8"));
                    }
                    if ("author".equals(fileItem.getFieldName())) {
                        bookBean.setAuthor(fileItem.getString("UTF-8"));
                    }if ("cid".equals(fileItem.getFieldName())) {
                        bookBean.setCid(fileItem.getString("UTF-8"));
                    }

                }
            }
            //!!!!
//            System.out.println(bookBean);
//            System.out.println("3");
            boolean fun = bookService.addBook(bookBean);
            //最后请转
            if (fun){
                request.setAttribute("msg","添加成功");
                request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request,response);

            }else {
                request.setAttribute("msg","添加失败");
                request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request,response);

            }

        } else {
            //不是文件的表单里边的项
            BookBean bookBean = CommonUtils.toBean(request.getParameterMap(), BookBean.class);
        boolean fun =    bookService.addBook(bookBean);
            if (fun){
                request.setAttribute("msg","添加成功");
                request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request,response);

            }else {
                request.setAttribute("msg","添加失败");
                request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request,response);

            }

        }
    }

    private void mod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookBean bookBean = CommonUtils.toBean(request.getParameterMap(), BookBean.class);
      //  System.out.println(bookBean);
        boolean flag = bookService.updateBook(bookBean);
        if (flag) {
            request.setAttribute("msg", "修改成功");
            request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "修改失败");
            request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
        }


    }

    private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookBean bookBean = CommonUtils.toBean(request.getParameterMap(), BookBean.class);
        boolean flag = bookService.delBook(bookBean.getBid());
        if (flag) {
            request.setAttribute("msg", "删除成功");
            request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "删除失败");
            request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
        }


    }


    private void queryAllbook(HttpServletRequest request, HttpServletResponse response) {

        List<BookBean> bookBeanList = bookService.AllBook();
        request.getSession().setAttribute("bookBeanList", bookBeanList);
        try {
            request.getRequestDispatcher("/adminjsps/admin/book/list.jsp").forward(request, response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void load(HttpServletRequest request, HttpServletResponse response) {
        String bid = request.getParameter("bid");
        BookBean bookBean = bookService.LoadBook(bid);
        request.setAttribute("bookBean", bookBean);

        try {
            request.getRequestDispatcher("/adminjsps/admin/book/desc.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
