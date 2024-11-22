package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener {

	private ExtentSparkReporter sparkReporter;
	private ExtentReports extentReports;
	private ExtentTest test;
	private String reportName;
	private String timeStamp;
	private String reportPath = ".\\reports\\";
	
	@Override
	public void onStart(ITestContext context) {
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		reportName = "report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(reportPath + reportName);
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test = extentReports.createTest(result.getClass().getName());
		test.log(Status.PASS, result.getName() + " successfully executed.");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test = extentReports.createTest(result.getClass().getName());
		test.log(Status.FAIL, result.getName() + " failed.");
	}
}
