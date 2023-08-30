package com.Digitalcodes.listners;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class LogResult_IReporterListner implements IReporter {

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		for (ISuite iSuite : suites) {
			String suiteName = iSuite.getName();

			Map<String, ISuiteResult> suiteResults = iSuite.getResults();

			for (ISuiteResult result : suiteResults.values()) {

				ITestContext context = result.getTestContext();

				System.out.println("All Passed Test Results of " + suiteName + " = "
						+ context.getPassedTests().getAllResults().size());
				System.out.println("All Faild Test Results of " + suiteName + " = "
						+ context.getFailedTests().getAllResults().size());
				System.out.println("All Skip Test Results of " + suiteName + " = "
						+ context.getSkippedTests().getAllResults().size());

			}

		}

	}

}
