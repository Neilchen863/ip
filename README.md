# HiChat Project Template

Welcome to **HiChat**, a Java-based chatbot application designed to provide a smart and engaging conversation experience. This project serves as a template for a greenfield Java project.

## Setting Up in IntelliJ

### Prerequisites
- **JDK 17** (Ensure no other versions are used)
- **Latest IntelliJ IDEA version**

### Steps to Set Up
1. **Open IntelliJ IDEA**
   - If you have an existing project open, click `File` > `Close Project` first.
2. **Import HiChat into IntelliJ**
   - Click `Open` and select the project directory.
   - Click `OK` and accept any further prompts with default settings.
3. **Configure JDK 17**
   - Follow [this guide](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk) to set JDK 17.
   - In the same dialog, set the **Project language level** to `SDK default`.
4. **Run HiChat**
   - Locate `src/main/java/hichat/HiChat.java`.
   - Right-click the file and choose `Run HiChat.main()`.
   - If there are compilation errors, restart IntelliJ and try again.

If the setup is successful, you should see an output like this:
```
Hello from
 __    __   __    ______  __    __       ___    .___________.
|  |  |  | |  |  /      ||  |  |  |     /   \\  |           |
|  |__|  | |  | |  ,----'|  |__|  |    /  ^  \\ `---|  |----`
|   __   | |  | |  |     |   __   |   /  /_\  \\    |  |     
|  |  |  | |  | |  `----.|  |  |  |  /  _____  \\   |  |     
|__|  |__| |__| \\______||__|  |__| /__/     \__\\  |__|     
```

### Important Notes
- Keep the `src/main/java` folder structure intact.
- Avoid renaming or moving Java files outside of this path, as build tools like Gradle expect this structure.

Now you're all set to start developing **HiChat**! 🚀

