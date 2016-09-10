package com.myswap.services;

import com.myswap.exceptions.CommentInsertException;
import com.myswap.exceptions.CommentNotFoundException;
import com.myswap.models.Comment;

import junit.framework.TestCase;

public final class CommentServiceTests extends TestCase {

	public CommentServiceTests(final String s) {
		super(s);
	}

	CommentService commentService = new CommentService();

	public void testFindcommentByIdWithInvalidValues() throws Exception {

		// Finds an object with a unknown identifier
		try {
			commentService.findComment(-1);
			fail("Object with unknonw id should not be found");
		} catch (CommentNotFoundException e) {
		}

		// Finds an object with an empty identifier
		try {
			commentService.findComment(Long.parseLong("0"));
			fail("Object with empty id should not be found");
		} catch (CommentNotFoundException e) {
		}

	}

	public void testFindCommentByUserWithInvalidValues() throws Exception {

		// Finds an object with a unknown identifier
		try {
			commentService.findCommentsByUser(Long.parseLong("0"));
			fail("Object with unknonw id should not be found");
		} catch (CommentNotFoundException e) {
		}

		// Finds an object with an empty identifier
		try {
			commentService.findCommentsByUser(Long.parseLong("0"));
			fail("Object with empty id should not be found");
		} catch (CommentNotFoundException e) {
		}

	}

	public void testInsertDeleteComment() throws Exception {

		// Creates an object
		Comment comment = commentService.insertComment("label", 5, "1", "2");

		// Ensures that the object exists
		long commentId = comment.getIdComment();
		
		comment = null;
		
		try {
			comment = commentService.findComment(commentId);
		} catch (CommentNotFoundException e) {
			fail("Object has been created it should be found");
		}

		// Cleans the test environment
		commentService.deleteComment(comment.getIdComment());

		try {
			commentService.findComment(commentId);
			fail("Object has been deleted it shouldn't be found");
		} catch (CommentNotFoundException e) {
		}
	}
	
	public void testCreateCommentWithInvalidValues() throws Exception {

        // Creates an object with a null parameter
        try {
        	commentService.insertComment("label", null, "1", "2");
            fail("Object with null parameter should not be created");
        } catch (CommentInsertException e) {
        }

		// Creates an object with invalid User
        try {
        	commentService.insertComment("label", 5, "1", "0");
            fail("Object with invalid user should not be created");
        } catch (CommentInsertException e) {
        }
		
		// Creates an object with invalid User
        try {
        	commentService.insertComment("label", 5, "0", "2");
            fail("Object with invalid user should not be created");
        } catch (CommentInsertException e) {
        }

    }
	
	public void testUpdatecomment() throws Exception {
		
		Comment comment = commentService.insertComment("label", 5, "1", "2");

		// Ensures that the object exists
		long commentId = comment.getIdComment();
		
		comment = null;
		
		try {
			comment = commentService.findComment(commentId);
		} catch (CommentNotFoundException e) {
			fail("Object has been created it should be found");
		}
		
		commentService.updateComment(commentId, "label", 4);
		
		try {
			comment = commentService.findComment(commentId);
		} catch (CommentNotFoundException e) {
			fail("Object has been updated it should be found");
		}
		
		commentService.deleteComment(commentId);

		try {
			commentService.findComment(commentId);
			fail("Object has been deleted it shouldn't be found");
		} catch (CommentNotFoundException e) {
		}
	
	}
	
	
}