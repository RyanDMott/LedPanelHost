For Eclipse:

See also: https://www.vogella.com/tutorials/EclipseGradle/article.html#import-an-existing-gradle-project

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
	
