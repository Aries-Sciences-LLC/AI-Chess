const electron = require('electron');
const url = require('url');
const path = require('path');

const { app, BrowserWindow } = electron;

let mainWindow;

if (handleSquirrelEvent(app)) {
    // Squirrel event handled will exit in 1000ms, so don't do anything else.
    return;
}

// Listen for the app to be ready.
app.on('ready', function() {
    // Create new window.
    mainWindow = new BrowserWindow({
        width: 400,
        height: 420,
        center: true,
        resizable: false,
        icon: url.format({
            pathname: path.join(__dirname, 'Assets/AppIcon.png'),
            protocol: 'file:',
            slashes: true,
        }),
        titleBarStyle: "hidden",
        transparent: true,
        hasShadow: false,
        useContentSize: true,
        title: "Dr. Henry's Chess Game",
        fullscreenable: false,
        maximizable: false,
        show: false,
        webPreferences: {
            nodeIntegration: true,
        },
    });
    // Load HTML file into the window.
    mainWindow.loadURL(url.format({
        pathname: path.join(__dirname, 'mainWindow.html'),
        protocol: 'file:',
        slashes: true,
    }));
    // Launching window after app sets up.
    mainWindow.once('ready-to-show', () => {
        mainWindow.show();
    });
});

function handleSquirrelEvent(application) {
    if (process.argv.length === 1) {
        return false;
    }

    const ChildProcess = require('child_process');
    const path = require('path');

    const appFolder = path.resolve(process.execPath, '..');
    const rootAtomFolder = path.resolve(appFolder, '..');
    const updateDotExe = path.resolve(path.join(rootAtomFolder, 'Update.exe'));
    const exeName = path.basename(process.execPath);

    const spawn = function(command, args) {
        let spawnedProcess, error;

        try {
            spawnedProcess = ChildProcess.spawn(command, args, {
                detached: true
            });
        } catch (error) {}

        return spawnedProcess;
    };

    const spawnUpdate = function(args) {
        return spawn(updateDotExe, args);
    };

    const squirrelEvent = process.argv[1];
    switch (squirrelEvent) {
        case '--squirrel-install':
        case '--squirrel-updated':
            // Optionally do things such as:
            // - Add your .exe to the PATH
            // - Write to the registry for things like file associations and
            //   explorer context menus

            // Install desktop and start menu shortcuts
            spawnUpdate(['--createShortcut', exeName]);

            setTimeout(application.quit, 1000);
            return true;

        case '--squirrel-uninstall':
            // Undo anything you did in the --squirrel-install and
            // --squirrel-updated handlers

            // Remove desktop and start menu shortcuts
            spawnUpdate(['--removeShortcut', exeName]);

            setTimeout(application.quit, 1000);
            return true;

        case '--squirrel-obsolete':
            // This is called on the outgoing version of your app before
            // we update to the new version - it's the opposite of
            // --squirrel-updated

            application.quit();
            return true;
    }
};