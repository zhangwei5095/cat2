package org.unidal.cat.report;

import junit.framework.Assert;

import org.junit.Test;
import org.unidal.cat.report.spi.remote.RemoteContext;
import org.unidal.lookup.ComponentTestCase;

public class ReportFilterManagerTest extends ComponentTestCase {
	@Test
	public void testNormal() throws Exception {
		defineComponent(ReportFilter.class, "mock:mock", MockReportFilter.class);

		ReportFilterManager manager = lookup(ReportFilterManager.class);
		ReportFilter<Report> filter = manager.getFilter("mock", "mock");
		ReportFilter<Report> filter2 = manager.getFilter("mock", "mock");

		Assert.assertEquals("mock", filter.getReportName());
		Assert.assertEquals("mock", filter.getId());
		Assert.assertSame(filter, filter2);
	}

	@Test
	public void testMissing() throws Exception {
		ReportFilterManager manager = lookup(ReportFilterManager.class);
		ReportFilter<Report> filter = manager.getFilter("mock", "mock");
		ReportFilter<Report> filter2 = manager.getFilter("mock", "mock");

		Assert.assertEquals(null, filter);
		Assert.assertSame(null, filter2);
	}

	public static class MockReportFilter implements ReportFilter<Report> {
		@Override
		public void applyTo(RemoteContext ctx, Report report) {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getId() {
			return "mock";
		}

		@Override
		public String getReportName() {
			return "mock";
		}
	}
}
