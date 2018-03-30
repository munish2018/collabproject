package ms.niitmrt.dao;

import java.util.List;

import ms.niitmrt.model.Blog;
import ms.niitmrt.model.BlogComment;

public interface BlogDAO {
	
	public boolean addBlog(Blog blog);
	public boolean deleteBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public Blog getBlog(int blogId);
	public boolean approveBlog(Blog blog);
	public boolean rejectBlog(Blog blog);
	public List<Blog> listBlog(String username);
	public boolean incrementBlog(Blog blog);
	
	public boolean addBlogComment(BlogComment blogComment);
	public boolean deleteBlogComment(BlogComment blogComment);
	public BlogComment  getBlogComment(int blogcommentid);
	public List<BlogComment>  listBlogComments(int blogid);


}
