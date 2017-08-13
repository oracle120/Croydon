package org.gqz.bcs.test.util;


import java.util.List;

import org.gqz.bcs.model.Issue;

import junit.framework.Assert;


public class EntitiesHelper {
	private static Issue baseIssue = new Issue(1, "title_1", "description", "solution", "userid");

	public static void assertIssue(Issue expected, Issue actual) {
		Assert.assertNotNull(expected);
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getDescription(), actual.getDescription());
		Assert.assertEquals(expected.getSolution(), actual.getSolution());
		Assert.assertEquals(expected.getUserid(), actual.getUserid());
		//Assert.assertEquals(expected.getDate(), actual.getDate());
	}

	public static void assertIssues(List<Issue> expected, List<Issue> actuals) {
		for (int i = 0; i < expected.size(); i++) {
			Issue eu = expected.get(i);
			Issue au = actuals.get(i);
			assertIssue(eu, au);
		}
	}
}
