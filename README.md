# COMP2011 Project
* * *
* First readme, see if this shit works!


* * *
## How To Git
Firstly, [Atlassian Git](https://www.atlassian.com/git/) provides some good detailed information on how git works and how to work on projects. Below is the basic steps to set up the project, make branches and push your changes. More will be added as necessary.

### Setting-up
1. `git clone https://username@bitbucket.org/swemyss/sengproject.git`. This will create the project folder in your current directory.
2. `cd sengproject`
3. Use `git help` to show a list of possible commands
4. `git remote add origin https://username@bitbucket.org/swemyss/sengproject`
5. You can now open the project in IntelliJ or Eclipse

### Pull latest changes
1. Use `git pull` to pull the most recent changes on the remote repository to your computer

### Creating a new branch
1. In the project folder type `git branch` to see a list of existing branches and the branch you are currently using.
2. If you want to make a new branch from master (you usually will) make sure the current branch is "master".
3. `git checkout -b "myNewBranchName"
4. You will notice if you run `git branch` it will show "myNewBranchName" as your current working branch

### Commit and Push
1. You will only ever want to commit to a branch , not master
2. While on the branch with your changes use `git commit -am "Descriptive commit message of my changes."`
3. Then you can use

### Changing branch
1. To change to another branch simply use `git checkout <branchname>`
2. This will fail if you have unsaved commits to your current branch, if you wish to save your changes commit them, else you can use `git stash` to remove them (git stash can do more than that, look it up if you like).

