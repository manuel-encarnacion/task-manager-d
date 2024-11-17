# Simple task manager for roadmap.sh
- You can check the "kata" here -> https://roadmap.sh/projects/task-tracker

## get started
- requirements: java 21 in the actual terminal

### debian users
- download task-manager-deb.deb file
- use command `sudo dpkg -i task-manager-deb.deb` to install the program
- start using it by running `task-manager --help`

### other users
- place the .jar file and the script file (task-manager) some place your path points to
- restart your terminal emulator
- start using it by running `task-manager --help`

### acknowledge
- This project main contains bugs and things to improve
- This is just a Kata to learn how to deal with cli programs (installing in the system and that stuff).
- In this project I challenged my self to:
    - use a strategy design patter
    - work with formatted json without libraries 
    - making the program executable in debian by creating a package
    - develop an scalable code that could manage new features "easily" by adding just the neededs files without modifying the Main class

### things to do
- [ ] Do a good handle of errors (the user must know what is doing bad)
- [ ] Move the database.json to .config folder
- [ ] Return the user info about the action performed
