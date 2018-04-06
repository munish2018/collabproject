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

import ms.niitmrt.dao.ForumDAO;
import ms.niitmrt.model.Forum;
import ms.niitmrt.model.ForumComment;

@RestController
public class
ForumController {
	@Autowired
	ForumDAO forumdao;
	
	// ---------------- Add forum -----------------------------------

			@PostMapping(value = "/addforum")
			public ResponseEntity<String> addforum(@RequestBody Forum forum) {
				forum.setCreatedate(new java.util.Date());
				forum.setStatus("A");
				forum.setUsername("munish");
				
				
				if (forumdao.addForum(forum)) {
					return new ResponseEntity<String>("Forum Added- Success", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("Forum insert failed", HttpStatus.NOT_FOUND);
				}
			}
			
			// -----------------list forums ---------------------------------

			@GetMapping(value = "/listforums")
			public ResponseEntity<List<Forum>> listforum() {
				List<Forum> listforums = forumdao.listForum("munish");
				if (listforums.size() != 0) {
					return new ResponseEntity<List<Forum>>(listforums, HttpStatus.OK);
				} else {
					return new ResponseEntity<List<Forum>>(listforums, HttpStatus.NOT_FOUND);
				}
			}
			
			// -----------------------Get forum ------------------------------------

			@GetMapping(value = "/getforum/{forumid}")
			public ResponseEntity <Forum> getforum(@PathVariable("forumid") int forumid)
			{
				Forum forum = forumdao.getForum(forumid);
				if (forum == null) {
					return new ResponseEntity <Forum>(forum, HttpStatus.NOT_FOUND);
				} else {
					return new ResponseEntity<Forum>(forum, HttpStatus.OK);
				}
			}
			
			// ------------------Update Forum -----------------------------------

			@PutMapping(value = "/updateforum/{forumid}")
			public ResponseEntity<String> updateforum(@PathVariable("forumid") int forumid, @RequestBody Forum forum) {
				System.out.println("Updating forum " + forumid);
				Forum mforum = forumdao.getForum(forumid);
				if (mforum == null) {
					System.out.println("forum with forumid " + forumid + " Not Found");
					return new ResponseEntity<String>("Update Forum Failue", HttpStatus.NOT_FOUND);
				}
				
				mforum.setForumcontent(forum.getForumcontent());
				mforum.setForumname(forum.getForumname());
							
				forumdao.updateForum(mforum);
				return new ResponseEntity<String>("Update Forum Success", HttpStatus.OK);
			}
			
			// -----------------------Approve Forum ----------------------------------

			@PutMapping(value = "/approveforum/{forumId}")
			public ResponseEntity<String> appforum(@PathVariable("forumId") int forumId) {
				System.out.println("Approve forum with forum ID: " + forumId);
				Forum mforum = forumdao.getForum(forumId);
				if (mforum == null) {
					System.out.println("Not forum with forum Id: " + forumId + " found for Approval");
					return new ResponseEntity<String>("No forum found for Approval", HttpStatus.NOT_FOUND);
				} else {
					mforum.setStatus("A");
					forumdao.approveForum(mforum);
					return new ResponseEntity<String>("forum " + forumId + " Approved Successfully", HttpStatus.OK);
				}
			}
			
			// -----------------------Reject Forum ----------------------------------

						@PutMapping(value = "/rejectforum/{forumId}")
						public ResponseEntity<String> rejforum(@PathVariable("forumId") int forumId) {
							System.out.println("Approve forum with forum ID: " + forumId);
							Forum mforum = forumdao.getForum(forumId);
							if (mforum == null) {
								System.out.println("Not forum with forum Id: " + forumId + " found for Rejection");
								return new ResponseEntity<String>("No forum found for Rejection", HttpStatus.NOT_FOUND);
							} else {
								mforum.setStatus("NA");
								forumdao.rejectForum(mforum);
								return new ResponseEntity<String>("forum " + forumId + " Rejection Successfully", HttpStatus.OK);
							}
						}
						
						// -------------------------Delete Forum ---------------------

						@PostMapping(value = "/deleteforum/{forumId}")
						public ResponseEntity<String> deleteBlog(@PathVariable("forumId") int forumId) {
							System.out.println("Delete forum withforum Id: " + forumId);
							Forum mforum = forumdao.getForum(forumId);
							
							if (mforum == null) {
								System.out.println("No forum " + forumId + " found to delete");
								return new ResponseEntity<String>("No forum with forum Id: " + forumId + " found to delete",
										HttpStatus.NOT_FOUND);
							} else {
								forumdao.deleteForum(mforum);
								return new ResponseEntity<String>("forum with forum Id " + forumId + " deleted successfully", HttpStatus.OK);
							}

						}					
			
						// ---------------- Add forumComments -----------------------------------

						@PostMapping(value = "/addforumcomment")
						public ResponseEntity<String> addforumcomment(@RequestBody ForumComment forumcomment) {
							forumcomment.setCommentdate(new Date());
							forumcomment.setUsername("munish");
							forumcomment.setForumid(1);
												
							if (forumdao.addForumComment(forumcomment)) {
								return new ResponseEntity<String>("forumComment Added- Success", HttpStatus.OK);
							} else {
								return new ResponseEntity<String>("forumComment insert failed", HttpStatus.NOT_FOUND);
							}
						}
						
						// -----------------list forum comments ---------------------------------
						@GetMapping(value = "/listforumcomments/{forumid}")
						public ResponseEntity<List<ForumComment>> listforumcomments(@PathVariable("forumid") int forumid) 
						{
							List<ForumComment> listforumcomments = forumdao.listForumComments(forumid);
							if (listforumcomments.size() != 0) {
								return new ResponseEntity<List<ForumComment>>(listforumcomments, HttpStatus.OK);
							} else {
								return new ResponseEntity<List<ForumComment>>(listforumcomments, HttpStatus.NOT_FOUND);
							}
						}
						
						// -----------------------Get forumComment ------------------------------------

						@GetMapping(value = "/getforumcomment/{commentid}")
						public ResponseEntity<ForumComment> getforumcomment(@PathVariable("commentid") int commentid)
						{
							System.out.println("Get ForumComment " + commentid);
							ForumComment forumcomment = forumdao.getForumComment(commentid);
							if (forumcomment == null) {
								return new ResponseEntity<ForumComment>(forumcomment, HttpStatus.NOT_FOUND);
							} else {
								return new ResponseEntity<ForumComment>(forumcomment, HttpStatus.OK);
							}
						}
						
						// -------------------------Delete forumComment	 ---------------------

						@DeleteMapping(value = "/deleteforumcomment/{commentid}")
						public ResponseEntity<String> deleteforumcomment(@PathVariable("commentid") int commentid) {
							System.out.println("Delete forumComment with comment id: " + commentid);
							ForumComment forumcomment = forumdao.getForumComment(commentid);
							System.out.println(" Forum detail for deletion");;
							System.out.println(" Forum text"+forumcomment.getForumcommenttext());
							if (forumcomment == null)
							{
								System.out.println("No forumcomment  " + commentid + " found to delete");
								return new ResponseEntity<String>("No forumcomment with comment Id: " + commentid + " found to delete",
										HttpStatus.NOT_FOUND);
							}
							else 
							{
								forumdao.deleteForumComment(forumcomment);
								return new ResponseEntity<String>("ForumComment with comment Id " + commentid + " deleted successfully", HttpStatus.OK);
							}
						}
			
			
			
}
