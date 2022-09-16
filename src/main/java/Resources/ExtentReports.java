package Resources;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReports {
		
	
	public void getReportObject()
	{
		String path = System.getProperty("user.dir"+"//reports//index.html");
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation results");
		reporter.config().setDocumentTitle("Test results");
		
		ExtentReports extent = new ExtentReports();

		
	}

	
}
