For Eclipse:

See also: https://www.vogella.com/tutorials/EclipseGradle/article.html#import-an-existing-gradle-project

INSTALL GIT
	See also https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
	
	1. Install Git from https://git-scm.com/ (Download for Windows)
	2. Turn on option to add Git Bash to right-click menu
	3. Open git-bash
	4. A few git config's you may want to run (customized of course, and assuming you use Notepad++):
		git config --global user.name "Your Name"
		git config --global user.email <your@email.address>
		git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"

INSTALL GIT INTEGRATION: EGIT
	Note I don't actually use this - I badly messed up my Eclipse installation trying to set it up - so we can probably skip it.
	1. Open Eclipse Marketplace from Eclipse's Help menu
	2. Search for EGit and install. If it tells you you need to either modify your own installation or modify the packages it is about to install, tell it to modify the new installs. (Don't let it modify your existing installation or you may have to upgrade your JDK just to revert it!)
	3. So I think the next time you open Eclipse it will ask you for a name and email. Choose wisely: the email you provide may end up on GitHub.com (we may want to make an account and make this repository private at this point).

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
	2. Search for Buildship and install, if not already installed.
	
IMPORT GRADLE PROJECT
	1. File > Import
	2. Select Gradel > Existing Gradle Project
	3. If welcome screen appears, click next when you've read as much as you care to. I thought it was moderately useful.
	4. Browse to the root directory, in this case the one with this README.txt in it.
	5. Click finish. (I think it will do everything right on its own.)
	6. If it tells you "Auto share git projects" has encountered a problem ... I'm confused because it probably should have asked Git where it downloaded all this junk from.
	
TEST GRADLE PROJECt
	1. So in my Eclipse there was a "Gradle Tasks" tab right next to console where I could click "LedPanelHost > build" and double-click "build" to see the tests run. The Gradle Execution tab then said Under Run build > Run tasks > :test > com.towerccc.com.tutorial.FizzBuzzProcessorTest that the four tests worked. If you don't see that, bummer, call me.
	2. Then back to the Gradle Tasks: instead of "LedPanelHost > build" go to "application > run" and double-click "Run." Go to the console and it should have a bunch of numbers and Fizz's and Buzz's. Yay!
	
