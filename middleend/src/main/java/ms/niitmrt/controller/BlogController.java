package ms.niitmrt.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ms.niitmrt.dao.BlogDAO;
import ms.niitmrt.model.Blog;
import ms.niitmrt.model.BlogComment;

@RestController
public class BlogController {

	@Autowired
	BlogDAO blogDAO;
	
	// ---------------- testing -----------------------------------
	@GetMapping(value = "/demo")
	public ResponseEntity<String> testDemo() {
		return new ResponseEntity<String>("Demo Rest Controller- Success", HttpStatus.OK);
	}
	
	// ---------------- Add Blog -----------------------------------

		@PostMapping(value = "/addblog")
		public ResponseEntity<String> addblog(@RequestBody Blog blog) {
			blog.setCreatedate(new java.util.Date());
			blog.setLikes(0);
			blog.setStatus("A");
			blog.setUsername("munish");
					
			if (blogDAO.addBlog(blog)) {
				return new ResponseEntity<String>("Blog Added- Success", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Blod insert failed", HttpStatus.NOT_FOUND);
			}
		}
		
		// -----------------list Blogs ---------------------------------

		@GetMapping(value = "/listblogs")
		public ResponseEntity<List<Blog>> listblog() {
			List<Blog> listBlogs = blogDAO.listBlog("munish");
			if (listBlogs.size() != 0) {
				return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Blog>>(listBlogs, HttpStatus.NOT_FOUND);
			}
		}
		
		// -----------------------Get Blog ------------------------------------

		@GetMapping(value = "/getblog/{blogId}")
		public ResponseEntity <Blog> getblog(@PathVariable("blogId") int blogId)
		{
			Blog blog = blogDAO.getBlog(blogId);
			if (blog == null) {
				return new ResponseEntity <Blog>(blog, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}
		}

		// ------------------Update Blog -----------------------------------

		@PutMapping(value = "/updateblog/{blogId}")
		public ResponseEntity<String> updateblog(@PathVariable("blogId") int blogId, @RequestBody Blog blog) {
			System.out.println("Updating Blog " + blogId);
			Blog mBlog = blogDAO.getBlog(blogId);
			if (mBlog == null) {
				System.out.println("Blog with blogId " + blogId + " Not Found");
				return new ResponseEntity<String>("Update Blog Failue", HttpStatus.NOT_FOUND);
			}
			
			mBlog.setBlogcontent(blog.getBlogcontent());
			mBlog.setBlogname(blog.getBlogname());
						
			blogDAO.updateBlog(mBlog);
			return new ResponseEntity<String>("Update Blog Success", HttpStatus.OK);
		}

		// -----------------------Approve Blog ----------------------------------

		@PutMapping(value = "/approveblog/{blogId}")
		public ResponseEntity<String> approveblog(@PathVariable("blogId") int blogId) {
			System.out.println("Approve Blog with Blog ID: " + blogId);
			Blog blog = blogDAO.getBlog(blogId);
			if (blog == null) {
				System.out.println("Not blog with blog Id: " + blogId + " found for Approval");
				return new ResponseEntity<String>("No Blog found for Approval", HttpStatus.NOT_FOUND);
			} else {
				blog.setStatus("A");
				blogDAO.approveBlog(blog);
				return new ResponseEntity<String>("Blog " + blogId + " Approved Successfully", HttpStatus.OK);
			}
		}

		// -----------------------Reject Blog-----------------------------

				@PutMapping(value = "/rejectblog/{blogid}")
				public ResponseEntity<String> rejectblog(@PathVariable("blogid") int blogid) {
					System.out.println("Reject blog with Blog ID: " + blogid);
					Blog blog = blogDAO.getBlog(blogid);
					if (blog == null) {
						System.out.println("Not blog with blog Id: " + blogid + " found for Rejection");
						return new ResponseEntity<String>("No Blog found for Rejection", HttpStatus.NOT_FOUND);
					} else {
						blog.setStatus("NA");
						blogDAO.rejectBlog(blog);
						return new ResponseEntity<String>("Blog " + blogid + " Rejected Successfully", HttpStatus.OK);
					}
				}
				// -------------------------Delete Blog ---------------------

				@PostMapping(value = "/deleteblog/{blogId}")
				public ResponseEntity<String> deleteBlog(@PathVariable("blogId") int blogId) {
					System.out.println("Delete blog with blog Id: " + blogId);
					Blog blog = blogDAO.getBlog(blogId);
					if (blog == null) {
						System.out.println("No blog " + blogId + " found to delete");
						return new ResponseEntity<String>("No blog with blog Id: " + blogId + " found to delete",
								HttpStatus.NOT_FOUND);
					} else {
						blogDAO.deleteBlog(blog);
						return new ResponseEntity<String>("Blog with Blog Id " + blogId + " deleted successfully", HttpStatus.OK);
					}

				}		
				// -----------------------Increment Likes Blog-----------------------------

				@PutMapping(value = "/incrblog/{blogid}")
				public ResponseEntity<String> incrblog(@PathVariable("blogid") int blogid) {
					System.out.println("Increment likes blog with Blog ID: " + blogid);
					Blog blog = blogDAO.getBlog(blogid);
					if (blog == null) {
						System.out.println("Not blog with blog Id: " + blogid + " found for incrementing");
						return new ResponseEntity<String>("No Blog found for incrementing", HttpStatus.NOT_FOUND);
					} else {
						blogDAO.incrementBlog(blog);
						return new ResponseEntity<String>("Blog " + blogid + " incremented likes Successfully", HttpStatus.OK);
					}
				}		
		
				// ---------------- Add BlogComments -----------------------------------

				@PostMapping(value = "/addblogcomment")
				public ResponseEntity<String> addblogcomment(@RequestBody BlogComment blogcomment) {
					blogcomment.setCommentdate(new Date());
					//Blog blog = blogDAO.getBlog(1);
					//String username = blog.getUsername();
					//int blogid = blog.getBlogid();
					//blogcomment.setBlogid(blogid);
					blogcomment.setUsername("munish");
					blogcomment.setBlogid(1);
					if (blogDAO.addBlogComment(blogcomment)) {
						return new ResponseEntity<String>("BlogComment Added- Success", HttpStatus.OK);
					} else {
						return new ResponseEntity<String>("BlodComment insert failed", HttpStatus.NOT_FOUND);
					}
				}
				
				// -----------------------Get BlogComment ------------------------------------

				@GetMapping(value = "/getblogcomment/{commentid}")
				public ResponseEntity<BlogComment> getblogcomment(@PathVariable("commentid") int commentid)
				{
					System.out.println("Get BlogComment " + commentid);
					BlogComment blogcomment = blogDAO.getBlogComment(commentid);
					if (blogcomment == null) {
						return new ResponseEntity<BlogComment>(blogcomment, HttpStatus.NOT_FOUND);
					} else {
						return new ResponseEntity<BlogComment>(blogcomment, HttpStatus.OK);
					}
				}

				// -----------------list Blogs ---------------------------------
				@GetMapping(value = "/listblogcomments/{blogid}")
				public ResponseEntity<List<BlogComment>> listblogcomments(@PathVariable("blogid") int blogid) {
					List<BlogComment> listblogcomments = blogDAO.listBlogComments(blogid);
					if (listblogcomments.size() != 0) {
						return new ResponseEntity<List<BlogComment>>(listblogcomments, HttpStatus.OK);
					} else {
						return new ResponseEntity<List<BlogComment>>(listblogcomments, HttpStatus.NOT_FOUND);
					}
				}
				
				
				// -------------------------Delete BlogComment	 ---------------------

				@DeleteMapping(value = "/deleteblogcomment/{commentid}")
				public ResponseEntity<String> deleteblogcomment(@PathVariable("commentid") int commentid) {
					System.out.println("Delete blogComment with comment id: " + commentid);
					BlogComment blogcomment = blogDAO.getBlogComment(commentid);
					System.out.println(" Blog detail for deletion");;
					System.out.println(" Blog text"+blogcomment.getBlogcommenttext());
					if (blogcomment == null)
					{
						System.out.println("No blog " + commentid + " found to delete");
						return new ResponseEntity<String>("No blogcomment with comment Id: " + commentid + " found to delete",
								HttpStatus.NOT_FOUND);
					}
					else 
					{
						blogDAO.deleteBlogComment(blogcomment);
						return new ResponseEntity<String>("BlogComment with comment Id " + commentid + " deleted successfully", HttpStatus.OK);
					}
				}
				
								
}
