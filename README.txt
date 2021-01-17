For Eclipse:

See also: https://www.vogella.com/tutorials/EclipseGradle/article.html#import-an-existing-gradle-project

INSTALL GIT
	1. Install Git from https://git-scm.com/ (Download for Windows)
	2. Turn on option to add Git Bash to right-click menu

CLONE CODE FROM GITHUB
	OPTION 1: Git Bash
	1. Navigate to a folder, e.g. .../Documents/repos/, where you want to put a new folder with code
	2. Right-click in folder to open Git Bash you just installed
	3. Enter the following command to create a new folder called "LedPanelHost" with all this code in it:
	
		git clone https://github.com/RyanDMott/LedPanelHost.git LedPanelHost
		
	OPTION 2: Git Gui
	1. Open Git Gui you just installed
	2. Select "Clone existing repository"
	3. For "Source location" enter
		
		https://github.com/RyanDMott/LedPanelHost.git
		
	4. For "Target Directory" select a folder name for the Gui to create, e.g. .../Documents/repos/LedPanelHost, where you want it to put this code.
	5. Continue. The GitGui should think for a bit and then open up a Gui with a few empty windows in it. Check for the folder you told it to create and there should be code there (like this Readme file!)
	

INSTALL GRADLE INTEGRATION: BUILDSHIP
	1. Open Eclipse Marketplace from Eclipse's Help menu
	2. Search for Buildship and install, if not already installed
	
IMPORT GRADLE PROJECT
	1. File > Import
	2. Select Gradel > Existing Gradle Project
	3. If welcome screen appears, click next when you've read as much as you care to. I thought it was moderattely useful.
	4. Browse to the root directory, in this case the one with this README.txt in it.
	5. Click finish. (I think it will do everything right on its own.)
	6. If it tells you "Auto share git projects" has encountered a problem ... I'm confused because it probably should have asked Git where it downloaded all this junk from.
	
TEST GRADLE PROJECt
	1. So in my Eclipse there was a "Gradle Tasks" tab right next to console where I could click "LedPanelHost > build" and double-click "build" to see the tests run. The Gradle Execution tab then said Under Run build > Run tasks > :test > com.towerccc.com.tutorial.FizzBuzzProcessorTest that the four tests worked. If you don't see that, bummer, call me.
	2. Then back to the Gradle Tasks: instead of "LedPanelHost > build" go to "application > run" and double-click "Run." Go to the console and it should have a bunch of numbers and Fizz's and Buzz's. Yay!
	
