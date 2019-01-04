# Curriculum Vitae Mikołaj Nowak

## For recruiting developers
This application is a CurriculumVitae of Android Developer - Mikołaj Nowak.

It's intent is to show out developer skills by both content and code.

### A few words from developer
Usually I like to choose tools appropriatly for a problem, however in this application even if it's really simple and won't need to scale, I decided to assume that it is just a part of big application and use solutions which I would use while designing medium complexity application (meaningful size but without necessity to switch some of it's parts based on any conditions - for that I have some extra solutions, but they could make code hard to learn as a cost of maintanability).

I've decided for MVVM because I like to work with this pattern and I wanted to show out some ideas I got which I do like such as having all logic in ViewModel and make all data pass through it so all View logic might be tested by testing ViewModel. Also I like the idea of having ViewModels for List items which used to be painfull for me that it often is even less then MVC even for complex logic in that. Nevertheless I think that in normal conditions for such app which only displays data delivered only once I'd use MVP. 
Also some solutions which I feel might need explanations as I've seen not keeping some rules I follow, a few times in my carrer:
- ViewModels always store all data in LiveData even if it's unlikely to be updated any way because you never know how requirements might change and View shouldn't be touched with any changes made with data sources. If I'd for example moved to Firebase database all that data might change in the meantime
- Even if ViewModels has directly the same data as Models all it's data is moved to separate LiveData objects instead of just passing Model, because View shouldn't be dependent on Model any way and be resistant to any changes in that. 

### Changes
It was developed in 06.2017, in 12.2018-01.2019 it's updated to actual knowladge and skills of developer.

Things already changed in 2018/2019:
- Refactored to Kotlin
- Refactored ViewModels from simple POJOs to Architecture Components ViewModels with LiveData
- Differed Model from ViewModel in a spirit of rule that View part shouldn't be affected with Model changes
- Added dependency injection and moved data providing layer far out of View and decopuled from other parts of application so it might be easily replaced with any other source of data 

Things still to do in 2018/2019:
- Refactor Layouts to ConstraintLayouts (I could leave it isn't really badly done without it, but previously I have used some minus margins, which hurts me right now and I do prefer ConstraintLayouts right now)
- Get rid of DataBinding - I've decided to this solution previously because I thought that it will be only simple data passed to View and there will be no additional logic besides setting values. I was wrong and now View logic is divided into two places.
- Implement unit tests for ViewModels
- Clean up Activities and Fragment by making some super class (only one level) and maybe some delegates which would help to make sure all components are used in the same manner + would avoid repetition.
- Slightly improve UX by adding Bottom bar with available data in details activity and configure gradle to make it buildable without it for PDF purposes.

Some furthure plans:
- Move content to some remote source and use Retrofit with Room as it's always requested feature.

## For recruiting non technicals

PDF version might be found in main folder in a `CurriculumVitae-Mikolaj-Nowak.pdf` file, which is a simple file with screenshots made on Google Pixel 2 XL emulator. It doesn't contain all details which might be reached by expanding some sections or scrolling the screen.

In order to see whole content of the CV it needs to be installed on an Android device. It might be done by either downloading a code, building it with gradle and installing on a device with ADB tool.

Also to simplify the process there is an APK file `CurriculumVitae.apk` in main folder which might be downloaded to the device and installed there (from my experience it needs to be switched to Desktop mode in order to be display button to download :().
