# UniMsg - Centralized Message Dashboard

**UniMsg** is an Android application built with Kotlin in Android Studio, designed to streamline communication and enhance message management. It provides a centralized dashboard where users can view, organize, and interact with messages from multiple sources (e.g., SMS, email, or in-app messages) through a unified, customizable interface.

## Features

- **Unified Inbox**: Aggregate messages from various sources into a single view.
- **Customizable Layouts**: Choose between list, grid, or card-based message displays.
- **Message Categorization**: Organize messages by type (e.g., Personal, Work) or source.
- **Search Functionality**: Quickly find messages with keyword-based search.
- **Message Actions**: Reply, delete, archive, or mark messages as read/unread.
- **Real-Time Notifications**: Get alerts for new messages with customizable settings.
- **Filtering & Sorting**: Filter by status (e.g., unread) and sort by date or sender.
- **Settings**: Personalize the app with themes, notification preferences, and more.

## Installation

### Prerequisites
- Android Studio (Latest version recommended)
- Android device or emulator running API 21 (Android 5.0) or higher
- Git installed on your system

### Steps
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/UniMsg/UniMsg.git
   ```
2. **Open in Android Studio**:
   - Launch Android Studio.
   - Select "Open an existing project" and navigate to the cloned `UniMsg` folder.
3. **Sync Project**:
   - Ensure an internet connection for Gradle to download dependencies.
   - Click "Sync Project with Gradle Files" in Android Studio.
4. **Run the App**:
   - Connect an Android device or start an emulator.
   - Click `Run > Run 'app'` (Shift + F10) to build and install UniMsg.

## Usage

1. **Login**: Open the app and log in using your credentials (Firebase Authentication).
2. **View Messages**: Browse the unified inbox to see all your messages.
3. **Customize Layout**: Tap the layout toggle button to switch between list or grid views.
4. **Manage Messages**: Swipe to delete, tap to reply, or use filtros to sort messages.
5. **Search**: Use the search bar to find specific messages quickly.
6. **Settings**: Adjust preferences like notifications or themes in the settings menu.

## Technical Details

### Tech Stack
- **Language**: Kotlin
- **IDE**: Android Studio
- **Minimum SDK**: API 21 (Android 5.0)
- **Dependencies**:
  - `androidx.recyclerview` - For dynamic message lists
  - `androidx.cardview` - For card-based layouts
  - `com.google.android.material` - For Material Design components
  - `org.jetbrains.kotlinx:kotlinx-coroutines` - For asynchronous operations
  - `com.google.firebase:firebase-auth` - For user authentication
  - `androidx.work:work-runtime-ktx` - For background syncing

### Project Structure
```
├── .gitignore
├── .idea
    ├── AndroidProjectSystem.xml
    ├── compiler.xml
    ├── deploymentTargetSelector.xml
    ├── gradle.xml
    ├── kotlinc.xml
    ├── material_theme_project_new.xml
    ├── migrations.xml
    ├── misc.xml
    ├── runConfigurations.xml
    └── vcs.xml
