package com.bikes.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

 


/*********************************************************
 * This class is used to generate HTML Reports
 **********************************************************/
public class ExtentReportManager {
    
//    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports report;
    
    public static ExtentReports getReportInstance()
    {
        if( report == null)
        {
            String reportName = DateUtils.getTimeStamp() + ".html";
            
            /*************************************************************************
             * We are storing the name of the reports using the Date Stamp to 
             * distinguish the reports generated while testing the program multiple times.
             ********************************************************************************/
            
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\HTML_Reports\\"+reportName);
            report = new ExtentReports();
            report.attachReporter(htmlReporter);
            
            //Stating some of the system info in the report in which the project has run  
            report.setSystemInfo("OS", "Windows 10 Home");
            report.setSystemInfo("Environment", "UAT");
            report.setSystemInfo("Build Number", "10.8.1");
            report.setSystemInfo("Browser", "chrome");
            
            //Stating some of the project info in the report
            htmlReporter.config().setDocumentTitle("ZigWheels Automation Results");
            htmlReporter.config().setReportName("All Headlines of ZigWheels Automation");
            htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
            htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
            
        }
        
        return report;
        
    }
    

 

}
 
