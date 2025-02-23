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
UniMsg/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/unimsg/
│   │   │   │   ├── MainActivity.kt           # Main dashboard
│   │   │   │   ├── MessageAdapter.kt        # RecyclerView adapter
│   │   │   │   ├── LoginActivity.kt         # Authentication screen
│   │   │   │   ├── SyncWorker.kt            # Background sync
│   │   │   │   └── SettingsFragment.kt      # User preferences
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml    # Main UI
│   │   │   │   │   ├── item_message.xml     # Message item layout
│   │   │   │   │   └── activity_login.xml   # Login UI
│   │   │   │   └── xml/preferences.xml      # Settings config
│   └── build.gradle                         # App dependencies
└── README.md                                # Project documentation
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
