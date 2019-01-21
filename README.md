# TicTacToeAndroid
This is a 4x4 (scalable) TicTacToe game, developed using Android Studio (Java and Android SDK) that can be played on an Android device or an Android Simulator. 

## Building the app
Clone the git repo using the following command in Bash/Terminal:

```
git clone https://github.com/jaynathani/TicTacToeAndroid.git
```

### Android Studio (recommended)

These instructions have been tested for the following configuration:

Android Studio 3.3
Build #AI-182.5107.16.33.5199772, built on December 24, 2018
JRE: 1.8.0_152-release-1248-b01 x86_64
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
macOS 10.14.2

* Open Android Studio and select `File->Open...` and navigate to the root directory of your project.
* Select the directory or drill in and select the file `build.gradle` in the cloned repo.
* Click 'OK' to open the the project in Android Studio.
* A Gradle sync should start, but you can force a sync and build the 'app' module as needed.

### Gradle (command line / Terminal)

* Build the APK: `./gradlew build`

## Running the Sample App

Connect an Android device to your development machine.

### Android Studio

* Select `Run -> Run 'app'` (or `Debug 'app'`) from the menu bar
* Select the device you wish to run the app on and click 'OK'

## Using the TicTacToe App

The application is a slightly advanced version of the 3x3 TicTacToe game which almost everyone must have played in their childhood. There is a `Current Player` indicator on the top that shows whose turn is it to play, either `X` or `O`. There is also a `New Game` button that will clear the board and restart the game. This is a two player game in which one player will play `X` and the other player will play `O` alternatively, by default beginning with `X`. The win conditions are as follows:

* All the four corner elements are either `X` or `O`.
* All the elements in one vertical column are either `X` or `O`.
* All the elements in one horizontal row are either `X` or `O`.
* All the elements on the diagonal and the reverse diagonal are either `X` or `O`.
* All the elements in one 2x2 grid are either `X` or `O`.

A win will be indicted by a pop up congrulations message and the background color of the elements which caused the win will turn `Red`. 

If none of the conditions are met and all the elements have been played, a Tie condition occurs which also have a pop indicating that the game has ended and the user can either start a new game or end the app.