├── README.md
├── app
    ├── .gitignore
    ├── build.gradle.kts
    ├── proguard-rules.pro
    └── src
    │   ├── androidTest
    │       └── java
    │       │   └── com
    │       │       └── example
    │       │           └── unimsg
    │       │               └── ExampleInstrumentedTest.kt
    │   ├── main
    │       ├── AndroidManifest.xml
    │       ├── ic_launcher-playstore.png
    │       ├── java
    │       │   └── com
    │       │   │   └── example
    │       │   │       └── unimsg
    │       │   │           ├── CustomSplashScreen.kt
    │       │   │           ├── DashboardActivity.kt
    │       │   │           ├── MainActivity.kt
    │       │   │           ├── OnboardingActivity.kt
    │       │   │           ├── SearchAndFilterActivity.kt
    │       │   │           ├── db
    │       │   │               ├── Converters.kt
    │       │   │               ├── NotificationDao.kt
    │       │   │               ├── NotificationDatabase.kt
    │       │   │               ├── NotificationEntity.kt
    │       │   │               ├── RecentlyClearedNotificationDao.kt
    │       │   │               └── RecentlyClearedNotificationEntity.kt
    │       │   │           └── utils
    │       │   │               ├── DrawableToByteArray.kt
    │       │   │               ├── FirstLaunchHelper.kt
    │       │   │               ├── NotificationAdapter.kt
    │       │   │               ├── NotificationItemAnimator.kt
    │       │   │               ├── NotificationListener.kt
    │       │   │               ├── NotificationModel.kt
    │       │   │               ├── NotificationRepository.kt
    │       │   │               ├── NotificationSwitchStateHelper.kt
    │       │   │               ├── OnboardingViewPagerAdapter.kt
    │       │   │               ├── Permissions.kt
    │       │   │               ├── RecentNotificationAdapter.kt
    │       │   │               ├── RecentNotificationRepository.kt
    │       │   │               ├── SafeAreaInsets.kt
    │       │   │               └── TimeStamp.kt
    │       └── res
    │       │   ├── anim
    │       │       ├── fade_in.xml
    │       │       ├── fade_in_card.xml
    │       │       ├── fade_out_card.xml
    │       │       ├── slide_in_right.xml
    │       │       ├── slide_out_left.xml
    │       │       └── slide_up.xml
    │       │   ├── drawable
    │       │       ├── app_background.xml
    │       │       ├── baseline_delete_outline_24.xml
    │       │       ├── bg_app.jpg
    │       │       ├── bg_card_notification.xml
    │       │       ├── dot.xml
    │       │       ├── ic_archive.xml
    │       │       ├── ic_delete.xml
    │       │       ├── ic_launcher_background.xml
    │       │       ├── ic_launcher_foreground.xml
    │       │       ├── ic_whatsapp.png
    │       │       ├── icon.png
    │       │       ├── img_onboarding_screen_1.png
    │       │       ├── img_onboarding_screen_1_temp.png
    │       │       ├── img_onboarding_screen_2.png
    │       │       └── img_onboarding_screen_3.png
    │       │   ├── font
    │       │       ├── roboto.ttf
    │       │       └── roboto_thin.xml
    │       │   ├── layout
    │       │       ├── activity_dashboard.xml
    │       │       ├── activity_main.xml
    │       │       ├── activity_onboarding.xml
    │       │       ├── activity_search_and_filter.xml
    │       │       ├── activity_splash_screen.xml
    │       │       ├── item_notification.xml
    │       │       └── view_onboarding_slider.xml
    │       │   ├── mipmap-anydpi-v26
    │       │       ├── ic_launcher.xml
    │       │       └── ic_launcher_round.xml
    │       │   ├── mipmap-hdpi
    │       │       ├── ic_launcher.webp
    │       │       ├── ic_launcher_foreground.webp
    │       │       └── ic_launcher_round.webp
    │       │   ├── mipmap-mdpi
    │       │       ├── ic_launcher.webp
    │       │       ├── ic_launcher_foreground.webp
    │       │       └── ic_launcher_round.webp
    │       │   ├── mipmap-xhdpi
    │       │       ├── ic_launcher.webp
    │       │       ├── ic_launcher_foreground.webp
    │       │       └── ic_launcher_round.webp
    │       │   ├── mipmap-xxhdpi
    │       │       ├── ic_launcher.webp
    │       │       ├── ic_launcher_foreground.webp
    │       │       └── ic_launcher_round.webp
    │       │   ├── mipmap-xxxhdpi
    │       │       ├── ic_launcher.webp
    │       │       ├── ic_launcher_foreground.webp
    │       │       └── ic_launcher_round.webp
    │       │   ├── values-night
    │       │       └── themes.xml
    │       │   ├── values
    │       │       ├── colors.xml
    │       │       ├── font_certs.xml
    │       │       ├── ic_launcher_background.xml
    │       │       ├── preloaded_fonts.xml
    │       │       ├── strings.xml
    │       │       └── themes.xml
    │       │   └── xml
    │       │       ├── backup_rules.xml
    │       │       └── data_extraction_rules.xml
    │   └── test
    │       └── java
    │           └── com
    │               └── example
    │                   └── unimsg
    │                       └── ExampleUnitTest.kt
├── build.gradle.kts
├── gradle.properties
├── gradle
    ├── libs.versions.toml
    └── wrapper
    │   ├── gradle-wrapper.jar
    │   └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── settings.gradle.kts

```

### Implementation Highlights
- **RecyclerView**: Displays messages with a dynamic adapter.
- **WorkManager**: Handles periodic message syncing.
- **Firebase**: Secures user data with authentication.
- **Material Design**: Ensures a modern, user-friendly UI.

## Contributing

We welcome contributions to enhance UniMsg! To contribute:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m "Add your feature"`).
4. Push to your branch (`git push origin feature/your-feature`).
5. Open a Pull Request with a clear description of your changes.

Please report bugs or suggest features via the [Issues](https://github.com/yourusername/UniMsg/issues) tab.

## License

This project is licensed under the [MIT License](LICENSE). See the `LICENSE` file for details.

## Acknowledgments

- Built with ❤️ by Shashwat, Ishaant and Sarthak.
- Thanks to the open-source community for tools and libraries.

---
