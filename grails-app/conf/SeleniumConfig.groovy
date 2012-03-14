selenium {
	slow = false									// true to run tests in slow resources mode
	singleWindow = true								// true for single window mode, false for multi-window mode
	browser = "firefox"								// set browser (value is firefox or iexplore or chrome)
	//customBrowserPath = "fullPath"				// (Optional) can include full path to executable
	url = null										// the base URL for tests, defaults to Grails server url
    defaultTimeout = 60000  						// the timeout after which selenium commands will fail
	windowMaximize = false  
	screenshot {
		dir = "./target/test-reports/screenshots"	// directory where screenshots are placed relative to project root
		onFail = false								// true to capture screenshots on test failures
	}
	server {
		host = "localhost"							// the host the selenium server will run on
		port = 4444									// the port the selenium server will run on
	}
	userExtensions = ""								// path to user extensions javascript file
}