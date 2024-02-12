# Javim Editor

A minimalist Java-based text editor designed for simplicity and keyboard-centric workflows.

![JavimWindow](https://github.com/meinarchive/Javim/assets/143524576/fd845a75-c1fc-4540-9c21-164d28158017)

# Features

- **Text Editing**: Open, edit, and save text files.
- **Commander Interface**: Utilize simple commands to manage files and settings.
- **Working Directory Management**: View and set your current working directory.
- **Status Updates**: Feedback on your actions within the editor.
- **Terminal Access**: Open a terminal in your current working directory directly from the editor.

# Commands

Activate the Commander with `ALT` and use the following commands:

- `s <filename>`: Save the current file as `<filename>`. If no filename is specified and the file is new, it defaults to Untitled.txt.
- `o <filename>`: Open a file by its name `<filename>`. This will load the specified file into the editor for editing.
- `cl`: Close the current file. Clears the editor and resets the file label to "New file".
- `see wrd`: Display the current working directory. Useful for understanding where files are being managed.
- `set wrd <path>`: Set the working directory to `<path>`. Changes the default directory used for opening and saving files.
- `rset wrd`: Revert to the default working directory. Resets the working directory to the system's default or to a predefined path in the editor.
- `term`: Open a terminal in the current working directory. 
- `q`: Quit the editor. Immediately exits the application.

# Development and Contributions

Javim is open for improvements and contributions. Feel free to fork the repository, make changes, and submit pull requests with enhancements or fixes.
